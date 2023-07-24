package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

    public ResultDate<Article> modifyArticle(int id, String title, String body, HttpSession session, Article foundArticle) {

        if ((int) session.getAttribute("loginedMemberId") != foundArticle.getMemberId()){
            return ResultDate.from("F-B", "해당 게시물에 대한 권한이 없습니다.");
        }

        articleDao.modifyArticle(id, title, body);
        return ResultDate.from("S-1", String.format("%d번째 게시글이 수정 되었습니다", id), "article", getArticleById(id));
    }

    public int getLastInsertId() {
        return articleDao.getLastInsertId();
    }

    public Article getArticleByNickname(int id) {
        return articleDao.getArticleByNickname(id);
    }
}
