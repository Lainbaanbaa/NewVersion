<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="com.emp_effect.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.effect.model.*"%>
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
<jsp:useBean id="effectSvc" scope="page" class="com.effect.model.EffectService"/>

<%
Integer emp_id = (Integer)session.getAttribute("emp_id");

Emp_effectVO emp_effectVO = (Emp_effectVO) request.getAttribute("Emp_effectVO");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
<title>權限新增資料 </title>

<style>
  table {
	width: 450px;
	margin-top: 1px;
	margin-bottom: 1px;
	margin-left: 30%;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }

</style>

</head>
<body>

<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
			<%-- 錯誤表列 --%>
			<div class="btnTitle">
			<button onclick="location.href='<%=request.getContextPath()%>/backend/emp/listAllEmp.jsp'" class="btn btn-primary btnIn">回員工查詢</button>
		</div>
		<div class="btnTitle">
			<button onclick="location.href='<%=request.getContextPath()%>/backend/emp_effect/select_page.jsp'" class="btn btn-primary btnIn">查詢權限</button>
		</div>
		<div class="titleBlock">新增員工權限</div>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp_effect/Emp_effectServlet" name="form1">
<table>
	<tr>
		<td>員工姓名:</td>
		<td><input type="${emp_effectVO.emp_id ==null? 'TEXT':'hidden'}" name="emp_id" size="45" 
			 value="${emp_effectVO.emp_id}"  />${emp_effectVO.empVO.emp_name}</td>
	</tr>
	<tr>
		<td>權限:</td>
		
		<td>
		<select class="effect" name="effect_id">
		<c:forEach var="effectVO" items="${effectSvc.all}">
		<option value="${effectVO.effect_id}">${effectVO.effect_name}</option>
		</c:forEach>
		</select>
		</br><font color="red">${errorMsgs.effect_id}</font>
		</td>
		
	</tr>


</table>
<br>
<div class="subBlock">
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" class="btn btn-success btnIn"></FORM>
</div>
		</section>
	</main>



</body>