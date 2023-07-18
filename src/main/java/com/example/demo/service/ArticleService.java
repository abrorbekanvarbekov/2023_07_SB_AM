package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private ArticleDao articleDao;
    @Autowired
    public ArticleService(ArticleDao articleDao){
        this.articleDao = articleDao;
    }

    public void writeArticle(String title, String body, int memberId){
        articleDao.writeArticle(title, body, memberId);
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public void deleteArticle(int id) {
        articleDao.deleteArticle(id);
    }

    public List<Article> getArticles(){
        return articleDao.getArticles();
    }

    public void modifyArticle(int id, String title, String body) {
        articleDao.modifyArticle(id, title, body);
    }

    public int getLastInsertId() {
        return articleDao.getLastInsertId();
    }
}
