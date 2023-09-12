<%@ page import="com.my.product.dto.PageGroup"%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
$(() => {
	$('.productlist>.pagegroup>span').click((e) => {
		alert($(e.target).html() + ": " + $(e.target).attr('class') + " 페이지가 클릭되었습니다.")
	})
})
</script>
</head>
<body>
<div class="productlist">
<h3>상품목록</h3>
<%
PageGroup<Product> pb = (PageGroup)request.getAttribute("pb");
// List<Product> list = (List)request.getAttribute("list");
List<Product> list = pb.getList();
for(Product p : list) {
%>
	<div class="product">
		<ul>
			<li><img src="./images/<%=p.getProdNo()%>.jpg"></li>
			<li><%=p.getProdName()%></li>
		</ul>
	</div>
<%	}%>
	<div class="pagegroup">
		<%
		int startPage = pb.getStartPage();
		int endPage = pb.getEndPage();
		int totalPage = pb.getTotalPage();
		
		if(startPage > 1) {
		%> [<span class="pg<%=startPage-1 %>">PREV</span>]&nbsp;&nbsp;&nbsp;	
		<%}
		
		for(int i = startPage; i <= endPage; i++) {
		%> [<span class="pg<%=i%>"><%=i%></span>]&nbsp;&nbsp;&nbsp;	
		<%}
		
		if(endPage != totalPage) {
			// endPage < totalPage
		%> [<span class="pg<%=endPage+1%>">NEXT</span>]
		<%}
		%>
	</div>
</div>
</body>
</html>