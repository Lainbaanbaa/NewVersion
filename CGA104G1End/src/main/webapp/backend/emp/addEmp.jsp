<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>

<%
EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>
<%
EmpService empSvc = new EmpService();
List<EmpVO> list = empSvc.getAll();
pageContext.setAttribute("list", list);
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

<div class= "container">

<table id="table-1">
	<tr><td>
		 <h3>員工資料新增 </h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/emp/select_page.jsp" class="btn btn-primary">回員工管理首頁</a></h4>
	</td></tr>
</table>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/emp/EmpServlet" name="form1">

		<table>
			<tr>
				<td>員工名稱:</td>
				<td><input type="TEXT" name="emp_name" size="45"
					value="<%=(empVO == null) ? "無名氏" : empVO.getEmp_name()%>" />
					</br><font color="red">${errorMsgs.emp_name}</font></td>
			</tr>

			<tr>
				<td>員工帳號:</td>
				<td><input type="TEXT" name="account" size="45"
					value="<%=(empVO == null) ? "123456" : empVO.getAccount()%>" /></br><font color="red">${errorMsgs.account}</font></td>
			</tr>
			<tr>
				<td>員工密碼:</td>
				<td><input type="password" name="password" size="45"
					value="<%=(empVO == null) ? "123456" : empVO.getPassword()%>" /></br><font color="red">${errorMsgs.password}</font></td>
			</tr>

			<tr>
				<td>員工到職時間:</td>
				<td><input name="onjob_date" id="f_date1" type="TEXT"  /></br><font color="red">${errorMsgs.onjob_date}</font></td>
			</tr>
			<tr>
				<tr>
				<td>員工狀態:</td>
				<input type=hidden name="emp_status"  id ="status">
				<td><select class="status" >
						<option value=" "selected></option>
 						<option value="0">離職</option>
						<option value="1" >在職</option>
				</select></br><font color="red">${errorMsgs.emp_status}</font></td></td></tr></table>
				<jsp:useBean id="effectSvc" scope="page" class="com.effect.model.EffectService"/>
				<div  id = effectstert>
				<table>
				<tr>

					<td>員工權限:&ensp;&ensp;&ensp;&ensp;</td>

						<td>
							<select   class="effect" name="effect_id" >
							<c:forEach var="effectVO" items="${effectSvc.all}" >
 								<option  id ="effectop" value="${effectVO.effect_id}">${effectVO.effect_name}</option>
							</c:forEach>
							</select>
						</td>

					</tr>

		</table>
		</div>	
		<br> 
		<div class="btnBlock">
		<input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增"class="btn btn-warning btnIn">
			</div>
	</FORM>
	</div>


<script type="text/javascript">
$(document).ready(function() {

	switch($('#status').val()){
		case '0':
			$('.status').val(0)
			break;
		case '1':
			$('.status').val(1)
	}
	$('.status').change(function () {
		$('#status').val($('.status option:selected').val());
	});
});

</script>


<%
java.sql.Date onjob_date = null;
try {
	onjob_date = empVO.getOnjob_date();
} catch (Exception e) {
	onjob_date = new java.sql.Date(System.currentTimeMillis());
}
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=onjob_date%>' , // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
        
</script>

</body>



</html>
