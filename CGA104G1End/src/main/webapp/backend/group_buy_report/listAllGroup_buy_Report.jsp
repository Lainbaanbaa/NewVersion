<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_buy_report.model.*"%>


<%
	Group_Buy_ReportService GBRSvc = new Group_Buy_ReportService();
    List<Group_Buy_ReportVO> list = GBRSvc.getAll();
    pageContext.setAttribute("list",list);
%>



<html>
<head>
<title>團購檢舉</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">


<style>
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

b{
	color: black;
}

h3 {
	color: #6c757d;
}

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
	width: 80%;
	margin-top: 5px;
	margin-bottom: 5px;
}
/*    table, th, td {  */
/*      border: 1px solid #212529;  */
/*    }  */
th, td {
	padding: 5px;
	text-align: center;
}

tr {
	text-align: center !important;
}

tr:nth-child(even) {
	background-color: rgba(255,255,255,0.4) !important;
}

.btn-warning{
  	margin-top: 15px;
  }
  
  .btnTitle{
  	margin-top: 25px;
  }
  
.btnSmall {
	margin-top: 0;
}

td{
	font-weight: 700;
}

</style>

</head>
<body>

	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
	<div class="titleBlock">團購檢舉管理</div>


	<table class="styled-table">
		<thead>
	<tr>
		<th>團購檢舉編號</th>
		<th>團購訂單編號</th>
		<th>會員編號</th>
		<th>團購檢舉內容</th>
		<th>團購檢舉時間</th>
		<th>團購檢舉狀態</th>
		<th>團購檢舉審核結果</th>
		<th>員工編號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
		</thead>
		<tbody>
	<c:forEach var="Group_Buy_ReportVO" items="${list}">
		
		<tr>
			<td>${Group_Buy_ReportVO.gbfrep_id}</td>
			<td>${Group_Buy_ReportVO.gborder_id}</td>
			<td>${Group_Buy_ReportVO.mem_id}</td>
			<td>${Group_Buy_ReportVO.frep_content}</td>
			<td>${Group_Buy_ReportVO.frep_time}</td>




<!-- 		<td><select  name="mem_status"> -->
<%-- 				<option value="0" ${(memVO.mem_status==0)? 'selected':'' } >停權</option> --%>
<%-- 				<option value="1" ${(memVO.mem_status==1)? 'selected':'' } >未驗證</option> --%>
<%-- 				<option value="2" ${(memVO.mem_status==2)? 'selected':'' } >已驗證</option> --%>
<!-- 		</select></td> -->

	
			<c:if test="${Group_Buy_ReportVO.frep_status==0}" >
					<td><b><c:out value="待審核" ></b></c:out></td>
			</c:if>
			<c:if test="${Group_Buy_ReportVO.frep_status==1}">
					<td><b><c:out value="已審核"></b></c:out></td>
			</c:if>

			
			
			<c:if test="${Group_Buy_ReportVO.frep_result==0}" >
					<td><b><c:out value="尚未審核完畢" ></b></c:out></td>
			</c:if>
			<c:if test="${Group_Buy_ReportVO.frep_result==1}">
					<td><b><c:out value="檢舉屬實"></b></c:out></td>
			</c:if>
			<c:if test="${Group_Buy_ReportVO.frep_result==2}">
					<td><b><c:out value="檢舉不屬實"></b></c:out></td>
			</c:if>

			<td><b>${Group_Buy_ReportVO.emp_id}</b></td> 


			<td>
			  <FORM METHOD="post" ACTION="/CGA104G1/Group_Buy_ReportServlet" style="margin-bottom: 0px;">
<!-- 			     <input type="submit" onclick="return up_confirm()" value="修改"> -->
			     <button type="submit" onclick="return up_confirm()"  class="btn btn-warning btnIn btnSmall">修改</button>
			     <input type="hidden" name="gbfrep_id"  value="${Group_Buy_ReportVO.gbfrep_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Group_Buy_ReportServlet" style="margin-bottom: 0px;">
<!-- 			     <input type="submit" onclick="return de_confirm()" value="刪除"> -->
			     <button type="submit" onclick="return up_confirm()"  class="btn btn-danger btnIn btnSmall">刪除</button>
			     <input type="hidden" name="gbfrep_id"  value="${Group_Buy_ReportVO.gbfrep_id}">
			     <input type="hidden" name="action" value="delete">
			   </FORM>
			</td>
		</tr>
	</c:forEach>
</tbody>

	</table>


<script>

function up_confirm(){
	var r=confirm("你確定要修改嗎?")
  		if (r==true){
	   		return true;
  		}else{
	 		 return false;
  		}
 }
  
function de_confirm(){
	var r=confirm("你確定要刪除嗎?")
		if (r==true){
	  		alert("成功刪除");
  		}else{
	  		return false;
  		}
}

// window.onbeforeunload = function(){

// return confirm('Are you sure you want to leave this page?');

</script>

</body>
</html>