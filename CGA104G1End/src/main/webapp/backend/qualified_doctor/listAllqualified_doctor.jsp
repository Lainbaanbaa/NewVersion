<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qualified_doctor.model.*"%>

<%
	Qualified_doctorService qualified_doctorSvc = new Qualified_doctorService();
    List<Qualified_doctorVO> list = qualified_doctorSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<%-- <jsp:useBean id="qualified_doctorSvc" scope="page" class="com.qualified_doctor.model.Qualified_doctorService" /> --%>
<html>
<head>
<title>認證醫師</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
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
}

.styled-table thead tr {
	background-color: gray;
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
b {
	color: black;
}
table#table-1 {
	text-align: left;
	margin-left: auto;
	margin-right: auto;
	width: 1000px;
	margin-top: 5px;
	margin-bottom: 5px;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

.h3 {
	color: black;
	font-weight: 700 !important;
	display: flex;
	justify-content: center;
}

.btnBlock{
	display: flex;
	justify-content: center;
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
	text-align: left;
}

td{
	color: black;
	font-weight: 600;
}

thead {
	color: white !important;
}

.btnIn{
	border-radius: 20px !important;
}

.tdForm{
	text-align: center !important;
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
		<td>
		 	<div class="h3">認證醫師總覽</div>
		 	<div class="btnBlock">
			<button class="btn btn-dark" onclick="location.href='<%=request.getContextPath()%>/backend/index.jsp'">回到後台首頁</button>&ensp;
			<button class="btn btn-success" onclick="location.href='<%=request.getContextPath()%>/backend/qualified_doctor/addQualified_doctor.jsp'">認證醫師開通</button>
			</div>
		</td>
	</tr>
</table>

	<table class="styled-table">
		<thead>
			<tr style="background-color: black;">
		<th> 醫師編號 </th>
		<th> 會員編號 </th>
		<th> 認證醫師狀態 </th>
		<th class="tdForm"> 修改 </th>
		<th class="tdForm"> 刪除 </th>
	</tr>
		</thead>
		<tbody>
	<c:forEach var="qualified_doctorVO" items="${list}">
		
</tr>

			<td> ${qualified_doctorVO.doc_id} </td>

				<td>

				<div>

				<c:forEach var="memVO" items="${memSvc.all}">
                <c:if test="${qualified_doctorVO.mem_id==memVO.mem_id}"> 
	            	${memVO.mem_id}-【${memVO.mem_name} 醫師】
	            	 
                </c:if>
                </c:forEach>

				</div>

				</td>

<!-- 		<td><select  name="doc_status"> -->
<%-- 				<option value="0" ${(qualified_doctorVO.doc_status==0)? 'selected':'' } >關閉中</option> --%>
<%-- 				<option value="1" ${(qualified_doctorVO.doc_status==1)? 'selected':'' } >已啟用</option> --%>
<!-- 		</select></td> -->
			
				<c:if test="${qualified_doctorVO.doc_status==0}" >
					<td> <c:out value="關閉中" ></c:out> </td>
				</c:if>
				<c:if test="${qualified_doctorVO.doc_status==1}">
					<td> <c:out value="已啟用"></c:out> </td>
				</c:if>

			<td class="tdForm">
			  <FORM METHOD="post" ACTION="/CGA104G1/Qualified_doctorServlet" style="margin-bottom: 0px;">
			     <input type="submit" class="btn btn-warning btnIn" onclick="return up_confirm()" value="修改">
			     <input type="hidden" name="doc_id"  value="${qualified_doctorVO.doc_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td class="tdForm">
			  <FORM METHOD="post" ACTION="/CGA104G1/Qualified_doctorServlet" style="margin-bottom: 0px;">
			     <input type="submit" class="btn btn-danger btnIn" onclick="return de_confirm()" value="刪除">
			     <input type="hidden" name="doc_id"  value="${qualified_doctorVO.doc_id}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
</tbody>

	</table>


		</section>
	</main>

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

</script>
</body>
</html>