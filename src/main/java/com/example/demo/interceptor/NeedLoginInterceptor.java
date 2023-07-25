package com.example.demo.interceptor;

import com.example.demo.vo.Rq;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Rq rq = new Rq(request, response);

        if (rq.getLoginedMemberId() == 0){
            rq.jsPrintHistoryBack("로그인 후 이용해주세요");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
