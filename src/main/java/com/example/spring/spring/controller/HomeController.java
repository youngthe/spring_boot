package com.example.spring.spring.controller;

import com.example.spring.spring.dao.UserTb;
import com.example.spring.spring.repository.UserTbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserTbRepository userTbRepository;

    @RequestMapping(value = "/")
    public String login(){

        return "login";
    }

    @RequestMapping(value = "/loginCheck")
    @ResponseBody
    public String loginCheck(HttpServletRequest request){
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        if(userTbRepository.LoginCheck(id, pw) != null){
            return "true";
        }else{
            return "false";
        }

    }

}
