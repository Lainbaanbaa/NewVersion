<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<html>



<head>
<title>員工資料</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
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

table {
 margin-left: auto;
 margin-right: auto;
 width: 800px;
 margin-top: 5px;
 margin-bottom: 5px;
}
th, td {
 padding: 5px;
 text-align: center;
}

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
  text-align: center !important;
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
   <div class="titleIn">查詢員工資料</div><br>
   <button onclick="location.href='<%=request.getContextPath()%>/backend/emp/select_page.jsp'" class="btn btn-primary btnIn">回員工管理首頁</button>
   </div>
<table class="styled-table">
  <thead>
   <tr>
    <th>員工編號</th>
    <th>員工姓名</th>
    <th>雇用日期</th>
    <th>員工狀態</th>
    <th colspan="2">變更資料</th>
    <th>查詢權限</th>
   </tr>
  </thead>
  <tbody>
   <c:forEach var="empVO" items="${listemp_and_effect}">
    <tr class="active-row" align='center' valign='middle'>
     <td>${empVO.emp_id}</td>
     <td>${empVO.emp_name}</td>
     <td>${empVO.onjob_date}</td>
     <c:if test="${empVO.emp_status==0}">
     <td><c:out value="離職"></c:out></td>
     </c:if>
     <c:if test="${empVO.emp_status==1}">
     <td><c:out value="在職"></c:out></td>
     </c:if>   
     <td>
      <FORM METHOD="post" ACTION="EmpServlet"
       style="margin-bottom: 0px;">
       <input type="submit" value="修改" class="btn btn-warning btnIn"> <input type="hidden"
        name="emp_id" value="${empVO.emp_id}"> <input
        type="hidden" name="action" value="getOne_For_Update">
      </FORM>
     </td>
     <td>
      <FORM METHOD="post" ACTION="EmpServlet"
       style="margin-bottom: 0px;">
       <input type="submit" value="刪除" class="btn btn-danger btnIn"> <input type="hidden"
        name="emp_id" value="${empVO.emp_id}"> <input
        type="hidden" name="action" value="delete">
      </FORM>
     </td>
     <td>
      <FORM METHOD="post"
       ACTION="../../backend/emp_effect/Emp_effectServlet"
       style="margin-bottom: 0px;">
       <input type="submit" id="listBtn" value="查詢" class="btn btn-info btnIn"> <input
        type="hidden" name="emp_id" value="${empVO.emp_id}"> <input
        type="hidden" name="action" value="getOne_For_Display">
      </FORM>
     </td>
    </tr>
   </c:forEach>
  </tbody>

 </table>

</div>
  </section>
 </main>

</body>
</html>