<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tibame_T14
  Date: 2022/10/20
  Time: 下午 04:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.coupon.model.entity.Coupon" %>


<%
    String path = request.getContextPath();
    Coupon coupon = (Coupon) request.getAttribute("coupon");
%>
<%
    Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String now = df.format(d);
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>折價券</title>


    <!-- import bootstrap 5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    <!-- import font-style -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">

    <!-- import css stylesheet -->
    <link rel="stylesheet" href="#">

    <!-- import jquery-3.6.0 -->
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>

    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>

    <link rel="stylesheet" type="text/css" href="../../resources/static/css/orderDetails.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/static/css/orderDetails.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/static/css/backendDetail.css"/>
    <link rel="stylesheet" type="text/css" href="../../resources/static/css/backendDetail.css"/>
    <style>
        body {
            background-image: url('../asset/img/share_icon/bg.jpg');
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
            background-size: cover;
            background-color: rgba(29, 29, 29, 0.8);
            background-blend-mode: multiply;
        }

        input[type=text], input[type=password] {
            border-color: #bbb;
            height: 38px;
            font-size: 14px;
            border-radius: 2px;
            outline: 0;
            border: #ccc 1px solid;
            padding: 0 10px;
            width: 250px;
            -webkit-transition: box-shadow .5s;
            margin-bottom: 15px;
        }

        input[type=text]:hover, input[type=text]:focus, input[type=password]:hover, input[type=password]:focus {
            border: 1px solid #56b4ef;
            box-shadow: inset 0 1px 3px rgba(0, 0, 0, .05), 0 0 8px rgba(82, 168, 236, .6);
            -webkit-transition: box-shadow .5s;
        }

        input::-webkit-input-placeholder {
            color: #999;
            -webkit-transition: color .5s;
        }

        input:focus::-webkit-input-placeholder, input:hover::-webkit-input-placeholder {
            color: #c2c2c2;
            -webkit-transition: color .5s;
        }


        .table-wrapper {
            min-width: 500px;
            background: #fff;
            padding: 20px 25px;
            border-radius: 3px;
            box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
        }
    </style>
</head>
<body>

<main>

    <div class="container">

        <div class="container row justify-content-md-end" style="height: auto">
            <%-- 錯誤表列 --%>
            <c:if test="${not empty errorMsgs}">
                <div class="col-md-6"
                     style="padding: 10px; border-radius: 10px; background-color: rgba(245,245,245,0.63)">
                    <h1 style="color:red; ">警告:</h1>
                    <ul>
                        <c:forEach var="message" items="${errorMsgs}">
                            <li style="color:red"><h5>${message}</h5></li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
        </div>

        <div class="container-xl col-md-6">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-4">
                                <h2>後台 <b>新增優惠券</b></h2>
                            </div>
                        </div>
                    </div>
                    <div class="table table-striped table-hover">
                        <form class="form-group"
                              action="<%=path%>/backend/coupon/addCoupon.do"
                              method="post" name="coupon">

                            <div class="mb-3">
                                <strong>輸入說明文字</strong>
                                <input type="text" name="couponInfo" id="couponInfo"
                                       value="<%= (coupon==null ? "" : coupon.getCouponNar())%>">
                            </div>

                            <div class="mb-3">
                                <strong>設定優惠面額</strong>
                                <input type="text" name="couponValue"
                                       value="<%= (coupon==null)? "0" : coupon.getCouponVal()%>"/>
                            </div>

                            <div class="mb-3">
                                <strong>設定領取時間</strong>
                                <input type="text" id="get" class="datetimes"
                                       value="<c:if test = "null"> </c:if>">
                                <input type="hidden" id="get_time_start" name="getTimeStart" value="<%=now%>">
                                <input type="hidden" id="get_time_over" name="getTimeOver" value="<%=now%>">
                            </div>

                            <div class="mb-3">
                                <strong>設定使用期限</strong>
                                <input type="text" id="use" class="datetimes"
                                       value="<c:if test = "null"> </c:if>">
                                <input type="hidden" id="use_time_start" name="useTimeStart" value="<%=now%>">
                                <input type="hidden" id="use_time_over" name="useTimeOver" value="<%=now%>">
                            </div>

                            <div class="mb-3">
                                <strong>最低消費金額</strong>
                                <input type="text" name="minimum"
                                       value="<%= (coupon==null)? "0" : coupon.getMinimum()%>"/>
                            </div>

                            <button class="btn btn-warning"
                                    type="submit"
                                    name="action" value="quit"
                                    href="listAllCoupon.jsp">放棄新增</button>
                            <button class="btn btn-primary"
                                    type="submit"
                                    name="action" value="addCoupon">
                                送出
                            </button>
                            <br/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


</main>

<script>
    $(function () {
        $('input[class="datetimes"]').daterangepicker({
            showDropdowns: true,
            startDate:<%=now%>,
            endDate: <%=now%>,
            locale: {
                format: ' YYYY / MM / DD '
            }
        });
    });

    // $('#get').daterangepicker();
    $('#get').on('apply.daterangepicker', function (ev, picker) {
        $('#get_time_start').val(picker.startDate.format('YYYY-MM-DD'));
        $('#get_time_over').val(picker.endDate.format('YYYY-MM-DD'));
    });
    // $('#use').daterangepicker();
    $('#use').on('apply.daterangepicker', function (ev, picker) {
        $('#use_time_start').val(picker.startDate.format('YYYY-MM-DD'));
        $('#use_time_over').val(picker.endDate.format('YYYY-MM-DD'));
    });
</script>
<!-- import bootstrap 5.2.1 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</body>

</html>
