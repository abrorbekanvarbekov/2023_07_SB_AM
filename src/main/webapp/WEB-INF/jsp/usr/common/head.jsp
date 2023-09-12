<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html data-theme="light">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="shortcut icon" href="/resource/images/favicon.ico">
    <!-- 테일윈드 불러오기 -->
    <!-- 노말라이즈, 라이브러리 -->
    <link href="https://cdn.jsdelivr.net/npm/daisyui@3.5.0/dist/full.css" rel="stylesheet" type="text/css"/>
    <script src="https://cdn.tailwindcss.com"></script>
    <%--    alert 불러 오기--%>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <!-- 제이쿼리 불러오기 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <!-- 폰트어썸 불러오기 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"/>
    <%-- google Icons   --%>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <link rel="stylesheet" href="/resource/common.css" type="text/css">
    <script src="/resource/common.js" defer="defer"></script>
    <title>${pageTitle}</title>

    <script>
        function Theme_Toggle(){
            const theme = localStorage.getItem("theme") ?? "light";
            if(theme == "light"){
                localStorage.setItem("theme", "dark")
            }else {
                localStorage.setItem("theme", "light")
            }

            location.reload();
        }

        function Theme_applyTo(themeName){
            $('html').attr('data-theme', themeName)
        }

        function Theme_init(){
            const theme = localStorage.getItem("theme") ?? "light";
            Theme_applyTo(theme);
        }

        Theme_init();
    </script>
</head>
<body>
<div class="h-20 container flex mx-auto text-3xl mt-2 px-10">
    <a class="px-3 flex items-center" href="">LoGo</a>
    <div class="flex-grow"></div>
    <ul class="flex">
        <li>
            <a href="javascript:Theme_Toggle();" class="h-full theme-toggle px-3 flex items-center">
                <span><i class="fa-regular fa-sun"></i></span>
                <span><i class="fa-solid fa-sun"></i></span>
            </a>
        </li>
        <li class="hover:underline"><a class="h-full px-3 flex items-center" href="/">HOME</a></li>
        <li class="hover:underline"><a class="h-full px-3 flex items-center"
                                       href="/usr/article/list?boardId=1">F_LIST</a></li>
        <li class="hover:underline"><a class="h-full px-3 flex items-center"
                                       href="/usr/article/list?boardId=2">N_LIST</a></li>
        <c:if test="${rq.getLoginedMemberId() == 0}">
            <li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/login">LOGIN</a></li>
        </c:if>
        <c:if test="${rq.getLoginedMemberId() != 0}">
            <li class="hover:underline"><a href="/usr/member/myPage"class="h-full px-3 flex items-center">MyPAGE</a></li>
            <li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/doLogOut">LOGOUT</a></li>
        </c:if>
        <li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/api/APITest">API</a></li>
        <li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/api/APITest2">API2</a></li>
    </ul>
</div>

<section class="my-3 text-2xl ">
    <div class="container mx-auto px-10">
        <h1>${pageTitle}</h1>
    </div>
</section>
</body>
</html>
