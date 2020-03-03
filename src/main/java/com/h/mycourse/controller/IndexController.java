package com.h.mycourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    //首页
    @RequestMapping(value={"/","/index"})
    public String index(HttpSession session){
        if(session.getAttribute("user")!=null){
            return "redirect:/main";
        }else {
            return "index";
        }
    }
}
