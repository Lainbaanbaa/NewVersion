<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_buy_item.model.*"%>
<%@ page import="com.group_buy_item_picture.model.*"%>
<%@ page import="java.util.*"%>
<%
Group_Buy_ItemVO group_Buy_ItemVO = (Group_Buy_ItemVO) request.getAttribute("Group_Buy_ItemVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>團購商品資料修改 - update_groupBuyItem_input.jsp</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">

<style>


	<style>
table {
	width: 800px;
	min-height: 600px;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
	color: black;
}

form {
	margin-left: 28% !important;
}

.fileInput{
	border-radius: 0;
}

.btnSmall{
	width: 100px;
}

.file{
	text-decoration: none;
}

#picPreview{
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>
<!-- 複製起點 -->
	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="btnTitle">
<button onclick="location.href='<%=request.getContextPath()%>/backend/group_Buy_Item/select_page.jsp'" class="btn btn-primary btnIn">回團購商品首頁</button>
</div>
<div class="titleBlock">修改團購商品資料</div>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_Buy_Item/groupBuyItem.do" name="form1">
		<table>
			<tr>
				<td>團購商品編號:</td>
				<td><%=group_Buy_ItemVO.getGbitem_id()%></td>
			</tr>
			<tr>
				<td>團購商品名稱:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="gbitem_name" size="45"
					value="<%=group_Buy_ItemVO.getGbitem_name()%>" /></td>
			</tr>
			<tr>
				<td>團購商品內容:</td>
				<td><input type="TEXT" name="gbitem_content" size="45"
					value="<%=group_Buy_ItemVO.getGbitem_content()%>" /></td>
			</tr>
			<tr>
				<td>團購商品價格</td>
				<td><input type="TEXT" name="gbitem_price" size="45"
					value="<%=group_Buy_ItemVO.getGbitem_price()%>"></td>
			</tr>
			<!-- 	//selected disabled hidden -->
			<c:if test="value=0">超出團購期限下架</c:if>
			<tr>
				<td>團購商品狀態:</td>
				<td><select size="1" name="gbitem_status">
						<%-- 				<option value="${Group_Buy_ItemVO.gbitem_status}" id = "gbitemStatus">${Group_Buy_ItemVO.gbitem_status}</option> --%>
						<c:if test="${Group_Buy_ItemVO.gbitem_status == '0'}">
							<option value="${Group_Buy_ItemVO.gbitem_status}"><c:out
									value="(now)超出團購期限下架"></option>
							</c:out>
						</c:if>
						<c:if test="${Group_Buy_ItemVO.gbitem_status == '1'}">
							<option value="${Group_Buy_ItemVO.gbitem_status}"><c:out
									value="(now)上架中"></option>
							</c:out>
						</c:if>
						<c:if test="${Group_Buy_ItemVO.gbitem_status == '2'}">
							<option value="${Group_Buy_ItemVO.gbitem_status}"><c:out
									value="(now)無庫存"></option>
							</c:out>
						</c:if>
						<c:if test="${Group_Buy_ItemVO.gbitem_status == '3'}">
							<option value="${Group_Buy_ItemVO.gbitem_status}"><c:out
									value="(now)有庫存"></option>
							</c:out>
						</c:if>
						<option value="0">超出團購期限下架</option>
						<option value="1">上架中</option>
						<option value="2">無庫存</option>
						<option value="3">有庫存</option>
				</select></td>
			</tr>
			<tr>
				<td>團購商品上檔日期:</td>
				<td><input name="gbitem_startdate" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>團購商品下檔日期:</td>
				<td><input name="gbitem_enddate" id="f_date2" type="text"></td>
			</tr>

			<tr>
				<td>團購商品類別</td>
				<td><input type="TEXT" name="gbitem_type" size="45"
					value="<%=group_Buy_ItemVO.getGbitem_type()%>"></td>
			</tr>

		</table>

	</FORM>
		<br>



<!-- 	圖片顯示區及刪除 -->
	<div id="delete-form">
		<form method="post"
			action="<%=request.getContextPath()%>/groupBuyItemPicture/groupBuyItemPictureDelete.do"
			onsubmit="return checkConfirm();">
			<br>
			<c:if test="${list2 != null}">

				<c:forEach var="gbipVO" items="${list2}">
					<img
						src="<%=request.getContextPath()%>/groupBuyItemPicture/groupBuyItemPictureGetOneByGBItemID.do?gbip_id=${gbipVO.gbip_id}"
						height="128px" width="128px" class="uploadedImg">
					<input type="hidden" name="gbip_id" value="${gbipVO.gbip_id}">
					<input class="form-check-input"
						type="checkbox" name="gbip_ids" value="${gbipVO.gbip_id}"
						class="delete_checkbox">
				</c:forEach>
				<input type="hidden" name="gbitem_id" value="${Group_Buy_ItemVO.gbitem_id}">
				<input type="hidden" name="gbitem_name"value="${Group_Buy_ItemVO.gbitem_name}">
				<input type="hidden" name="gbitem_content"value="${Group_Buy_ItemVO.gbitem_content}">
				<input type="hidden" name="gbitem_price"value="${Group_Buy_ItemVO.gbitem_price}">
				<input type="hidden" name="gbitem_status"value="${Group_Buy_ItemVO.gbitem_status}">
				<input type="hidden" name="gbitem_startdate"value="${Group_Buy_ItemVO.gbitem_startdate}">
				<input type="hidden" name="gbitem_enddate"value="${Group_Buy_ItemVO.gbitem_enddate}">
				<input type="hidden" name="gbitem_type"value="${Group_Buy_ItemVO.gbitem_type}">
				<input class="btn btn-danger btnIn btnSmall" type="submit" value="刪除圖片">

			</c:if>
		</form>
	</div>
	<!-- 	上傳圖片區 -->
	<div>
		<form id="upload"
			action="<%=request.getContextPath()%>/groupBuyItemPicture/groupBuyItemPictureInsertMulti.do"
			method="POST" enctype="multipart/form-data" name="form2" onsubmit="return">
			<a href="javascript:;" class="file">
			<input type="file" name="upfile1" multiple id="upfile" class="fileInput">
			</a>
			<input type="hidden" name="gbitem_id" value="${Group_Buy_ItemVO.gbitem_id}">
			<input type="hidden" name="gbitem_name"value="${Group_Buy_ItemVO.gbitem_name}">
			<input type="hidden" name="gbitem_content"value="${Group_Buy_ItemVO.gbitem_content}">
			<input type="hidden" name="gbitem_price"value="${Group_Buy_ItemVO.gbitem_price}">
			<input type="hidden" name="gbitem_status"value="${Group_Buy_ItemVO.gbitem_status}">
			<input type="hidden" name="gbitem_startdate"value="${Group_Buy_ItemVO.gbitem_startdate}">
			<input type="hidden" name="gbitem_enddate"value="${Group_Buy_ItemVO.gbitem_enddate}">
			<input type="hidden" name="gbitem_type"value="${Group_Buy_ItemVO.gbitem_type}">
			<input class="button btn btn-dark btnIn btnSmall" type="submit" value="上傳圖片">
		</form>
		<div id="picPreview"></div>
	</div>
	<div class="subBlock">
	<input type="hidden" name="action" value="update">
			 <input type="hidden" name="gbitem_id" value="<%=group_Buy_ItemVO.getGbitem_id()%>">
			 <input type="submit" value="送出修改" class="btn btn-success btnIn">
	</div>
		</section>
	</main>

</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.sql.Date gbitem_startdate = null;
try {
	gbitem_startdate = group_Buy_ItemVO.getGbitem_startdate();
} catch (Exception e) {
	gbitem_startdate = new java.sql.Date(System.currentTimeMillis());
}
%>
<%
java.sql.Date gbitem_enddate = null;
try {
	gbitem_enddate = group_Buy_ItemVO.getGbitem_enddate();
} catch (Exception e) {
	gbitem_enddate = new java.sql.Date(System.currentTimeMillis());
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
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=gbitem_startdate%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=gbitem_enddate%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	minDate:               '"-"+<%=gbitem_startdate%>' // 去除今日(不含)之前
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
<script type="text/javascript">
 //上傳多選圖片

 window.addEventListener("load", function(){



 		let upfile = document.getElementById("upfile");
 		upfile.addEventListener("change", function(event) {
 			let files = event.target.files || event.dataTransfer.files;
 			for (let i = 0; i < files.length; i++) {
 				previewfile(files[i])
 			}
 		}, false);


 		function previewfile(file) {

 				let reader = new FileReader();
 				reader.onload = function(event) {
 					let image = new Image();
 					image.src = event.target.result;
 					image.width = 128;
 					image.classList.add("imgCss");
 					picPreview.appendChild(image);
 				};
 				reader.readAsDataURL(file);
 		}


 		// 當upload重新選擇 清空舊有資料
 		$("#upload").change(function(){
 		    $("#picPreview").empty() // 清空當下預覽
 		    previewfile(this.files) // this即為<input>元素
 		})
 })

 </script>

</html>
