package com.example.demo.dao;

import com.example.demo.vo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    public void writeArticle(String title, String body, int memberId, int boardId);

    public Article getArticleById(int id);

    public void deleteArticle(int id);

    public List<Article> getFreeArticles(int id);

    public void modifyArticle(int id, String title, String body);

    public int getLastInsertId();

    public Article getArticleByNickname(int id);

    public List<Article> getNoticeArticles(int id);

    public List<Article> getAllArticles();
}
