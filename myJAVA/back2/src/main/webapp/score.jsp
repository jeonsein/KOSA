<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>score.jsp</title>
</head>
<body>
<%!
int totalScore=0;
int totalCnt=0;
%>
<%String star = request.getParameter("star");%>
선택한 별점은 <%=star%>점입니다.
<%
totalScore += Integer.parseInt(star);
totalCnt++;
%>
<hr>
총점은 <%=totalScore%>점입니다.<br>
참여인원은 <%=totalCnt%>명입니다.<br>
평점은 <%=(float)totalScore/totalCnt%>
</body>
</html>