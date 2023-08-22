<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Detail"/>
<%@include file="../common/head.jsp" %>

<script>

    function goodPoint() {
        $.get('../reactionPoint/doInsertReactionPoint', {
            relTypeCode: "article",
            relId: ${article.id},
            point: 1
        }, function (data) {

        }, 'json')
    }

    function badPoint() {
        $.get('../reactionPoint/doInsertReactionPoint', {
            relTypeCode: "article",
            relId: ${article.id},
            point: -1
        }, function (data) {

        }, 'json')
    }

    function deletePoint() {
        $.get('../reactionPoint/doDeleteReactionPoint', {
            relTypeCode: "article",
            relId: ${article.id},
        }, function (data) {

        }, 'json')
    }

    function getReactionPoint() {
        $.get('../reactionPoint/getReactionPoint', {
            relTypeCode: 'article',
            relId: ${article.id}
        }, function (data) {
            const point = data.data1.point;
            if (point == 1) {
                $('#goodPoint').removeClass("btn-outline");
                $('#goodPoint').attr("onclick", "deletePoint();");
            } else if (point == -1) {
                $('#badPoint').removeClass("btn-outline");
                $('#badPoint').attr("onclick", "deletePoint();");
            }

        }, 'json')
    }

    getReactionPoint();
</script>


<section class="mt-4 ">
    <div class="container mx-auto border-bottom-line py-4">
        <div class="table-box-type-1 ">
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
                    <td>${article.getForPrintBody()}</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${article.writerName}</td>
                </tr>
                <tr>
                    <th>조회수</th>
                    <td><span id="articleDetail_increaseHitCnt">${article.views}</span></td>
                </tr>
                <tr>
                    <th>추천</th>
                    <td>
                        <c:if test="${rq.loginedMemberId != 0}">
                            <div class="flex justify-center items-center mb-2">
                                <a onclick="goodPoint();" href=""
                                   id="goodPoint" class="btn btn-outline btn-accent btn-sm mr-3">
                                    <span class="material-symbols-outlined ">thumb_up</span>
                                </a>
                                <span>좋아요 : ${article.goodReactionPoint}</span>개
                            </div>
                        </c:if>
                        <c:if test="${rq.loginedMemberId != 0}">
                            <div class="flex justify-center items-center">
                                <a onclick="badPoint();" href="" id="badPoint"
                                   class="btn btn-outline btn-accent btn-sm mr-2">
                                    <span class="material-symbols-outlined ">thumb_down</span>
                                </a>
                                <span>싫어요 : ${article.badReactionPoint}</span>개
                            </div>
                        </c:if>
                        <c:if test="${rq.loginedMemberId == 0}">
                            <span>${article.sumReactionPoint}</span>
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="flex mt-8 justify-between">
            <div>
                <button onclick="history.back();" class="btn btn-outline btn-accent">뒤로가기</button>
                <c:if test="${article.memberId == rq.loginedMemberId}">
                    <a class="btn btn-outline btn-accent" href="modify?id=${article.id}">Modify</a>
                    <a class="btn btn-outline btn-accent" href="doDelete?id=${article.id}"
                       onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">Delete</a>
                </c:if>
            </div>
        </div>
    </div>
</section>

<section class="mt-4 mb-5">
    <div class="container mx-auto px-3 text-xl">
        <h2>댓글</h2>
        <c:forEach var="reply" items="${replyList}">
            <div class="text-base py-2 pl-16 border-bottom-line">
                <div class=""><span>@${reply.writerName}</span></div>
                <div class="my-1 text-lg pl-2"><span>${reply.getForPrintBody()}</span></div>
                <div class="text-xs text-gray-400"><span>${reply.updateDate}</span></div>
            </div>
        </c:forEach>
        <c:if test="${rq.loginedMemberId != 0}">
            <form action="/usr/reply/doWrite" method="post">
                <div class="mt-4 border border-gray-400 rounded-lg p-4 text-base">
                    <div class="mb-2"><span>@${rq.loginedMember.nickname}</span></div>
                    <div class="">
                        <input type="hidden" name="articleId" value="${article.id}">
                        <input type="hidden" name="relTypeCode" value="article">
                        <textarea class="textarea textarea-bordered w-full" name="replyBody" placeholder="댓글을 남겨보세요..."></textarea>
                    </div>
                    <div class="mt-3 flex justify-end">
                        <button class="btn btn-accent btn-sm">등록...</button>
                    </div>
                </div>
            </form>
        </c:if>
        <div class="mt-8">
            <button onclick="history.back();" class="btn btn-outline btn-accent">뒤로가기</button>
        </div>
    </div>
</section>
<%@include file="../common/foot.jsp" %>
