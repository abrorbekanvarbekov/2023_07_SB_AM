package com.example.demo.UsrArticleController;

import com.example.demo.vo.Rq;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class UsrHomeController {

    private Rq rq;

    public UsrHomeController(Rq rq) {
        this.rq = rq;
    }

    @RequestMapping("/usr/home/main")
    public String showMain(Model model ){
        model.addAttribute("loginedMember", rq.getLoginedMemberId());
        return "usr/home/home";
    }

    @RequestMapping("/")
    public String showRoot(){
        return "redirect:usr/home/main";
    }

    @RequestMapping("/usr/home/popUp.com")
    public String showPopUp(){
        return "usr/home/popUp";
    }
}


