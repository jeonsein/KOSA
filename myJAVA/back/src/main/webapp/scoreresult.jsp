<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
선택한 별점은 <%=request.getParameter("star")%>점입니다.
<hr>
총점은 <%=request.getAttribute("ts")%>점입니다.<br>
참여인원은 <%=request.getAttribute("tc")%>명입니다.<br>
<hr>
평점은 <%=request.getAttribute("as")%>점입니다.
</body>
</html>