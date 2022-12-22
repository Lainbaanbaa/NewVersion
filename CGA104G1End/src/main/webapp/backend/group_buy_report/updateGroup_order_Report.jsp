<%@page import="com.group_buy_report.model.Group_Buy_ReportVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.group_buy_order.model.*"%>

<%
Group_Buy_ReportVO Group_Buy_ReportVO = (Group_Buy_ReportVO) request.getAttribute("Group_Buy_ReportVO");

%>

<%= Group_Buy_ReportVO==null %>
<html>
<head>
<title>團購檢舉修改</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>

<style>
/* <!-- ===========================================樣式欄位================================================================== --> */

/* tr:nth-child(odd){ */
/*   background:white; */
/* } */

/* tr:nth-child(even){ */
/*   background:#a4a9ad; */
/* } */
.styled-table {
	margin-left: auto;
	margin-right: auto;
	border-collapse: collapse;
	margin: auto;
	font-size: 0.9em;
	font-family: sans-serif;
	min-width: 400px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.styled-table thead tr {
	background-color: #212529;
	color: #ffffff;
	text-align: left;
}

.styled-table th, .styled-table td {
	padding: 12px 15px;
}

.styled-table tbody tr {
	border-bottom: 1px solid #dddddd;
}

.styled-table tbody tr:nth-of-type(even) {
	background-color: #f3f3f3;
}

.styled-table tbody tr:last-of-type {
	border-bottom: 2px solid #212529;
}

.styled-table tbody tr.active-row {
	font-weight: bold;
	color: #212529;
}

/* <!-- ===========================================樣式欄位================================================================== --> */
h4 {
	color: blue;
	display: inline;
}

/*   a{ */
/*     color: white; */
/*     display: inline; */
/*   } */
</style>

<style>
table {
	margin-left: auto;
	margin-right: auto;
	width: 50%;
	margin-top: 5px;
	margin-bottom: 5px;
}
/*    table, th, td {  */
/*      border: 1px solid #212529;  */
/*    }  */
th, td {
	padding: 5px;
	text-align: left;
}

.btn-warning{
  	margin-top: 15px;
  }

  .btnTitle{
  	margin-top: 25px;
  }

</style>

</head>
<body bgcolor='white'>

	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>

<div class="btnTitle">
<button onclick="location.href='<%=request.getContextPath()%>/backend/group_buy_report/listAllGroup_buy_Report.jsp'" class="btn btn-primary btnIn">回團購檢舉首頁</button>
</div>
<div class="titleBlock">修改團購檢舉</div>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Group_Buy_ReportServlet" name="form1">
<table>

	<tr>
		<td style="color: gray"><b>團購檢舉編號:</b></td>
		<td><input type="TEXT" name="gbfrep_id" readonly style="color: gray" size="45" value="<%=Group_Buy_ReportVO.getGbfrep_id()%>" /></td>
	</tr>
	<tr>
		<td style="color: gray"><b>團購訂單編號:</b></td>
		<td><input type="TEXT" name="gborder_id" readonly style="color: gray" size="45"	value="<%=Group_Buy_ReportVO.getGborder_id()%>" /></td>
	</tr>
	  <jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
	<tr>
		<td style="color: gray"><b>會員編號:</b></td>
		<td><input type="TEXT" name="mem_id" readonly style="color: gray" size="45" value="<%=Group_Buy_ReportVO.getMem_id()%>" /></td>
	</tr>
	<tr>
		<td><b style="color: gray">團購檢舉內容:</b></td>
		<td><input type="TEXT" name="frep_content" readonly style="color: gray" size="45"	value="<%=Group_Buy_ReportVO.getFrep_content()%>" /></td>
	</tr>

<!-- 		<tr> -->
<!-- 		<td style="color: gray"><b>團購檢舉狀態:</b></td> -->
<!-- 		<td> -->
<%-- 		<input type="radio" name="frep_status" size="45" onclick="return false" style="color: gray" value="0" ${(Group_Buy_ReportVO.frep_status=="0")? 'checked':'' }><b style="color: gray">待審核</b> --%>
<%-- 		<input type="radio" name="frep_status" size="45" onclick="return false" value="1" ${(Group_Buy_ReportVO.frep_status=="1")? 'checked':'' }><b style="color: gray">已審核</b> --%>
<%-- 		<input type="hidden" name="frep_status" value="${Group_Buy_ReportVO.frep_status}"> --%>
<!-- 		</td> -->
<!-- 		</tr> -->

	<tr >
		<td><b>團購檢舉審核結果:</b><font color=red><b>*</b></font></td>
		<td><select size="1" name="frep_result">
				<option value="0" ${(Group_Buy_ReportVO.frep_result==0)? 'selected':'' } >尚未審核完畢</option>
				<option value="1" ${(Group_Buy_ReportVO.frep_result==1)? 'selected':'' } >檢舉屬實</option>
				<option value="2" ${(Group_Buy_ReportVO.frep_result==2)? 'selected':'' } >檢舉不屬實</option>
		</select></td>
	</tr>
<!-- 	<tr > -->
<!-- 		<td>員工編號:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="emp_id"> -->
<%-- 				<option value="0" ${(Group_Buy_ReportVO.emp_id==0)? 'selected':'' } >停權</option> --%>
<%-- 				<option value="1" ${(Group_Buy_ReportVO.emp_id==1)? 'selected':'' } >未驗證</option> --%>
<%-- 				<option value="2" ${(Group_Buy_ReportVO.emp_id==2)? 'selected':'' } >已驗證</option> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

	  <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
       	<tr >
		<td><b>選擇員工:</b></td>
       	  <td><select size="1" name=emp_id>
          <c:forEach var="EmpVO" items="${empSvc.all}" >
          <option value="${EmpVO.emp_id}">${EmpVO.emp_id} -【${EmpVO.emp_name}】

         </c:forEach>
       </select>
       </td>


	</tr>



</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="gbfrep_id" value="<%=Group_Buy_ReportVO.getGbfrep_id()%>">
<input type="submit" style="margin-left:39%" value="送出修改" class="btn btn-success btnIn">
</FORM>
		</section>
	</main>

</body>

</html>
