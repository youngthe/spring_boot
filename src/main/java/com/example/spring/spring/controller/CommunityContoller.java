package com.example.spring.spring.controller;

import com.example.spring.spring.dao.CommunityTb;
import com.example.spring.spring.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class CommunityContoller {

    @Autowired
    CommunityRepository communityRepository;

    @RequestMapping(value = "/community")
    public String community_view(HttpServletRequest request, Model model){

        List<CommunityTb> communityTb =  communityRepository.getCommunity();

        System.out.println(communityTb.get(0));
        model.addAttribute("community_list", communityTb);
        return "/community/community";
    }


    @RequestMapping(value = "/community/save")
    public void community_save(HttpServletRequest request, HttpSession session){

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

    }

    @RequestMapping(value = "/community/write")
    public String community_write(){

        return "community/community_write";
    }

    @RequestMapping(value = "/community/detail/{community_id}")
    public String community_detail(@PathVariable int community_id, Model model){

        System.out.println(community_id);

        try{
            CommunityTb communityTb = communityRepository.getCommunityById(community_id);
            System.out.println(communityTb.getContent());
            model.addAttribute("community", communityTb);

        }catch(Exception e){
            System.out.println("db error");
            System.out.println(e);
        }



        return "/community/community_detail";
    }
}
