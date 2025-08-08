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

  <h1>BBS 계층게시판</h1>
  
  <form method="post"
        action="${contextPath}/bbs/writeBbs">
    <textarea name="content"></textarea>
    <br>
    <button type="submit">게시글 등록</button>
  </form>
  
  <hr>
  
  <table border="1">
    <tbody>    
      <c:forEach items="${bbsList}" var="bbs">
      
      </c:forEach>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="1">
        
        </td>
      <tr>
    </tfoot>
  </table>

</body>
</html>