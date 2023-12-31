<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Writer"/>

<%@include file="../common/head.jsp" %>
<%@include file="../common/toastUIEditorLib.jsp" %>

<section class="mt-8">
    <div class="container mx-auto">
        <form action="doWrite" method="POST" onsubmit="submitForm(this); return false;">
            <input type="hidden" name="body">
            <div class="table-box-type-1">
                <table border="1">
                    <tbody>
                    <tr>
                        <th>게시판</th>
                        <td>
                            <label>
                                <input type="radio" name="boardId" value="2">
                                &nbsp;공지사항
                            </label>
                            &nbsp;&nbsp;&nbsp;
                            <label>
                                <input type="radio" name="boardId" value="1" checked>
                                &nbsp;자유
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <th>Title</th>
                        <td>
                            <input class="input input-bordered input-info w-full" type="text"
                                   placeholder="제목을 입력해주세요" name="title">
                        </td>
                    </tr>
                    <tr>
                        <th>Body</th>
                        <td>
                            <div class="toast-ui-editor">
                                <script type="text/x-template"></script>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <button class="w-60 btn btn-outline btn-accent" type="submit">Write</button>
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