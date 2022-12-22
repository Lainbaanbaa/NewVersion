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

    <!-- import jquery-3.6.0 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/static/js/jquery-3.6.0.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/static/css/main.css"/>
    <style type="text/css">
        #forgot .active {
            background-color: pink;
            margin-bottom: 10px;
            border-radius: 50%;
        }

        body {
            background: rgba(0, 0, 0, .6) url('<%=request.getContextPath()%>/resources/static/image/andrew-s-ouo1hbizWwo-unsplash.jpg') no-repeat fixed center center;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            background-size: cover;
            -o-background-size: cover;
            z-index: -999999;
        }
    </style>
</head>
<body>

<div class="container" style="background-color: rgba(245,245,245,0.58); padding-top: 5%; padding-bottom: 5%; margin-bottom: 10%; border-radius: 10%">
    <div class="row justify-content-center">
        <ul id="forgot" class="nav col-3 h-100" style="font-size: 20px; text-shadow: 3px 3px 3px whitesmoke">
            <li class="nav-item" style="visibility: hidden">
                <strong>TEST</strong>
            </li>
            <li class="nav-item">
                <a class="nav-link active" data-bs-toggle="tab" href="#forgotAccount"><strong>忘記帳號</strong></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-bs-toggle="tab" href="#forgotPassword"><strong>忘記密碼</strong></a>
            </li>
        </ul>
    </div>

    <div class="tab-content">
        <div class="tab-pane fade show active" id="forgotAccount">
            <form action="<%=request.getContextPath()%>/MemServlet" method="post">
                <div class="row">
                    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                        <div class="card border-0 shadow rounded-3 my-5">
                            <div class="card-body p-4 p-sm-5">
                                <h1 class="card-title text-center mb-5 fw-light fs-5">忘記帳號</h1>

                                <div class="form-floating mb-3">
                                    <input class="form-control" type="text" name="mem_uid" value="" size=25>
                                    <label>身分證字號</label>
                                </div>

                                <div class="form-floating mb-3">
                                    <input class="form-control" type="TEXT" name="mem_email" value="" size=25>
                                    <label>會員信箱 </label>
                                </div>
                                <strong style="color:red">${msg}</strong>
                                <br>
                                <div class="d-grid gap-2">
                                    <input type="hidden" name="action" value="forgotac">
                                    <input class="btn btn-secondary" type="submit" value="送出">
                                    <input class="btn btn-secondary" type="reset" value="清除">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade" id="forgotPassword">
            <form action="<%=request.getContextPath()%>/MemServlet" method="post">
                <div class="row">
                    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                        <div class="card border-0 shadow rounded-3 my-5">
                            <div class="card-body p-4 p-sm-5">
                                <h1 class="card-title text-center mb-5 fw-light fs-5">忘記密碼</h1>

                                <div class="form-floating mb-3">
                                    <input class="form-control" type="TEXT" name="mem_account" value="" size=25>
                                    <label>會員帳號</label>
                                </div>

                                <div class="form-floating mb-3">
                                    <input class="form-control" type="TEXT" name="mem_email" value="" size=25>
                                    <label>會員信箱</label>
                                </div>
                                <strong style="color:red">${msg}</strong>
                                <br>
                                <div class="d-grid gap-2">
                                    <input type="hidden" name="action" value="forgotpw">
                                    <input class="btn btn-secondary" type="submit" value="送出">
                                    <input class="btn btn-secondary" type="reset" value="清除">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

        </div>
    </div>

</div>

<!-- import bootstrap 5.2.1 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
        crossorigin="anonymous"></script>

<!--  NavBar  -->
<script src="<%=request.getContextPath()%>/resources/static/js/navbar.js"></script>
<!-- 導覽列登入狀態 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/static/js/getName.js"></script>
<!--  Cart -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/static/js/cart.js"></script>
<!--  Footer  -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/static/js/footer.js"></script>
</body>
</html>
