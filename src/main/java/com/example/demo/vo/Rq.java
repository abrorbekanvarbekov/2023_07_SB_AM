package com.example.demo.vo;

import com.example.demo.util.Util;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
    @Getter
    private int loginedMemberId;
    @Getter
    private Member loginedMember;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    public Rq(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        this.request = request;
        this.response = response;
        this.session = session;

        int loginedMemberId = 0;
        Member loginedMember = null;
        if (session.getAttribute("loginedMemberId") != null){
            loginedMemberId = (int) session.getAttribute("loginedMemberId");
            loginedMember = (Member) session.getAttribute("loginedMember");
        }

        this.loginedMemberId = loginedMemberId;
        this.loginedMember = loginedMember;
        this.request.setAttribute("rq", this);
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
        this.session.setAttribute("loginedMember", member);
    }

    public void logout() {
        this.session.removeAttribute("loginedMemberId");
        this.session.removeAttribute("loginedMember");
    }

    public String jsReturnOnView(String msg) {
        this.request.setAttribute("msg", msg);
        return "usr/article/errorPage";
    }

    public void init() {

    }
}
