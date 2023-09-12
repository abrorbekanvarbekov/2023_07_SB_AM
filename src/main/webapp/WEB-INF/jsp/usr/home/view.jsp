<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
   <div class="flex">
       <c:forEach var="file" items="${fileVoList }">
           <div>
               <img src="/usr/home/file/${file.id}"/>
           </div>
       </c:forEach>
   </div>

    <div>
        <a href="/"><span>메인으로</span></a>
    </div>
</body>
</html>