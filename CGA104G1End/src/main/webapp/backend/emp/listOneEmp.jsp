<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.emp.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<title>員工資料</title>

<style>
section {
 			height: 100%; 
            background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
            background-color: #FFDEE9;
            background-repeat: no-repeat;
            background-size: cover;
        }

.box-a{width:789px;height:100px; float:left}
.box-b{width:px;height:100px; float:left}
</style>

<style>
  table {
    height:90px;
	width: 800px;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 10%;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
  	height:50px;
    padding: 5px;
    text-align: center;
    color: black;
  }
  
  th{
  	background-color: black;
  	color: white;
  }
  
  tr{
  text-align: center;
  }
  
  .btnTitle {
	margin-top: 2% !important;
	margin-bottom: 2% !important;
	text-align :center;
}
  
  .titleIn {
	font-size: 24px;
	color: black;
	font-weight: 700;
	text-align :center;
}

.btnIn {
	border-radius: 20px !important;
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
			<div class="titleIn">所有員工資料</div><br>
			<button onclick="location.href='<%=request.getContextPath()%>/backend/emp/select_page.jsp'" class="btn btn-primary btnIn">回員工管理首頁</button>
			</div>
<table>
		<tr>
			<th>員工編號</th>
			<th>員工姓名</th>
			<th>員工帳號</th>
			<th>員工密碼</th>
			<th>員工開始上班日</th>
			<th>員工狀態</th>
		</tr>
		<tr>
			<td><%=empVO.getEmp_id()%></td>
			<td><%=empVO.getEmp_name()%></td>
			<td><%=empVO.getAccount()%></td>
			<td><%=empVO.getPassword()%></td>
			<td><%=empVO.getOnjob_date()%></td>
			<c:if test="${empVO.getEmp_status()==0}">
			<td><c:out value="離職"></c:out></td>
			</c:if>
			<c:if test="${empVO.getEmp_status()==1}">
			<td><c:out value="在職"></c:out></td>
			</c:if>
<%-- 			<td><%=empVO.getEmp_status()%></td> --%>

		</tr>
	</table>
</div>
		</section>
	</main>

		</section>
	</main>

	
</body>
</html>