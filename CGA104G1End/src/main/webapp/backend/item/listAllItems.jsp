<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%
ItemService itemSvc = new ItemService();
List<ItemVO> list = itemSvc.getAll();
pageContext.setAttribute("itemList", list); 
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
img {
	width: 100px;
	height: 100px;
}

table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 400px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
</head>

<body>
	<table border="1">
		<thead>
			<tr>
				<th>編號</th>
				<th>種類編號</th>
				<th>名稱</th>
				<th>描述</th>
				<th>單價</th>
				<th>數量</th>
				<th>狀態</th>
				<th>上架日期</th>
				<th>下架日期</th>
				<th>圖片</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty itemList}">
				<c:forEach var="item" items="${itemList}">
					<tr>
						<td>${item.itemId}</td>
						<td>${item.itemtId}</td>
						<td>${item.itemName}</td>
						<td>${item.itemContent}</td>
						<td>${item.itemPrice}</td>
						<td>${item.itemAmount}</td>
						<td>${item.itemStatus}</td>
						<td>${item.itemDate}</td>
						<td>${item.itemEnddate}</td>
						<td><img src="<%= request.getContextPath()%>/item/GetItemPhoto?action=getOnePhoto&item_id=${item.itemId}"></td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/item/items"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="oneactid" value="${item.itemId}"> <input
									type="hidden" name="action" value="oneupdate">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/item/items"
								style="margin-bottom: 0px;">
								<input type="submit" value="刪除"> <input type="hidden"
									name="deleteactid" value="${item.itemId}"> <input
									type="hidden" name="action" value="delete">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

<script>
	
</script>
</body>
</html>
