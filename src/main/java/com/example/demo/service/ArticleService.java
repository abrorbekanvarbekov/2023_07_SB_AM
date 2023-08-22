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

    public void writeArticle(String title, String body, int memberId, int boardId, int views){
        articleDao.writeArticle(title, body, memberId, boardId, views);
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public void deleteArticle(int id) {
        articleDao.deleteArticle(id);
    }

    public void modifyArticle(int id, String title, String body) {
        articleDao.modifyArticle(id, title, body);
    }

    public int getLastInsertId() {
        return articleDao.getLastInsertId();
    }

    public Article getArticleByNickname(int id) {
        return articleDao.getArticleByNickname(id);
    }

    public List<Article> getAllArticles(int boardId, int limitFrom, int itemsInPage, String searchKeyword, String selectKey) {
        return articleDao.getAllArticles(boardId, limitFrom, itemsInPage, searchKeyword, selectKey);
    }

    public int getArticleCountByBoard(int boardId, String searchKeyword, String selectKey) {
        return articleDao.getArticleCountByBoard(boardId, searchKeyword, selectKey);
    }

    public int increaseVCnt(int id) {
        return articleDao.increaseVCnt(id);
    }

    public void addReactionPoint(String relTypeCode, int relId, int memberId, int point) {
        articleDao.addReactionPoint(relTypeCode ,relId, memberId, point);
    }

}
