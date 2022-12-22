<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <form method="post" class="form1" action="/CGA104G1/Group_Buy_OrderServlet">
	 
        <input type="hidden" name="action" value="group_buy_goOrder">
        <input type="hidden" name="gb_id" value="1">
        <input type="hidden" name="discount_id" value="1">
        <input type="hidden" name="discount_nar" value="打到骨折">
        <input type="hidden" name="mem_id" value="1">
        <input type="hidden" name="gbitem_id" value="1">
        <input type="hidden" name="gb_name" value="買起來">
        <input type="hidden" name="gb_min" value="20">
        <input type="hidden" name="gbitem_name" value="Petkit 小佩智能感應式除臭貓砂盆">
        <input type="hidden" name="gbitem_content" value="貓砂盆">
        <input type="hidden" name="gbitem_price" value="2380">
        <input type="hidden" name="gbitem_status" value="1">
		<input type="hidden" name="gb_price" value="1234">
        
        <button type="submit" class="btn btn-success">確認送出</button>
    </form>


</body>
</html>