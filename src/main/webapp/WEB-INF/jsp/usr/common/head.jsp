<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 테일윈드 불러오기 -->
    <!-- 노말라이즈, 라이브러리 -->
    <script src="https://unpkg.com/tailwindcss-jit-cdn"></script>
    <!-- 제이쿼리 불러오기 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <!-- 폰트어썸 불러오기 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"/>
    <link rel="stylesheet" href="/resource/common.css" type="text/css">
    <title>${pageTitle}</title>
</head>
<body>
<div class="h-20 container flex mx-auto text-4xl">
    <a class="px-3 flex items-center" href="">LoGo</a>
    <div class="flex-grow"></div>
    <ul class="flex">
        <li class="hover:underline"><a class="h-full px-3 flex items-center" href="/">HOME</a></li>
        <li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/article/list">LIST</a></li>
        <c:if test="${rq.getLoginedMemberId() == 0}">
        <li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/login">LOGIN</a></li>
        </c:if>
        <c:if test="${rq.getLoginedMemberId() != 0}">
            <li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/doLogOut">LOGOUT</a></li>
        </c:if>
    </ul>
</div>

<section class="my-3 text-2xl">
    <div class="container mx-auto px-3">
        <h1>${pageTitle}&nbsp;Page</h1>
    </div>
</section>
</body>
</html>
