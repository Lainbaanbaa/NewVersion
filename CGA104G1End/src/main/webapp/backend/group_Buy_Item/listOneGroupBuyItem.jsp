<%@page import="com.group_buy_item.model.Group_Buy_ItemVO"%>
<%@ page import="com.group_buy_item.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Group_Buy_ItemVO group_Buy_ItemVO = (Group_Buy_ItemVO) request.getAttribute("Group_Buy_ItemVO");
%>
<html>
<head>
<title>單一團購商品資料 - listOneGroupBuyItem.jsp</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<style>
section {
 			height: 100%; 
            background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
            background-color: #FFDEE9;
            background-repeat: no-repeat;
            background-size: cover;
            justify-content: center;
        }

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 10%;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
	color: black;
}

th {
	color: white;
	text-align: center;
}

.titleBlock{
	text-align:center;
	font-size: 24px;
	font-weight: 700;
	margin-bottom: 20px;
}

.btnTitle {
	width: 1000px;
	text-align: center;
	margin-top: 20px;
}

.tableTitle {
	background-color: black;
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
<!-- 		把原本body的東西貼到這邊 -->

<div class="btnTitle">
<button onclick="location.href='<%=request.getContextPath()%>/backend/group_Buy_Item/select_page.jsp'" class="btn btn-info">回首頁</button>
</div>
<div class="titleBlock">單一團購商品資料</div>


	<table>
		<tr class="tableTitle">
			<th>團購商品編號</th>
			<th>團購商品名稱</th>
			<th>團購商品內容</th>
			<th>團購商品價格</th>
			<th>團購商品狀態</th>
			<th>團購商品上檔日期</th>
			<th>團購商品下檔日期</th>
			<th>團購商品類別</th>
		</tr>
		<tr>
			<td><%=group_Buy_ItemVO.getGbitem_id()%></td>
			<td><%=group_Buy_ItemVO.getGbitem_name()%></td>
			<td><%=group_Buy_ItemVO.getGbitem_content()%></td>
			<td><%=group_Buy_ItemVO.getGbitem_price()%></td>
			<c:if test="${Group_Buy_ItemVO.gbitem_status == '0'}"><td><c:out value="超出團購期限下架"></td></c:out></c:if>
				<c:if test="${Group_Buy_ItemVO.gbitem_status == '1'}"><td><c:out value="上架中"></td></c:out></c:if>
				<c:if test="${Group_Buy_ItemVO.gbitem_status == '2'}"><td><c:out value="無庫存"></td></c:out></c:if>
				<c:if test="${Group_Buy_ItemVO.gbitem_status == '3'}"><td><c:out value="有庫存"></td></c:out></c:if>
			<td><%=group_Buy_ItemVO.getGbitem_startdate()%></td>
			<td><%=group_Buy_ItemVO.getGbitem_enddate()%></td>
			<td>${Group_Buy_ItemVO.gbitem_type}</td>
		</tr>
	</table>
		</section>
	</main>
	

</body>
</html>