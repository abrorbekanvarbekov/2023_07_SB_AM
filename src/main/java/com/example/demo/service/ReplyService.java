package com.example.demo.service;

import com.example.demo.dao.ReplyDao;
import com.example.demo.vo.Article;
import com.example.demo.vo.Reply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
    private ReplyDao replyDao;

    public ReplyService(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }

    public void writeReply(int loginedMemberId, int articleId, String relTypeCode, String body) {
        replyDao.writeReply(loginedMemberId, articleId, relTypeCode, body);
    }

    public List<Reply> getReplyList(int articleId) {
        return replyDao.getReplyList(articleId);
    }
}
