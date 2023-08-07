package com.example.demo.UserArticleController;

import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.Rq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsrArticleController {
    private ArticleService articleService;
    private BoardService boardService;
    private Rq rq;

    @Autowired
    public UsrArticleController(ArticleService articleService, BoardService boardService, Rq rq) {
        this.articleService = articleService;
        this.boardService = boardService;
        this.rq = rq;
    }

    @RequestMapping("/usr/article/write")
    public String writer() {
        return "usr/article/writer";
    }

    @RequestMapping("/usr/article/doWrite")
    @ResponseBody
    public String doAdd(String title, String body, int boardId) {

        if (title == null || title.trim().length() == 0) {
            return Util.jsHistoryBack("제목을 입력해주세요");
        }

        if (body == null || body.trim().length() == 0) {
            return Util.jsHistoryBack("내용을 입력해주세요");
        }

        int memberId = rq.getLoginedMemberId();
        articleService.writeArticle(title, body, memberId, boardId);
        int id = articleService.getLastInsertId();

        return Util.jsReplace(String.format("%d번 게시글이 생성되었습니다.", id), "detail?id=" + id);
    }

    @RequestMapping("/usr/article/list")
    public String getAllArticles(Model model,
                                 @RequestParam(defaultValue = "1") int boardId,
                                 @RequestParam(defaultValue = "1") int page) {

        if (page <= 0){
            return rq.jsReturnOnView("페이지 번호가 옳바르지 않습니다.");
        }

        Board board = boardService.getBoardById(boardId);

        if (board == null) {
            return rq.jsReturnOnView("존재하지 않는 게시판입니다.");
        }

        int articleCntByBoard = articleService.getArticleCountByBoard(boardId);

        int itemsInPage = 10;
        int limitFrom = (page - 1) * itemsInPage;
        int totalPage = (int) Math.ceil((double) articleCntByBoard / itemsInPage);

        List<Article> articles = articleService.getAllArticles(boardId, limitFrom, itemsInPage);

        int pageSize = 5;
        int from = page - pageSize;
        if(from < 1){
            from = 1;
        }
        int end = page + pageSize;
        if (end > totalPage){
            end = totalPage;
        }

        model.addAttribute("articles", articles);
        model.addAttribute("board", board);
        model.addAttribute("articleCnt", articleCntByBoard);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", page);
        model.addAttribute("from", from);
        model.addAttribute("end", end);

        return "usr/article/list";
    }


    @RequestMapping("/usr/article/detail")
    public String getArticle(Model model, int id) {

        Article foundArticle = articleService.getArticleByNickname(id);
        if (foundArticle == null) {
            return rq.jsReturnOnView(id + "번 게시글이 존재하지 않습니다");
        }

        model.addAttribute("article", foundArticle);
        return "usr/article/detail";
    }

    @RequestMapping("/usr/article/doDelete")
    @ResponseBody
    public String doDelete(int id) {

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
    public String modify(Model model, int id) {

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
    public String doModify(int id, String title, String body) {

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
