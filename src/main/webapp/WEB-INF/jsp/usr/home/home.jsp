<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Main"/>
<%@include file="../common/head.jsp" %>

<section class="mt-8">
    <div class="container mx-auto px-10">
        <div>
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Doloribus, quibusdam odit. Nihil maiores voluptate, voluptatum, voluptatibus quis error pariatur et voluptates numquam facilis unde, corrupti tempore debitis ab dolore cupiditate.
        </div>
        <div>안녕하세요</div>
        <div>
            <img src="/resource/images/a.png" class="w-80" alt="이미지 나옵니다">
        </div>

        <div>
            <form action="fileUpload" method="post" enctype="multipart/form-data">
                <input type="file" name="file">
                <button>Upload</button>
            </form>
        </div>

        <div>
            <a href="/usr/home/view">파일 불러오기</a>
        </div>
        <div class="mt-6 flex justify-between">
            <span class="btn btn-accent btn-sm modal-exam">모달 예시</span>
            <span class="btn btn-accent btn-sm popUp-exam">팝업 예시</span>
        </div>

        <div class="layer-bg"></div>
        <div class="layer">
            <h1>MODAL</h1>
            <span id="close-x-btn" class="material-symbols-outlined">close</span>
            <div>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet architecto atque cum dignissimos dolorum expedita facere facilis fuga ipsam, minus natus neque officiis perferendis quae, quisquam quod ratione repellat voluptatibus.</div>
            <button id="close-btn" class="btn btn-accent mt-2">Close</button>
        </div>

    </div>
</section>

<%@include file="../common/foot.jsp" %>
