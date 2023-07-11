package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserHomeController {
    int num;

    UserHomeController(){
        this.num = 0;
    }

    @RequestMapping("/")
    @ResponseBody
    public String showMain(){
        return "hello world";
    }


    @RequestMapping("/usr/main1")
    @ResponseBody
    public int showMain1(){
        return num++;
    }

    @RequestMapping("/usr/main2")
    @ResponseBody
    public List<String> showMain2(){
        List<String> list  = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        return list;
    }

    @RequestMapping("/usr/main3")
    @ResponseBody
    public Map<String, Integer> showMain3(){
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        return map;
    }

//    @RequestMapping("/usr/main4")
//    @ResponseBody
//    public Article showMain4(){
//        Article article = new Article();
//        article.id = 1;
//        article.title = "abd";
//        return article;
//    }

    @RequestMapping("/usr/main5")
    @ResponseBody
    public List<Article> showMain5(){
        List<Article> articles = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            Article article = new Article(i, "qwe" + 1);
            articles.add(article);
        }
        return articles;
    }
}

@AllArgsConstructor
class Article{
    public int id;
    public String title;
}