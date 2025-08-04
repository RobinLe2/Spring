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

  <h1>Board Write</h1>
  
  <form method="post"
        action="${contextPath}/board/write">
  
    <div>작성자 : ${sessionScope.nickname}</div>
    
    <label>제목 <input type="text" name="title" autofocus></label>
    <br>
    <textarea name="content">Editor 필요</textarea>
    <br>
    
    <button type="submit">등록</button>
    
  </form>

</body>
</html>