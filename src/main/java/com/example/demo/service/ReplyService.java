package com.example.demo.service;

import com.example.demo.dao.ReplyDao;
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

    public void doDeleteReply(int id) {
        replyDao.doDeleteReply(id);
    }

    public Reply getReplyByArticleId(int id) {
        return replyDao.getReplyByArticleId(id);
    }

    public void doModify(int id, String body) {
        replyDao.doModify(id, body);
    }

    public int getReplyCnt(int id) {
        return replyDao.getReplyCnt(id);
    }
}
