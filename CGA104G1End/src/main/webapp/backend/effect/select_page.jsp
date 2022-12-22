<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
<title>權限管理首頁</title>

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

			<div class="titleBlock">權限管理首頁</div>

<font color="red">${errorMsgs.fail}</font>
  
    <FORM METHOD="post" ACTION="EffectServlet" >
    	<b>輸入權限編號:</b>
        <input type="text" name="effect_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出" class="btn btn-info btnIn btnSmall">
      <br> <font color="red">${errorMsgs.effect_id}</font>
    </FORM><br> 

  <jsp:useBean id="effectSvc" scope="page" class="com.effect.model.EffectService" />
   
     <FORM METHOD="post" ACTION="EffectServlet" >
       <b>選擇權限編號:</b>
       <select size="1" name="effect_id">
         <c:forEach var="effectVO" items="${effectSvc.all}" > 
          <option value="${effectVO.effect_id}">[${effectVO.effect_id}]-${effectVO.effect_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="btn btn-info btnIn btnSmall">
    </FORM><br>
  
   
		<button onclick="location.href='<%=request.getContextPath()%>/backend/effect/addEffect.jsp'" class="btn btn-success btnL">新增權限</button>
					&ensp;&ensp;
		<button onclick="location.href='<%=request.getContextPath()%>/backend/effect/listAllEffect.jsp'" class="btn btn-dark btnL">查詢所有權限</button>	
		</section>
	</main>


		</section>
	</main>

</body>
</html>