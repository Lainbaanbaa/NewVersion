<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.coupon.model.service.impl.CouponServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.coupon.model.entity.Coupon" %><%--
  Created by IntelliJ IDEA.
  User: Tibame_T14
  Date: 2022/11/4
  Time: 下午 11:01
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%
    CouponServiceImpl couponService = new CouponServiceImpl();
    List<Coupon> list = couponService.listAllCoupon();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>顯示所有折價券</title>

    <!-- import bootstrap 5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    <!-- import font-style -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">

    <!-- import jquery-3.6.0 -->
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>


    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/static/css/orderDetails.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/static/css/backendDetail.css"/>
    <style>


        select, input {
            width: 200px;
            height: 30px;
            border-radius: 20px;
            border: none;
            text-align: center;
        }

        #add_coupon {
            position: relative;
        }

        #add_coupon:hover::after {
            position: absolute;
            top: -50px;
            left: 0;
            content: '新增折價券';
        }
    </style>
</head>
<body id="body">
<nav>
    <%@include file="/backend/topNavbar.jsp" %>
</nav>
<main>
    <%@include file="/backend/leftside.jsp" %>
    <section>
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

            <div class="container-xl">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-4">
                                    <h2>後台 <b>折價券管理</b></h2>
                                </div>
                            </div>
                        </div>

                        <table class="table table-striped table-hover">
                            <tr>
                                <th>#</th>
                                <th>折價券面額</th>
                                <th>折價券說明</th>
                                <th>開始領取日</th>
                                <th>結束領取日</th>
                                <th>開始使用日</th>
                                <th>結束使用日</th>
                                <th>最低消費金額</th>
                                <th>ACTION</th>
                            </tr>
                            <%@ include file="page1.file" %>
                            <c:forEach var="coupon" items="${list}" begin="<%=pageIndex%>"
                                       end="<%=pageIndex+rowsPerPage-1%>">

                                <tr>
                                    <th>${coupon.couponId}</th>
                                    <td>${coupon.couponVal}</td>
                                    <td>${coupon.couponNar}</td>
                                    <td>${coupon.receiveStart}</td>
                                    <td>${coupon.receiveOver}</td>
                                    <td>${coupon.useStart}</td>
                                    <td>${coupon.useOver}</td>
                                    <td>${coupon.minimum}</td>
                                    <td class="gap-2">
                                        <form method="post" action="updateCoupon.do" style="margin-bottom: 0px;">
                                            <input class="btn btn-light" type="submit" value="修改">
                                            <input type="hidden" name="coupon_id" value="${coupon.couponId}">
                                            <input type="hidden" name="action" value="getOne_For_Update"></form>
                                        <form method="post" action="removeCoupon.do" style="margin-bottom: 0px;">
                                            <input class="btn btn-light" type="submit" value="刪除">
                                            <input type="hidden" name="coupon_id" value="${coupon.couponId}">
                                            <input class="btn btn-light" type="hidden" name="action" value="delete">
                                        </form>
                                    </td>

                                </tr>
                            </c:forEach>

                        </table>

                        <%@ include file="page2.file" %>

                        <div id="add_coupon">
                            <a href="addCoupon.jsp">
                                <i class="fa-solid fa-plus fa-2xl"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
</body>
</html>
