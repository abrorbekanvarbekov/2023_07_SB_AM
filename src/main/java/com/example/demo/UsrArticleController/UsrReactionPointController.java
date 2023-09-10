package com.example.demo.UsrArticleController;

import com.example.demo.service.ReactionPointService;
import com.example.demo.vo.ReactionPoint;
import com.example.demo.vo.ResultDate;
import com.example.demo.vo.Rq;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrReactionPointController {

    private ReactionPointService reactionPointService;
    private Rq rq;

    public UsrReactionPointController(ReactionPointService reactionPointService, Rq rq) {
        this.reactionPointService = reactionPointService;
        this.rq = rq;
    }

    @RequestMapping("/usr/reactionPoint/getReactionPoint")
    @ResponseBody
    public ResultDate<ReactionPoint> getReactionPoint(String relTypeCode, int relId) {

        ReactionPoint reactionPoint = reactionPointService.getReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

        return ResultDate.from("S-1", "리액션 정보 조회 성공", "reactionPoint", reactionPoint);
    }

    @RequestMapping("/usr/reactionPoint/doInsertReactionPoint")
    public String doInsertReactionPoint(String relTypeCode, int relId, int point) {

        ReactionPoint reactionPoint = reactionPointService.getReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

        if (reactionPoint.getPoint() != 0){
            reactionPointService.doDeleteReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
        }

        reactionPointService.doInsertReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId, point);
        return null;
    }

    @RequestMapping("/usr/reactionPoint/doDeleteReactionPoint")
    @ResponseBody
    public String doDeleteReactionPoint(String relTypeCode, int relId) {

        reactionPointService.doDeleteReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
        return null;
    }
}


