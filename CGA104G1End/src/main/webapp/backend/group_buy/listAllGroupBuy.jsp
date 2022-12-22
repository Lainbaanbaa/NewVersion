<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_buy.model.*"%>
<%
Group_BuyService group_BuyService = new Group_BuyService();
List<Group_BuyVO> list = group_BuyService.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有團購團</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<style>
section {
 			height: 100%; 
            background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
            background-color: #FFDEE9;
            background-repeat: no-repeat;
            background-size: cover;
            display: flex;
            justify-content: center;
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
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

.btnTitle {
	width: 1000px;
	text-align: center;
}

.btn-info {
	width: 200px;
	border-radius: 20px !important;
	margin-top: 15px;
}

table {
	width: 90%;
	text-align: center;
	margin-left: 5%;
}

b {
	margin-left: 5%;
}

.formTitle {
	background-color: black;
	color: white;
}

.btnIn {
	border-radius: 20px !important;
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
<div class="container">
<div class="btnTitle">
<button onclick="location.href='<%=request.getContextPath()%>/backend/group_buy/select_page.jsp'" class="btn btn-info">回首頁</button>
</div>
<table>
		<tr class="formTitle">
			<th>團購團編號</th>
			<th>團購主編號</th>
			<th>團購商品編號</th>
			<th>團購商品數量低標</th>
			<th>目前團購商品下訂總數</th>
			<th>團購開始</th>
			<th>團購結束</th>
			<th>團購狀態</th>
			<th>團購價格</th>
			<th>團購名稱</th>
			<th></th>
			<th></th>
		</tr>
		<%@ include file="page1.file" %> 
		<c:forEach var="Group_BuyVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${Group_BuyVO.gb_id}</td>
				<td>${Group_BuyVO.mem_id}</td>
				<td>${Group_BuyVO.gbitem_id}</td>
				<td>${Group_BuyVO.gb_min}</td>
				<td>${Group_BuyVO.gb_amount}</td>
				<td>${Group_BuyVO.gbstart_date}</td>
				<td>${Group_BuyVO.gbend_date}</td>
<%-- 				<td>${Group_BuyVO.gb_status}</td> --%>
				<c:if test="${Group_BuyVO.gb_status == '0'}">
							<td><c:out value="尚未達到開團時間"></td>
							</c:out>
						</c:if>
						<c:if test="${Group_BuyVO.gb_status == '1'}">
							<td><c:out value="團購進行中，目前團購商品下訂總數不足"></td>
							</c:out>
						</c:if>
						<c:if test="${Group_BuyVO.gb_status == '2'}">
							<td><c:out value="團購進行中，目前團購商品下訂總數已達標"></td>
							</c:out>
						</c:if>
						<c:if test="${Group_BuyVO.gb_status == '3'}">
							<td><c:out value="團購關閉，出貨處理中"></td>
							</c:out>
						</c:if>
						<c:if test="${Group_BuyVO.gb_status == '4'}">
							<td><c:out value="團購關閉，已出貨"></td>
							</c:out>
						</c:if>
						<c:if test="${Group_BuyVO.gb_status == '8'}">
							<td><c:out value="團購結束。團購時間逾期且團購商品下訂總數不足"></td>
							</c:out>
						</c:if>
						<c:if test="${Group_BuyVO.gb_status == '9'}">
							<td><c:out value="團購結束。訂單已完成"></td>
							</c:out>
						</c:if>
				
				
				
				<td>${Group_BuyVO.gb_price}</td>
				<td>${Group_BuyVO.gb_name}</td>
				
				
				
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/GroupBuyServlet" style="margin-bottom: 0px;">
				     <input type="submit" value="修改" class="btn btn-warning btnIn">
				     <input type="hidden" name="gb_id"  value="${Group_BuyVO.gb_id}">
				     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/GroupBuyServlet" style="margin-bottom: 0px;">
				     <input type="submit" value="刪除" class="btn btn-danger btnIn">
				     <input type="hidden" name="gb_id"  value="${Group_BuyVO.gb_id}">
				     <input type="hidden" name="action" value="delete"></FORM>
				</td>
				</tr>
		</c:forEach>
		</table>
		<%@ include file="page2.file" %>
		</div>
		</section>
	</main>
<!-- 複製終點 -->


</body>
</html>