package com.zgl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * @Author: zgl
 * @Date: 2021/12/26 10:25
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){

        return "index";
    }
}
