<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_join.model.*"%>
<%@include file="/backend/backNavbar.jsp"%>
<jsp:useBean id="group_buySvc" scope="page" class="com.group_buy.model.Group_BuyService" />
<html>
<head>
<title>參團查詢後資料</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>

<style>

.styled-table {
	margin-left: auto;
	margin-right: auto;
	border-collapse: collapse;
	margin: auto;
	font-size: 0.9em;
	font-family: sans-serif;
	min-width: 400px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.styled-table thead tr {
	background-color: #212529;
	color: #ffffff;
	text-align: left;
}

.styled-table th, .styled-table td {
	padding: 12px 15px;
}

.styled-table tbody tr {
	border-bottom: 1px solid #dddddd;
}

.styled-table tbody tr:nth-of-type(even) {
	background-color: #f3f3f3;
}

.styled-table tbody tr:last-of-type {
	border-bottom: 2px solid #212529;
}

.styled-table tbody tr.active-row {
	font-weight: bold;
	color: #212529;
}

table#table-1 {
	background-color: #212529;
	border: 2px solid black;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	width: 800px;
	margin-top: 5px;
	margin-bottom: 5px;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h3 {
	color: #6c757d;
}

h4 {
	color: blue;
	display: inline;
}
table {
	margin-left: auto;
	margin-right: auto;
	width: 800px;
	margin-top: 5px;
	margin-bottom: 5px;
}
th, td {
	padding: 5px;
	text-align: center;
}

</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<th><h3>參團資料</h3>
				<h4>
					<a class="btn btn-light" href="backend/group_join/select_page.jsp">參團資料查詢首頁</a>
				</h4></th>
		</tr>
	</table>

	<!-- ===========================================樣式欄位================================================================== -->

	<table class="styled-table">
		<thead>
			<tr>
				<th>團購團名稱</th>
				<th>團購團編號</th>
				<th>會員編號</th>
				<th>團購付款狀態</th>
				<th>取貨狀態</th>
				<th>物流狀態</th>
				<th>購買數量</th>
				<th>價格</th>
				<th colspan="2">變更資料</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="group_joinVO" items="${list_group_join}">
				<tr class="active-row" align='center' valign='middle'>
					<td>${group_joinVO.group_BuyVO.gb_name}</td>
					<td>${group_joinVO.gb_id}</td>
					<td>[${group_joinVO.mem_id}]-${group_joinVO.memVO.mem_name}</td>
					<td>${group_joinVO.gbpay_status==0 ? '未付款':'已付款'}</td>
					<td>${group_joinVO.pickup_status==0 ? '未取貨':'已取貨'}</td>
					<c:if test="${group_joinVO.deliver_status==0}">
				<td><c:out value="未出貨"></c:out></td>
					</c:if>
					<c:if test="${group_joinVO.deliver_status==1}">
				<td><c:out value="已出貨"></c:out></td>
					</c:if>
					<c:if test="${group_joinVO.deliver_status==2}">
				<td><c:out value="配送中"></c:out></td>
					</c:if>
					<c:if test="${group_joinVO.deliver_status==3}">
				<td><c:out value="已送達"></c:out></td>
					</c:if>
					<td>${group_joinVO.gbbuy_amount}</td>
					<td>${group_joinVO.gbbuy_price}</td>
					<td>
						<FORM METHOD="post" ACTION="/CGA104G1/Group_Join_backServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="gb_id" value="${group_joinVO.gb_id}"> 
								<input type="hidden"
								name="mem_id" value="${group_joinVO.mem_id}"><input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="/CGA104G1/Group_Join_backServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> <input type="hidden"
								name="gb_id" value="${group_joinVO.gb_id}"> 
								<input type="hidden"
								name="mem_id" value="${group_joinVO.mem_id}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</body>
</html>