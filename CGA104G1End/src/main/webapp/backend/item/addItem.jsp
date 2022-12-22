<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>
<%
ItemVO itemVO = (ItemVO) request.getAttribute("itemVO");  /* 保留填錯物件 */
%>

--<%=itemVO == null%>-- --${itemVO.itemId}--

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增商品 -addItem.jsp</title>
</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>商品資料新增 - addItem.jsp</h3>
			</td>
			<td></td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item/items" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>商品名稱:</td>
				<td><input type="text" name="item_name" size="45"
					value="<%=(itemVO == null) ? "商品名稱" : itemVO.getItemName()%>" /></td>
			</tr>

			<tr>
				<td>商品描述:</td>
				<td><input type="text" name="item_content" size="45"
					value="<%=(itemVO == null) ? "商品描述" : itemVO.getItemContent()%>" /></td>
			</tr>
			<tr>
				<td>商品價錢:</td>
				<td><input type="text" name="item_price" size="45"
					value="<%=(itemVO == null) ? "商品價錢" : itemVO.getItemPrice()%>" /></td>
			</tr>
			<tr>
				<td>商品庫存:</td>
				<td><input type="text" name="item_amount" size="45"
					value="<%=(itemVO == null) ? "商品庫存" : itemVO.getItemAmount()%>" /></td>
			</tr>

			<tr>
				<td>商品上架日期:</td>
				<td><input type="text" name="item_date" id="start_date" size="45"/>
<%-- 					value="<%=(itemVO == null) ? "商品上架日期" : itemVO.getItem_date()%>" /> --%>
					</td>
			</tr>
			<tr>
				<td>商品下架日期:</td>
				<td><input type="text" name="item_enddate" id="end_date" size="45"/>
					<%-- value="<%=(itemVO == null) ? "商品下架日期" : itemVO.getItem_enddate()%>" /> --%>
					</td>
			</tr>
			<tr>
				<td>商品狀態:</td>
				<td><input type="text" name="item_status" size="45"
					value="<%=(itemVO == null) ? "商品狀態" : itemVO.getItemStatus()%>" /></td>
			</tr>
			<jsp:useBean id="itemtypeSvc" scope="page"
				class="com.itemType.model.ItemTypeService" />
			<tr>
				<td>商品類別:<font color=red><b>*</b></font></td>
				<td><select size="1" name="itemtypeno">
						<c:forEach var="itemtypeVO" items="${itemtypeSvc.all}">
							<option value="${itemtypeVO.itemtId}"
								${(itemtypeVO.itemtId==itemtypeVO.itemtId)? 'selected':'' }>${itemtypeVO.itemtName}
						</c:forEach>
				</select></td>
			</tr>

		<tr>
				<td>商品圖片:</td>
				<td><input type="file" name="item_photo" size="45" multiple="multiple"/>
				</td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>




<link rel="stylesheet" type="text/css"
 href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
 src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>



<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
$.datetimepicker.setLocale('zh');
$(function(){
	 $('#start_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_date').val()?$('#end_date').val():false
	   })
	  },
	  timepicker:false
	 });

	 $('#end_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_date').val()?$('#start_date').val():false
	   })
	  },
	  timepicker:false
	 });
});
</script>

</html>
