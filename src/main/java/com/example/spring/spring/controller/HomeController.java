package com.example.spring.spring.controller;

import com.example.spring.spring.dao.UserTb;
import com.example.spring.spring.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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


    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/")
    public String login(HttpSession session){

        if(session.getAttribute("user") != null){
            return "redirect:/community";
        }
        return "login";
    }

    @RequestMapping(value = "/loginCheck")
    public String loginCheck(HttpServletRequest request, HttpSession session){
        String account = request.getParameter("account");
        String pw = request.getParameter("pw");

        UserTb user = userRepository.getUserTbByAccount(account);
        String get_pw = user.getPw();
        if(passwordEncoder.matches(pw, get_pw)){
            session.setAttribute("user", account);
            return "redirect:/community";
        }else{
            return "login";
        }

    }

    @RequestMapping(value = "/register")
    public String Register(HttpServletRequest request){

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        System.out.println(id);
        System.out.println(pw);

        if(request.getMethod().equals("POST")){
            UserTb userTb = new UserTb();
            userTb.setAccount(id);
            userTb.setPw(passwordEncoder.encode(pw));
            userTb.setName(name);
            userRepository.save(userTb);
            return "login";
        }

        return "register";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "login";
    }

    @RequestMapping(value = "/test")
    public String popup(){

        return "popup";
    }
}
