<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_join.model.*"%>
<jsp:useBean id="group_buySvc" scope="page" class="com.group_buy.model.Group_BuyService" />
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<title>員工資料修改</title>

<style>
		body {
			height: 100%; 
		    background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
		    background-color: #FFDEE9;
		    background-repeat: no-repeat;
		    background-size: cover;
		    text-align: center;
		}
		
		table {
			margin-left: 40%;
		}
		
		.btn-info {
			padding: 0 5px;;
			height: 30px;
		}
		
		.btnBot {
			width: 300px;
		}

</style>

</head>
<body bgcolor='white'>


	<h3>參團資料修改:<font color=red>${errorMsgs.gb_id}</font> </h3>
	


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
				 size="45"
					value="${group_joinVO.gbbuy_amount}" />
					<input    class="field2 btn btn-info"  type="button"  value="計算價格" onclick="price()"><font color=red>${errorMsgs.gbbuy_amount}</font>
			</tr>
			
			<tr>
				<td>總價格:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="gbbuy_price" size="4"
					value="${group_joinVO.gbbuy_price}"   readonly="readonly"   required="required" /><font color=red>${errorMsgs.gbbuy_price}</font></td>
			</tr>
		</table><br>

		<input type="hidden" name="deliver_status" size="45"
					value="${group_joinVO.deliver_status}" readonly /><input
			type="hidden" name="gb_id" value="${group_joinVO.gb_id}">
			<input type="hidden" name="mem_id" value="${group_joinVO.mem_id}"> 
		<div id ="yesboss">	<input  class="btn btn-success btnBot" type="button"  value="確認修改"  onclick="update()"></div>
	</FORM>
	<FORM METHOD="post" ACTION="/CGA104G1/Group_JoinServlet" >
				<input class="btn btn-danger btnBot" type= submit value="取消" class = "back"  > 
				<input type="hidden" name="action" value="getOne_Display_ByMem">
	</FORM>
	
	
	<!--  NavBar  -->
	<script src="<%=request.getContextPath() %>/resources/static/js/navbar.js"></script>
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
		document.form1.action="/CGA104G1/Group_JoinServlet?action=update_Newprice";
		document.form1.submit();	
	}

	function update() {
		document.form1.action="/CGA104G1/Group_JoinServlet?action=UpdateByMem";
		document.form1.submit();	
	}
	
	</script>
</body>
</html>