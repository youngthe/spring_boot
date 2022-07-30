package com.example.spring.spring.controller;

import com.example.spring.spring.dao.CommunityTb;
import com.example.spring.spring.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class CommunityContoller {

    @Autowired
    CommunityRepository communityRepository;

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
}
