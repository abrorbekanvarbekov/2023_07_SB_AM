package com.example.demo.UserArticleController;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultDate;
import com.example.demo.vo.Rq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsrMemberController {
    private MemberService memberService;
    private Rq rq;

    @Autowired
    public UsrMemberController(MemberService memberService, Rq rq){
        this.memberService = memberService;
        this.rq = rq;
    }

    @RequestMapping("usr/member/join")
    public String Join(){
        return "usr/member/join";
    }

    @RequestMapping("usr/member/loginIdCheck")
    @ResponseBody
    public ResultDate<String> loginIdCheck(String loginId){

        if (Util.empty(loginId)){
            return ResultDate.from("F-1", "아이디를 입력해주세요!");
        }

        Member member = memberService.getExistLoginId(loginId);

        if (member != null){
            return ResultDate.from("F-1", "이미 존재하는 아이디 입니다", "loginId", loginId);
        }

        if (loginId.length() < 6){
            return ResultDate.from("F-1", "아이디: 5 ~ 20자 이상여야 합니다", "loginId", loginId);
        }

        return ResultDate.from("S-1", "사용가능한 아이디 입니다", "loginId", loginId);
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

        if(doJoinMember.isFail()){
            return Util.jsHistoryBack("이미 사용중인 아이디 입니다.");
        }

        return Util.jsReplace(doJoinMember.getMsg(), "login");
    }

    @RequestMapping("usr/member/login")
    public String Login(){
        return "usr/member/login";
    }

    @RequestMapping("usr/member/doLogin")
    @ResponseBody
    public String doLogin(String loginId, String loginPw){

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
    public String doLogOut(){

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

    @RequestMapping("usr/member/myPage")
    public String memberAccount(){
        return "usr/member/myPage";
    }


    @RequestMapping("usr/member/passwordChk")
    public String passwordChk(){
        return "usr/member/passwordChk";
    }


    @RequestMapping("usr/member/doPasswordChk")
    public String doPasswordChk(String loginPw){

        if (Util.empty(loginPw)){
            return rq.jsReturnOnView("비밀번호를 입력하세요");
        }

        if (rq.getLoginedMember().getLoginPw().equals(loginPw) == false){
            return rq.jsReturnOnView("비밀번호가 일치하지 않습니다.");
        }

        return "usr/member/modify";
    }


    @RequestMapping("usr/member/doModify")
    @ResponseBody
    public String doModify(String nickname, String cellphoneNum, String email){

        if (Util.empty(nickname)){
            return Util.jsHistoryBack("닉네임을 입력하세요");
        }

        if (Util.empty(cellphoneNum)){
            return Util.jsHistoryBack("전화번호를 입력하세요");
        }

        if (Util.empty(email)){
            return Util.jsHistoryBack("이메일을 입력하세요");
        }

        memberService.doModify(rq.getLoginedMemberId(), nickname, cellphoneNum, email);
        return Util.jsReplace("회원 정보가 수정 되었습니다.", "myPage");
    }


    @RequestMapping("usr/member/passwordModify")
    public String passwordModify(){
        return "usr/member/passwordModify";
    }


    @RequestMapping("usr/member/doPasswordModify")
    @ResponseBody
    public String doPasswordModify(String loginPw, String loginPwChk){

        if (Util.empty(loginPw)){
            return Util.jsHistoryBack("새 비밀번호를 입력하세요");
        }

        if (Util.empty(loginPwChk)){
            return Util.jsHistoryBack("새 비밀번호 확인을 입력하세요");
        }

        if (loginPw.equals(loginPwChk) == false){
            return Util.jsHistoryBack("비밀번호를 동일하게 입력해주세요");
        }

        memberService.doPasswordModify(rq.getLoginedMemberId(), loginPw);
        return Util.jsReplace("비밀번호 변경되었습니다.", "myPage");
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
