<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_buy.model.*"%>
<%@ page import="com.group_buy_item.model.*"%>

<%
pageContext.getAttribute("list");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- import font-style -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap"
	rel="stylesheet">


<!-- import jquery-3.6.0 -->
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- import icon -->
<script src="https://kit.fontawesome.com/b5ef6b60f3.js"
	crossorigin="anonymous"></script>

<script src="https://cdn.bootcdn.net/ajax/libs/qs/6.11.0/qs.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>團購主的團購團申請單狀態</title>
<style>
html {
	font-size: 22px;
}

.home_main {
	z-index: -999999;
	position: relative;
	transition: 3s;
	opacity: 30%;
	height: 4160px;
	width: auto;
	background-image:
		url('../resources/static/image/andrew-s-ouo1hbizWwo-unsplash.jpg');
	background-repeat: no-repeat;
	background-size: 100%;
	background-color: transparent;
	transform: scale(1.111);
	margin-bottom: 10%;
}

body {
	background: #eee;
}

footer {
	background: #fbaa70;
	padding: 50px 10px;
	font-size: 18px;
}

.badge {
	padding-left: 9px;
	padding-right: 9px;
	-webkit-border-radius: 9px;
	-moz-border-radius: 9px;
	border-radius: 9px;
}

.label-warning[href], .badge-warning[href] {
	background-color: #c67605;
}

#lblCartCount {
	font-size: 12px;
	background: #ff0000;
	color: #fff;
	padding: 0 5px;
	vertical-align: top;
	margin-left: -10px;
}

/* footer 樣式 */
footer {
	border-top: solid 1px #c9c9c9;
	padding: 80px 0px;
}

footer a {
	color: black;
	text-decoration: none;
}

footer a:hover {
	color: #929292;
	opacity: 60%;
}

footer {
	border-top: solid 1px #c9c9c9;
	padding: 80px 0;
	background-color: white;
}

footer img {
	width: 110px;
	margin-bottom: 0;
}

@media all and (min-width: 992px) {
	.navbar .nav-item .dropdown-menu {
		display: none;
	}
	.navbar .nav-item:hover .nav-link {
		
	}
	.navbar .nav-item:hover .dropdown-menu {
		display: block;
	}
	.navbar .nav-item .dropdown-menu {
		margin-top: 0;
	}
}

.upPic {
	width: 50px;
	height: 50px;
}

body {
	height: 100%; 
    background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
    background-color: #FFDEE9;
    background-repeat: no-repeat;
    background-size: cover;
}

main {
	text-align: center;
}

.titleBlock {
	font-size: 20px;
	font-weight: 700;
}

.titleName {
	color: blue;
}

th {
	background-color: black;
	color: white;
	font-size: 10px;
}

td {
	font-size: 10px;
}

.btnSmall{
padding: 0;
 font-size: 12px;
 width: 80px;
}
</style>

</head>

