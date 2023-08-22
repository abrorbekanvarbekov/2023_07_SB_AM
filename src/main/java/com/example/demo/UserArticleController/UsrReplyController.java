package com.example.demo.UserArticleController;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Reply;
import com.example.demo.vo.Rq;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsrReplyController {

    private ReplyService replyService;
    private Rq rq;

    public UsrReplyController(ReplyService replyService, Rq rq) {
        this.replyService = replyService;
        this.rq = rq;
    }


    @RequestMapping("/usr/reply/doWrite")
    @ResponseBody
    public String ReplyDoWriter(int articleId, String relTypeCode, String replyBody){
        replyService.writeReply(rq.getLoginedMemberId(), articleId, relTypeCode, replyBody);
        return Util.jsReplace("../article/detail?id=" + articleId);
    }
}


