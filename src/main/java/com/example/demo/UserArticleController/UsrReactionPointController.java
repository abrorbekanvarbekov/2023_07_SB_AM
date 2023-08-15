package com.example.demo.UserArticleController;

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
    public ResultDate<ReactionPoint> getReactionPoint(String relTypeCode, int relId){

        ReactionPoint reactionPoint = reactionPointService.getReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

        return ResultDate.from("S-1", "리액션 정보 조회 성공", "reactionPoint", reactionPoint);
    }

}


