<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <a href="">LoGo</a>
        <ul>
            <li><a href="/">HOME</a></li>
            <li><a href="/usr/article/list">LIST</a></li>
        </ul>
    </div>
    <h1>Article Detail Page</h1>
    <table border="1">
        <thead>
        <tr>
            <th>번호</th>
            <th>작성일</th>
            <th>제목</th>
            <th>작성자</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${article.id}</td>
                <td>${article.regDate}</td>
                <td>${article.title}</td>
                <td>${article.memberId}</td>
            </tr>
        </tbody>
    </table>
</body>
</html>
