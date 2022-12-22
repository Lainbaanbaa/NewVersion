<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mem.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>login</title>
    <!-- import bootstrap 5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    <!-- import font-style -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">
    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>

    <!-- import jquery-3.6.0 -->
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>

    <!-- import css stylesheet -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/static/css/main.css"/>

    <style type="text/css">
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
                    <h1 class="card-title text-center mb-5 fw-light fs-5">會員登入</h1>
                    <form class="d-flex justify-content-center h-100"
                          action="<%=request.getContextPath()%>/MemServlet" method="post">

                        <div class="justify-content-center">


                            <div class="form-floating mb-3">
                                <input class="form-control" type="text" placeholder="請輸入帳號"
                                       name="account" value="" size=25>
                                <label>帳號</label>
                            </div>

                            <div class="form-floating mb-3">
                                <input class="form-control" placeholder="請輸入密碼"
                                       type="password" name="password" value="" size=25>
                                <label>密碼</label>
                            </div>
                            <div style="color:red">${msg}</div>
                            <div class="d-grid gap-2">
                                <input type="hidden" name="action" value="login">
                                <button class="btn btn-secondary text-uppercase fw-bold" type="submit">送出</button>
                                <button class="btn btn-secondary text-uppercase fw-bold" type="reset">清除</button>
                            </div>
                            <br>

                        </div>

                    </form>
                    <div class="gap-2">

                        <FORM ACTION="<%=request.getContextPath()%>/frontend/memLogin/register.jsp"
                              style="margin-bottom: 0px;">
                            <span style="color:blue">沒有會員?點此</span>
                            <input class="btn" type="submit" value="註冊會員">
                            <input type="hidden" name="action" value="register">
                        </FORM>

                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/memLogin/forgot.jsp"
                              style="margin-bottom: 0px;">
                            <span style="color:blue">忘記帳號或密碼?點擊</span>
                            <input class="btn" type="submit" value="忘記帳號/密碼">
                            <input type="hidden" name="action" value="forget">
                        </FORM>
                    </div>
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
</html>
