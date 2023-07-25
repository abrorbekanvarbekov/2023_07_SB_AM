package com.example.demo.interceptor;

import com.example.demo.vo.Rq;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Rq rq = new Rq(request);
        request.setAttribute("rq", rq);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