<body>
	<main>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<span class="titleBlock">團購狀態頁<span class="titleName">(團購主)</span></span>
		<table class="table table-hover thead-light table-bordered table-sm .table-responsive">
			<tr>
				<th>團購團名稱</th>
				<th>團購商品</th>
				<th>最低數量</th>
				<th>目前已下訂</th>
				<th>開始時間</th>
				<th>結束時間</th>
				<th>團購狀態</th>
				<th>原始價格</th>
				<th>團購價格</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			<%-- 		<%@ include file="page1.file" %>  --%>
			<%-- 		<c:forEach var="Group_BuyVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
			<c:forEach var="Group_BuyVO" items="${list}">

				<tr>
					<td>${Group_BuyVO.gb_name}</td>
					<td>${Group_BuyVO.gbitem_name}</td>
					<td>${Group_BuyVO.gb_min}</td>
					<td>${Group_BuyVO.gb_amount}</td>
					<td>${Group_BuyVO.gbstart_date}</td>
					<td>${Group_BuyVO.gbend_date}</td>
					<%-- 				<td>${Group_BuyVO.gb_status}</td> --%>
					<c:if test="${Group_BuyVO.gb_status == '0'}">
						<td><c:out value="尚未達到開團時間"></td>
						</c:out>
					</c:if>
					<c:if test="${Group_BuyVO.gb_status == '1'}">
						<td><c:out value="團購進行中，目前團購商品下訂總數不足"></td>
						</c:out>
					</c:if>
					<c:if test="${Group_BuyVO.gb_status == '2'}">
						<td><c:out value="團購進行中，目前團購商品下訂總數已達標"></td>
						</c:out>
					</c:if>
					<c:if test="${Group_BuyVO.gb_status == '3'}">
						<td><c:out value="團購關閉，出貨處理中"></td>
						</c:out>
					</c:if>
					<c:if test="${Group_BuyVO.gb_status == '4'}">
						<td><c:out value="團購關閉，已出貨"></td>
						</c:out>
					</c:if>
					<c:if test="${Group_BuyVO.gb_status == '8'}">
						<td><c:out value="團購結束。團購時間逾期且團購商品下訂總數不足"></td>
						</c:out>
					</c:if>
					<c:if test="${Group_BuyVO.gb_status == '9'}">
						<td><c:out value="團購結束。訂單已完成"></td>
						</c:out>
					</c:if>


					<td>${Group_BuyVO.gbitem_price}</td>
					<td>${Group_BuyVO.gb_price}</td>


					<td><FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/GroupBuyServlet"
							style="margin-bottom: 0px;">

							<c:if
								test="${Group_BuyVO.gb_status != '8' && Group_BuyVO.gb_status != '9' && Group_BuyVO.gb_status != '3' && Group_BuyVO.gb_status != '4'}">
								<input class="btn btn-warning btnSmall" type="submit" value="修改團購資訊">
							</c:if>
							<c:if
								test="${Group_BuyVO.gb_status == '8' || Group_BuyVO.gb_status == '9' || Group_BuyVO.gb_status == '3' || Group_BuyVO.gb_status == '4'}">
								<input class="btn btn-warning btnSmall" type="submit" value="修改團購資訊" disabled="disabled">
							</c:if>

							<input type="hidden" name="gb_id" value="${Group_BuyVO.gb_id}">
							<input type="hidden" name="mem_id" value="${Group_BuyVO.mem_id}">
							<input type="hidden" name="gbitem_id" value="${Group_BuyVO.gbitem_id}">
							<input type="hidden" name="action"
								value="getOneMyGroupBuy_For_Update">
						</FORM></td>

					<td><FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Group_Buy_OrderServlet"
							style="margin-bottom: 0px;">

							  <c:if test="${Group_BuyVO.gb_min <= Group_BuyVO.gb_amount}">
						        <input type="submit" value="立即結帳">
						       </c:if>
						       <c:if test="${Group_BuyVO.gb_min > Group_BuyVO.gb_amount}">
						        <input type="submit" value="立即結帳" disabled="disabled">
						       </c:if>


							<input type="hidden" name="gb_price"
								value="${Group_BuyVO.gb_price}"> <input type="hidden"
								name="gbitem_name" value="${Group_BuyVO.gbitem_name}"> <input
								type="hidden" name="gb_id" value="${Group_BuyVO.gb_id}">
							<input type="hidden" name="mem_id" value="${Group_BuyVO.mem_id}"><br>
							<input type="hidden" name="gbitem_id"
								value="${Group_BuyVO.gbitem_id}"> <input type="hidden"
								name="gb_min" value="${Group_BuyVO.gb_min}"> <input
								type="hidden" name="gb_amount" value="${Group_BuyVO.gb_amount}">
							<input type="hidden" name="gbstart_date"
								value="${Group_BuyVO.gbstart_date}"> <input
								type="hidden" name="gbend_date"
								value="${Group_BuyVO.gbend_date}"> <input type="hidden"
								name="gb_status" value="${Group_BuyVO.gb_status}"> <input
								type="hidden" name="gb_name" value="${Group_BuyVO.gb_name}">

							<input type="hidden" name="gbitem_content"
								value="${Group_BuyVO.gbitem_content}"> <input
								type="hidden" name="gbitem_price"
								value="${Group_BuyVO.gbitem_price}"> <input
								type="hidden" name="gbitem_status"
								value="${Group_BuyVO.gbitem_status}"> <input
								type="hidden" name="action" value="group_buy_goOrder">
						</FORM></td>


					<td><FORM METHOD="post" ACTION="/CGA104G1/Group_JoinServlet" name="form1">
						<!-- 		<input type="hidden" name="gb_price" value="200">  -->
						<!-- 		<input type="hidden" name="gbitem_name" value="Petkit 小佩智能感應式除臭貓砂盆">  -->
<%-- 						<input type="hidden" name="gb_name" value="${Group_BuyVO.gb_name}"> --%>
<%-- 						<input type="hidden" name="gb_id" value="${Group_BuyVO.gb_id}"> --%>
						<!-- 		<input type="hidden" name="discount_price" value="9">  -->
						<!-- 		<input type="hidden" name="mem_id" value="1"><br>  -->
						<!-- 		<input type="hidden" name="gbitem_id" value="1">  -->
						<!-- 		<input type="hidden" name="gb_min" value="30">  -->
						<!-- 		<input type="hidden" name="gb_amount" value="5"> -->
						<!-- 		<input type="hidden" name="gbstart_date" value="2022-10-11 09:10:40">  -->
						<!-- 		<input type="hidden" name="gbend_date" value="2022-10-11 09:10:40">  -->
						<!-- 		<input type="hidden" name="gb_status" value="1">  -->
						<input class="btn btn-dark btnSmall" type="submit" value="參團資料查詢"> <input
							type="hidden" name="action" value="getOne_Display_ByMem">
					</FORM></td>



					<!-- 						<td><FORM METHOD="post" -->
					<%-- 							ACTION="<%=request.getContextPath()%>/GroupBuyServlet" --%>
					<!-- 							style="margin-bottom: 0px;"> -->
					<!-- 							<input type="submit" value="團購結束">  -->
					<%-- 							<input type="hidden" name="gb_id" value="${Group_BuyVO.gb_id}">  --%>
					<%-- 							<input type="hidden" name="mem_id" value="${Group_BuyVO.mem_id}"> --%>
					<!-- 							<input type="hidden" name="gb_status" value="9"> -->
					<!-- 							<input type="hidden" name="action" value="updateGBStatusByGroupBuyMaster"> -->
					<!-- 						</FORM></td> -->
			</c:forEach>
		</table>
<!-- 		<FORM METHOD="post" ACTION="/CGA104G1/Group_JoinServlet" name="form1"> -->
<!-- 						<input class="btn btn-warning" type="submit" value="參團資料查詢"> -->
<!-- 						<input type="hidden" name="action" value="getOne_Display_ByMem"> -->
<!-- 					</FORM> -->
		<%-- 		<%@ include file="page2.file" %> --%>
	</main>

	<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<!--  NavBar  -->
	<script src="<%=request.getContextPath() %>/resources/static/js/navbar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/getName.js"></script>
	<!--  Cart -->
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/cart.js"></script>
</body>

</html>