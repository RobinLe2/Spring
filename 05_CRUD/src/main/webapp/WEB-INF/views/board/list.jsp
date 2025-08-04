<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="redirectURL" value="${pageContext.request.requestURI}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>

  <h1>Board List</h1>
  
  <table border="1">
    <caption>${boardCount}개 게시글이 있습니다.</caption>
    <tbody>
      <c:forEach items="${boards}" var="board">
        <tr>
          <td><a href="${contextPath}/board/detail?bid=${board.bid}">${board.title}</a></td>
          <td>${board.user.nickname}</td>
          <td><fmt:formatDate value="${board.createdAt}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  
  <a href="${contextPath}/board/write">작성</a>

  <script type="text/javascript">
  	const msg = "${msg}";
  	if (msg !== "") alert(msg);
  </script>

</body>
</html>