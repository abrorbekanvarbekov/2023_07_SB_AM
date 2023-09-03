package com.example.demo.config;

import com.example.demo.interceptor.BeforeActionInterceptor;
import com.example.demo.interceptor.NeedLogOutInterceptor;
import com.example.demo.interceptor.NeedLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    private BeforeActionInterceptor beforeActionInterceptor;
    private NeedLoginInterceptor needLoginInterceptor;
    private NeedLogOutInterceptor needLogOutInterceptor;

    @Autowired
    public MyWebMvcConfigurer(BeforeActionInterceptor beforeActionInterceptor
            ,NeedLoginInterceptor needLoginInterceptor
            ,NeedLogOutInterceptor needLogOutInterceptor) {
        this.beforeActionInterceptor = beforeActionInterceptor;
        this.needLoginInterceptor = needLoginInterceptor;
        this.needLogOutInterceptor = needLogOutInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(beforeActionInterceptor)
                .addPathPatterns("/**")
                .addPathPatterns("/favicon.ico")
                .excludePathPatterns("/resource/**");
        registry.addInterceptor(needLoginInterceptor)
                .addPathPatterns("/usr/article/doAdd")
                .addPathPatterns("/usr/article/doDelete")
                .addPathPatterns("/usr/article/doModify")
                .addPathPatterns("/usr/article/modify")
                .addPathPatterns("/usr/article/write")
                .addPathPatterns("/usr/article/doWrite")
                .addPathPatterns("/usr/member/doLogOut")
                .addPathPatterns("/usr/member/myPage")
                .addPathPatterns("/usr/member/passwordChk")
                .addPathPatterns("/usr/member/doPasswordChk")
                .addPathPatterns("/usr/member/doModify")
                .addPathPatterns("/usr/member/passwordModify")
                .addPathPatterns("/usr/member/doPasswordModify")
                .addPathPatterns("/usr/member/doDelete")
                .addPathPatterns("/usr/reactionPoint/getReactionPoint")
                .addPathPatterns("/usr/reactionPoint/doInsertReactionPoint")
                .addPathPatterns("/usr/reactionPoint/doDeleteReactionPoint")
                .addPathPatterns("/usr/reply/doWrite")
                .addPathPatterns("/usr/reply/doModify")
                .addPathPatterns("/usr/reply/doDelete")
                .addPathPatterns("/usr/reply/getReplyContent");
        registry.addInterceptor(needLogOutInterceptor)
                .addPathPatterns("/usr/member/join")
                .addPathPatterns("/usr/member/doJoin")
                .addPathPatterns("/usr/member/login")
                .addPathPatterns("/usr/member/doLogin");
    }

}
