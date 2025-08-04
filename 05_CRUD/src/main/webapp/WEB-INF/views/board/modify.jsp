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

  <h1>Board Modify</h1>
  
  <form method="post"
        action="${contextPath}/board/modify">
  
    <div>작성자 : ${board.user.nickname}</div>
    
    <label>제목 <input type="text" name="title" value="${board.title}" autofocus></label>
    <br>
    <textarea name="content">${board.content}</textarea>
    <br>
    
    <input type="hidden" name="bid" value="${board.bid}">
    
    <button type="submit">수정</button>
    <button type="button" onclick="onDelete()">삭제</button>
    
  </form>
  
  <script type="text/javascript">
    function onDelete() {
      if (confirm("현재 게시글을 삭제할까요?")) {
        location.href = "${contextPath}/board/delete?bid=${board.bid}";
      }
    }
  </script>
  
</body>
</html>