package com.example.demo.service;

import com.example.demo.dao.ReactionPointDao;
import com.example.demo.vo.ReactionPoint;
import org.springframework.stereotype.Service;

@Service
public class ReactionPointService {
    private ReactionPointDao reactionPointDao;

    public ReactionPointService(ReactionPointDao reactionPointDao) {
        this.reactionPointDao = reactionPointDao;
    }

    public ReactionPoint getReactionPoint(int loginedMemberId, String relTypeCode, int relId) {
        return reactionPointDao.getReactionPoint(loginedMemberId, relTypeCode, relId);
    }

    public void doInsertReactionPoint(int loginedMemberId, String relTypeCode, int relId, int point) {
        reactionPointDao.doInsertReactionPoint(loginedMemberId,  relTypeCode,  relId,  point);
    }

    public void doDeleteReactionPoint(int loginedMemberId, String relTypeCode, int relId) {
        reactionPointDao.doDeleteReactionPoint(loginedMemberId,  relTypeCode,  relId);
    }
}
