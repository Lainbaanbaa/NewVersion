<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@include file="/backend/backNavbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>IBM Emp: Home</title>

<style>

/* =======================================================================下拉==================================================== */
/* @import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro); */

body {
  background: #ffffff; 
  color: #414141;
  font: 400 17px/2em 'Source Sans Pro', sans-serif;
}

.select-box {
  cursor: pointer;
  position : relative;
  max-width:  10em;
  width: 100%;
}

.select,
.label {
  color: #414141;
  display: block;
  font: 400 17px/2em 'Source Sans Pro', sans-serif;
}

.select {
  width: 100%;
  position: absolute;
  top: 0;
  padding: 5px 0;
  height: 40px;
  opacity: 0;
  -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
  background: none transparent;
  border: 0 none;
}
.select-box1 {
  background: #ececec;
}

.label {
  position: relative;
  padding: 1px 1px;
  cursor: pointer;
}
.open .label::after {
   content: "▲";
}
.label::after {
  content: "▼";
  font-size: 12px;
  position: absolute;
  right: 0;
  top: 0;
  padding: 5px 15px;
  border-left: 5px solid #fff;
}

/* =======================================================================下拉結束==================================================== */

div.input-block {
  position: relative;
}
div.input-block input {
  font-weight: 500;
  font-size: 1rem;
  color: #495055;
  width: 200px;
  padding: 5px 5px;
  border-radius: 1rem;
  border: 2px solid  #495055;
  outline:none;
}
div.input-block span.placeholder {
  position: absolute;
  margin: 17px 0;
  padding: 0 4px;
  font-family: Roboto, sans-serif;
  color:  #6c757d;
  display: flex;
  align-items: center;
  font-size: 1.6rem;
  top: 0;
  left: 17px;
  transition: all 0.2s;
  transform-origin: 0% 0%;
  background: none;
  pointer-events: none;
}
div.input-block input:valid + span.placeholder,
div.input-block input:focus + span.placeholder {
  transform: scale(0.8) translateY(-30px);
  background: #fff;
}
div.input-block input:focus{
  color: #284B63;
  border-color: #284B63;
}
div.input-block input:focus + span.placeholder {
  color: #284B63;
}


/* ============================================================input 結束=================================*/
FROM{
 margin-left:auto; 
	margin-right:auto;
}

table#table-1 {
	background-color: #212529;
    border: 2px solid black;
    text-align: center;
     margin-left:auto; 
	margin-right:auto;
 	width: 800px;
 	height:98px;
 	margin-top: 5px; 
 	margin-bottom: 5px; 
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h3{color: #6c757d;
  }
  h4 {
    color: blue;
    display: inline;
  }
  
  
/*   a{ */
/*     color: white; */
/*     display: inline; */
/*   } */

</style>

<style>
   table { 
   margin-left:auto; 
	margin-right:auto;
 	width: 800px; 
 	margin-top: 5px; 
 	margin-bottom: 5px; 
   } 
/*    table, th, td {  */
/*      border: 1px solid #212529;  */
/*    }  */
    th, td {  
      padding: 5px; 
    text-align: center;  
  }  
/*   td:first-child{ */
/*   border-top-left-radius: 10px; */
/*   border-bottom-left-radius: 10px; */
/* } */

/* td:last-child{ */
/*   border-top-right-radius: 10px; */
/*   border-bottom-right-radius: 10px; */
/* } */
</style>
</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>資料查詢</h3><h4></h4></td></tr>
</table>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>



<ul>  
  <li>   
    <FORM METHOD="post" ACTION="EmpServlet" name="form1">
    

        <b><font color=blue>萬用複合查詢:</font></b> <br>
        <span class="label-desc">輸入員工編號:</span>
        <input type="text" name="emp_id" value=""><br>
           <span class="label-desc">輸入員工姓名:</span>
       <input type="text" name="emp_name" value=""><br>

         <div class="select-box">
    <label for="select-box1" class="label select-box1">
   <span class="label-desc">員工狀態</span> </label>
    <select id="select-box1" class="select">
      <option value=" "></option>
      <option value="0">離職</option>
      <option value="1" selected>在職</option>
    </select>
  </div>
       <b>雇用日期:</b>
	   <input name="onjob_date" id="f_date1" type="text"
	   >
		        
        <input type="submit" class="btn btn-dark" value="送出">
        <input type="hidden" name="action" value="listemp_no_effect">
     </FORM>
  </li>
</ul>


</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
		   value: '',              // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
//         ========================================================================================
       $("select").on("click" , function() {
  
  $(this).parent(".select-box").toggleClass("open");
  
});

$(document).mouseup(function (e)
{
    var container = $(".select-box");

    if (container.has(e.target).length === 0)
    {
        container.removeClass("open");
    }
});


$("select").on("change" , function() {
  
  var selection = $(this).find("option:selected").text(),
      labelFor = $(this).attr("id"),
      label = $("[for='" + labelFor + "']");
    
  label.find(".label-desc").html(selection);
    
});
//      ========================================================================================

   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>