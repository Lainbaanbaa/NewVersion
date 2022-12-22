<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_join.model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
width: 830px;
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


.styled-table tbody tr:last-of-type {
	border-bottom: 2px solid #212529;
}

.styled-table tbody tr.active-row {
	font-weight: bold;
	color: #212529;
}

.ok{
float:right;
margin-right:162px;
margin-top: 13px; 
}
.back{
float:left;
margin-left:162px;
margin-top: 13px; 


}

/* <!-- ===========================================樣式欄位================================================================== --> */


h3 {
	color: #6c757d;
}

h4 {
	color: blue;
	display: inline;
}

</style>

<style>
table {
	margin-left: auto;
	margin-right: auto;
	width: 95%;
	margin-top: 5px;
	margin-bottom: 5px;
}


th, td {

	padding: 5px;
	text-align: center;
	font-size: 10px;
}

.btnTitle{
	margin-bottom: 10px;
}

.titleIn {
	font-size: 24px;
	font-weight: 700;
}

.status {
	width: 60px;
}

tr:nth-child(even) {
	background-color: rgba(255,255,255,0.4);
}
</style>

</head>
<body>
<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
			<table class="styled-table">
		<thead>
			<tr>
				<th>團購團編號</th>
				<th>會員編號</th>
				<th>團購付款狀態</th>
				<th>取貨狀態</th>
				<th>物流狀態</th>
				<th>購買數量</th>
				<th>價格</th>
				<th>繳費確認</th>
				<th>物流確認</th>
				<th>取貨確認</th>
				<th colspan="2">數量更改或取消參團</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="group_joinVO" items="${list_group_join}">
				<tr class="active-row" align='center' valign='middle'>
					<%-- 					<td>${group_joinVO.emp_id}</td> --%>
					<td>[${group_joinVO.gb_id}]-${group_joinVO.group_BuyVO.gb_name}</td>
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
						<FORM METHOD="post" ACTION="/CGA104G1/Group_Join_backServlet" style="margin-bottom: 0px;">
						<select class="status" name=gbpay_status >
								<option value="0"
									${(group_joinVO.gbpay_status==0)? 'selected': ''}>未付款</option>
								<option value="1"
									${(group_joinVO.gbpay_status==1)? 'selected': ''}>已付款</option>
							</select> 
							<input type="hidden" name="gb_id" value="${group_joinVO.gb_id}">
							<input type="hidden" name="mem_id" value="${group_joinVO.mem_id}">
							<input class="btn btn-success btnIn btnSmall" type="submit" value="送出">
							<input type="hidden" name="action" value="updatePay">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="/CGA104G1/Group_Join_backServlet" style="margin-bottom: 0px;">
							<select class="status" name="deliver_status">
								<option value="0"
									${(group_joinVO.deliver_status==0)? 'selected': ''}>未出貨</option>
								<option value="1"
									${(group_joinVO.deliver_status==1)? 'selected': ''}>已出貨</option>
								<option value="2"
									${(group_joinVO.deliver_status==2)? 'selected': ''}>配送中</option>
								<option value="3"
									${(group_joinVO.deliver_status==3)? 'selected': ''}>已送達</option>

							</select> <input type="hidden" name="action" value="updateDeliver">
									  <input type="hidden" name="gb_id" value="${group_joinVO.gb_id}">
									  <input type="hidden" name="mem_id" value="${group_joinVO.mem_id}"> 
									  <input class="btn btn-success btnIn btnSmall" type="submit" value="送出" >
						</FORM>
					</td>					
					<td>
						<FORM METHOD="post" ACTION="/CGA104G1/Group_Join_backServlet" style="margin-bottom: 0px;">
							<select class="status" name="pickup_status">
								<option value="0"
									${(group_joinVO.pickup_status==0)? 'selected': ''}>未取貨</option>
								<option value="1"
									${(group_joinVO.pickup_status==1)? 'selected': ''}>已取貨</option>
							</select> <input type="hidden" name="action" value="updatePickup">
									  <input type="hidden" name="gb_id" value="${group_joinVO.gb_id}">
									  <input type="hidden" name="mem_id" value="${group_joinVO.mem_id}"> 
									  <input class="btn btn-success btnIn btnSmall" type="submit" value="送出" >
						</FORM>					
					</td>
					<td>
						<FORM METHOD="post" ACTION="/CGA104G1/Group_Join_backServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-warning btnIn btnSmall" type="submit" value="修改"> <input type="hidden"
								name="gb_id" value="${group_joinVO.gb_id}"> 
								<input type="hidden"
								name="mem_id" value="${group_joinVO.mem_id}"><input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="/CGA104G1/Group_Join_backServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-danger btnIn btnSmall" type="submit" value="刪除" ${group_joinVO.deliver_status!=0||group_joinVO.pickup_status!=0||group_joinVO.deliver_status!=0? 'disabled':' '}> 
							<input type="hidden"
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
		</section>
	</main>
	
</body>
</html>