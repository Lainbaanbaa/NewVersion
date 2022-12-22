<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mem.model.*" %>
<%
    MemVO memVO = (MemVO) request.getAttribute("memVO");
// Object errorMsgs = session.getAttribute("errorMsgs");
%>


<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>會員資料新增 </title>

    <!-- import bootstrap 5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    <!-- import font-style -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">

    <!-- import css stylesheet -->

    <!-- import jquery-3.6.0 -->
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>

    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/static/css/main.css"/>
    <style>
        body {
            background: rgba(0, 0, 0, .6) url('<%=request.getContextPath()%>/resources/static/image/andrew-s-ouo1hbizWwo-unsplash.jpg') no-repeat fixed center center;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            background-size: cover;
            -o-background-size: cover;
            z-index: -999999;
            /*opacity: 30%;*/
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card border-0 shadow rounded-3 my-5">
                <div class="card-body p-4 p-sm-5">
                    <h1 class="card-title text-center mb-5 fw-light fs-5">會員註冊</h1>


                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemServlet">


                        <label>會員帳號:</label>
                        <strong style="color:gray">${msg}</strong>
                        <strong style="color:red">${errorMsgs.mem_account}</strong>
                        <input id="mem_account" name="mem_account" type="TEXT" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMem_account()%>"
                               class="form-control"/>
                        <br>
                        <input class="btn btn-secondary" type="button" id="chkaccount" value=" 檢查是否可以使用">
                        <br>
                        <br>
                        <!-- 			 <td><input type="submit" name="action" value="檢查帳號是否可用" size="45" ></td> -->


                        <label>會員密碼:</label>

                        <strong style="color:red">${errorMsgs.mem_password}</strong>
                        <input class="form-control" type="password" name="mem_password" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMem_password()%>"/>
                        <label>會員姓名:</label>

                        <strong style="color:red">${errorMsgs.mem_name}</strong>
                        <input class="form-control" type="TEXT" name="mem_name" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMem_name()%>"/>
                        <label>會員地址:</label>

                        <strong style="color:red">${errorMsgs.mem_address}</strong>
                        <input class="form-control" type="TEXT" name="mem_address" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMem_address()%>"/>
                        <label>會員電話:</label>

                        <strong style="color:red">${errorMsgs.mem_phone}</strong>
                        <input class="form-control" type="TEXT" name="mem_phone" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMem_phone()%>"/>
                        <label>身分證字號:</label>

                        <strong style="color:red">${errorMsgs.mem_uid}</strong>
                        <input class="form-control" type="TEXT" name="mem_uid" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMem_uid()%>"/>
                        <label>會員信箱:</label>

                        <strong style="color:red">${errorMsgs.mem_email}</strong>
                        <input class="form-control" type="TEXT" name="mem_email" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMem_email()%>"/>

                        <label>會員性別:</label>

                        <strong style="color:red">${errorMsgs.mem_sex}</strong>
                        <div class="input-group gap-2">
                            <input type="radio" name="mem_sex" size="38"
                                   value="男" ${(memVO.mem_sex=="男")? 'checked':'' }><b>男</b>
                            <input type="radio" name="mem_sex" size="38"
                                   value="女" ${(memVO.mem_sex=="女")? 'checked':'' }><b>女</b>
                            <input type="hidden" name="mem_sex" value="${memVO.mem_sex}">
                        </div>
                        <label>會員生日:</label>

                        <strong style="color:red">${errorMsgs.mem_dob}</strong>
                        <input class="form-control" type="TEXT" name="mem_dob" id="dob_date1" size="38"/>

                        <!-- 	<tr> -->
                        <!-- 		<td>會員編號:<font color=red><b>*</b></font></td> -->
                        <!-- 		<td><select size="1" name="mem_id"> -->
                        <%-- 			<c:forEach var="memVO" items="${memSvc.all}"> --%>
                        <%-- 				<option value="${memVO.mem_id}" ${(memVO.mem_id==memVO.mem_id)? 'selected':'' } >${memVO.mem_id} --%>
                        <%-- 			</c:forEach> --%>
                        <!-- 		</select></td> -->
                        <!-- 	</tr> -->

                        <br>
                        <div class="justify-content-center row">
                        <input type="hidden" name="action" value="register">
                        <button type="submit" class="btn btn-outline-info">送出註冊</button>
                            </div>
                    </FORM>

                </div>
            </div>
        </div>
    </div>
</div>

<!--  Navbar  -->
<script src="<%=request.getContextPath()%>/resources/static/js/navbar.js"></script>
<!--  Footer  -->
<script src="<%=request.getContextPath()%>/resources/static/js/footer.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/static/js/getName.js"></script>
<!--  Cart -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/static/js/cart.js"></script>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
    java.sql.Date mem_dob = null;
    try {
        mem_dob = memVO.getMem_dob();
    } catch (Exception e) {
        mem_dob = new java.sql.Date(System.currentTimeMillis());
    }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css"/>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
    $('#dob_date1').datetimepicker({
        theme: '',              //theme: 'dark',
        timepicker: false,       //timepicker:true,
        step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
        format: 'Y-m-d',         //format:'Y-m-d H:i:s',
        value: '<%=mem_dob%>', // value:   new Date(),
        //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
        //startDate:	            '2017/07/10',  // 起始日
        //minDate:               '-1970-01-01', // 去除今日(不含)之前
        //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
    });


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


<script>
    function showEmployee(json) {
        //剖析json字串,將其轉成js物件
        let chkAc = JSON.parse(json);

        if (!(chkAc.mem_account == "null")) {


            console.log(chkAc.mem_account);

            alert("此帳號已被使用");

//         let html;
//         html = `
//         <table class='empTable' align='center'>
//         <tr><th>工號</th><td>${emp.empno}</td></tr>
//         <tr><th>姓名</th><td>${emp.ename}</td></tr>
//         <tr><th>薪資</th><td>${emp.sal}</td></tr>
//         <tr><th>到職日</th><td>${emp.hiredate}</td></tr>
//       </table>

        } else {
            alert("此帳號可以使用");
//           html = "<center>查無此員工</center>";

        }


    }

    //       document.getElementById("showPanel").innerHTML = html;


    $("#chkaccount").click(function getEmployee() {
        //===實作(填入程式碼)
        let xhr = new XMLHttpRequest();
        let mem_account = $("#mem_account").val();
        //設定好回呼函數
        if (mem_account == null || mem_account === '') {
            alert('帳號不可為空白')
        } else {

            let url = "/CGA104G1/MemServlet?action=accountchk&mem_account=" + mem_account;
//         xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
            xhr.open("get", url, true);
            xhr.onload = function () {
                if (xhr.status == 200) {
                    console.log(xhr.responseText);
                    showEmployee(xhr.responseText);
// alert(xhr.responseText);
//             alert("123");
                } else {
                    alert(xhr.status);
                }// status
            };// onload

            //建立好Get連接與送出請求
// let mem_account = document.getElementById("mem_account").value;
            xhr.send(null);
        }
    })

</script>


</html>
