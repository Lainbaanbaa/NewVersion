<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>測試頁</title>
</head>
<body>
	<FORM METHOD="post" ACTION="/CGA104G1/Group_JoinServlet"
		name="form1">
<!-- 		<input type="hidden" name="mem_id" value="小佩智能感應式除臭貓砂盆"> -->
<!-- 		<input type="hidden" name="gb_id" value="1">  -->
		<input type="submit" value="送出參團查詢"> <input type="hidden" name="action"
			value="getOne_Display_ByMem">
	</FORM>
	<FORM METHOD="post" ACTION="/CGA104G1/Group_JoinServlet"
		name="form1">
<!-- 		<input type="hidden" name="gb_price" value="200">  -->
<!-- 		<input type="hidden" name="gbitem_name" value="Petkit 小佩智能感應式除臭貓砂盆">  -->
<input type="hidden" name="gb_name" value="Petkit 小佩智能感應式除臭貓砂盆">
		<input type="hidden" name="gb_id" value="1">
<!-- 		<input type="hidden" name="discount_price" value="9">  -->
<!-- 		<input type="hidden" name="mem_id" value="1"><br>  -->
<!-- 		<input type="hidden" name="gbitem_id" value="1">  -->
<!-- 		<input type="hidden" name="gb_min" value="30">  -->
<!-- 		<input type="hidden" name="gb_amount" value="5"> -->
<!-- 		<input type="hidden" name="gbstart_date" value="2022-10-11 09:10:40">  -->
<!-- 		<input type="hidden" name="gbend_date" value="2022-10-11 09:10:40">  -->
<!-- 		<input type="hidden" name="gb_status" value="1">  -->
		<input type="submit" value="送出參團資料查詢"> <input type="hidden" name="action"
			value="getOneGB_For_Display">
	</FORM>
</body>
</html>