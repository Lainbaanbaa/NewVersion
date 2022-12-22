<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
<title>參團查詢首頁</title>
<style>


  table#table-1 {
	width: 450px;
	background-color: #212529;
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

h4 {
	color: blue;
	display: inline;
}
h3{
	color: white;
}

.formIn {
	margin-left: 28%;
}

.titleIn {
	font-size: 24px;
	color: black;
	font-weight: 700;
}
</style>

</head>
<body>
	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
			<div class="btnTitle">
			<div class="titleIn">參團查詢</div>
			</div>
			<FORM class="formIn" METHOD="post" ACTION="/CGA104G1/Group_Join_backServlet" name="form1">
			<font color=red>${errorMsgs.gb_id}</font><br>
			<font color=red>${errorMsgs.fail}</font> <br>
			<span class="label-desc">團購團編號:</span>&emsp; 
			<input type="text" name="gb_id" value=""><br><br> 
			<span>參團會員編號:</span> 
			<input type="text" name="mem_id" value=""><br><br>
			<div class="select-box">
				<label for="select-box1"> <span>團購付款狀態</span>
				</label> <select name ="gbpay_status">
					<option value=" "selected>請選擇</option>
					<option value="0">未付款</option>
					<option value="1">已付款</option>
				</select><br><br>
			</div>
				<div class="select-box">
				<label for="select-box1"> <span>取貨狀態</span>&emsp; &emsp; 
				</label> <select name ="pickup_status">
					<option value=" " selected>請選擇</option>
					<option value="0">未取貨</option>
					<option value="1">已取貨</option>
				</select><br><br>
			</div>
				<div class="select-box">
				<label for="select-box1"> <span>物流狀態</span>&emsp; &emsp; 
				</label> <select name ="deliver_status">
					<option value=" " selected>請選擇</option>
					<option value="0">未出貨</option>
					<option value="1">已出貨</option>
					<option value="2">配送中</option>
					<option value="3">已送達</option>
				</select><br><br>
			</div>&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; 
			<input class="btn btn-success btnIn" type="submit" value="送出"> <input type="hidden"
				name="action" value="list_group_join">
		</FORM>
	<div class="btnTitle">
	<a class="btn btn-warning btnIn" href="<%=request.getContextPath()%>/backend/group_join/addNewGj.jsp">新增參團資料</a>
		</div>
		</section>
	</main>


		
	


</body>

</html>