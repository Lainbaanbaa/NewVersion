<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_join.model.*"%>
<jsp:useBean id="group_buySvc" scope="page" class="com.group_buy.model.Group_BuyService" />
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<title>員工資料修改</title>

<style>

table {
	margin-left:5%;
	width: 900px;
	height: 300px;
}

.titleIn {
	font-size: 24px;
	font-weight: 700;
}

.subBlock {
margin-left: 5%;
}

.btnSmall{
	width: 100px;
}

.field{
	margin-left: 98px;
}
</style>

</head>
<body bgcolor='white'>
<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
		<div class="titleBlock">
			<h3>參團資料修改:<font color=red>${errorMsgs.gb_id}</font> </h3>
		</div>


	<FORM METHOD="post" name="form1">
<!-- 	ACTION="/CGA104G1/Group_JoinServlet" -->	
		<table>
			<tr>
				<td>團購團編號</td>
				<td><input type="hidden" name="gb_id" size="45"
					value="${group_joinVO.gb_id} " readonly />[${group_joinVO.gb_id}]-${group_joinVO.group_BuyVO.gb_name}</td>
			</tr>
			<tr>
				<td>參團會員編號:</td>
				<td><input type="hidden" name="mem_id" size="45"
					value="${group_joinVO.mem_id}" readonly />[${group_joinVO.mem_id}]-${group_joinVO.memVO.mem_name}</td>
			</tr>
			<tr>
				<td>團購付款狀態:</td>
				<td><input type="hidden" name="gbpay_status" size="45"
					value="${group_joinVO.gbpay_status}" readonly />${group_joinVO.gbpay_status==0 ? '未付款':'已付款'}</td>
			</tr>
			
			<tr>
				<td>取貨狀態:</td>
				<td><input type="hidden" name="pickup_status" size="45"
					value="${group_joinVO.pickup_status}" readonly />${group_joinVO.pickup_status==0 ? '未取貨':'已取貨'}</td>
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
				<td>購買數量:<font color=red><b>*</b></font></td>
				<td><input  class="field" type="number" name="gbbuy_amount" min="1" max="${group_joinVO.group_BuyVO.gb_min - nowamount}" required
				 size="45" value="${group_joinVO.gbbuy_amount}" />
					<input   type="button"  class="field2 btn btn-info btnIn btnSmall" value="計算價格" onclick="price()"><font color=red>${errorMsgs.gbbuy_amount}</font>
			</tr>
			
			<tr>
				<td>總價格:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="gbbuy_price" size="4"
					value="${group_joinVO.gbbuy_price}" readonly /><font color=red>${errorMsgs.gbbuy_price}</font></td>
			</tr>
		</table>

<!-- 		<br> <input type="hidden" name="action" value="update">  -->
		<input type="hidden" name="deliver_status" size="45"
					value="${group_joinVO.deliver_status}" readonly /><input
			type="hidden" name="gb_id" value="${group_joinVO.gb_id}">
			<input type="hidden" name="mem_id" value="${group_joinVO.mem_id}"> 
			<div id ="yesboss subBlock">
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			<input  class="btn btn-success btnIn" type="button"  value="確認更改"  onclick="update()">
			</div>
	</FORM>
		</section>
	</main>

	

	
	
	
	<script type="text/javascript">
	$(document).ready(function(){
		  $(".field").change(function(){ 
			  $("#yesboss").addClass('disabled');			 
		  });
		});
	$(document).ready(function(){
		  $(".field2").change(function(){
			  $("#yesboss").removeClass('disabled');
		  });
		});
	
	
	$(document).ready(function(){
	    $("#yesboss").mousemove(function(){ 
	        if($(this).hasClass('disabled')){
	               alert("請先計算價格");  
	        }
	    })
	 });
	
	function price() {
		document.form1.action="/CGA104G1/Group_Join_backServlet?action=update_Newprice";
		document.form1.submit();	
	}

	function update() {
		document.form1.action="/CGA104G1/Group_Join_backServlet?action=UpdateByEmp";
		document.form1.submit();	
	}
	
	</script>
</body>
</html>