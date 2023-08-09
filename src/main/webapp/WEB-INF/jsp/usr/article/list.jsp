<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="${board.name} 게시판"/>
<%@include file="../common/head.jsp" %>


<section class="mt-8">
    <div class="container mx-auto">
        <div class="table-box-type-1">
            <div class="flex justify-between mb-2">
                <div class="flex items-center">
                    <span>총 : ${articleCnt}개</span>
                </div>
                <div class="flex justify-between w-2/4">
                    <form action="" method="get" class="flex justify-between w-full">
                        <input type="hidden" value="${board.id}" name="boardId">
                        <select data-value="${selectKey}" class="select select-warning w-2/5 max-w-xs" name="selectKey">
                            <option value="title">제목</option>
                            <option value="body">내용</option>
                            <option value="title, body">제목 + 내용</option>
                        </select>
                        <div class="flex w-full justify-end">
                            <input type="search" value="${searchKeyword}" name="searchKeyword" placeholder="검색어를 입력하세요"
                                   class="input input-bordered input-success w-4/5 max-w-xs w-2/4">
                            <button class="btn btn-active btn-accent w-1/5" type="submit">검색</button>
                        </div>
                    </form>
                </div>
            </div>
            <table border="1">
                <thead>
                <tr>
                    <th>번호</th>
                    <th>작성일</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>추천</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="article" items="${articles}">
                    <tr>
                        <td>${article.id}</td>
                        <td>${article.regDate.substring(2, 16)}</td>
                        <td><a class="hover:underline" href="detail?id=${article.id}">${article.title}</a></td>
                        <td>${article.writerName}</td>
                        <td>${article.sumReactionPoint}</td>
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

        <div class="mt-2 flex justify-center">
            <div class="join">
                <c:set var="pageMenuLen" value="5"/>
                <c:set var="startPage" value="${page - pageMenuLen > 1 ? page - pageMenuLen : 1}"/>
                <c:set var="endPage" value="${page + pageMenuLen < totalPage ?  page + pageMenuLen : totalPage}"/>
                <c:set var="pageBaseUri" value="list?boardId=${board.id}&searchKeyword=${searchKeyword}&selectKey=${selectKey}"/>

                <c:if test="${page == 1}">
                    <a class="join-item btn btn-disabled">«</a>
                    <a class="join-item btn btn-disabled">&lt;</a>
                </c:if>
                <c:if test="${page > 1}">
                    <a class="join-item btn" href="${pageBaseUri}&page=1">«</a>
                    <a class="join-item btn" href="${pageBaseUri}&page=${page - 1}">&lt;</a>
                </c:if>


                <c:forEach var="i" begin="${startPage}" end="${endPage}">
                    <a class="join-item btn btn-md ${page == i ? 'btn-active' : ''}"
                       href="${pageBaseUri}&page=${i}">${i}</a>
                </c:forEach>

                <c:if test="${page < totalPage}">
                    <a class="join-item btn" href="${pageBaseUri}&page=${page + 1}">&gt;</a>
                    <a class="join-item btn" href="${pageBaseUri}&page=${totalPage}">»</a>
                </c:if>
                <c:if test="${page == totalPage}">
                    <a class="join-item btn btn-disabled">»</a>
                    <a class="join-item btn btn-disabled">&gt;</a>
                </c:if>
            </div>
        </div>
    </div>
</section>

<%@include file="../common/foot.jsp" %>