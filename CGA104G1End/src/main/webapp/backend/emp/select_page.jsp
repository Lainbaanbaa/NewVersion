<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<title>員工查詢</title>

<style>
section {
 			height: 100%; 
            background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
            background-color: #FFDEE9;
            background-repeat: no-repeat;
            background-size: cover;
        }
        
form{
	text-align: center;
}

h4 {
	color: blue;
	display: inline;
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

.btnTitle {
	margin-top: 2% !important;
	margin-bottom: 2% !important;
	display: flex;
	justify-content: center;
}

.titleIn {
	font-size: 24px;
	color: black;
	font-weight: 700;
	text-align: center;
}

.btnIn {
	width: 200px;
	border-radius: 20px !important;
}

.btnSub {
	width: 405px;
	border-radius: 20px !important;
}
</style>

</head>

<body>

	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>

	
	<div class="titleIn">員工管理</div><br>


		<FORM METHOD="post" ACTION="EmpServlet" name="form1">
		  
			<font style="color:red">${errorMsgs.fail}</font></br>
			 <span
				class="label-desc">輸入員工編號:</span> <input type="text" name="emp_id"
				value=""><br><br> <span>輸入員工姓名:</span> <input type="text"
				name="emp_name" value=""><br><br>

			<div class="select-box">
				<label for="select-box1"> <span>員工狀態:</span>&ensp;&ensp;&ensp;&ensp;
				</label> <select name ="emp_status">
					<option value=" "></option>
					<option value="0">離職</option>
					<option value="1" selected>在職</option>
				</select>
			</div><br>
			<b>雇用日期:</b>&ensp;&ensp;&ensp;&ensp;
	   <input name="onjob_date" id="f_date1" type="text"><br><br>
			
			
			<input type="submit" value="送出" class="btn btn-info btnSub"> <input type="hidden"
				name="action" value="listemp_and_effect">
		</FORM>
		</li>
	</ul>
	<div class="btnTitle">
	<button onclick="location.href='<%=request.getContextPath()%>/backend/emp/listAllEmp.jsp'" class="btn btn-dark btnIn">檢視所有員工</button>&ensp;
	<button onclick="location.href='<%=request.getContextPath()%>/backend/emp/addEmp.jsp'" class="btn btn-success btnIn">新增員工</button>
	</div>
		</section>
	</main>


	


		</section>
	</main>

</body>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script type="text/javascript">
$.datetimepicker.setLocale('zh');
$('#f_date1').datetimepicker({
    theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '',              // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});

const selected = document.querySelector(".selected");
const optionsContainer = document.querySelector(".options-container");

const optionsList = document.querySelectorAll(".option");

selected.addEventListener("click", () => {
  optionsContainer.classList.toggle("active");
});

optionsList.forEach(o => {
  o.addEventListener("click", () => {
    selected.innerHTML = o.querySelector("label").innerHTML;
    optionsContainer.classList.remove("active");
  });
});
</script>

</html>