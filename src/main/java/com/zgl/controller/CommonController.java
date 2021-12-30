package com.zgl.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("common")
public class CommonController {
    @RequestMapping("login")
    public String login(){

        return "user/login";
    }
    @RequestMapping("register")
    public String register(){

        return "user/register";
    }
}
