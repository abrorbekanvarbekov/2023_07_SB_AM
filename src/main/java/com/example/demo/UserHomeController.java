package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserHomeController {

    @RequestMapping("/usr/home/main")
    public String showMain(){
        return "usr/home/home";
    }

    @RequestMapping("/")
    public String showRoot(){
        return "redirect:usr/home/main";
    }
}


