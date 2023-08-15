package com.example.demo.dao;

import com.example.demo.vo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    public void writeArticle(String title, String body, int memberId, int boardId, int views);

    public Article getArticleById(int id);

    public void deleteArticle(int id);

    public void modifyArticle(int id, String title, String body);

    public int getLastInsertId();

    public Article getArticleByNickname(int id);

    public List<Article> getAllArticles(int boardId, int limitFrom, int itemsInAPage, String searchKeyword, String selectKey);

    public int getArticleCountByBoard(int boardId, String searchKeyword, String selectKey);

    public int increaseVCnt(int id);

    public void addReactionPoint(String relTypeCode, int relId, int memberId, int point);

    public List<Article> getReactionPointArticle(int relId, int memberId);

    public void removeReactionPoint(int relId);
}
