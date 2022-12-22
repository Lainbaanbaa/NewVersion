<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/frontend/frontNavbar.jsp"%>
<!DOCTYPE html>
<html>
<head>

<!-- ===============================來自團購編號查詢============================================= -->

<title>團購查詢</title>
<style>
body {
	height: 100%; 
    background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
    background-color: #FFDEE9;
    background-repeat: no-repeat;
    background-size: cover;
    text-align: center;
}

.titleBlock {
	text-align: center;
	font-size: 24px;
	font-weight: 700;
}

input {
	height: 40px;
}
</style>

</head>
<body>

   <div class="titleBlock">團購訂單查詢</div>

    <br><br>
  
    <FORM METHOD="post" ACTION="/CGA104G1/Group_Buy_OrderServlet" >
        <input type="text" name="gborder_id" placeholder="請輸入訂單編號">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input  class="btn btn-success" type="submit" value="送出">
        <br>
        <font color=red>${errorMsgs.gborder_id}</font>
    </FORM>
    
    <a href='<%=request.getContextPath()%>/frontend/group_buy_report/addGroup_Buy_Report.jsp' class="btn btn-danger">團購檢舉</a>
    <a href='<%=request.getContextPath()%>/frontend/mem/mem_index.jsp' class="btn btn-dark">回會員中心</a>


</body>
</html>