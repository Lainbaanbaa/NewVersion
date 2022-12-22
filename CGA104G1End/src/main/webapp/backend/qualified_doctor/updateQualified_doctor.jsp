<%@page import="com.qualified_doctor.model.Qualified_doctorVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
Qualified_doctorVO qualified_doctorVO = (Qualified_doctorVO) request.getAttribute("qualified_doctorVO");
%>
<%-- <%= memVO==null %> --%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>認證醫師修改 </title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">

<style>
	section {
 			height: 100%;
            background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
            background-color: #FFDEE9;
            background-repeat: no-repeat;
            background-size: cover;
        }
  table#table-1 {
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  b{
      color: black;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid black;
  }
  th, td {
    padding: 1px;
  }

  h3 {
  	text-align: center;
  	color: black;
  	font-weight: 700 !important;
  }

  .container {
  	text-align: center;
  }

  .block{
  	texe-align: center;
  	margin-top: 15px;
  }

  table{
  	width: 100%;
  }

  .btnIn{
  	border-radius: 20px !important;
  	width: 200px;
  	margin-bottom: 20px;
  }

  .formIn{
  	margin-left: 25%;
  }

  .btn-warning{
  	margin-top: 15px;
  }

</style>
</head>
<body>

	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
		<div class="container">
<table id="table-1">
	<tr><td>
	<div class="block">
		 <h3>醫師認證狀態</h3>
			<button class="btn btn-dark btnIn" onclick="location.href='<%=request.getContextPath()%>/backend/index.jsp'">回到後台首頁</button>
	</div>
	</td></tr>
</table>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="formIn">
<FORM METHOD="post" ACTION="/CGA104G1/Qualified_doctorServlet" name="form1">
<table>

<!-- 	<tr> -->
<!-- 		<td>認證醫師編號:</td> -->
<!-- 		<td><input type="TEXT" name="doc_id" size="45"  -->
<%-- 			 value="<%= (qualified_doctorVO==null)? "" : qualified_doctorVO.getDoc_id()%>" /></td> --%>
<!-- 	</tr> -->

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
		<tr>
		<td style="color:gray"><b>會員編號:</b></td>
		<td><input type="TEXT" name="mem_id" readonly style="color:gray" size="45"
			value="${qualified_doctorVO.mem_id}"></td>
<%-- 			 value="<%= (qualified_doctorVO==null)? "1" : qualified_doctorVO.getMem_id()%>" /></td> --%>
	</tr>

<!-- 	<tr> -->
<!-- 		<td>會員帳號:</td> -->
<!-- 		<td><input type="TEXT" name="mem_account" size="45"  -->
<%-- 			 value="<%= (memVO==null)? "xyz" : memVO.getMem_account()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 		<tr> -->
<!-- 	<tr> -->
<!-- 		<td>會員姓名:</td> -->
<!-- 		<td><input type="TEXT" name="mem_name" size="45"  -->
<%-- 			 value="<%= (memVO==null)? "Amy" : memVO.getMem_name()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 		<tr> -->
<!-- 		<td>會員Email:</td> -->
<!-- 		<td><input type="TEXT" name="mem_email" size="45" -->
<%-- 			 value="<%= (memVO==null)? "abc123@gmail.com" : memVO.getMem_email()%>" /></td> --%>
<!-- 	</tr> -->

		<tr>
		<td ><b>狀態修改:</b></td>
		<td>

		<input type="radio" name="doc_status" size="45" value="0" ${(qualified_doctorVO.doc_status==0)? 'checked':'' }><b>關閉中</b>
		<input type="radio" name="doc_status" size="45" value="1" ${(qualified_doctorVO.doc_status==1)? 'checked':'' }><b>已開啟</b>
		<input type="hidden" name="doc_status" value="${qualified_doctorVO.doc_status}">
		</td><td style="color:red">${errorMsgs.doc_status}</td>
		</tr>


</table>
</div>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="doc_id" value="<%=qualified_doctorVO.getDoc_id()%>">
<input type="submit" value="送出修改" class="btn btn-success btnIn"></FORM>

</div>
		</section>
	</main>

<script>		console.log(${qualified_doctorVO.doc_status});</script>
</body>

</html>
