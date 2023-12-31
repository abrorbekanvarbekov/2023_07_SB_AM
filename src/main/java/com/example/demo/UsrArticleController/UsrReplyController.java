package com.example.demo.UsrArticleController;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Reply;
import com.example.demo.vo.ResultDate;
import com.example.demo.vo.Rq;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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


    @RequestMapping("/usr/reply/doModify")
    @ResponseBody
    public String ReplyDoModify(int replyId, String body){
        Reply foundReply = replyService.getReply(replyId);

        if (foundReply == null){
            return Util.jsHistoryBack("해당 댓글이 존재하지 않습니다.");
        }

        if (foundReply.getMemberId() != rq.getLoginedMemberId()){
            return Util.jsHistoryBack("해당 댓글이 대한 권한이 없습니다.");
        }

        replyService.doModify(replyId, body);
        return Util.jsReplace("/usr/article/detail?id=" + foundReply.getArticleId());
    }


    @RequestMapping("/usr/reply/getReplyContent")
    @ResponseBody
    public ResultDate<Reply> getReplyContent(int id){
        Reply reply = replyService.getReply(id);

        if (reply == null){
            return ResultDate.from("F-1","해당 댓글이 존재하지 않습니다.");
        }

        return ResultDate.from("S-1", "댓글 조회 성공", "reply", reply);
    }



    @RequestMapping("/usr/reply/doDelete")
    @ResponseBody
    public String ReplyDoDelete(int id){

        Reply foundReply = replyService.getReply(id);

        if (foundReply == null){
            return Util.jsHistoryBack("해당 댓글이 존재하지 않습니다.");
        }

        if (foundReply.getMemberId() != rq.getLoginedMemberId()){
            return Util.jsHistoryBack("해당 댓글이 대한 권한이 없습니다.");
        }

        replyService.doDeleteReply(id);
        return Util.jsReplace("댓글이 삭제되었습니다.","../article/detail?id=" + foundReply.getArticleId());
    }

}


