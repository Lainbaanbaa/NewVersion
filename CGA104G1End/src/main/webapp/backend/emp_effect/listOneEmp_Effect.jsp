<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.emp_effect.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
<jsp:useBean id="effectSvc" scope="page" class="com.effect.model.EffectService" />
<%-- <% --%>
<!-- // Integer emp_id =(Integer)request.getAttribute("emp_id"); -->
<!-- // Emp_effectService emp_effectSvc = new Emp_effectService(); -->
<!-- // List<Emp_effectVO> list = emp_effectSvc.getOneEmp(emp_id); -->
<!-- // pageContext.setAttribute("list", list); -->
<%-- %> --%>

<html>
<head>
<title>權限單查</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
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
			<button onclick="location.href='<%=request.getContextPath()%>/backend/emp/listAllEmp.jsp'" class="btn btn-primary btnIn">回查詢員工資料</button>
		</div>
				<div class="btnTitle">
			<button onclick="location.href='<%=request.getContextPath()%>/backend/emp_effect/select_page.jsp'" class="btn btn-primary btnIn">回查詢權限資料</button>
		</div>
		<div class="titleBlock">單一員工權限列表</div>
			<table>
	<tr>
		<th>員工姓名</th>
		<th>權限</th>
		<th colspan="2">資料變更</th>
		
	</tr>
	<c:forEach var="emp_effectVO" items="${emp_effectVO}" >
	<tr>
		<td>${emp_effectVO.empVO.emp_name}</td>
		<td>${emp_effectVO.effectVO.effect_name}</td>
		<td>
		  <FORM METHOD="post" ACTION="Emp_effectServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="新增" class="btn btn-warning btnIn btnSmall">
			     <input type="hidden" name="emp_id"  value="${emp_effectVO.emp_id}">
			     <input type="hidden" name="action"	value="go_Insert"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="Emp_effectServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-danger btnIn btnSmall">
			     <input type="hidden" name="effect_id"  value="${emp_effectVO.effect_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
	</c:forEach>
</table>
		</section>
	</main>



</body>
</html>