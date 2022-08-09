package com.example.spring.spring.controller;

import com.example.spring.spring.dao.UserTb;
import com.example.spring.spring.jpa.JwtToken;
import com.example.spring.spring.repository.UserRepository;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwt;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    private JwtToken jwttoken = new JwtToken();

    @RequestMapping(value = "/")
    public String login(HttpServletRequest request){
//        String token = request.getHeader("jwt-token");
//        System.out.println(token);
//        if(request.getHeader("jwt-token") != null){
//            return "redirect:/community";
//        }
        return "login";
    }

    @RequestMapping(value = "/loginCheck")
    public String loginCheck(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session){
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        UserTb userTb = new UserTb();
        userTb.setId(id);
        userTb.setPw(pw);

        System.out.println(userRepository.LoginCheck(userTb));

        if(userRepository.LoginCheck(userTb)){
            session.setAttribute("user", id);
//            String token = jwttoken.makejwtToken(id);
//            model.addAttribute("jwt-token", token);
//            response.setHeader("jwt-token", token);
            return "redirect:/community";
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

//    @RequestMapping("/ajax.do")
//    @ResponseBody
//    public void ajax(HttpServletResponse response, @RequestParam String name) throws IOException {
//        Gson gson = new Gson();
//
//        Map<String, Object> data = new HashMap<String, Object>();
//
//        data.put("result", "ok");
//        data.put("msg", "json data");
//        data.put("name", name);
//
//        response.getWriter().print(gson.toJson(data));
//    }
//
//    @RequestMapping(value = "/main.do")
//    public ModelAndView home() {
//        return new ModelAndView("/index");
//    }

}
