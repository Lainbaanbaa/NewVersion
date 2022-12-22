<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.effect.model.*"%>>
<% 
EffectVO effectVO = (EffectVO) request.getAttribute("effectVO");
%>

<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<title>權限資料</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
<style>
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
	width: 600px;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 20%;
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


<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
		<div class="btnTitle">
			&ensp;<button onclick="location.href='<%=request.getContextPath()%>/backend/effect/select_page.jsp'" class="btn btn-primary btnIn">回權限管理首頁</button>
		</div>
		<div class="titleBlock">單一權限查詢</div>
			<table>
	<tr>
		<th>權限編號</th>
		<th>權限名稱</th>
		<th>權限說明</th>
		
	</tr>
	<tr>
		<td><%=effectVO.getEffect_id()%></td>
		<td><%=effectVO.getEffect_name()%></td>
		<td><%=effectVO.getEffect_info()%></td>
	
	</tr>
</table>
		</section>
	</main>



</body>
</html>