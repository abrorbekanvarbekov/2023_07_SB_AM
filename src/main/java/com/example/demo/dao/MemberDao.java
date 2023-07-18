package com.example.demo.dao;

import com.example.demo.vo.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {

    public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

    public Member getMemberById(int id);

    public List<Member> getMembers();

    public void doModify(int id, String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

    public void doDelete(int id);

    public Member doLogin(String loginId);

    public Member isExistsMember(String loginId);

    public int getLastInsertId();

    public Member existsNickName(String nickname);

    public Member existsEmailAndName(String name, String email);
}
