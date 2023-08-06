<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="${board.name} 게시판"/>
<%@include file="../common/head.jsp" %>


<section class="mt-8">
    <div class="container mx-auto">
        <div class="table-box-type-1">
            <div class="mb-2">
                <span>총 : ${articleCnt}개</span>
            </div>
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
                <c:forEach var="article" items="${articles}">
                    <tr>
                        <td>${article.id}</td>
                        <td>${article.regDate.substring(2, 16)}</td>
                        <td><a class="hover:underline" href="detail?id=${article.id}">${article.title}</a></td>
                        <td>${article.writerName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <c:if test="${rq.loginedMemberId != 0}">
            <div class="flex mt-8 justify-end">
                <a class="btn btn-outline btn-accent w-60" href="write">Write</a>
            </div>
        </c:if>
    </div>
</section>

<%@include file="../common/foot.jsp" %>