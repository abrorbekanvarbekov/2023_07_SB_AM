<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Join"/>
<%@include file="../common/head.jsp" %>

<script>
    // ====================================================== //
    validLoginId = ""
    function loginIdChk(el) {
        let loginIdMsg = $('#loginIdMsg')

        loginIdMsg.empty();
        el.value = el.value.trim();

        if (el.value.length == 0){
            loginIdMsg.html("<span>아이디는 필수 입력 정보 입니다</span>");
            return;
        }

        $.get('loginIdCheck', {
            loginId: el.value
        }, function (data) {
            let msg = data.msg
            if (data.fail){
                loginIdMsg.html(msg)
                validLoginId = ""
            }else{
                loginIdMsg.html(`<span class='text-green-600'>\${data.data1}은(는) \${msg}</span>`);
                validLoginId = data.data1
            }
        }, "json")
    }

    // ====================================================== //

    function isLoginPwEquals(el) {
        let loginPw = $('#loginPwI')
        let loginPwChk = el.value.trim();

        $('#loginPwChkMsg').empty();

        if (loginPw.val() != loginPwChk) {
            loginPw.focus();
            $('#loginPwChkMsg').html("비밀번호를 확인해주세요!")
        }
    }

    // ====================================================== //

    function join_submitForm(form) {
        form.loginId.value = form.loginId.value.trim();
        $('#loginIdMsg').empty()

        if(form.loginId.value.length == 0){
            $('#loginIdMsg').html("아이디: 5 ~ 20자 이상여야 합니다")
            form.loginId.focus();
            return
        }

        if (form.loginId.value != validLoginId){
            $('#loginIdMsg').html(form.loginId.value + `은(는) 이미 사용중인 아이디 입니다`)
            form.loginId.value = ""
            form.loginId.focus();
            return;
        }

        form.loginPw.value = form.loginPw.value.trim();
        $('#loginPwMsg').empty()

        if(form.loginPw.value.length == 0){
            $('#loginPwMsg').html("비밀번호를 입력하세요!")
            form.loginPw.focus();
            return
        }

        form.loginPwCheck.value = form.loginPwCheck.value.trim();
        $('#loginPwChkMsg').empty()

        if(form.loginPwCheck.value.length == 0){
            $('#loginPwChkMsg').html("비밀번호 확인을 입력하세요!")
            form.loginPwCheck.focus();
            return
        }

        form.name.value = form.name.value.trim();
        $('#nameMsg').empty()

        if(form.name.value.length == 0){
            $('#nameMsg').html("이름을 입력하세요!")
            form.name.focus();
            return
        }

        form.nickname.value = form.nickname.value.trim();
        $('#nicknameMsg').empty()

        if(form.nickname.value.length == 0){
            $('#nicknameMsg').html("닉네임을 입력하세요!")
            form.nickname.focus();
            return
        }

        form.cellphoneNum.value = form.cellphoneNum.value.trim();
        $('#cellphoneNumMsg').empty()

        if(form.cellphoneNum.value.length == 0){
            $('#cellphoneNumMsg').html("전화번호를 입력하세요!")
            form.cellphoneNum.focus();
            return
        }

        form.email.value = form.email.value.trim();
        $('#emailMsg').empty()

        if(form.email.value.length == 0){
            $('#emailMsg').html("이메일을 입력하세요!")
            form.email.focus();
            return
        }

        form.submit();
    }

    // ====================================================== //
</script>

<section class="mt-8">
    <div class="container mx-auto">
        <form action="doJoin" method="get" onsubmit="join_submitForm(this); return false;">
            <div class="table-box-type-1">
                <table border="1">
                    <tbody>
                    <tr>
                        <th>LoginId</th>
                        <td>
                            <input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="LoginId" name="loginId" id="loginId" onblur="loginIdChk(this);">
                            <div class="mt-2 text-red-600" id="loginIdMsg"></div>
                        </td>
                    </tr>
                    <tr>
                        <th>loginPw</th>
                        <td>
                            <input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="LoginPw" id="loginPwI" name="loginPw">
                            <div class="mt-2 text-red-600" id="loginPwMsg"></div>
                        </td>

                    </tr>
                    <tr>
                        <th>LoginPwCheck</th>
                        <td>
                            <input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="loginPwCheck" id="loginPwChk" name="loginPwCheck"
                                   onblur="isLoginPwEquals(this);">
                            <div class="mt-2 text-red-600" id="loginPwChkMsg"></div>
                        </td>
                    </tr>
                    <tr>
                        <th>Name</th>
                        <td>
                            <input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="name" name="name" id="name">
                            <div class="mt-2 text-red-600" id="nameMsg"></div>
                        </td>
                    </tr>
                    <tr>
                        <th>Nickname</th>
                        <td>
                            <input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="nickname" name="nickname" id="nickname">
                            <div class="mt-2 text-red-600" id="nicknameMsg"></div>
                        </td>
                    </tr>
                    <tr>
                        <th>CellphoneNum</th>
                        <td>
                            <input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="cellphoneNum" name="cellphoneNum" id="cellphoneNum">
                            <div class="mt-2 text-red-600" id="cellphoneNumMsg"></div>
                        </td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td>
                            <input class="w-60 input input-bordered input-info w-full max-w-xs" type="text"
                                   placeholder="email" name="email" id="email">
                            <div class="mt-2 text-red-600" id="emailMsg"></div>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <button class="w-60 btn btn-outline btn-accent" type="submit">Join
                            </button>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </form>
        <div class="mt-8">
            <button onclick="history.back();" class="btn btn-outline btn-accent">뒤로가기</button>
            <a class="btn btn-outline btn-accent" href="login">Login PAge</a>
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