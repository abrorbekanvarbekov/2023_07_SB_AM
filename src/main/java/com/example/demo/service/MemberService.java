package com.example.demo.service;

import com.example.demo.dao.MemberDao;
import com.example.demo.vo.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private MemberDao memberDao;

    public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
        memberDao.doJoin(loginId, loginPw,name, nickname, cellphoneNum, email);
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
