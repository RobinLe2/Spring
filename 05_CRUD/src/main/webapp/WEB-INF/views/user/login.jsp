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

  <h1>로그인 화면</h1>
  
  <form action="${contextPath}/user/login"
        method="post">
    <label>아이디 <input type="text" name="email"></label><br>
    <label>비밀번호 <input type="password" name="password"></label><br>
    <!-- 로그인 성공 후 되돌아갈 주소(redirectURL)를 숨겨서 전달 -->
    <input type="hidden" name="redirectURL" value="${param.redirectURL != null ? param.redirectURL : '/'}">
    <button type="submit">로그인</button>
  </form>
  
  <c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
  </c:if>

</body>
</html>