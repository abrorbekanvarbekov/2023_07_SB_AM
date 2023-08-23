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


    function modifyReply(id) {
        $('#modifyInput').removeClass("hidden")
        $('#doWriteInput').addClass("hidden")
        let replyBody = $('#replyBody_' + id).html()
        $('#afterReplyBody').html(replyBody)
        $('#replyBody').html(replyBody)
        $('#replyId').val(id);

        window.scrollTo(0, 400);
    }

    function doModifyReply() {
        $.ajax({
            url: '/usr/reply/doModify',
            data: {
                replyId: $('#replyId').val(),
                body: $('#replyBody').val()
            },
            method: "POST",
            success: function (data) {
                console.log(data);
                location.replace(data);
            },
            error: function (request, status, error) {
                console.log(error);
                console.log("aerror");
            },
            complete: function () {
                console.log("완료");
            }
        });

        function modifyCancel(){

        }
    }
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
        <h2>댓글 : ${replyCnt}개</h2>
        <c:if test="${rq.loginedMemberId != 0}">
            <%--            여긴 수정 input box--%>
            <div id="modifyInput" class="hidden mt-4 border border-gray-400 rounded-lg p-4 text-base">
                <div class="flex justify-start items-center">
                    <div class="px-2"><span class="material-symbols-outlined">stylus</span></div>
                    <div class="text-blue-600 text-lg">댓글 수정</div>
                </div>
                <div class="mb-2 px-2 mt-2" id="afterReplyBody"></div>
                <div class="">
                    <input type="hidden" id="replyId" name="replyId" value="">
                    <textarea class="textarea textarea-bordered w-full" id="replyBody" name="body"
                              placeholder="댓글을 남겨보세요..."></textarea>
                </div>
                <div class="mt-3 flex justify-end">
                    <button onclick="modifyCancal();" class="btn btn-accent btn-sm mr-2">취소</button>
                    <button onclick="doModifyReply();" class="btn btn-accent btn-sm">등록</button>
                </div>
            </div>
            <%--            여기까지 --%>

            <%--            여기 부터 doWrite input box--%>
            <form action="/usr/reply/doWrite" method="post">
                <div id="doWriteInput" class="mt-4 border border-gray-400 rounded-lg p-4 text-base ">
                    <div class="mb-2"><span>@${member.nickname}</span></div>
                    <div class="">
                        <input type="hidden" name="articleId" value="${article.id}">
                        <input type="hidden" name="relTypeCode" value="article">
                        <textarea class="textarea textarea-bordered w-full" name="replyBody"
                                  placeholder="댓글을 남겨보세요..."></textarea>
                    </div>
                    <div class="mt-3 flex justify-end">
                        <button class="btn btn-accent btn-sm">등록...</button>
                    </div>
                </div>
            </form>
            <%--            여기 까지--%>
        </c:if>
        <c:forEach var="reply" items="${replyList}">
            <div class="text-base py-2 pl-16 border-bottom-line">
                <div class="flex justify-between items-center">
                    <div class=""><span>@${reply.writerName}</span></div>
                    <c:if test="${reply.memberId == rq.loginedMemberId}">
                        <div class="dropdown">
                            <button class="btn btn-ghost btn-circle mr-6">
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                     class="inline-block w-5 h-5 stroke-current">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                          d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"></path>
                                </svg>
                            </button>
                            <ul tabindex="0"
                                class="menu menu-sm dropdown-content  z-[1] p-2 shadow bg-base-300 rounded-box w-28">
                                <li><a onclick="modifyReply(${reply.id});">수정</a></li>
                                <li><a href="../reply/doDelete?id=${reply.id}"
                                       onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a></li>
                            </ul>
                        </div>
                    </c:if>
                </div>
                <div class="my-1 text-lg pl-2"><span id="replyBody_${reply.id}">${reply.getForPrintBody()}</span></div>
                <div class="text-xs text-gray-400"><span>${reply.updateDate}</span></div>
            </div>
        </c:forEach>

        <div class="mt-8">
            <button onclick="history.back();" class="btn btn-outline btn-accent">뒤로가기</button>
        </div>
    </div>
</section>
<%@include file="../common/foot.jsp" %>
