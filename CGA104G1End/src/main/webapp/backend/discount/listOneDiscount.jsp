<%@page import="com.discount.model.DiscountVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
DiscountVO discountVO = (DiscountVO) request.getAttribute("DiscountVO");
%>
<html>
<head>
<title>團購折扣資料 - listOneDiscount.jsp</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 20%;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
	color: black;
}

th{
	color: white;
}

tr:nth-child(even) {
	background-color: rgba(255,255,255,0.4);
}
</style>

</head>
<body bgcolor='white'>


<!-- 複製起點 -->
	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
<!-- 		把原本body的東西貼到這邊 -->
<div class="titleBlock">團購折扣查詢結果</div>

	<table>
		<tr>
			<th>折扣編號</th>
			<th>團購商品編號</th>
			<th>商品數量下限</th>
			<th>商品數量上限</th>
			<th>折扣價格</th>
			<th>折扣說明</th>
		</tr>
		<tr>
			<td><%=discountVO.getDiscount_id()%></td>
			<td><%=discountVO.getGbitem_id()%></td>
			<td><%=discountVO.getDiscount_minamount()%></td>
			<td><%=discountVO.getDiscount_maxamount()%></td>
			<td><%=discountVO.getDiscount_price()%></td>
			<td><%=discountVO.getDiscount_nar()%></td>
<%-- 			<td>${DiscountVO.discount_id}</td> --%>
<%-- 			<td>${DiscountVO.gbitem_id}</td> --%>
<%-- 			<td>${DiscountVO.discount_minamount}</td> --%>
<%-- 			<td>${DiscountVO.discount_maxamount}</td> --%>
<%-- 			<td>${DiscountVO.discount_price}</td> --%>
<%-- 			<td>${DiscountVO.discount_nar}</td> --%>
		</tr>
	</table>
		</section>
	</main>
<!-- 複製終點 -->



	

</body>
</html>