<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_buy_item.model.*"%>
<%-- <%@ page import="lombok.EqualsAndHashCode.Include"%> --%>
<%
Group_Buy_ItemService group_Buy_ItemService = new Group_Buy_ItemService();
List<Group_Buy_ItemVO> list = group_Buy_ItemService.getAll();
pageContext.setAttribute("list", list);
%>
<html>
<head>
<meta charset="UTF-8">
<title>所有團購商品</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">

<style>
table {
	width: 950px;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 3%;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

tr:nth-child(even) {
	background-color: rgba(255,255,255,0.4);
}
</style>
</head>
<body>

<!-- 複製起點 -->
	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
<!-- 		把原本body的東西貼到這邊 -->
<div class="btnTitle">
<button onclick="location.href='<%=request.getContextPath()%>/backend/group_Buy_Item/select_page.jsp'" class="btn btn-primary btnIn">回團購商品首頁</button>
</div>
<div class="titleBlock">所有團購商品資料</div>
	<table>
		<tr>
			<th>團購商品編號</th>
			<th>團購商品名稱</th>
			<th>團購商品內容</th>
			<th>團購商品價格</th>
			<th>團購商品狀態</th>
			<th>團購商品上檔日期</th>
			<th>團購商品下檔日期</th>
			<th>團購商品類別</th>
			<th></th>
			<th></th>
		</tr>
		<%@ include file="page1.file" %>
		<c:forEach var="Group_Buy_ItemVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${Group_Buy_ItemVO.gbitem_id}</td>
				<td>${Group_Buy_ItemVO.gbitem_name}</td>
				<td>${Group_Buy_ItemVO.gbitem_content}</td>
				<td>${Group_Buy_ItemVO.gbitem_price}</td>
<%-- 				<td>${Group_Buy_ItemVO.gbitem_status}</td> --%>
				<c:if test="${Group_Buy_ItemVO.gbitem_status == '0'}"><td><c:out value="超出團購期限下架"></td></c:out></c:if>
				<c:if test="${Group_Buy_ItemVO.gbitem_status == '1'}"><td><c:out value="上架中"></td></c:out></c:if>
				<c:if test="${Group_Buy_ItemVO.gbitem_status == '2'}"><td><c:out value="無庫存"></td></c:out></c:if>
				<c:if test="${Group_Buy_ItemVO.gbitem_status == '3'}"><td><c:out value="有庫存"></td></c:out></c:if>
				<td>${Group_Buy_ItemVO.gbitem_startdate}</td>
				<td>${Group_Buy_ItemVO.gbitem_enddate}</td>
				<td>${Group_Buy_ItemVO.gbitem_type}</td>


				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Group_Buy_Item/groupBuyItem.do" style="margin-bottom: 0px;">
				     <input type="submit" value="修改" class="btn btn-warning btnIn btnSmall">
				     <input type="hidden" name="gbitem_id"  value="${Group_Buy_ItemVO.gbitem_id}">
				     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Group_Buy_Item/groupBuyItem.do" style="margin-bottom: 0px;">
				     <input type="submit" value="刪除" class="btn btn-danger btnIn btnSmall">
				     <input type="hidden" name="gbitem_id"  value="${Group_Buy_ItemVO.gbitem_id}">
				     <input type="hidden" name="action" value="delete"></FORM>
				</td>
				</tr>
		</c:forEach>
		</table>
		<%@ include file="page2.file" %>
		</section>
	</main>
<!-- 複製終點 -->



</body>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- <script type="text/javascript"> -->


<!-- </script> -->

</html>
