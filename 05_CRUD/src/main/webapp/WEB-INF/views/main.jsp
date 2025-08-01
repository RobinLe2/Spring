<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>

  <c:if test="${empty sessionScope.nickname}">
    <a href="${contextPath}/user/login?currentURL=${currentURL}">로그인</a>
  </c:if>
  <c:if test="${not empty sessionScope.nickname}">
    <p>${sessionScope.nickname}님 반갑습니다</p>
    <a href="${contextPath}/user/logout">로그아웃</a>
  </c:if>

  <hr>

  <h1>Hello World</h1>
  
</body>
</html>