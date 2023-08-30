<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="MyPage"/>
<%@include file="../common/head.jsp" %>


<section class="mt-8">
    <div class="container mx-auto">
        <div class="table-box-type-1">
            <table border="1">
                <tbody>
                <tr>
                    <th>가입 일</th>
                    <td>${rq.loginedMember.regDate.substring(2,16)}</td>
                </tr>
                <tr>
                    <th>로그인 아이디</th>
                    <td>${rq.loginedMember.loginId}</td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td>${rq.loginedMember.name}</td>
                </tr>
                <tr>
                    <th>닉네임</th>
                    <td>${rq.loginedMember.nickname}</td>
                </tr>
                <tr>
                    <th>전화번호</th>
                    <td>${rq.loginedMember.cellphoneNum}</td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td>${rq.loginedMember.email}</td>
                </tr>
                <tr class="hidden" id="verifyPassword">
                    <th>비밀번호 확인</th>
                    <td>
                        <input class="w-60 input input-bordered input-info w-full max-w-xs"
                               type="password" placeholder="비밀번호를 입력해주세요"
                               name="loginPw" id="verifyLoginPw">
                        <a href="/usr/member/modify=id${rq.loginedMember.id}" class="btn btn-outline btn-accent">확인</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="flex mt-8 justify-between">
            <button onclick="history.back();" class="btn btn-outline btn-accent">뒤로가기</button>
            <div>
                <a class="btn btn-outline btn-accent mr-2" href="passwordModify">비밀번호 변경</a>
                <a class="btn btn-outline btn-accent" href="passwordChk">회원정보 수정</a>
            </div>
        </div>
    </div>
</section>

<%@include file="../common/foot.jsp" %>