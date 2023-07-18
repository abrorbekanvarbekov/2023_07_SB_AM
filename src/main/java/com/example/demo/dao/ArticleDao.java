package com.example.demo.dao;

import com.example.demo.vo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    public void writeArticle(String title, String body);

    public Article getArticleById(int id);

    public void deleteArticle(int id);

    public List<Article> getArticles();

    public void modifyArticle(int id, String title, String body);

    public int getLastInsertId();
}
