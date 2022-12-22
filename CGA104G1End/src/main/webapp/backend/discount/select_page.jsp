<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.discount.model.*"%>
<%
DiscountService discountService = new DiscountService();
List<DiscountVO> list = discountService.getAll();
pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>GroupBUy: Home</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  
  section{
  	text-align: center;
  }
  
  .btn-primary{
  	margin-bottom: 15px;
  }
</style>

</head>
<body>

<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
			<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<h3>團購折扣管理</h3>
  <button onclick="location.href='<%=request.getContextPath()%>/backend/discount/listAllDiscount.jsp'" class="btn btn-primary btnIn">所有團購折扣</button>
  
    <FORM METHOD="post" ACTION="DiscountServlet" >
        <b>輸入折扣編號:</b>
        <input type="text" name="discount_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input class="btn btn-info btnIn btnSmall" type="submit" value="送出">
    </FORM>

  <jsp:useBean id="disSvc" scope="page" class="com.discount.model.DiscountService" /> 
   
     <FORM METHOD="post" ACTION="DiscountServlet" >
       <b>選擇折扣編號:</b>
       <select size="1" name="discount_id">
         <c:forEach var="DiscountVO" items="${list}" > 
          <option value="${DiscountVO.discount_id}">${DiscountVO.discount_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input class="btn btn-info btnIn btnSmall" type="submit" value="送出">
    </FORM>
  
     <FORM METHOD="post" ACTION="DiscountServlet" >
       <b>選擇折扣說明:</b>
       <select size="1" name="discount_id">
         <c:forEach var="DiscountVO" items="${list}" > 
          <option value="${DiscountVO.discount_id}">${DiscountVO.discount_nar}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input class="btn btn-info btnIn btnSmall" type="submit" value="送出">
     </FORM>



	<button onclick="location.href='<%=request.getContextPath()%>/backend/discount/addDiscount.jsp'" class="btn btn-success btnIn">新增團購折扣</button>
		</section>
	</main>
	
<%-- 錯誤表列 --%>

  
</body>
</html>