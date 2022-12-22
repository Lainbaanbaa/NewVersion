<%@page import="com.group_buy_item.model.Group_Buy_ItemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_buy.model.*"%>
<%
Group_BuyVO group_BuyVO = (Group_BuyVO) request.getAttribute("Group_BuyVO");
%>
<%
Group_Buy_ItemVO group_Buy_ItemVO = (Group_Buy_ItemVO) request.getAttribute("Group_Buy_ItemVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>團購主修改團購資訊</title>
<style>
body {
	height: 100%; 
    background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
    background-color: #FFDEE9;
    background-repeat: no-repeat;
    background-size: cover;
    text-align: center;
}

form{
	margin-left:25%;
}

.titleBlock {
	text-align: center;
}

.btnBlock {
	margin-left:26%;
}
</style>
</head>
<body>
<div style="height:120px;"></div>
<div class="titleBlock">
<h3>團購團資料修改:</h3>
</div>
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
				<td>團購名稱:</td>
				<td><input type="TEXT" name="gb_name" size="45"
					value="<%=group_BuyVO.getGb_name()%>" /></td>
			</tr>
			<tr>
				<td>團購團編號:</td>
				<td><%=group_BuyVO.getGb_id()%></td>
			</tr>
			<tr>
				<td>團購主編號:</td>
				<td><%=group_BuyVO.getMem_id()%>
				<input type="hidden" name="mem_id" size="45"
					value="<%=group_BuyVO.getMem_id()%>" /></td>
			</tr>
			<tr>
				<td>團購商品名稱:</td>
				<td><%=group_Buy_ItemVO.getGbitem_name()%>
				<input type="hidden" name="gbitem_id" size="45"
					value="<%=group_BuyVO.getGbitem_id()%>" /></td>
			</tr>
			<tr>
				<td>團購商品數量低標</td>
				<td><%=group_BuyVO.getGb_min()%>
				<input type="hidden" name="gb_min" size="45"
					value="<%=group_BuyVO.getGb_min()%>"></td>
			</tr>
			<tr>
				<td>目前團購商品下訂總數</td>
				<td><%=group_BuyVO.getGb_amount()%>
				<input type="hidden" name="gb_amount" size="45"
					value="<%=group_BuyVO.getGb_amount()%>"></td>
			</tr>
			
			<tr>
				<td>團購開始日期:</td>
				<td>${Group_BuyVO.gbstart_date}
				<input type="hidden" name="gbstart_date" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>團購結束下檔日期:</td>
				<td><input name="gbend_date" id="f_date2" type="text" value="">團購結束下檔日期修改前為${Group_BuyVO.gbend_date}</td>
			</tr>
			
			<!-- 	//selected disabled hidden -->
			<c:if test="value=0">超出團購期限下架</c:if>
			<tr><input type="hidden" name="gb_status" size="45" value="<%=group_BuyVO.getGb_status()%>" />
				<td>團購商品狀態:</td>
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
			</tr>
			<tr>
				<td>團購價格</td>
				<td><%=group_BuyVO.getGb_price()%>
				<input type="hidden" name="gb_price" size="45"
					value="<%=group_BuyVO.getGb_price()%>"></td>
			</tr>
		
		</table>
		<br> 
		<div class="btnBlock">
		<input type="hidden" name="action" value="updateGBStatusByGroupBuyMaster"> 
		<input type="hidden" name="gb_id" value="<%=group_BuyVO.getGb_id()%>"> 
		<input class="btn btn-success" type="submit" value="送出修改">
		</div>
	</FORM>

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

<!--  NavBar  -->
<%@include file="/frontend/frontNavbar.jsp"%>
<%-- <script src="<%=request.getContextPath()%>/frontend/frontNavbar.jsp"></script> --%>
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