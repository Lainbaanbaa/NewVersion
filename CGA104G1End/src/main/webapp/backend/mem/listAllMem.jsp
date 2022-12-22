<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>


<%
	MemService memSvc = new MemService();
    List<MemVO> list = memSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>會員帳號管理 </title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">

<style>
/* <!-- ===========================================樣式欄位================================================================== --> */
section {
 			height: 100%; 
            background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
            background-color: #FFDEE9;
            background-repeat: no-repeat;
            background-size: cover;
        }
.styled-table {
	margin-left: auto;
	margin-right: auto;
	border-collapse: collapse;
	margin: auto;
	font-size: 0.9em;
	font-family: sans-serif;
	min-width: 400px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
	padding: 0 10px !important;
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
	background-color: rgba(255,255,255,0.4);
}

.styled-table tbody tr:last-of-type {
	border-bottom: 2px solid #212529;
}

.styled-table tbody tr.active-row {
	font-weight: bold;
	color: #212529;
}

/* <!-- ===========================================樣式欄位================================================================== --> */
table#table-1 {
/* 	background-color: #212529; */
/* 	border: 2px solid black; */
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	width: 1000px;
	margin-top: 15px;
	margin-bottom: 5px;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h3 {
	color: black;
	font-weight: 700 !important;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	margin-left: auto;
	margin-right: auto;
	width: 100%;
	margin-top: 5px;
	margin-bottom: 5px;
}
th, td {
	padding: 5px;
	text-align: center;
}

th{
	text-align: center !important;
}

.btnIn{
	border-radius: 20px !important;
}

</style>



</head>
<body bgcolor='white'>

	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
<table id="table-1">
	<tr>
		<th>
		 <h3>會員帳號管理 </h3>
		 <h4><a class="btn btn-success" href="<%=request.getContextPath()%>/backend/mem/select_page.jsp">回到會員資料查詢</a></h4>
		</th>
	</tr>
</table>

	<table class="styled-table">
	<thead>
	<tr>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員姓名</th>
		<th>會員地址</th>
		<th>會員電話</th>
		<th>會員證號</th>
		<th>會員Email</th>
		<th>會員性別</th>
		<th>會員生日</th>
		<th>會員狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	</thead>

	<tbody>
	<c:forEach var="memVO" items="${list}">
		
		<tr class="active-row" align='center' valign='middle'>
			<td>${memVO.mem_id}</td>
			<td>${memVO.mem_account}</td>
			<td>${memVO.mem_name}</td>
			<td>${memVO.mem_address}</td>
			<td>${memVO.mem_phone}</td>
			<td>${memVO.mem_uid}</td> 
			<td>${memVO.mem_email}</td>
			<td>${memVO.mem_sex}</td>
			<td>${memVO.mem_dob}</td>


<!-- 		<td><select  name="mem_status"> -->
<%-- 				<option value="0" ${(memVO.mem_status==0)? 'selected':'' } >停權</option> --%>
<%-- 				<option value="1" ${(memVO.mem_status==1)? 'selected':'' } >未驗證</option> --%>
<%-- 				<option value="2" ${(memVO.mem_status==2)? 'selected':'' } >已驗證</option> --%>
<!-- 		</select></td> -->

	
			<c:if test="${memVO.mem_status==0}" >
					<td><c:out value="停權" ></c:out></td>
			</c:if>
			<c:if test="${memVO.mem_status==1}">
					<td><c:out value="未驗證"></c:out></td>
			</c:if>
			<c:if test="${memVO.mem_status==2}">
					<td><c:out value="已驗證"></c:out></td>
			</c:if>


			<td>
			  <FORM METHOD="post" ACTION="/CGA104G1/MemServlet" style="margin-bottom: 0px;">
			     <input type="submit" onclick="return up_confirm()" value="修改" class="btn btn-warning btnIn">
			     <input type="hidden" name="mem_id"  value="${memVO.mem_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="/CGA104G1/MemServlet" style="margin-bottom: 0px;">
			     <input type="submit" onclick="return de_confirm()" value="刪除" class="btn btn-danger btnIn">
			     <input type="hidden" name="mem_id"  value="${memVO.mem_id}">
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
		</section>
	</main>

</html>