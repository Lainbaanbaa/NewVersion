<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
<title>員工權限首頁</title>
<style>
section{
	text-align: center;
}
</style>

</head>
<body>
<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
		<div class="btnTitle">
			<button onclick="location.href='<%=request.getContextPath()%>/backend/emp/listAllEmp.jsp'" class="btn btn-primary btnIn">回查詢員工資料</button>
		</div>
	<div class="titleBlock">員工權限管理首頁</div></br>	
		
  <font color="red">${errorMsgs.fail}</font>
  
  <jsp:useBean id="emp_effectSvc" scope="page" class="com.emp_effect.model.Emp_effectService" />
    <jsp:useBean id="effectSvc" scope="page" class="com.effect.model.EffectService" />
     <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
     	
     <FORM METHOD="post" ACTION="Emp_effectServlet" >
       <b>選擇權限編號:</b>
       <select size="1" name="effect_id">
         <c:forEach var="effectVO" items="${effectSvc.all}" > 
          <option value="${effectVO.effect_id}">[${effectVO.effect_id}]-${effectVO.effect_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_DisplayEffect">
       <input type="submit" value="送出" class="btn btn-info btnIn btnSmall">
    </FORM><br>
  
     <FORM METHOD="post" ACTION="Emp_effectServlet" >
       <b>選擇員工名稱:</b>
       <select size="1" name="emp_id">
        <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.emp_id}">[${empVO.emp_id}]-${empVO.emp_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="btn btn-info btnIn btnSmall">
     </FORM><br>
					&ensp;&ensp;
		<button onclick="location.href='<%=request.getContextPath()%>/backend/emp_effect/listAllEmp_Effect.jsp'" class="btn btn-dark btnL">查詢所有員工權限</button>	
		</section>
	</main>

</body>
</html>