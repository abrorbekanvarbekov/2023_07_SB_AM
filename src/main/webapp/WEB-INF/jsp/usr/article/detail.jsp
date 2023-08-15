<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Detail"/>
<%@include file="../common/head.jsp" %>

<script>

    function getReactionPoint() {
        $.get('../reactionPoint/getReactionPoint', {
            relTypeCode: 'article',
            relId: ${article.id}
        }, function (data) {
            const point = data.data1.point;
            console.log(point);
            if (point == 1) {
                $('#goodPoint').removeClass("btn-outline");
            } else if (point == -1) {
                $('#badPoint').removeClass("btn-outline");
            }

        }, 'json')
    }

    getReactionPoint();


    function goodPoint() {
        $.get('reactionPoint', {
            relTypeCode: "article",
            relId: ${article.id},
            point: 1
        }, function (data) {
            console.log(data);
        }, 'json')

    }

    function badPoint() {

        $.get('reactionPoint', {
            relTypeCode: "article",
            relId: ${article.id},
            memberId: ${article.memberId},
            point: -1
        }, function (data) {
            console.log(data);
        }, 'json')

    }

</script>


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
                <tr>
                    <th>추천</th>
                    <td>
                        <c:if test="${rq.loginedMemberId != 0}">
                            <div class="flex justify-center items-center mb-2">
                                <a href="" id="goodPoint" class="btn btn-outline btn-accent btn-sm mr-3"
                                   onclick="goodPoint();">
                                    <span class="material-symbols-outlined ">thumb_up</span>
                                </a>
                                <span>좋아요 : ${article.goodReactionPoint}</span>개
                            </div>
                        </c:if>
                        <c:if test="${rq.loginedMemberId != 0}">
                            <div class="flex justify-center items-center">
                                <a href="" id="badPoint" class="btn btn-outline btn-accent btn-sm mr-2"
                                   onclick="badPoint();">
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
