<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Password Modify"/>
<%@include file="../common/head.jsp" %>
<section class="mt-8">
    <div class="container mx-auto">
        <form action="doPasswordModify" method="POST">
            <div class="table-box-type-1">
                <table border="1">
                    <tbody>
                    <tr>
                        <th>새 비밀번호</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text" placeholder="새 비밀번호를 입력해주세요" name="loginPw"></td>
                    </tr>
                    <tr>
                        <th>새 비밀번호 확인</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text" placeholder="새 비밀번호 확인을 입력해주세요" name="loginPwChk"></td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <button class="w-60 btn btn-outline btn-accent" type="submit">변경</button>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </form>
        <div class="mt-8">
            <button onclick="history.back();" class="btn btn-outline btn-accent">뒤로가기</button>
        </div>
    </div>
</section>

<%@include file="../common/foot.jsp" %>
