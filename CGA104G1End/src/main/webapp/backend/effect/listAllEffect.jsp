<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.effect.model.*"%>

<%
EffectService effectSvc = new EffectService();
List<EffectVO> list = effectSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">

<title>所有權限資料</title>

<style>
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
  }
  
  th{
  	background-color: black;
  }
  
  tr{
  	text-align: center;
  }
  
  td{
  	color: black;
  }
  
  tr:nth-child(even) {
	background-color: rgba(255,255,255,0.4);
}
</style>

</head>
<body>
<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
		<div class="btnTitle">
			<button onclick="location.href='<%=request.getContextPath()%>/backend/effect/select_page.jsp'" class="btn btn-primary btnIn">回權限管理首頁</button>
		</div>
		<div class="titleBlock">所有權限列表</div>
			<table>
	<tr>
		<th>權限編號</th>
		<th>權限名稱</th>
		<th>權限說明</th>
		<th>修改資料</th>
		<th>刪除資料</th>

	</tr>

	<c:forEach var="effectVO" items="${list}" >
		
		<tr>
			<td>${effectVO.effect_id}</td>
			<td>${effectVO.effect_name}</td>
			<td>${effectVO.effect_info}</td>
			

			<td>
			  <FORM METHOD="post" ACTION="EffectServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-warning btnIn btnSmall">
			     <input type="hidden" name="effect_id"  value="${effectVO.effect_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="EffectServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-danger btnIn btnSmall">
			     <input type="hidden" name="effect_id"  value="${effectVO.effect_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
		</section>
	</main>



</body>
</html>