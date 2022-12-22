<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%
EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>
<html>
<head>
<title>員工資料修改</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">

<style>
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
	display: flex;
	justify-content: center;
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

h3 {
	color: black;
	font-weight: 700 !important;
	text-align: center;
}

h4 {
	color: blue;
	display: inline;
	text-align: center;
}

</style>

<style>
table {
	margin-left: auto;
	margin-right: auto;
	width: 50%;
	margin-top: 5px;
	margin-bottom: 5px;
}
th, td {
	padding: 5px;
	text-align: left;
}

td {
	color: black;
	font-weight: 700;
}

.btnSub{
	width: 200px;
	border-radius: 20px !important;
}

select, input {
    	width: 350px;
    	height: 30px;
    	border-radius: 20px;
    	border: none;
    	text-align: center;
    }
    
select:focus, input:focus {
	border: 2px solid pink !important;
}

.btnBlock{
	text-align: center;
}

.btnIn {
	border-radius: 20px !important;
}


font {
	font-size: 8px;
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
		 <h3>員工資料修改 </h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/emp/select_page.jsp" class="btn btn-primary">回員工管理首頁</a></h4>
	</td></tr>
</table>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp/EmpServlet" name="form1">
		<table>
		
			<tr>
				<td>員工姓名:</td>
				<td><input type="TEXT" name="emp_name" size="45"
					value="<%=empVO.getEmp_name()%>" /><br><font color=red>${errorMsgs.emp_name}</font></td>
			</tr>
			<tr>
				<td>員工帳號:</td>
				<td><input type="TEXT" name="account" size="45"
					value="<%=empVO.getAccount()%>" /><br><font color=red>${errorMsgs.account}</font></td>
			</tr>
			<tr>
				<td>員工密碼:<font color=red><b>*</b></font></td>
				<td><input type="password" name="password"  size="45"
					value="<%=empVO.getPassword()%>" /><br><font color=red>${errorMsgs.password}</font></td>
			</tr>
			<tr>
				<td>雇用日期:</td>
				<td><input name="onjob_date" id="f_date1" type="text"><br><font color=red>${errorMsgs.f_date1}</font></td>
			</tr>
			<tr>
				<td>員工狀態:</td>
<!-- 				<input type=hidden name="emp_status"  id ="status"> -->
				<td><select class="status"  name="emp_status" >
 						<option value="0" ${(empVO.emp_status==0)? 'selected': ''}>離職</option> 
						<option value="1" ${(empVO.emp_status==1)? 'selected': ''}>在職</option>			
<!--  						<option value="0" >離職</option>  -->
<!-- 						<option value="1" >在職</option>			 -->
				</select></td>
		</table>

		<br> 
		<div class="btnBlock">
		<input type="hidden" name="action" value="update"> <input
			type="hidden" name="emp_id" value="<%=empVO.getEmp_id()%>"> 
			<input type="submit" value="送出修改" class="btn btn-success btnIn">
			</div>
	</FORM>
	</div>
		</section>
	</main>



</body>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: "<%=empVO.getOnjob_date()%>" , // value:   new Date(),
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		startDate : 'new Date()', // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
</html>