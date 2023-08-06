<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="Modify"/>
<%@include file="../common/head.jsp" %>
<section class="mt-8">
    <div class="container mx-auto">
        <form action="doModify" method="POST">
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
                        <th>작성자</th>
                        <td>${article.writerName}</td>
                    </tr>

                        <input type="hidden" placeholder="제목을 입력해주세요" value="${article.id}"name="id">

                    <tr>
                        <th>Title</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" value="${article.title}" type="text" placeholder="제목을 입력해주세요"
                                   name="title">
                        </td>
                    </tr>
                    <tr>
                        <th>Body</th>
                        <td><input class="w-60 input input-bordered input-info w-full max-w-xs" value="${article.body}" type="text" placeholder="내용을 입력해주세요"
                                   name="body"></td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <button class="w-60 btn btn-outline btn-accent" type="submit"
                                    onclick="if(confirm('정말 수정하시겠습니까?') == false) return false;">Modify
                            </button>
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
