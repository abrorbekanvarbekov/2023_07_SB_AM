package com.example.demo.vo;

import com.example.demo.util.Util;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Rq {
    @Getter
    private int loginedMemberId;
//    private HttpServletRequest request;
    private HttpServletResponse response;

    public Rq(HttpServletRequest request, HttpServletResponse response){
//        this.request = request;
        this.response = response;
        int loginedMemberId = 0;
        HttpSession session = request.getSession();
        if (session.getAttribute("loginedMemberId") != null){
            loginedMemberId = (int) session.getAttribute("loginedMemberId");
        }

        this.loginedMemberId = loginedMemberId;
    }

    public void jsPrintHistoryBack(String msg){
        this.response.setContentType("text/html; charset=UTF-8;");

        try {
            this.response.getWriter().append(Util.jsHistoryBack(msg));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
