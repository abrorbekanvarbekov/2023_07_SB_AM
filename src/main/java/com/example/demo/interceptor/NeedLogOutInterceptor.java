package com.example.demo.interceptor;

import com.example.demo.vo.Rq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class NeedLogOutInterceptor implements HandlerInterceptor {

    private Rq rq;

    @Autowired
    public NeedLogOutInterceptor(Rq rq) {
        this.rq = rq;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (rq.getLoginedMemberId() != 0){
            rq.jsPrintHistoryBack("로그아웃 후 이용해주세요");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
