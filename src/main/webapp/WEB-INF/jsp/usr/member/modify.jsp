<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Modify"/>
<%@include file="../common/head.jsp" %>
<section class="mt-8">
    <div class="container mx-auto">
        <form action="/usr/member/doModify" method="POST">
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
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs"
                                   value="${rq.loginedMember.nickname}" type="text" placeholder="제목을 입력해주세요"
                                   name="nickname"></td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs"
                                   value="${rq.loginedMember.cellphoneNum}" type="text" placeholder="내용을 입력해주세요"
                                   name="cellphoneNum"></td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" value="${rq.loginedMember.email}"
                                   type="text" placeholder="내용을 입력해주세요"
                                   name="email"></td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <button class="w-60 btn btn-outline btn-accent" type="submit">수정</button>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </form>
        <div class="mt-8 flex justify-between">
            <button onclick="history.back();" class="btn btn-outline btn-accent">뒤로가기</button>
            <a class="btn btn-outline btn-accent mr-2" href="passwordModify">비밀번호 변경</a>
        </div>
    </div>
</section>

<%@include file="../common/foot.jsp" %>
