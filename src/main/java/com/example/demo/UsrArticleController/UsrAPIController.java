package com.example.demo.UsrArticleController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UsrAPIController {


    @RequestMapping("/usr/api/APITest")
    public String APITest( ){
        return "usr/api/APITest";
    }

}


