<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="com.favorite_list.model.*"%>

<%
     Favorite_listService favorite_listSvc = new Favorite_listService();
    List<Favorite_listVO> list = favorite_listSvc.getAll();
     pageContext.setAttribute("list",list);
 %>


<html>
<head>
<title>所有會員資料</title>
</head>
<body>
<table>
<tr>
<td>
<a href="select_page.jsp">回首頁</a>
</td>
</tr>
</table>
<table>
	<tr>
		<th>會員編號</th>
		<th>商品編號</th>
		<th>加入時間</th>
		
	</tr>
	
	<c:forEach var="favorite_listVO" items="${list}" >	
		<tr>
			<td>${favorite_listVO.mem_id}</td>
			<td>${favorite_listVO.item_id}</td>
			<td>${favorite_listVO.fav_time}</td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/favorite_list/fav.do" >
			     <input type="submit" value="修改">
			     <input type="hidden" name="mem_id"  value="${favorite_listVO.mem_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/favorite_list/fav.do">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="mem_id"  value="${favorite_listVO.mem_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>