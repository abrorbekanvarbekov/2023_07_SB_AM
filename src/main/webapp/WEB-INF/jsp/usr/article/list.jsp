<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Article List</title>
</head>
<body>
    <div>
        <a href="">LoGo</a>
        <ul>
            <li><a href="/">HOME</a></li>
            <li><a href="/usr/article/list">LIST</a></li>
        </ul>
    </div>
  <h1>게시물 리스트</h1>

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
          <c:forEach var="article" items="${articles}">
          <tr>
              <td>${article.id}</td>
              <td>${article.regDate}</td>
              <td><a href="detail?id=${article.id}">${article.title}</a></td>
              <td>${article.memberId}</td>
          </tr>
          </c:forEach>
      </tbody>
  </table>
</body>
</html>
