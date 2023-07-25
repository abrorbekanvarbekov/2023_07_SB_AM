package com.example.demo;

import com.example.demo.vo.Rq;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserHomeController {

    @RequestMapping("/usr/home/main")
    public String showMain(Model model, HttpServletResponse response, HttpServletRequest request){
        Rq rq = new Rq(request, response);
        model.addAttribute("loginedMember", rq.getLoginedMemberId());
        return "usr/home/home";
    }

    @RequestMapping("/")
    public String showRoot(){
        return "redirect:usr/home/main";
    }

}


