<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_buy_order.model.*"%>
<%@ page import="com.discount.model.*"%>
<%@ page import="com.group_buy_item.model.*"%>
<%@include file="/frontend/frontNavbar.jsp"%>

<%
Group_Buy_OrderVO group_buy_orderVO = (Group_Buy_OrderVO) request.getAttribute("group_buy_orderVO");
%>

<jsp:useBean id="group_buy_itemService" scope="page"
	class="com.group_buy_item.model.Group_Buy_ItemService" />
<html>
<head>

<title>團購訂單資料</title>
<!-- ===============================來自團購主查詢 付款和領貨============================================= -->
<!-- ===============================抓取團購團會員編號和登入帳號同編號<未執行>============================================= -->
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
	width: 95%;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 25px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
body {
	height: 100%; 
    background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
    background-color: #FFDEE9;
    background-repeat: no-repeat;
    background-size: cover;
    text-align: center;
}

th {
	color: white;
	background-color: black;
}

tr:nth-child(even) {
	background-color: rgba(255,255,255,0.4);
}

.titleBlock {
	font-weight: 700;
	font-size: 24px;
}
</style>

</head>
<body>

	<div class="btnTitle">
			<button onclick="location.href='<%=request.getContextPath()%>/frontend/group_buy_order/select_page.jsp'" class="btn btn-warning btnIn">回團購查詢首頁</button>
		</div>
		<div class="titleBlock">團購一覽(團主)</div>

	<table>
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
			<th>線上繳費</th>
			<th>領貨確認</th>


		</tr>
		<tr>
			<td>${group_buy_orderVO.gborder_id}</td>
			<td>${group_buy_orderVO.group_buy_itemVO.gbitem_name}</td>
			<td>[${group_buy_orderVO.gb_id}]-${group_buy_orderVO.group_BuyVO.gb_name}</td>
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
			<!--    ==================== 改成傳來團購訂單編號,目前寫死==================== -->
			<td>
				
				<FORM METHOD="post" ACTION="/CGA104G1/Group_Buy_OrderServlet">
					<input type="hidden" name="gborder_id" value="${group_buy_orderVO.gborder_id}"> 
					<input type="hidden" name="action" value="update_paying" > 
					<input class="btn btn-danger" type="submit" value="${(group_buy_orderVO.gborder_status >= 3) ? '已付款' : '線上付款'}"
					${(group_buy_orderVO.gborder_status >= 3 ) ? ' disabled=" " ' : ' '}> 
				</FORM>
				
			</td>
			<!--    ==================== 改成傳來團購訂單編號,目前寫死==================== -->
			<td>
				<FORM METHOD="post" ACTION="/CGA104G1/Group_Buy_OrderServlet">
					<input type="hidden" name="gborder_id" value="${group_buy_orderVO.gborder_id}"> 
					<input type="hidden" name="action" value="update_pt" >
					<input class="btn btn-success" type="submit" value="${(group_buy_orderVO.pickup_time != null) ? '已領貨' : '領貨確認'}"
					${(group_buy_orderVO.pickup_time != null) ? ' disabled=" " ' : ' '}>
				</FORM>
			</td>
		</tr>
	</table>

</body>
</html>