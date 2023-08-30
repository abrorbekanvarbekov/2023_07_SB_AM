<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Password Check"/>
<%@include file="../common/head.jsp" %>
<section class="mt-8">
    <div class="container mx-auto">
        <form action="doPasswordChk" method="POST">
            <div class="table-box-type-1">
                <table border="1">
                    <tbody>
                    <tr>
                        <th>LoginId</th>
                        <td>${rq.loginedMember.loginId}</td>
                    </tr>
                    <tr>
                        <th>loginPw</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text" placeholder="LoginPw" name="loginPw"></td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <button class="w-60 btn btn-outline btn-accent" type="submit">확인</button>
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
