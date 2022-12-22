<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_buy.model.*"%>
<%@ page import="java.util.*"%>

<%
Group_BuyVO group_BuyVO = (Group_BuyVO) request.getAttribute("Group_BuyVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>團購團資料修改 - update_groupBuy_input.jsp</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">

<style>
table {
	width: 500px;
	height: 370px;
	margin-top: 1px;
	margin-bottom: 1px;
	margin-left: 25%;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>

<body>

	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
	<div class="btnTitle">
<button onclick="location.href='<%=request.getContextPath()%>/backend/group_buy/select_page.jsp'" class="btn btn-primary btnIn">回團購團管理首頁</button>
</div>
<div class="titleBlock">修改團購團資料</div>
			<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/GroupBuyServlet" name="form1">
		<table>
			<tr>
				<td>團購團編號:</td>
				<td><%=group_BuyVO.getGb_id()%></td>
			</tr>
			<tr>
				<td>團購主編號:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="mem_id" size="45"
					value="<%=group_BuyVO.getMem_id()%>" /></td>
			</tr>
			<tr>
				<td>團購商品編號:</td>
				<td><input type="TEXT" name="gbitem_id" size="45"
					value="<%=group_BuyVO.getGbitem_id()%>" /></td>
			</tr>
			<tr>
				<td>團購商品數量低標</td>
				<td><input type="TEXT" name="gb_min" size="45"
					value="<%=group_BuyVO.getGb_min()%>"></td>
			</tr>
			<tr>
				<td>目前團購商品下訂總數</td>
				<td><input type="TEXT" name="gb_amount" size="45"
					value="<%=group_BuyVO.getGb_amount()%>"></td>
			</tr>
			
			<tr>
				<td>團購開始日期:</td>
				<td><input name="gbstart_date" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>團購結束下檔日期:</td>
				<td><input name="gbend_date" id="f_date2" type="text"></td>
			</tr>
			
			<!-- 	//selected disabled hidden -->
			<c:if test="value=0">超出團購期限下架</c:if>
			<tr>
				<td>團購商品狀態:</td>
				<td><select size="1" name="gb_status">
						<%-- 				<option value="${Group_Buy_ItemVO.gbitem_status}" id = "gbitemStatus">${Group_Buy_ItemVO.gbitem_status}</option> --%>
						<c:if test="${group_BuyVO.gbitem_status == '0'}">
							<option value="${group_BuyVO.gbitem_status}"><c:out
									value="(now)參團人數不足"></option>
							</c:out>
						</c:if>
						<c:if test="${group_BuyVO.gbitem_status == '1'}">
							<option value="${group_BuyVO.gbitem_status}"><c:out
									value="(now)參團人數已達標"></option>
							</c:out>
						</c:if>
						
						<option value="0">參團人數不足</option>
						<option value="1">參團人數已達標</option>
				</select></td>
			</tr>
			<tr>
				<td>團購價格</td>
				<td><input type="TEXT" name="gb_price" size="45"
					value="<%=group_BuyVO.getGb_price()%>"></td>
			</tr>
				<tr>
				<td>團購名稱:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="gb_name" size="45"
					value="<%=group_BuyVO.getGb_name()%>" /></td>
			</tr>
		</table>
		<br> 
		<div class="subBlock">
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="gb_id" value="<%=group_BuyVO.getGb_id()%>"> 
		<input type="submit" value="送出修改" class="btn btn-success btnIn">
		</div>
	</FORM>
		</section>
	</main>

</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.sql.Timestamp gbstart_date = null;
try {
	gbstart_date = group_BuyVO.getGbstart_date();
} catch (Exception e) {
	gbstart_date = new java.sql.Timestamp(System.currentTimeMillis());
}
%>
<%
java.sql.Timestamp gbend_date = null;
try {
	gbend_date = group_BuyVO.getGbend_date();
} catch (Exception e) {
	gbend_date = new java.sql.Timestamp(System.currentTimeMillis());
}
%>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=gbstart_date%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=gbend_date%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	minDate:               '"-"+<%=gbstart_date%>' // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	// $(document).ready(function(){
	// 	var status = $("#gbitemStatus").text();
	// 	switch(status){
	// 		case '0':
	// 			$("#gbitemStatus").text("(當前)超出團購期限下架");
	// 			break;
	// 		case '1':
	// 			$("#gbitemStatus").text("(當前)上架中");
	// 			break;
	// 		case '2':
	// 			$("#gbitemStatus").text("(當前)無庫存");
	// 			break;
	// 		case '3':
	// 			$("#gbitemStatus").text("(當前)有庫存");
	// 			break;
	// 	}
	// });

</script>
</html>