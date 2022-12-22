<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.favorite_list.model.*"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>

<%
Favorite_listVO favorite_listVO = (Favorite_listVO) request.getAttribute("favorite_listVO");
%>
<%
   Date dNow = new Date( );
   SimpleDateFormat ft = 
   new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
   String time = ft.format(dNow);
%>
<html>
<head>
<title>會員資料修改</title>
</head>
<body>
	<a href="select_page.jsp">回首頁</a>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="fav.do" name="form1">
		<table>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="mem_id" size="45"
					value="<%=favorite_listVO.getMem_id()%>" /></td>
			</tr>
			<tr>
				<td>商品編號:</td>
				<td><input type="TEXT" name="item_id" size="45"
					value="<%=favorite_listVO.getItem_id()%>" /></td>
			</tr>
			<tr>
				<td>加入時間:</td>
				<td><input type="TEXT" name="fav_time" size="45"
					value="<%=time%>" readonly /></td>
			</tr>

			<jsp:useBean id="favorite_listSvc" scope="page"
				class="com.favorite_list.model.Favorite_listService" />


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="mem_id" value="<%=favorite_listVO.getMem_id()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>
</html>