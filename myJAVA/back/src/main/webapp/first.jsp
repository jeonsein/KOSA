<%@ page import="java.io.FileNotFoundException"%>
<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page buffer="1024kb" %>
<%@ page errorPage="err.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>SENGNA's</h1> FIRST JSP입니다.
<%int i; // 지역변수, _jspService() 내부에 들어감%>
<%out.print(new Date());%>
<hr>
<%=new Date()%>
<hr>
<%!int i; // 멤버변수%>
<%i=99;%>
지역변수 i: <%=i%>, 멤버변수 i: <%=this.i%>
<hr>
<%for(int j=1; j<=1000; j++) { %>
<span><%=j%></span>
<%} %>
<hr>
<%
//try {
//	FileInputStream fis = null;
//	fis = new FileInputStream("a.txt");
//} catch(FileNotFoundException e) {
%><%-- <h3> <%=e.getMessage()%> </h3> --%>
<%
//	RequestDispatcher rd = request.getRequestDispatcher("err.jsp");
//	rd.forward(request, response);
//}
%>
<%
FileInputStream fis = null;
fis = new FileInputStream("a.txt");
%>
</body>
</html>