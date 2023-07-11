package com.example.demo;

import com.example.demo.vo.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsrArticleController {
    private int lastArticleId;
    private List<Article> articles;

    public UsrArticleController(){
        this.lastArticleId = 0;
        this.articles = new ArrayList<>();
    }


    @RequestMapping("/user/article/doAdd")
    @ResponseBody
    public Article doAdd(String title, String body){
        int id = this.lastArticleId + 1;
        this.lastArticleId = id;
        Article article = new Article(id, title, body);
        this.articles.add(article);

        return article;
    }

    @RequestMapping("/user/article/getArticle")
    @ResponseBody
    public List<Article> getArticle(){
        return this.articles;
    }
}
