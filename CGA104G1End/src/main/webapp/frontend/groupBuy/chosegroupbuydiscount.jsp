<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_buy.model.*"%>
<%@ page import="com.group_buy_item.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.discount.model.*"%>
<%
Group_BuyVO group_BuyVO = (Group_BuyVO) request.getAttribute("Group_BuyVO");
%>
<%
Group_Buy_ItemVO group_Buy_ItemVO = (Group_Buy_ItemVO) request.getAttribute("Group_Buy_ItemVO");
%>

<%
DiscountService discountSvc = new DiscountService();
Integer gbitem_id = (Integer) session.getAttribute("gbitem_id");
List<DiscountVO> list = discountSvc.getoneGbitem_id((Integer) session.getAttribute("gbitem_id"));
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<!-- import font-style -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap"
	rel="stylesheet">

<!-- import css stylesheet -->
<link rel="stylesheet" href="../../resources/static/css/plugins.css">
<link rel="stylesheet" href="../../resources/static/css/style.css">
<link rel="stylesheet" href="../../resources/static/css/responsive.css">
<!-- import jquery-3.6.0 -->
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- import icon -->
<script src="https://kit.fontawesome.com/b5ef6b60f3.js"
	crossorigin="anonymous"></script>

<script src="https://cdn.bootcdn.net/ajax/libs/qs/6.11.0/qs.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

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

body {
	height: 100%; 
    background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
    background-color: #FFDEE9;
    background-repeat: no-repeat;
    background-size: cover;
    display: flex;
    justify-content: center;
}

.upPic {
	width: 50px;
	height: 50px;
}

.btn-success{
	width: 412px !important;
	border-radius: 20px !important;
}
</style>
</head>
<body>
	<main>
	<div style="height: 150px;"></div>
	<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
	<form
		action="<%=request.getContextPath()%>/GroupBuyMasterApplyListServlet">

		<br> <b>選擇折扣:</b> <select size="1" name="discount_price" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
<%-- 			<option value="${Group_Buy_ItemVO.gbitem_price}" selected>原始價格為${Group_Buy_ItemVO.gbitem_price}，暫無折扣 --%>
			<option value="100" selected>原始價格為${Group_Buy_ItemVO.gbitem_price}，暫無折扣
			<c:forEach var="DiscountVO" items="${list}">
				<option value="${DiscountVO.discount_price}">
					${DiscountVO.discount_nar} , 原始價格為${Group_Buy_ItemVO.gbitem_price}
					,
					折扣後價格為 <c:out value="${Math.round(DiscountVO.discount_price*Group_Buy_ItemVO.gbitem_price*0.01)}" />
				
			</c:forEach>
		</select> 
		<br><br>
		
			
			
			<input type="hidden" name="mem_id" value="${Group_BuyVO.mem_id}">
			<input type="hidden" name="gbitem_id" value="${Group_BuyVO.gbitem_id}">
			<input type="hidden" name="gb_min" value="${Group_BuyVO.gb_min}">
			<input type="hidden" name="gb_amount" value="${Group_BuyVO.gb_amount}">
			<input type="hidden" name="gbstart_date" value="${Group_BuyVO.gbstart_date}">
			<input type="hidden" name="gbend_date" value="${Group_BuyVO.gbend_date}">
			<input type="hidden" name="gb_status" value="${Group_BuyVO.gb_status}">
			<input type="hidden" name="gb_price" value="${Group_BuyVO.gb_price}">
			<input type="hidden" name="gb_name" value="${Group_BuyVO.gb_name}">
			
			
			<input type="hidden" name="gbitem_price"value="${Group_Buy_ItemVO.gbitem_price}"> 
			<input type="hidden" name="gb_id" value="${Group_BuyVO.gb_id}"> 
			<input type="hidden" name="action" value="confirmGroupBuy"> 
			<input type="submit" class="btn btn-success" value="確認開團">
	</form>
<!-- 	<FORM METHOD="post" -->
<%-- 		ACTION="<%=request.getContextPath()%>/GroupBuyServlet" --%>
<!-- 		style="margin-bottom: 0px;"> -->
<!-- 		<input type="submit" value="取消開團">  -->
<%-- 		<input type="hidden"name="gb_id" value="${Group_BuyVO.gb_id}">  --%>
<%-- 		<input type="hidden" name="mem_id" value="${Group_BuyVO.mem_id}">  --%>
<!-- 		<input type="hidden" name="action" value="deleteByGroupBuyMaster"> -->
<!-- 	</FORM> -->

</main>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<!--  NavBar  -->
	<script src="<%=request.getContextPath() %>/resources/static/js/navbar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/getName.js"></script>
	<!--  Cart -->
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/cart.js"></script>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</html>