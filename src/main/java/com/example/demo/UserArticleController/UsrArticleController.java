package com.example.demo.UserArticleController;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Rq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UsrArticleController {
    private ArticleService articleService;

    @Autowired
    public UsrArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/usr/article/write")
    public String writer(int id) {
        return "usr/article/writer";
    }

    @RequestMapping("/usr/article/doWrite")
    @ResponseBody
    public String doAdd(HttpServletRequest request, String title, String body, int boardId) {

        Rq rq = (Rq) request.getAttribute("rq");

        if (title == null || title.trim().length() == 0) {
            return Util.jsHistoryBack("제목을 입력해주세요");
        }

        if (body == null || body.trim().length() == 0) {
            return Util.jsHistoryBack("내용을 입력해주세요");
        }

        int memberId = rq.getLoginedMemberId();
        articleService.writeArticle(title, body, memberId, boardId);
        int id = articleService.getLastInsertId();

        return Util.jsReplace(String.format("%d번 게시글이 생성되었습니다.", id), "detail?id="+id);
    }

    @RequestMapping("/usr/article/allList")
    public String getAllArticles(Model model) {

        List<Article> articles = articleService.getAllArticles();

        model.addAttribute("articles", articles);

        return "usr/article/list";
    }

    @RequestMapping("/usr/article/freeList")
    public String getFreeArticles(Model model, int boardId) {

        List<Article> articles = articleService.getFreeArticles(boardId);

        model.addAttribute("articles", articles);

        return "usr/article/list";
    }


    @RequestMapping("/usr/article/noticeList")
    public String getNoticeArticles(Model model, int boardId) {

        List<Article> articles = articleService.getNoticeArticles(boardId);

        model.addAttribute("articles", articles);

        return "usr/article/list";
    }

    @RequestMapping("/usr/article/detail")
    public String getArticle(Model model, int id, HttpServletRequest request) {

        Rq rq = (Rq) request.getAttribute("rq");

        Article foundArticle = articleService.getArticleByNickname(id);
        if (foundArticle == null) {
            return rq.jsReturnOnView(id + "번 게시글이 존재하지 않습니다");
        }

        model.addAttribute("article", foundArticle);
        return "usr/article/detail";
    }

    @RequestMapping("/usr/article/doDelete")
    @ResponseBody
    public String doDelete(HttpServletRequest request, int id) {

        Rq rq = (Rq) request.getAttribute("rq");

        Article foundArticle = articleService.getArticleById(id);
        if (foundArticle == null) {
            return Util.jsHistoryBack(String.format("%d번 게시물이 존재하지 않습니다", id));
        }

        if (rq.getLoginedMemberId() != foundArticle.getMemberId()) {
            return Util.jsHistoryBack("해당 게시물에 대한 권한이 없습니다");
        }

        articleService.deleteArticle(id);
        return Util.jsReplace(String.format("%d번 게시물이 삭제되었습니다", id), "allList");
    }

    @RequestMapping("/usr/article/modify")
    public String modify(Model model, int id, HttpServletRequest request) {

        Rq rq = (Rq) request.getAttribute("rq");

        Article foundArticle = articleService.getArticleById(id);
        if (foundArticle == null) {
            return rq.jsReturnOnView(String.format("%d번 게시글이 존재하지 않습니다", id));
        }

        if (rq.getLoginedMemberId() != foundArticle.getMemberId()) {
            return rq.jsReturnOnView("해당 게시물에 대한 권한이 없습니다.");
        }

        Article article = articleService.getArticleByNickname(id);
        model.addAttribute("article", article);
        return "usr/article/modify";
    }

    @RequestMapping("/usr/article/doModify")
    @ResponseBody
    public String doModify(HttpServletRequest request, int id, String title, String body) {

        Rq rq = (Rq) request.getAttribute("rq");

        Article foundArticle = articleService.getArticleById(id);
        if (foundArticle == null) {
            return Util.jsReplace(String.format("%d번 게시글이 존재하지 않습니다", id), "list");
        }

        if (rq.getLoginedMemberId() != foundArticle.getMemberId()) {
            return Util.jsReplace("해당 게시물에 대한 권한이 없습니다.", "list");
        }

        articleService.modifyArticle(id, title, body);
        return Util.jsReplace(String.format("%d번 게시글이 수정 되었습니다.", id), String.format("detail?id=%s", id));
    }

}
