package com.example.spring.spring.controller;

import com.example.spring.spring.dao.UserTb;
import com.example.spring.spring.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/")
    public String login(HttpSession session){

        if(session.getAttribute("user") != null){
            return "/community/community";
        }
        return "login";
    }

    @RequestMapping(value = "/loginCheck")
    public String loginCheck(HttpServletRequest request, HttpSession session){
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        UserTb userTb = new UserTb();
        userTb.setId(id);
        userTb.setPw(pw);

        System.out.println(userRepository.LoginCheck(userTb));

        if(userRepository.LoginCheck(userTb)){
            session.setAttribute("user", id);
            return "/community/community";
        }else{
            return "login";
        }
    }

    @RequestMapping(value = "/register")
    public String Register(HttpServletRequest request){

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        System.out.println(id);
        System.out.println(pw);

        if(request.getMethod().equals("POST")){
            UserTb userTb = new UserTb();
            userTb.setId(id);
            userTb.setPw(pw);
            userRepository.save(userTb);
            return "login";
        }

        return "register";
    }

}
