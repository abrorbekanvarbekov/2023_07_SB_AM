<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Find LoginPw"/>
<%@include file="../common/head.jsp" %>
<section class="mt-8">
    <div class="container mx-auto">
        <form action="doFindByLoginPw" method="POST">
            <div class="table-box-type-1">
                <table border="1">
                    <tbody>
                    <tr>
                        <th>아이디</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="아이디를 입력하세요" name="loginId">
                        </td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="이름을 입력하세요" name="name">
                        </td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="이메일을 입력하세요" name="email"></td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <button class="w-60 btn btn-accent" type="submit">비밀번호 찾기</button>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </form>
        <div class="mt-8 flex justify-between">
            <div>
                <button onclick="history.back();" class="btn btn-accent">뒤로가기</button>
                <a class="btn btn-accent" href="join">Join Page</a>
            </div>
            <div>
                <a class="btn btn-accent" href="findByLoginId">아이디 찾기</a>
                <a class="btn btn-accent" href="findByLoginPw">비밀번호 찾기</a>
            </div>
        </div>
    </div>
</section>

<%@include file="../common/foot.jsp" %>
