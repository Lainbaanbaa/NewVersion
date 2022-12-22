
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.favorite_list.model.*"%>
<%
Favorite_listVO favorite_listVO = (Favorite_listVO) request.getAttribute("favorite_listVO");
%>

<!DOCTYPE html>
<html>
<head>
<title>會員商品資料</title>
</head>
<body bgcolor=>

	<table id=tab1>
		<tr>
			<td><a href="select_page.jsp">回首頁</a></td>
		</tr>
	</table>
	<table>
		<tr>
			<th>會員編號</th>
			<th>商品編號</th>
			<th>加入時間</th>
		</tr>
		<tr>
		    <td><%=favorite_listVO.getMem_id()%></td>
		    <td><%=favorite_listVO.getItem_id()%></td>
		    <td><%=favorite_listVO.getFav_time()%></td>
			
		</tr>
	
	</table>
</body>
</html>