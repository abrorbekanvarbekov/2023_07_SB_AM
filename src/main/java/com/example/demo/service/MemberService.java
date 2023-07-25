package com.example.demo.service;

import com.example.demo.dao.MemberDao;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultDate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private MemberDao memberDao;

    public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public ResultDate doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
        Member foundMember = isExistsMember(loginId);
        if (foundMember != null) {
            return ResultDate.from("F-1", "이미 사용중인 아이디 입니다.");
        }

        Member foundNickname = existsNickName(nickname);
        if(foundNickname != null){
            return ResultDate.from("F-2", "이미 사용중인 넥네임 입니다.");
        }

        Member foundEmailName = existsEmailAndName(name,email);
        if (foundEmailName != null){
            return ResultDate.from("F-3", "이미 같은 이름과 이메일로 가입한 회원이 존재합니다.");
        }

        memberDao.doJoin(loginId, loginPw,name, nickname, cellphoneNum, email);
        return ResultDate.from("S-1", String.format("%s님이 가입 성공하였습니다.",nickname), "member",getMemberById(getLastInsertId()));
    }

    public Member existsEmailAndName(String name, String email) {
        return memberDao.existsEmailAndName(name, email);
    }

    public Member existsNickName(String nickname) {
        return memberDao.existsNickName(nickname);
    }

    public Member getMemberById(int id){
        return memberDao.getMemberById(id);
    };

    public List<Member> getMembers() {
        return memberDao.getMembers();
    }

    public void doModify(int id, String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
        memberDao.doModify(id, loginId, loginPw, name, nickname, cellphoneNum, email);
    }

    public void doDelete(int id) {
        memberDao.doDelete(id);
    }

    public Member doLogin(String loginId) {
        return memberDao.doLogin(loginId);
    }

    public Member isExistsMember(String loginId) {
        return memberDao.isExistsMember(loginId);
    }

    public int getLastInsertId() {
        return memberDao.getLastInsertId();
    }
}
