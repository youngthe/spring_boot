package com.example.spring.spring.controller;

import com.example.spring.spring.dao.CommentTb;
import com.example.spring.spring.dao.CommunityTb;
import com.example.spring.spring.dao.UserTb;
import com.example.spring.spring.repository.CommentRepository;
import com.example.spring.spring.repository.CommunityRepository;
import com.example.spring.spring.repository.UserTbRepositoryCustom;
import com.example.spring.spring.utils.ScriptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class CommunityContoller {

    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(value = "/community")
    public String community_view(HttpServletRequest request,HttpSession session, Model model){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }
        List<CommunityTb> communityTb;

        String search = request.getParameter("search");
        if(search == null){
            communityTb  =  communityRepository.getCommunity();
        }else{
            communityTb = communityRepository.getCommunityBySearch(search);
        }

        model.addAttribute("community_list", communityTb);
        return "/community/community";
    }


    @RequestMapping(value = "/community/save")
    public String community_save(HttpServletRequest request, HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String writer = (String) session.getAttribute("user");
        String now = LocalDate.now().toString();

        System.out.println(title + content);

        CommunityTb communityTb = new CommunityTb();
        communityTb.setTitle(title);
        communityTb.setContent(content);
        communityTb.setWriter(writer);
        communityTb.setDate(now);
        communityRepository.save(communityTb);

        return "redirect:/community";
    }

    @RequestMapping(value = "/community/write")
    public String community_write(HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        return "community/community_write";
    }

    @RequestMapping(value = "/community/detail/{community_id}")
    public String community_detail(@PathVariable int community_id, Model model, HttpSession session){

        System.out.println(community_id);
        String user = (String)session.getAttribute("user");
        System.out.println(user);
        try{
            CommunityTb communityTb = communityRepository.getCommunityById(community_id);
            List<CommentTb> commentTb = commentRepository.getCommentList(community_id);

            communityRepository.Increase_like(communityTb);

            model.addAttribute("community", communityTb);
            model.addAttribute("comments",commentTb);
            model.addAttribute("user", user);

        }catch(Exception e){
            System.out.println("db error");
            System.out.println(e);
        }



        return "/community/community_detail";
    }

    @RequestMapping(value = "/community/comments/{community_id}")
    public String comment_add(@PathVariable int community_id, HttpServletRequest request, HttpSession session) {

        String comment = request.getParameter("comments");
        String now = LocalDate.now().toString();
        System.out.println(comment);

        CommentTb commentTb = new CommentTb();
        commentTb.setCommunity_id(community_id);
        commentTb.setComment(comment);
        commentTb.setWriter((String) session.getAttribute("user"));
        commentTb.setDate(now);
        commentRepository.save(commentTb);


        return "redirect:/community/detail/" + community_id;
    }

    @RequestMapping(value = "/community/comment/delete/{comment_id}")
    public String comment_delete(@PathVariable int comment_id){
        commentRepository.deleteByCommunityId(comment_id);
        return "redirect:/community";
    }

    @RequestMapping(value= "/community/delete/{community_id}", method = RequestMethod.DELETE)
    public String community_delete(@PathVariable int community_id, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {

        String deleter = (String) session.getAttribute("user");

        CommunityTb community = communityRepository.getCommunityById(community_id);
        String writer = community.getWriter();

        if(writer.equals(deleter)){
            communityRepository.deleteById(community_id);
            CommentTb comment = new CommentTb();
            comment.setCommunity_id(community_id);
            commentRepository.deleteByCommunityId(community_id);
            ScriptUtil.alert_location(response, "삭제되었습니다.", "/community");
         }else{
             ScriptUtil.alert_location(response, "삭제할 수 없습니다.", "/community/detail/"+community_id);
         }
        return "redirect:/community";
    }

    @RequestMapping(value = "/community/modify/{community_id}")
    public String communit_modify(@PathVariable int community_id, HttpServletResponse response,HttpServletRequest request, HttpSession session, Model model) throws IOException {

        String user = (String) session.getAttribute("user");

        CommunityTb communityTb = communityRepository.getCommunityById(community_id);
        if(user.equals(communityTb.getWriter())){

            model.addAttribute("community", communityTb);

        }else{
            ScriptUtil.alert_back(response, "수정할 수 있는 권한이 없습니다.");
        }
        return "community/community_modify";
    }

    @RequestMapping(value = "/community/modify/{community_id}/save")
    public String communit_modify_save(@PathVariable int community_id, HttpServletResponse response,HttpSession session, Model model, HttpServletRequest request) throws IOException {


        String user = (String) session.getAttribute("user");
        System.out.println("user : "+ user);
        CommunityTb communityTb = communityRepository.getCommunityById(community_id);
        System.out.println("writer : "+ communityTb.getWriter());

        if(user.equals(communityTb.getWriter())){
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            System.out.println(title);
            System.out.println(content);
            communityTb.setTitle(title);
            communityTb.setContent(content);
            communityRepository.updateCommunity(communityTb);
        }else{
            ScriptUtil.alert_back(response, "수정할 수 있는 권한이 없습니다.");
        }

        return "redirect:/community";
    }

}
