<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_join.model.*"%>
<%@ page import="com.group_buy.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="group_buySvc" scope="page" class="com.group_buy.model.Group_BuyService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<title>員工新增資料</title>


<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>


</head>
<body bgcolor='white'>

<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
			<h3>參團資料新增:</h3>
<div class= "container">
	
	<FORM METHOD="post" ACTION="/CGA104G1/Group_Join_backServlet" name="form1">
		<table>		
		<tr>
		<td>團購團編號</td>
		<td>
		<select  name="gb_id">
		<c:forEach var="group_buyVO" items="${group_buySvc.all}">
		<option value="${group_buyVO.gb_id}">${group_buyVO.gb_name}:</option>
		</c:forEach>
		</select>
		</td>		
	</tr>
	</table>
	 <input type="hidden" name="action" value="getOne_For_Display"> <input
			type="submit" value="查詢團購可購買數量"class="btn btn-warning"> 
	</FORM>	
</div>
		</section>
	</main>


	

</body>
</html>