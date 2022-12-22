<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.emp_effect.model.*"%>
<%@ page import="com.effect.model.*"%>
<%
EmpService empSvc = new EmpService();
List<EmpVO> list = empSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%
Emp_effectService emp_effectSvc = new Emp_effectService();
List<Emp_effectVO> list2 = emp_effectSvc.getAll();
pageContext.setAttribute("list2", list2);
%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<title>所有員工資料</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
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
<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>

			<div class="btnTitle">
			<div class="titleIn">所有員工資料</div><br>
			<button onclick="location.href='<%=request.getContextPath()%>/backend/emp/select_page.jsp'" class="btn btn-primary btnIn">回員工管理首頁</button>
			</div>
			<div class="btnTitle">
			<button onclick="location.href='<%=request.getContextPath()%>/backend/emp_effect/select_page.jsp'" class="btn btn-primary btnIn">員工權限查詢</button>
		</div>
<table>
	<tr >
		<th>員工姓名</th>
		<th>員工帳號</th>
		<th>員工密碼</th>
		<th>員工到職時間</th>
		<th>員工狀態</th>
		<th colspan="2">資料變更</th>
		<th>員工權限查詢</th>
	</tr>	
	<c:forEach var="empVO" items="${list}" >
		<tr>
			<td>${empVO.emp_name}</td>
			<td>${empVO.account}</td>
			<td>${empVO.password}</td>
			<td>${empVO.onjob_date}</td>
			
			<c:if test="${empVO.emp_status==0}">
			<td><c:out value="離職"></c:out></td>
			</c:if>
			<c:if test="${empVO.emp_status==1}">
			<td><c:out value="在職"></c:out></td>
			</c:if>			
		<td>
		
		
			  <FORM METHOD="post" ACTION="EmpServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-warning btnIn">
			     <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="EmpServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-danger btnIn">
			     <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="../../backend/emp_effect/Emp_effectServlet" style="margin-bottom: 0px;">
			     <input type="submit" id="listBtn" value="查詢" class="btn btn-info btnIn">
			     <input type="hidden" name="emp_id"  value="${empVO.emp_id}">
			     <input type="hidden" name="action" value="getOne_For_Display"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
		</section>
	</main>

</body>
</html>