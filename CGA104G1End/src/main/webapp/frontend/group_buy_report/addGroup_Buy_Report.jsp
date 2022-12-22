<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_buy_report.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
Group_Buy_ReportVO Group_Buy_ReportVO = (Group_Buy_ReportVO) request.getAttribute("Group_Buy_ReportVO");
%>

<html>
<head>
<title>團購檢舉</title>
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
table#table-1 {
	background-color: #212529;
	border: 2px solid black;
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

body {
	height: 100%; 
    background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
    background-color: #FFDEE9;
    background-repeat: no-repeat;
    background-size: cover;
    text-align: center;
}

.titleBlock {
	text-align: center;
	font-size: 24px;
	font-weight: 700;
}

input {
	height: 40px;
}

.subBlock {
	text-align: center;
}  

.btnIn {
	width: 200px;
	border-radius: 20px !important;
} 

select, input {
    	width: 300px;
    	height: 30px;
    	border-radius: 20px;
    	border: none;
    	text-align: center;
    }
    
select:focus, input:focus {
	border: 2px solid pink !important;
}  

</style>

</head>
<body bgcolor='white'>

	<main>
		<section>


<h3>團購檢舉</h3>
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


<!-- 	<tr> -->
<!-- 		<td>團購檢舉編號:</td> -->
<!-- 		<td><input type="TEXT" name="gbfrep_id" size="45"  -->
<%-- 			 value="<%= (Group_Buy_ReportVO==null)? "" : Group_Buy_ReportVO.getGbfrep_id()%>" /></td> --%>
<%-- 			 <td style="color:red">${errorMsgs.gbfrep_id}</td> --%>
<!-- 	</tr> -->
	<tr>
		<td><b>團購訂單編號:</b></td>
		<td><input type="TEXT"  name="gborder_id" size="35" 
			 value="<%= (Group_Buy_ReportVO==null)? "" : Group_Buy_ReportVO.getGborder_id()%>" /></td>
			 <td style="color:red">${errorMsgs.gborder_id}</td>
	</tr>
	<tr>
		<td><b>會員編號:</b></td>
		<td><input type="TEXT"  name="mem_id" size="35" 
			 value="<%= (Group_Buy_ReportVO==null)? "": Group_Buy_ReportVO.getMem_id()%>" /></td>
			 <td style="color:red">${errorMsgs.mem_id}</td>
			 
	</tr>
	
		<tr>
		<td><b>團購檢舉內容:</b></td>
		<td><input type="TEXT" name="frep_content" size="35" 
			 value="<%= (Group_Buy_ReportVO==null)? "" : Group_Buy_ReportVO.getFrep_content()%>" /></td>
			 <td style="color:red">${errorMsgs.frep_content}</td>
	</tr>


	  <jsp:useBean id="Group_Buy_ReportSvc" scope="page" class="com.group_buy_report.model.Group_Buy_ReportService" />
<%-- 	  <jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" /> --%>
<!-- 		<tr> -->
<!-- 			<td> -->
<!--        <b>選擇會員編號:</b> -->
<!--        <select size="1" name=mem_id> -->
<%--           <c:forEach var="memVO" items="${memSvc.all}" >  --%>
<%--           <option value="${memVO.mem_id}">${memVO.mem_name}</option> --%>
<%--           </c:forEach>    --%>
<!--        </select> -->
<%--        <td style="color:red">${errorMsgs.mem_id}</td> --%>
<!-- 		</tr> -->
<!-- 			</td> -->

<!-- 		<tr> -->
<!-- 		<td><b>團購檢舉狀態:</b></td> -->
<!-- 		<td><input type="TEXT" name="frep_status" size="45"  -->
<%-- 			 value="<%= (Group_Buy_ReportVO==null)? "" : Group_Buy_ReportVO.getFrep_status()%>" /></td> --%>
<%-- 			 <td style="color:red">${errorMsgs.frep_status}</td> --%>
<!-- 		</tr> -->

<!-- 		<tr> -->
<!-- 		<td> -->
<!-- 		<b>團購檢舉狀態:</b> -->
<%-- 		<input type="radio" name="frep_status" size="45" value="0" ><b>待審核</b>		${(Group_Buy_ReportVO.frep_status=="0")? 'checked':'' } --%>
<%-- 		<input type="radio" name="frep_status" size="45" value="1" ><b>已審核</b>		${(Group_Buy_ReportVO.frep_status=="1")? 'checked':'' } --%>
<%-- 		<input type="hidden" name="frep_status" value="${Group_Buy_ReportVO.frep_status}"> --%>
<%-- 		</td><td style="color:red">${errorMsgs.frep_status}</td> --%>
<!-- 		</tr> -->


<!-- 		<tr> -->
<!-- 		<td> -->
<!-- 		<b>團購檢舉審核結果:</b> -->
<%-- 		<input type="radio" name="frep_result" size="45" value="0" ${(Group_Buy_ReportVO.frep_result=="0")? 'checked':'' }><b>尚未審核完畢</b> --%>
<%-- 		<input type="radio" name="frep_result" size="45" value="1" ${(Group_Buy_ReportVO.frep_result=="1")? 'check':'' }><b>檢舉屬實</b> --%>
<%-- 		<input type="radio" name="frep_result" size="45" value="2" ${(Group_Buy_ReportVO.frep_result=="2")? 'check':'' }><b>檢舉不屬實</b> --%>
<%-- 		<input type="hidden" name="frep_result" value="${Group_Buy_ReportVO.frep_result}"> --%>
<%-- 			</td><td style="color:red">${errorMsgs.frep_result}</td> --%>
<!-- 		</tr> -->

		
<!-- 				<tr> -->
<!-- 		<td><b>團購檢舉審核結果:</b></td> -->
<!-- 		<td><input type="TEXT" name="frep_result" size="45"  -->
<%-- 			 value="<%= (Group_Buy_ReportVO==null)? "" : Group_Buy_ReportVO.getFrep_result()%>" /></td> --%>
<%-- 			 <td style="color:red">${errorMsgs.frep_result}</td> --%>
<!-- 	</tr> -->
		
<!-- 			<tr> -->
<!-- 		<td>員工編號:</td> -->
<!-- 		<td><input type="TEXT" name="emp_id" size="45"  -->
<%-- 			 value="<%= (Group_Buy_ReportVO==null)? " " : Group_Buy_ReportVO.getGborder_id()%>" /></td> --%>
<%-- 			 <td style="color:red">${errorMsgs.emp_id}</td> --%>
<!-- 	</tr> -->
	
	

</table>
<br>
<div class="subBlock">
</div><input type="hidden" name="action" value="insert" >
<input type="hidden" name="emp_id" value="3">
<input class="btn btn-success btnIn" type="submit" value="送出">
</div></FORM>
		</section>
	</main>

<!--  NavBar  -->
<script src="<%=request.getContextPath() %>/resources/static/js/navbar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/getName.js"></script>
<!--  Cart -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/cart.js"></script>
</body>


</html>