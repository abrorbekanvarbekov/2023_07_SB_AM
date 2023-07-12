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
        makeTestArticles();
    }

    public void makeTestArticles() {
        articleDao.makeTestArticles();
    }

    public Article writeArticle(String title, String body) {
        return articleDao.writeArticle(title, body);
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
}
