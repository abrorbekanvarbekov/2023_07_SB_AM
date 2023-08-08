<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Detail"/>
<%@include file="../common/head.jsp" %>

<script>

    function ArticleDetail_increaseHitCnt(){
        $.get('doIncreaseVCnt', {

            id : ${article.id}

        }, function (data){

            $('#articleDetail_increaseHitCnt').empty().html(data.data1);

        }, 'json')
    }

    ArticleDetail_increaseHitCnt();
    // setTimeout(ArticleDetail_increaseHitCnt, 2000)
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
                </tbody>
            </table>
        </div>
        <div class="mt-8">
            <button onclick="history.back();" class="btn btn-outline btn-accent">뒤로가기</button>
            <c:if test="${article.memberId == rq.loginedMemberId}">
                <a class="btn btn-outline btn-accent" href="modify?id=${article.id}">Modify</a>
                <a class="btn btn-outline btn-accent" href="doDelete?id=${article.id}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">Delete</a>
            </c:if>
        </div>
    </div>
</section>

<%@include file="../common/foot.jsp" %>
