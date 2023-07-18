package com.example.demo.UserArticleController;

import com.example.demo.UserHomeController;
import com.example.demo.dao.ArticleDao;
import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Object doJoin(String loginId, String loginPw, String loginPwCheck, String name, String nickname, String cellphoneNum, String email){

        if (Util.empty(loginId)){
            return "아이디를 입력해주세요.";
        }
        Member foundMember = memberService.isExistsMember(loginId);
        if (foundMember != null) {
            return loginId + "은 이미 존재하는 아이디 입니다.";
        }
        if (Util.empty(loginPw)){
            return "비밀번호를 입력해주세요.";
        }
        if (Util.empty(loginPwCheck)){
            return "비밀번호 확인을 입력해주세요.";
        }
        if (loginPw.equals(loginPwCheck) == false){
            return "비밀번호 확인후 다시 입력해주세요.";
        }
        if (Util.empty(name)){
            return "이름을 입력해주세요.";
        }
        if (Util.empty(nickname)){
            return " 닉네임을 입력해주세요.";
        }
        if (Util.empty(cellphoneNum)){
            return "휴대폰 번호를 입력해주세요.";
        }
        if (Util.empty(email)){
            return "이메일을 입력해주세요.";
        }

        memberService.doJoin(loginId,loginPw,name, nickname,cellphoneNum,email);
        int id = memberService.getLastInsertId();

        return memberService.getMemberById(id);
    }

    @RequestMapping("usr/member/doLogin")
    @ResponseBody
    public String doLogin(String loginId, String loginPw){
        Member member = memberService.doLogin(loginId);
        if(member == null) {
            return loginId + "이라는 아이디가 존재하지 않습니다.";
        }

        if (loginPw.equals(member.getLoginPw()) == false){
            return "비밀번호가 일치하지 않습니다.";
        }
        return loginId + "님 환영합니다.";
    }

    @RequestMapping("usr/member/getMembers")
    @ResponseBody
    public List<Member> getMembers(){
        return memberService.getMembers();
    }

    @RequestMapping("usr/member/getMember")
    @ResponseBody
    public Object getMember(int id){
        Member foundMember = memberService.getMemberById(id);

        if (foundMember == null){
            return id + "번째 회원이 존재하지 않습니다.";
        }
        return foundMember;
    }

    @RequestMapping("usr/member/doModify")
    @ResponseBody
    public String doModify(int id, String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email){
        Member foundMember = memberService.getMemberById(id);

        if (foundMember == null){
            return id + "번째 회원이 존재하지 않습니다.";
        }
        memberService.doModify(id, loginId, loginPw, name, nickname, cellphoneNum, email);
        return loginId + "님 개인 정보가 수정 되었습니다.";
    }

    @RequestMapping("usr/member/doDelete")
    @ResponseBody
    public String doDelete(int id){
        Member foundMember = memberService.getMemberById(id);
        if (foundMember == null){
            return id + "번 회원이 존재하지 않습니다.";
        }
        memberService.doDelete(id);
        return id + "번 회원이 삭제 되었습니다.";
    }
}
