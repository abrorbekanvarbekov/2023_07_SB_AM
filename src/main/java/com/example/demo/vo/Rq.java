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
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    public Rq(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        this.request = request;
        this.response = response;
        this.session = session;

        int loginedMemberId = 0;
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


    public void login(Member member) {
        this.session.setAttribute("loginedMemberId", member.getId());
    }

    public void logout() {
        this.session.removeAttribute("loginedMemberId");
    }

    public String jsReturnOnView(String msg) {
        this.request.setAttribute("msg", msg);
        return "usr/article/errorPage";
    }
}
