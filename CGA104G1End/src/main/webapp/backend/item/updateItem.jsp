<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.itemPhotos.model.ItemPhotosVO"%>
<%@page import="java.io.OutputStream"%>
<%@page import="org.json.JSONArray"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.itemType.model.*"%>
<%@ page import="java.util.*"%>

<%
ItemVO itemVO = (ItemVO) request.getAttribute("itemVO");  /* 拿到從資料庫傳回要修改的物件 */
/* List<ItemPhotosVO> list=(List<ItemPhotosVO>) request.getAttribute("itemPhotos"); */
String photo =(String) request.getAttribute("itemPhoto");

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>修改商品 -updateItem.jsp</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<style>
		img{
			width:300px;
			height:200px;
			margin-right:10px;
		}
		div#photo{
	/* 		display:inline-block;
	 */	}
	    a{
	    text-decoration:none;
	    color:black;
	    }
	    .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  		}
  		.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  		}
	</style>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>商品資料修改 - updateItem.jsp</h3>
			</td>
			<td></td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="items" name="form1" enctype="multipart/form-data">
<%--		改這--%>
		<input type="hidden" name="item_id" value="${itemVO.itemId}">
		<input type="hidden" name="action" value="update">
		<table>
			<tr>
				<td>商品名稱:</td>
				<td>
					<input type="text" name="item_name" size="45" value="<%=(itemVO == null) ? "商品名稱" : itemVO.getItemName()%>" />
				</td>
			</tr>
			<tr>
				<td>商品描述:</td>
				<td>
					<input type="text" name="item_content" size="45" value="<%=(itemVO == null) ? "商品描述" : itemVO.getItemContent()%>" />
				</td>
			</tr>
			<tr>
				<td>商品價錢:</td>
				<td>
					<input type="text" name="item_price" size="45" value="<%=(itemVO == null) ? "商品價錢" : itemVO.getItemPrice()%>" />
				</td>
			</tr>
			<tr>
				<td>商品庫存:</td>
				<td>
					<input type="text" name="item_amount" size="45" value="<%=(itemVO == null) ? "商品庫存" : itemVO.getItemAmount()%>" />
				</td>
			</tr>
			<tr>
				<td>商品上架日期:</td>
				<td>
					<input type="text" name="item_date" id="start_date" size="45" value="<%=(itemVO == null) ? "商品上架日期" : itemVO.getItemDate()%>" />
				</td>
			</tr>
			<tr>
				<td>商品下架日期:</td>
				<td>
					<input type="text" name="item_enddate" id="end_date" size="45" value="<%=(itemVO == null) ? "商品下架日期" : itemVO.getItemEnddate()%>" />
				</td>
			</tr>
			<tr>
				<td>商品狀態:</td>
				<td>
					<input type="text" name="item_status" size="45" value="<%=(itemVO == null) ? "商品狀態" : itemVO.getItemStatus()%>" />
				</td>
			</tr>
			<jsp:useBean id="itemtypeSvc" scope="page" class="com.itemType.model.ItemTypeService" />
			<tr>
				<td>
					商品類別:
					<font color=red><b>*</b></font>
				</td>
				<td>
					<select size="1" name="itemtypeno">
						<c:forEach var="itemtypeVO" items="${itemtypeSvc.all}">
							<option value="${itemtypeVO.itemtId}"
							${(itemtypeVO.itemtId==itemVO.itemtId)?"selected":""}>${itemtypeVO.itemtName}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>新增圖片:</td>
				<td>
					<input type="file" name="item_photo" size="45" multiple="multiple"/>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="送出修改">
				</td>
			</tr>
		</table>
	</FORM>

	<label>商品圖片</label>
	<div id="image"></div>
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

		showImage()
		async function showImage(){
			let pjName=getProjectName();

			let res=await fetch(pjName+"/item/items?action=GetAllPhotos&item_id="+${itemVO.itemId},{method:'get'});
			let data=await res.json();
			data.forEach(e => {
				$("#image").append(`
					<div id="photo">
						<img src="data:image/jpeg;base64,\${e.photo}">
						<button onclick="onDeleteButtonClick(\${e.ipId})">刪除</button>
					</div>`)

			})

		}



		function onDeleteButtonClick(ipId){
			   const result=confirm("確定刪除此張照片嗎");
			   if(result){
			      // 對 GetItemPhoto?action=DeletePhoto&ip_id=\${ipId} 發出請求
				   let pjName=getProjectName();
			      fetch(`${pjName}GetItemPhoto?action=DeletePhoto&ip_id=\${ipId}`)

			         // 接收回應，並將回應之Body做JSON格式解析
			         // resp是Response型態的物件，代表回應
			         .then(resp => resp.json())

			         // 針對回應的Body之處理
			         // body的型態，決定於前一個then()的回傳值
			         // 此例中，因為前一個then()回傳resp.json()，所以body就是Object型態
			         .then(body => {
			            // body物件裡有successful屬性，是因為後端有回應此屬性
			            alert(body.successful? "刪除成功":"刪除失敗");
			            location.reload();
			         }
			      )
			   }
			}

		function getProjectName(){
			let path = window.location.pathname;
			let webCtx = path.substring(0, path.indexOf('/', 1));
			return webCtx;
		}

	</script>
</body>
</html>
