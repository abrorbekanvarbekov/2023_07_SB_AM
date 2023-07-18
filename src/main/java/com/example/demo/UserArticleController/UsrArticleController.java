package com.example.demo.UserArticleController;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    public ResultDate<Article> doAdd(String title, String body, HttpSession session) {

        if (session.getAttribute("loginedMemberId") == null){
            return ResultDate.from("F-A", "로그인 후 이용해주세요");
        }

        if (title == null || title.trim().length() == 0){
            return ResultDate.from("F-1", "제목을 입력해주세요");
        }

        if (body == null || title.trim().length() == 0){
            return ResultDate.from("F-1", "내용을 입력해주세요");
        }

       int memberId = (int) session.getAttribute("loginedMemberId");

        articleService.writeArticle(title, body, memberId);
        int id = articleService.getLastInsertId();
        return ResultDate.from("S-1", String.format("%d번 게시글이 생성되었습니다.", id), articleService.getArticleById(id));
    }


    @RequestMapping("/usr/article/getArticles")
    @ResponseBody
    public ResultDate<List<Article>> getArticles(){
        List<Article> articles = articleService.getArticles();

        if(articles.size() == 0){
            return ResultDate.from("F-1", "게시물이 존재하지 않습니다.");
        }

        return ResultDate.from("S-1", "게시물 리스트", articles);
    }

    @RequestMapping("/usr/article/doDelete")
    @ResponseBody
    public ResultDate<String> doDelete(int id){
        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null){
            return ResultDate.from("F-1", String.format("%d번째 게시글이 존재하지 않습니다", id));
        }
        articleService.deleteArticle(id);
        return ResultDate.from("S-1", String.format("%d번째 게시글이 삭제 되었습니다", id));
    }

    @RequestMapping("/usr/article/doModify")
    @ResponseBody
    public ResultDate<String> doModify(int id, String title, String body){
        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null){
            return ResultDate.from("F-1", String.format("%d번째 게시글이 존재하지 않습니다", id));
        }
        articleService.modifyArticle(id, title, body);
        return ResultDate.from("S-1", String.format("%d번째 게시글이 수정 되었습니다", id));
    }

    @RequestMapping("/usr/article/getArticle")
    @ResponseBody
    public ResultDate<Article> getArticle(int id){
        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null){
            return ResultDate.from("F-1", String.format("%d번 게시글이 존재하지 않습니다", id));
        }

        return ResultDate.from("S-1", String.format("%d번 게시글 입니다", id), foundArticle);
    }
}
