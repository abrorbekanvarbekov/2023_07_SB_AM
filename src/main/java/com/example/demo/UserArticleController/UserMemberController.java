package com.example.demo.UserArticleController;

import com.example.demo.UserHomeController;
import com.example.demo.dao.ArticleDao;
import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultDate;
import com.example.demo.vo.Rq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserMemberController {
    private MemberService memberService;

    @Autowired
    public UserMemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @RequestMapping("usr/member/join")
    public String Join(Model model){
        return "usr/member/join";
    }

    @RequestMapping("usr/member/doJoin")
    @ResponseBody
    public String doJoin(String loginId, String loginPw, String loginPwCheck, String name, String nickname, String cellphoneNum, String email){

        if (Util.empty(loginId)){
            return Util.jsHistoryBack("아이디를 입력하세요");
        }
        if (Util.empty(loginPw)){
            return Util.jsHistoryBack("비밀번호를 입력해주세요.");
        }
        if (Util.empty(loginPwCheck)){
            return Util.jsHistoryBack("비밀번호 확인을 입력해주세요.");
        }
        if (loginPw.equals(loginPwCheck) == false){
            return Util.jsHistoryBack("비밀번호가 일치히지 않습니다.");
        }
        if (Util.empty(name)){
            return Util.jsHistoryBack("이름을 입력해주세요.");
        }
        if (Util.empty(nickname)){
            return Util.jsHistoryBack("닉네임을 입력해주세요.");
        }
        if (Util.empty(cellphoneNum)){
            return Util.jsHistoryBack("휴대포번호를 입력해주세요.");
        }
        if (Util.empty(email)){
            return Util.jsHistoryBack("이메일을 입력해주세요.");
        }


        ResultDate<Member> doJoinMember = memberService.doJoin(loginId,loginPw,name, nickname,cellphoneNum,email);

        return Util.jsReplace(doJoinMember.getMsg(), "login");
    }

    @RequestMapping("usr/member/login")
    public String Login(){
        return "usr/member/login";
    }

    @RequestMapping("usr/member/doLogin")
    @ResponseBody
    public String doLogin(String loginId, String loginPw, HttpServletRequest request, HttpSession session){

        Rq rq = (Rq) request.getAttribute("rq");

        if (rq.getLoginedMemberId()!= 0){
            return Util.jsHistoryBack("이미 로그인 상태 입니다.");
        }

        if(Util.empty(loginId)){
            return Util.jsHistoryBack("로그인 아이디를 입력해주세요");
        }

        if(Util.empty(loginPw)){
            return Util.jsHistoryBack("로그인 비밀번호를 입력해주세요");
        }

        Member member = memberService.doLogin(loginId);
        if(member == null) {
            return Util.jsHistoryBack(String.format("%s 이라는 아이디가 존재하지 않습니다.", loginId));
        }

        if (loginPw.equals(member.getLoginPw()) == false){
            return Util.jsHistoryBack("비밀번호가 일치하지 않습니다.");
        }

        rq.login(member);
        return Util.jsReplace(String.format("%s님 환영합니다.", member.getNickname()), "/usr/home/main");
    }

    @RequestMapping("usr/member/doLogOut")
    @ResponseBody
    public String doLogOut(HttpServletRequest request){

        Rq rq = (Rq) request.getAttribute("rq");

        if (rq.getLoginedMemberId() == 0){
            return Util.jsHistoryBack("로그인 후 이용해주세요.");
        }
        rq.logout();
        return Util.jsReplace("로그아웃 되었습니다.", "/");
    }

    @RequestMapping("usr/member/getMembers")
    @ResponseBody
    public ResultDate<List<Member>> getMembers(){
        List<Member> members = memberService.getMembers();

        if (members.size() == 0) {
            return ResultDate.from("F-1", "회원이 존재하지 않습니다.");
        }
        return ResultDate.from("S-1", "회원 리스트", "member", members);
    }

    @RequestMapping("usr/member/getMember")
    @ResponseBody
    public ResultDate<Member> getMember(int id){
        Member foundMember = memberService.getMemberById(id);

        if (foundMember == null){
            return ResultDate.from("F-1", String.format("%d번째 회원이 존재하지 않습니다.", id));
        }
        return ResultDate.from("S-1", String.format("%d 번째 회원 입니다.", id), "member", foundMember);
    }

    @RequestMapping("usr/member/doModify")
    @ResponseBody
    public ResultDate<Member> doModify(Integer id, String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email){

        if(id == null){
            return ResultDate.from("F-1", "회원 아이디를 입력헤주세요.");
        }

        Member foundMember = memberService.getMemberById(id);

        if (foundMember == null){
            return ResultDate.from("F-1", String.format("%d번째 회원이 존재하지 않습니다.", id));
        }
        memberService.doModify(id, loginId, loginPw, name, nickname, cellphoneNum, email);
        return ResultDate.from("S-1", String.format("%d 번 회원님의 개인 정보가 수정 되었습니다", id), "member", memberService.getMemberById(id));
    }

    @RequestMapping("usr/member/doDelete")
    @ResponseBody
    public ResultDate<String> doDelete(int id){
        Member foundMember = memberService.getMemberById(id);
        if (foundMember == null){
            return ResultDate.from("F-1", String.format("%d번째 회원이 존재하지 않습니다.", id));
        }
        memberService.doDelete(id);
        return ResultDate.from("F-1", String.format("%d번째 회원이 삭제 되었습니다.", id));
    }
}
