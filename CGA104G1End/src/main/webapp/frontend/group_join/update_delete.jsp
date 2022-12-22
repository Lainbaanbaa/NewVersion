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

	<!-- import jquery-3.6.0 -->
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
	<!-- import font-style -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">

	<!-- import icon -->
	<script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/static/css/main.css"/>
	<style type="text/css">

		#dropdownMenuLink {
			display: none;
		}


</style>

</head>
<body bgcolor='white'>
<table id="table-1">
		<tr>
			<td>
				<h3>參團資料修改</h3>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>



	<FORM METHOD="post" ACTION="/CGA104G1/Group_Join_backServlet"
		name="form1">
		<table>
			<tr>
				<td>團購團名稱</td>
				<td><input type="hidden" name="gb_name" size="45"
					value="${group_BuyVO.gb_name} " readonly />${group_BuyVO.gb_name}</td>
			</tr>
			<tr>
				<td>團購團編號</td>
				<td><input type="hidden" name="gb_id" size="45"
					value="${group_joinVO.gb_id} " readonly />${group_joinVO.gb_id}<font color=red>${errorMsgs.gb_id}</font></td>
			</tr>
			<tr>
				<td>參團會員編號:</td>
				<td><input type="hidden" name="account" size="45"
					value="${group_joinVO.mem_id}" readonly />${group_joinVO.memVO.mem_name}[${group_joinVO.mem_id}]</td>
			</tr>
			<tr>
				<td>團購付款狀態</td>
				<td><select  name="gbpay_status">
						<option value="0" ${(group_joinVO.gbpay_status==0)? 'selected': ''}>未付款</option>
						<option value="1" ${(group_joinVO.gbpay_status==1)? 'selected': ''}>已付款</option>
				</select></td>
			</tr>
			<tr>
				<td>取貨狀態</td>
				<td><select  name="pickup_status">
						<option value="0" ${(group_joinVO.pickup_status==0)? 'selected': ''}>未取貨</option>
						<option value="1" ${(group_joinVO.pickup_status==1)? 'selected': ''}>已取貨</option>
				</select></td>
			</tr>
			<tr>
				<td>物流狀態</td>
				<td><select  name="deliver_status">
						<option value="0" ${(group_joinVO.deliver_status==0)? 'selected': ''}>未出貨</option>
						<option value="1" ${(group_joinVO.deliver_status==1)? 'selected': ''}>已出貨</option>
						<option value="2" ${(group_joinVO.deliver_status==2)? 'selected': ''}>配送中</option>
						<option value="3" ${(group_joinVO.deliver_status==3)? 'selected': ''}>已送達</option>
				</select></td>
			</tr>
			<tr>
				<td>購買數量<font color=red><b>*</b></font></td>
				<td><input type="number" name="gbbuy_amount" min=1 max=${(group_joinVO.group_BuyVO.gb_min - group_joinVO.group_BuyVO.gb_amount)==0 ? '1' :'group_joinVO.group_BuyVO.gb_min - group_joinVO.group_BuyVO.gb_amount'}
				 size="45"
					value="${group_joinVO.gbbuy_amount}" /><font color=red>${errorMsgs.gbbuy_amount}</font></td>
			</tr>
			<tr>
				<td>總價格<font color=red><b>*</b></font></td>
				<td><input type="hidden" name="gbbuy_price" size="45"
					value="${group_joinVO.gbbuy_price}"  readonly />${group_joinVO.gbbuy_price}<font color=red>${errorMsgs.gbbuy_price}</font></td>
			</tr>
		</table>

		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="gb_id" value="${group_joinVO.gb_id}">
			<input type="hidden" name="mem_id" value="${group_joinVO.mem_id}"> <input
			type="submit" value="送出修改" class="btn btn-warning">
	</FORM>

<!--  NavBar  -->
<script src="<%=request.getContextPath() %>/resources/static/js/navbar.js"></script>
<!--  Footer  -->
<script src="<%=request.getContextPath() %>/resources/static/js/footer.js"></script>


<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/getName.js"></script>
<!--  Cart -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/cart.js"></script>
</body>

</html>
