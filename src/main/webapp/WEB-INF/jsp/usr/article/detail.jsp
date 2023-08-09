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
                    <td><span class="badge badge-neutral">${article.id}</span></td>
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
                    <th>내용</th>
                    <td>${article.body}</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${article.writerName}</td>
                </tr>
                <tr>
                    <th>조회수</th>
                    <td><span id="articleDetail_increaseHitCnt">${article.views}</span></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="flex w-full justify-center mt-5">
            <c:if test="${reactionPointArticle == null}">
                <a href=""><span class="material-symbols-outlined mr-8 ">thumb_up</span></a>
                <a href=""><span class="material-symbols-outlined ml-8 ">thumb_down</span></a>
            </c:if>
            <c:if test="${reactionPointArticle.point == 1}">
                <a href=""><span class="material-symbols-outlined mr-8 text-red-600">thumb_up</span></a>
                <a href=""><span class="material-symbols-outlined ml-8 ">thumb_down</span></a>
            </c:if>
            <c:if test="${reactionPointArticle.point != 1}">
                <a href=""><span class="material-symbols-outlined mr-8 ">thumb_up</span></a>
                <a href=""><span class="material-symbols-outlined ml-8 text-red-600">thumb_down</span></a>
            </c:if>
        </div>
        <div class="mt-8">
            <button onclick="history.back();" class="btn btn-outline btn-accent">뒤로가기</button>
            <c:if test="${article.memberId == rq.loginedMemberId}">
                <a class="btn btn-outline btn-accent" href="modify?id=${article.id}">Modify</a>
                <a class="btn btn-outline btn-accent" href="doDelete?id=${article.id}"
                   onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">Delete</a>
            </c:if>
        </div>
    </div>
</section>

<%@include file="../common/foot.jsp" %>
