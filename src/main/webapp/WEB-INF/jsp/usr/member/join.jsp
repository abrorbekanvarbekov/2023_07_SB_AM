<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Join"/>
<%@include file="../common/head.jsp" %>
<section class="mt-8">
    <div class="container mx-auto">
        <form action="doJoin" method="get">
            <div class="table-box-type-1">
                <table border="1">
                    <tbody>
                    <tr>
                        <th>LoginId</th>
                        <td>
                            <input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="LoginId" name="loginId">
                        </td>
                    </tr>
                    <tr>
                        <th>loginPw</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="LoginPw" name="loginPw"></td>
                    </tr>
                    <tr>
                        <th>LoginPwCheck</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="loginPwCheck" name="loginPwCheck"></td>
                    </tr>
                    <tr>
                        <th>Name</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="name" name="name"></td>
                    </tr>
                    <tr>
                        <th>Nickname</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="nickname" name="nickname"></td>
                    </tr>
                    <tr>
                        <th>CellphoneNum</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="cellphoneNum" name="cellphoneNum"></td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="email" name="email"></td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <button class="w-60 btn btn-outline btn-accent" type="submit">Login
                            </button>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </form>
        <div class="mt-8">
            <button onclick="history.back();" class="btn btn-outline btn-accent">뒤로가기</button>
            <a  class="btn btn-outline btn-accent" href="login">Login PAge</a>
        </div>
    </div>
</section>

<%@include file="../common/foot.jsp" %>
<%--<div class="table-box-type-1">--%>
<%--    <form action="doJoin" method="get">--%>
<%--        <div>--%>
<%--            <label for="loginId">LoginId</label>--%>
<%--            <input type="text" placeholder="LoginId" name="loginId" id="loginId">--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <label for="loginPw">LoginPw</label>--%>
<%--            <input type="text" placeholder="LoginPw" name="loginPw" , id="loginPw">--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <label for="loginPwCheck">LoginPwCheck</label>--%>
<%--            <input type="text" placeholder="LoginPwCheck" name="loginPwCheck" id="loginPwCheck">--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <label for="name">Name</label>--%>
<%--            <input type="text" placeholder="Name" name="name" id="name">--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <label for="nickname">Nickname</label>--%>
<%--            <input type="text" placeholder="Nickname" name="nickname" id="nickname">--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <label for="cellphoneNum">CellphoneNum</label>--%>
<%--            <input type="text" placeholder="CellphoneNum" name="cellphoneNum" id="cellphoneNum">--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <label for="email">Email</label>--%>
<%--            <input type="text" placeholder="Email" name="email" id="email">--%>
<%--        </div>--%>
<%--        <div class="mt-8 mr-8">--%>
<%--            <button class="mr-8" type="submit">JOin</button>--%>
<%--            <a href="/usr/member/login">LoginPage</a>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>