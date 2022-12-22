<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_join.model.*"%>
<%@ page import="com.group_buy_item.model.*"%>
<%
// Integer gb_id = (Integer) request.getAttribute("gb_id");
Group_JoinVO group_joinVO = (Group_JoinVO) session.getAttribute("group_joinVO");
%>
<jsp:useBean id="Group_Buy_ItemService" scope="page"
	class="com.group_buy_item.model.Group_Buy_ItemService" />
<!DOCTYPE html>


<html>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.1/dist/umd/popper.min.js"
	integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
	integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
	crossorigin="anonymous"></script>
<title>參團</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}
/* .row { */
/* width:200PX; */
}
h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>參團資料查詢</h3>
				<h4>
					<a href="#">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>參團資料查詢</h3>



	<table>
		<tr>
			<td>購買商品:</td>
			<td>${gbitem_name}</td>
		</tr>
		<tr>
			<td>團購團編號:</td>
			<td>${group_joinVO.gb_id}</td>
		</tr>
		<tr>
			<td>會員編號:</td>
			<td>${group_joinVO.mem_id}</td>
		</tr>
		<tr>
			<td>團購付款狀態:</td>
			<td>${group_joinVO.gbpay_status==0 ? '未付款' : '已付款'}</td>
		</tr>
		<tr>
			<td>取貨狀態:</td>
			<td>${group_joinVO.pickup_status==0 ? '未取貨' : '已取貨'}</td>
		</tr>

		<tr>
			<td>物流狀態:</td>
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
		</tr>
		<tr>
			<td>購買數量:</td>
			<td>${group_joinVO.gbbuy_amount}</td>


		</tr>
	</table>