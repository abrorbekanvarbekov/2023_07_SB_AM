package com.example.demo.UserArticleController;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsrArticleController {
    private ArticleService articleService;

    @Autowired
    public UsrArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @RequestMapping("/usr/article/doAdd")
    @ResponseBody
    public Article doAdd(String title, String body) {
        articleService.writeArticle(title, body);
        int id = articleService.getLastInsertId();
        return articleService.getArticleById(id);
    }


    @RequestMapping("/usr/article/getArticles")
    @ResponseBody
    public List<Article> getArticles(){
        return articleService.getArticles();
    }

    @RequestMapping("/usr/article/doDelete")
    @ResponseBody
    public String doDelete(int id){
        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null){
            return id + "번째 게시글이 존재하지 않습니다";
        }
        articleService.deleteArticle(id);
        return id + "번째 게시글이 삭제 되었습니다.";
    }

    @RequestMapping("/usr/article/doModify")
    @ResponseBody
    public String doModify(int id, String title, String body){
        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null){
            return id + "번 게시글이 존재하지 않습니다";
        }
        articleService.modifyArticle(id, title, body);
        return id + "번 게시글이 수정 되었습니다.";
    }

    @RequestMapping("/usr/article/getArticle")
    @ResponseBody
    public Object getArticle(int id){
        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null){
            return id + "번 게시글이 존재하지 않습니다";
        }

        return foundArticle;
    }
}
