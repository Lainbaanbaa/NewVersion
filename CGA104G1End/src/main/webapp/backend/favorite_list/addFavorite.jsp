<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.favorite_list.model.*"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>


<%
Favorite_listVO favorite_listVO = (Favorite_listVO) request.getAttribute("favorite_listVO");
%>
<%=favorite_listVO == null%>
<%
   Date dNow = new Date( );
   SimpleDateFormat ft =
   new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss ");
   String time = ft.format(dNow);
%>
<html>
<head>

<title>新增</title>
</head>
<body>

	<table>
		<tr>
			<td><a href="select_page.jsp">回首頁</a></td>
		</tr>
	</table>

	<c:if test="${not empty errorMsgs }">
		<font>請修正以下錯誤</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="post" action="fav.do" name="form1">
		<table>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="mem_id" size="45"
					value="<%=(favorite_listVO == null) ? "1" : favorite_listVO.getMem_id()%>" /></td>
			</tr>
			<tr>
				<td>商品編號:</td>
				<td><input type="TEXT" name="item_id" size="45"
					value="<%=(favorite_listVO == null) ? "1" : favorite_listVO.getItem_id()%>" /></td>
				<tr>
				<td>加入時間:</td>
				<td><input type="TEXT" name="fav_time" size="45"
					value="<%=time%>" readonly /></td>
			</tr>





		</table>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
	</FORM>
<%-- <jsp:useBean id="favorite_listSvc" scope="page" --%>
<%-- 		class="com.favorite_list.service.Favorite_listService"></jsp:useBean> --%>
<!-- <FORM METHOD="post" ACTION="fav.do"> -->
<!-- 				<b>選擇會員編號:</b>  -->
<!-- 				<select size="1" name="mem_id"> -->
<%-- 					<c:forEach var="favorite_listVO" items="${favorite_listSvc.all}"> --%>
<%-- 						<option value="${favorite_listVO.mem_id}">${favorite_listVO.mem_id} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> 	 -->
<!-- 				<b>選擇商品編號:</b>  -->
<!-- 				<select size="1" name="mem_id"> -->
<%-- 					<c:forEach var="favorite_listVO" items="${favorite_listSvc.all}"> --%>
<%-- 						<option value="${favorite_listVO.item_id}">${favorite_listVO.item_id} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> <input type="hidden" name="action" value="insert"> -->
<!-- 				<input type="submit" value="送出">		 -->
<!-- 	</FORM> -->
</body>
</html>
