<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_buy_order.model.*"%>
<% Group_Buy_OrderVO group_Buy_OrderVO = (Group_Buy_OrderVO) request.getAttribute("group_Buy_OrderVO"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
<title>查看更新資料</title>
<style>
/* <!-- ===========================================樣式欄位================================================================== --> */

/* tr:nth-child(odd){ */
/*   background:white; */
/* } */

/* tr:nth-child(even){ */
/*   background:#a4a9ad; */
/* } */
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

/* <!-- ===========================================樣式欄位================================================================== --> */
table#table-1 {
	background-color: #212529;
	border: 2px solid black;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	width: 1038px;
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

/*   a{ */
/*     color: white; */
/*     display: inline; */
/*   } */
</style>

<style>
table {
	margin-left: auto;
	margin-right: auto;
	width: 95%;
	margin-top: 5px;
	margin-bottom: 5px;
}
/*    table, th, td {  */
/*      border: 1px solid #212529;  */
/*    }  */
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

/* th, td { display: block; } */
/*   td:first-child{ */
/*   border-top-left-radius: 10px; */
/*   border-bottom-left-radius: 10px; */
/* } */

/* td:last-child{ */
/*   border-top-right-radius: 10px; */
/*   border-bottom-right-radius: 10px; */
/* } */
</style>
</head>
<body>
		<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
			<div class="btnTitle">
		<div class="titleIn">團購修改結果</div><br>
		<button onclick="location.href='<%=request.getContextPath()%>/backend/group_buy_order/select_page.jsp'" class="btn btn-primary btnIn">回團購查詢</button>
		</div>

	<table class="styled-table">
	
		<thead>
			<tr>
				<th>團購訂單編號</th>
				<th>購買商品</th>
				<th>團購團編號</th>
				<th>商品數量</th>
				<th>團購訂單原始金額</th>
				<th>團購價</th>
				<th>開啟訂單時間</th>
				<th>付款方式</th>
				<th>送貨方式</th>
				<th>訂單狀態</th>
				<th>訂單備註</th>
				<th>物流編號</th>
				<th>收件人姓名</th>
				<th>收件人地址</th>
				<th>收件人電話</th>
				<th>領貨時間</th>
				<th>變更資料</th>
			</tr>
		</thead>
		<tbody>
		
				<tr class="active-row" align='center' valign='middle'>
					<td>${group_buy_orderVO.gborder_id}</td>
					<td>${group_buy_orderVO.gbitem_id}</td>
					<td>${group_buy_orderVO.gb_id}</td>
					<td>${group_buy_orderVO.gbitem_amount}</td>
					<td>${group_buy_orderVO.gboriginal_price}</td>
					<td>${group_buy_orderVO.gb_endprice}</td>
					<td>${group_buy_orderVO.gborder_date}</td>
					<c:if test="${group_buy_orderVO.gborder_paying==0}">
						<td><c:out value="貨到付款"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_paying==1}">
						<td><c:out value="ATM轉帳"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_paying==2}">
						<td><c:out value="信用卡"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_send==0}">
						<td><c:out value="宅配"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_send==1}">
						<td><c:out value="便利商店"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_status==0}">
						<td><c:out value="等待付款中"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_status==1}">
						<td><c:out value="已取消"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_status==2}">
						<td><c:out value="等待賣家確認中"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_status==3}">
						<td><c:out value="準備出貨中"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_status==4}">
						<td><c:out value="已出貨"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_status==5}">
						<td><c:out value="未取貨，退回平台"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_status==6}">
						<td><c:out value="已取貨"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_status==7}">
						<td><c:out value="退貨"></c:out></td>
					</c:if>
					<c:if test="${group_buy_orderVO.gborder_status==8}">
						<td><c:out value="換貨"></c:out></td>
					</c:if>
					<td>${group_buy_orderVO.gborder_other}</td>
					<td>${group_buy_orderVO.tracking_num}</td>
					<td>${group_buy_orderVO.receiver_name}</td>
					<td>${group_buy_orderVO.receiver_address}</td>
					<td>${group_buy_orderVO.receiver_phone}</td>
					<td>${group_buy_orderVO.pickup_time}</td>

						<td>
						<FORM METHOD="post" ACTION="Group_Buy_OrderServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-warning btnIn btnSmall" type="submit" value="修改"> <input type="hidden"
								name="gborder_id" value="${group_buy_orderVO.gborder_id}"> <input
								type="hidden" name="action" value="getOne_For_Backend_Display">
						</FORM>
					</td>
				</tr>
			
		</tbody>

	</table>
		</section>
	</main>
		
		


</body>
</html>