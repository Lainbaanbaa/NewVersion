<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
EmpService navEmpSvc = new EmpService();
List<EmpVO> empList = navEmpSvc.login((String)session.getAttribute("account"),(String)session.getAttribute("password"));
session.setAttribute("empList", empList);
%> 

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/templates/assets/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/templates/assets/vendors/mdi/css/materialdesignicons.min.css">
	<style>
		.card-body{
			padding: 0 32px !important;
		}
		
		.identity{
			display:flex;
			align-items: center;
		}
	</style>
</head>


<body>
        <div class="row p-0 m-0 proBanner" id="proBanner">
        	<div class="card-body card-body-padding d-flex align-items-center justify-content-between">
        		<a class="navbar-brand" href="<%=request.getContextPath()%>/backend/index.jsp">
        		<img src="<%=request.getContextPath()%>/backend/asset/img/share_icon/ba-rei 02.png" width="100px" height="40px">
                </a>
                
                <c:if test="${empVO == null}">
            		<a class="d-flex log" href="<%=request.getContextPath()%>/backend/login/backLogin.jsp">
					<button class="btn btn-warning">員工登入</button>
					</a>
				</c:if>
				
				<c:if test="${empVO != null}">
					<div class="identity">
					<c:forEach var="empVO" items="${empList}" begin="0" end="0" >
             		<span class="name">${empVO.emp_name}&ensp;您好</span>&ensp;
					</c:forEach>
					<FORM ACTION="/CGA104G1/backend/login/LoginServlet" method="post" style="display:inline-block">
						<input type="hidden" name="action" value="logout">
						<button class="btn btn-warning" type=submit>登出</button>
					</FORM>
					</div>
				</c:if>
                
            </div>
        </div>
</body>

</html>