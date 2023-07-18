package com.example.demo.UserArticleController;

import com.example.demo.UserHomeController;
import com.example.demo.dao.ArticleDao;
import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("usr/member/doJoin")
    @ResponseBody
    public ResultDate<Member> doJoin(String loginId, String loginPw, String loginPwCheck, String name, String nickname, String cellphoneNum, String email){

        if (Util.empty(loginId)){
            return ResultDate.from("F-1", "아이디를 입력하세요");
        }
        if (Util.empty(loginPw)){
            return ResultDate.from("F-1", "비밀번호를 입력해주세요.");
        }
        if (Util.empty(loginPwCheck)){
            return ResultDate.from("F-1", "비밀번호 확인을 입력해주세요.");
        }
        if (loginPw.equals(loginPwCheck) == false){
            return ResultDate.from("F-2", "비밀번호가 일치히지 않습니다.");
        }
        if (Util.empty(name)){
            return ResultDate.from("F-1", "이름을 입력해주세요.");
        }
        if (Util.empty(nickname)){
            return ResultDate.from("F-1", "닉네임을 입력해주세요.");
        }
        if (Util.empty(cellphoneNum)){
            return ResultDate.from("F-1", "휴대포번호를 입력해주세요.");
        }
        if (Util.empty(email)){
            return ResultDate.from("F-1", "이메일을 입력해주세요.");
        }

        ResultDate<Member> doJoinRd = memberService.doJoin(loginId,loginPw,name, nickname,cellphoneNum,email);

        return doJoinRd;
    }

    @RequestMapping("usr/member/doLogin")
    @ResponseBody
    public ResultDate doLogin(String loginId, String loginPw, HttpSession session){

        if (session.getAttribute("loginedMemberId") != null){
            return ResultDate.from("F-1", "이미 로그인 상태 입니다.");
        }

        if(loginId == null || loginId.trim().length() == 0){
            return ResultDate.from("F-2", "로그인 아이디를 입력해주세요");
        }

        if(loginPw == null || loginPw.trim().length() == 0){
            return ResultDate.from("F-3", "로그인 비밀번호를 입력해주세요");
        }

        Member member = memberService.doLogin(loginId);
        if(member == null) {
            return ResultDate.from("F-1", String.format("%s 이라는 아이디가 존재하지 않습니다.", loginId));
        }

        if (loginPw.equals(member.getLoginPw()) == false){
            return ResultDate.from("F-2", "비밀번호가 일치하지 않습니다.");
        }

        session.setAttribute("loginedMemberId", member.getId());
        return ResultDate.from("S-1", String.format("%s님 환영합니다.", member.getNickname()));
    }

    @RequestMapping("usr/member/doLogOut")
    @ResponseBody
    public ResultDate doLogOut(HttpSession session){

        if (session.getAttribute("loginedMemberId") == null){
            return ResultDate.from("F-1", "로그인 후 이용해주세요.");
        }
        session.removeAttribute("loginedMemberId");
        return ResultDate.from("S-1", "로그아웃 되었습니다.");
    }

    @RequestMapping("usr/member/getMembers")
    @ResponseBody
    public ResultDate<List<Member>> getMembers(){
        List<Member> members = memberService.getMembers();

        if (members.size() == 0) {
            return ResultDate.from("F-1", "회원이 존재하지 않습니다.");
        }
        return ResultDate.from("S-1", "회원 리스트", members);
    }

    @RequestMapping("usr/member/getMember")
    @ResponseBody
    public ResultDate<Member> getMember(int id){
        Member foundMember = memberService.getMemberById(id);

        if (foundMember == null){
            return ResultDate.from("F-1", String.format("%d번째 회원이 존재하지 않습니다.", id));
        }
        return ResultDate.from("S-1", String.format("%d 번째 회원 입니다.", id), foundMember);
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
        return ResultDate.from("S-1", String.format("%d 번 회원님의 개인 정보가 수정 되었습니다", id), memberService.getMemberById(id));
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
