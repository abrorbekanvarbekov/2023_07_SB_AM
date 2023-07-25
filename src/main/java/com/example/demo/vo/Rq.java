package com.example.demo.vo;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Rq {
    @Getter
    private int loginedMemberId;

    public Rq(HttpServletRequest request){

        int loginedMemberId = 0;
        HttpSession session = request.getSession();
        if (session.getAttribute("loginedMemberId") != null){
            loginedMemberId = (int) session.getAttribute("loginedMemberId");
        }

        this.loginedMemberId = loginedMemberId;
    }
}
