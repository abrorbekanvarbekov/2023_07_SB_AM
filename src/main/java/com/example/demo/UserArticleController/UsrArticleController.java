package com.example.demo.UserArticleController;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultDate;
import com.example.demo.vo.Rq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public ResultDate<Article> doAdd(HttpServletRequest request, String title, String body, HttpSession session) {

//        Rq rq = new Rq(request);

//        if (rq.getLoginedMemberId() == 0){
//            return Util.jsHistoryBack("로그인 후 이용해주세요");
//        }

        if (title == null || title.trim().length() == 0){
            return ResultDate.from("F-1", "제목을 입력해주세요");
        }

        if (body == null || title.trim().length() == 0){
            return ResultDate.from("F-1", "내용을 입력해주세요");
        }

       int memberId = (int) session.getAttribute("loginedMemberId");

        articleService.writeArticle(title, body, memberId);
        int id = articleService.getLastInsertId();
        return ResultDate.from("S-1", String.format("%d번 게시글이 생성되었습니다.", id), "article", articleService.getArticleById(id));
    }


    @RequestMapping("/usr/article/list")
    public String getArticles(Model model){
        System.out.println("Controller shilheng tem??");

        List<Article> articles = articleService.getArticles();

        model.addAttribute("articles", articles);

        return "usr/article/list";
    }

    @RequestMapping("/usr/article/detail")
    public String getArticle(Model model, int id, HttpSession session){
        int loginedMemberId = 0;

        if (session.getAttribute("loginedMemberId") != null){
            loginedMemberId = (int) session.getAttribute("loginedMemberId");
        }

        Article foundArticle = articleService.getArticleByNickname(id);
        if (foundArticle == null){
            String msg =  id + "번 게시물이 존재하지 않습니다.";
            model.addAttribute("msg", msg);
            return "usr/article/errorPage";
        }

        model.addAttribute("article", foundArticle);
        model.addAttribute("loginedMemberId", loginedMemberId);
        return "usr/article/detail";
    }

    @RequestMapping("/usr/article/doDelete")
    @ResponseBody
    public String doDelete(HttpServletRequest request, int id, HttpSession session){

        Rq rq = (Rq) request.getAttribute("rq");

        if (rq.getLoginedMemberId() == 0){
            return Util.jsHistoryBack("로그인 후 이용해주세요");
        }

        Article foundArticle = articleService.getArticleById(id);
        if (foundArticle == null){
            return Util.jsHistoryBack(String.format("%d번 게시물이 존재하지 않습니다", id));
        }

        if (rq.getLoginedMemberId() != foundArticle.getMemberId()){
            return Util.jsHistoryBack("해당 게시물에 대한 권한이 없습니다");
        }

        articleService.deleteArticle(id);
        return Util.jsReplace(String.format("%d번 게시물이 삭제되었습니다",id), "list");
    }

    @RequestMapping("/usr/article/doModify")
    @ResponseBody
    public String doModify(HttpServletRequest request, int id, String title, String body, HttpSession session){

        Rq rq = (Rq) request.getAttribute("rq");

        if (rq.getLoginedMemberId() == 0){
            return Util.jsHistoryBack("로그인 후 이용해주세요");
        }

        Article foundArticle = articleService.getArticleById(id);
        if (foundArticle == null){
            return Util.jsReplace(String.format("%d번 게시글이 존재하지 않습니다", id), "list");
        }

        if ((int) session.getAttribute("loginedMemberId") != foundArticle.getMemberId()){
            return Util.jsReplace("해당 게시물에 대한 권한이 없습니다.", "list");
        }

        articleService.modifyArticle(id, title, body);
        return Util.jsReplace(String.format("%d번 게시글이 수정 되었습니다.",id), String.format("detail?id=%s", id));
    }

}
