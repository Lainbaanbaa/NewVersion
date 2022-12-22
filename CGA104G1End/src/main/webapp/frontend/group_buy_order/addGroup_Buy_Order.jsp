<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_buy_order.model.*"%>
<%@ page import="com.discount.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_buy_item.model.*"%>
<jsp:useBean id="discountSvc" scope="page"
	class="com.discount.model.DiscountService" />
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<title>團購訂單新增資料</title>
<!-- ===============================來自團購團資料============================================= -->


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

.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
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

.titleBlock {
	font-weight: 700;
	font-size: 24px;
}

table {
	margin-left: 30%;
}

select, input {
    	width: 300px;
    	height: 30px;
    	border: none;
    	text-align: center;
    }
    
select:focus, input:focus {
	border: 2px solid pink !important;
}  

.tdContent {
	width: 300px;
	text-align: center;
}
</style>


</head>
<body bgcolor='white'>

	<h3>訂單新增:</h3>
	<FORM METHOD="post" ACTION="/CGA104G1/Group_Buy_OrderServlet" name="form1">
		<table>
			<tr>
				<td>團購團名稱:</td>
				<td class="tdContent"><input type="hidden" name="gb_id" size="45" value="${group_buy_orderVO.group_BuyVO.gb_id }" readonly />${group_buy_orderVO.group_BuyVO.gb_name }</td>
			</tr>
			<tr>
				<td>團購商品名稱:</td>
				<td class="tdContent"><input type="hidden" name="gbitem_id" size="45"
					value="${group_buy_orderVO.gbitem_id}"readonly />${group_buy_orderVO.group_buy_itemVO.gbitem_name}</td>
			</tr>
			<tr>
				<td>團購商品內容:</td>
				<td class="tdContent"><input type="hidden" name="gbitem_content" size="45"
					value="${group_buy_orderVO.group_buy_itemVO.gbitem_content}" readonly/>${group_buy_orderVO.group_buy_itemVO.gbitem_content}</td>
			</tr>
			<tr>
				<td>團購數量:</td>
				<td class="tdContent"><input type="hidden" name="gbitem_amount" size="45"
					value="${group_buy_orderVO.group_BuyVO.gb_min}" readonly="readonly" />${group_buy_orderVO.group_BuyVO.gb_min}</td>
			</tr>

			<tr>
				<td>原價:</td>
				<td class="tdContent"><input type="hidden" name="gboriginal_price" size="45"
					value="${group_buy_orderVO.group_buy_itemVO.gbitem_price*group_buy_orderVO.group_BuyVO.gb_min}"
					readonly  />${group_buy_orderVO.group_buy_itemVO.gbitem_price*group_buy_orderVO.group_BuyVO.gb_min}</td>
			</tr>
			<tr>
				<td>團購價:</td>
				<td class="tdContent"><input type="hidden" name="gb_endprice" size="45"
					value="${group_buy_orderVO.group_BuyVO.gb_min*group_buy_orderVO.group_BuyVO.gb_price}" readonly/>${group_buy_orderVO.group_BuyVO.gb_min*group_buy_orderVO.group_BuyVO.gb_price}</td>
			</tr>
			<tr>
				<td>團購付款方式:</td>
				<td><select class="status" name="gborder_paying" id="select">
						<option value=" " selected></option>
						<option value="0">貨到付款</option>
						<option value="1">ATM轉帳</option>
						<option value="2">信用卡</option>
				</select></td><td><font color=red>${errorMsgs.gborder_paying}</font></td>
			</tr>
			<tr>
			<tr>
				<td>團購送貨方式:</td>
				<td><select class="status2" name="gborder_send" id="select">
						<option value=" " selected></option>
						<option value="0">宅配</option>
						<option value="1">便利商店</option>
				</select></td><td><font color=red>${errorMsgs.gborder_send}</font></td>
			</tr>
			<tr>
				<td>團購訂單狀態:</td>
				<td>
				<input type="hidden" name="gborder_status" value="0" size="45" />
				<input type="TEXT"  size="45"
					value="等待付款中"readonly="readonly" /></td><td><font color=red>${errorMsgs.gborder_status}</font></td>
			</tr>
			<tr>
				<td>團購訂單備註:</td>
				<td><input type="TEXT" name="gborder_other" size="45"
					placeholder="如有需要注意事項請於此輸入" /></td>
			</tr>
			<tr>
				<td>收件人姓名:</td>
				<td><input type="TEXT" name="receiver_name" size="45"  pattern="^[\u4e00-\u9fa5]+$|^[a-zA-Z\s]+$"  placeholder="請輸入姓名"  required/></td><td><font color=red>${errorMsgs.receiver_name}</font></td>
			</tr>

			<tr>
				<td>收件人地址:</td>
				<!--  ==============================團購主確認================================== -->
				<td><input type="TEXT" name="receiver_address" size="45" placeholder="請輸入地址"  required/></td><td><font color=red>${errorMsgs.receiver_address}</font></td>
			</tr>
			<tr>
				<td>收件人電話:</td>
				<!--  ==============================團購主確認================================== -->
				<td><input  type="TEXT" id="phone" name="receiver_phone" pattern="[0][9][0-9]{2}[0-9]{3}[0-9]{3}"  size="45" placeholder="請輸入電話"  required/></td><td><font color=red>${errorMsgs.receiver_phone}</font></td>
			</tr>
		</table>
		<br>
		<br> <input type="hidden" name="action" value="insert_NewOrder"> <input
			type="submit" value="送出新增"class="btn btn-warning">
	</FORM>
	<script type="text/javascript">
	let type = $('#select option:selected') .val();



	$("#selector").on("change", function() {
		  $("#selector option:selected")
		  });


// function act1(){
// 	document.form1.action="DiscountServlet?action=insertdiscount";
// 	document.form1.submit();
// }
// function act2(){
// 	document.form1.action="/CGA104G1/Group_Buy_OrderServlet?action=insert_NewOrder";
// 	document.form1.submit();
// }

</script>
	<!-- // ===============================下拉選單新增========================================== -->




	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>
</body>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<!--  NavBar  -->
<script src="<%=request.getContextPath() %>/resources/static/js/navbar.js"></script>
<!--  Footer  -->


<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/getName.js"></script>
<!--  Cart -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/cart.js"></script>
</html>
