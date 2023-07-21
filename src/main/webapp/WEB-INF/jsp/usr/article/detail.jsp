<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="pageTitle" value="Main"/>
<%@include file="../common/head.jsp" %>
    <table border="1">
        <thead>
        <tr>
            <th>번호</th>
            <th>작성일</th>
            <th>제목</th>
            <th>작성자</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${article.id}</td>
                <td>${article.regDate}</td>
                <td>${article.title}</td>
                <td>${article.memberId}</td>
            </tr>
        </tbody>
    </table>
<%@include file="../common/foot.jsp" %>
