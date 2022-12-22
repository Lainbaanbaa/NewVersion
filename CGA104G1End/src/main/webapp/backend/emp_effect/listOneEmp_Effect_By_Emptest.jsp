<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.emp_effect.model.*"%>
<%@ page import="com.effect.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<% 
Emp_effectVO emp_effectVO = (Emp_effectVO) request.getAttribute("emp_effectVO");
%>
<%
EffectService effectSvc = new EffectService();
List<EffectVO> list = effectSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%
Emp_effectService emp_effectSvc = new Emp_effectService();
List<Emp_effectVO> list2 = emp_effectSvc.getAll();
pageContext.setAttribute("list2", list2);
%>
<%@include file="/backend/backNavbar.jsp"%>

<html>
<head>
<title>權限資料</title>

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
	background-color: white;
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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>權限資料</h3>
		 <h4><a href="./emp/listAllEmp.jsp">回員工查詢</a></h4>
	</td></tr>
</table>
<%-- <jsp:useBean id="effectSvc" scope="page" class="com.effect.model.EffectService"/> --%>
<table>
	<tr>
	<c:forEach var="effectVO" items="${list}" >
		<th>${effectVO.effect_name}</th>
		</c:forEach>              
		<th colspan="2">權限變更</th>
	</tr>
	<c:forEach var="emp_effectVO" items="${list2}" >
		<tr>
			<td >${emp_effectVO.effect_id}</td>
			<td>
			  <FORM METHOD="post" ACTION="Emp_effectServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="新增">
			     <input type="hidden" name="emp_id"  value="${emp_effectVO.emp_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="Emp_effectServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="effect_id"  value="${emp_effectVO.effect_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>	

</table>

</body>
</html>