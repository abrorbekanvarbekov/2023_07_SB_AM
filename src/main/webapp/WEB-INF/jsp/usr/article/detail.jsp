<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Detail"/>
<%@include file="../common/head.jsp" %>

<section class="mt-8">
    <div class="container mx-auto">
        <div class="table-box-type-1">
            <table border="1">
                <tbody>
                <tr>
                    <th>번호</th>
                    <td>${article.id}</td>
                </tr>
                <tr>
                    <th>작성일</th>
                    <td>${article.regDate}</td>
                </tr>
                <tr>
                    <th>수정일</th>
                    <td>${article.updateDate}</td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>${article.title}</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${article.writerName}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="mt-8">
            <button onclick="history.back();" class="btn-text-link">뒤로가기</button>
            <a class="btn-text-link" href="modify?id=${article.id}">Modify</a>
            <a class="btn-text-link" href="doDelete?id=${article.id}">Delete</a>
        </div>
    </div>
</section>

<%@include file="../common/foot.jsp" %>