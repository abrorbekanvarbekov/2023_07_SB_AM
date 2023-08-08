package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultDate;
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

    public void writeArticle(String title, String body, int memberId, int boardId){
        articleDao.writeArticle(title, body, memberId, boardId);
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

    public ResultDate increaseVCnt(int id) {
        int affectedRowsCnt =  articleDao.increaseVCnt(id);

        if (affectedRowsCnt == 0){
            return ResultDate.from("F-1", "해당 게시글이 존재하지 않습니다.");
        }
        return ResultDate.from("S-1", "조회수 증가");
    }

    public int getArticleHitCnt(int id) {
        return articleDao.getArticleHitCnt(id);
    }
}
