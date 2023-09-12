<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.my.product.dto.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productlistresult.jsp</title>
<style>
	div.product {
		width:200px;
		display: inline-block;
	}
	div.product>ul{
		list-style-type: none;
	}
    div.product>ul>li>img{
		width:90%;
		border: 1px rgb(19, 63, 36);
		border-style: dotted solid dashed;
    }
    div.product>ul>li>span{
		width:90%;
		display:inline-block;
		text-align: center;
		font-weight: bold;
    }
    .productlist>h3{
		background: rgb(49, 80, 62);
		width:200px;
		color:white;
		text-align: right;
		margin: 10px auto;
	}
</style>
</head>
<body>
<% List<Product> list = (List)request.getAttribute("list");
	for(Product p : list) {
%>
<div>
	<ul>
		<li><img src="./images/<%=p.getProdNo()%>.jpg"></li>
		<li><%=p.getProdName()%></li>
	</ul>
</div>
<%	}
%>
</body>
</html>