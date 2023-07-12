package com.example.demo.dao;

import com.example.demo.vo.Article;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleDao {
    private int lastArticleId;
    private List<Article> articles;

    public ArticleDao() {
        this.lastArticleId = 0;
        this.articles = new ArrayList<>();
    }

    public void makeTestArticles() {
        for (int i = 1; i <= 10; i++) {
            String title = "제목" + i;
            String body = "내용" + i;
            writeArticle(title,body);
        }
    }

    public Article writeArticle(String title, String body) {
        int id = this.lastArticleId + 1;
        this.lastArticleId = id;
        Article article = new Article(id, title, body);
        articles.add(article);

        return article;
    }

    public Article getArticleById(int id) {
        for (Article article : this.articles){
            if (article.getId() == id){
                return article;
            }
        }
        return null;
    }

    public void deleteArticle(int id) {
        Article article = getArticleById(id);
        articles.remove(article);
    }

    public List<Article> getArticles() {
        return articles;
    }
}
