<%@ page import="com.mem.model.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    MemVO memVO = (MemVO) session.getAttribute("memVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <!-- import bootstrap 5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    <!-- import font-style -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">

    <!-- import jquery-3.6.0 -->
    <script type="text/javascript" src="../../resources/static/js/jquery-3.6.0.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/static/css/main.css"/>
</head>
<body  style="background-color: #eee;">

<section>
    <div class="container py-5">

        <div class="row">
            <div class="col-lg-4">
                <div class="card mb-4">
                    <div class="card-body text-center" id="profileImg">
                    </div>
                    <div class="text-center">
                        <h5 class="my-3"><%=memVO.getMem_name()%>
                        </h5>
                    </div>
                </div>
                <div class="card mb-4 mb-lg-0">
                    <div class="card-body p-0">
                        <ul class="list-group list-group-flush rounded-3">
                            <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                <i class="fas fa-globe fa-lg text-warning"></i>
                                <FORM ACTION="<%=request.getContextPath()%>/MemServletFront"
                                      style="margin-bottom: 0px;">
                                    <input class="btn btn-outline-dark" type="submit" value="修改基本資料">
                                    <input type="hidden" name="mem_id" value="${memVO.mem_id }">
                                    <input type="hidden" name="action" value="getOne_For_UpdateMem"></FORM>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                <i class="fa-solid fa-list fa-lg" style="color: #55acee;"></i>
                                <a class="btn btn-outline-dark"
                                   href="/CGA104G1/frontend/commodityDetails/OrderDetail.html">追蹤商城訂單</a>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                <i class="fa-solid fa-users-rectangle fa-lg" style="color: #333333;"></i>
                                <a class="btn btn-outline-dark"
                                   href="/CGA104G1/frontend/group_buy_order/select_page.jsp">查看團購訂單</a>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                <i class="fa-regular fa-user fa-lg" style="color: #ac2bac;"></i>
                                <form action="<%=request.getContextPath() %>/frontend/article/addPic.jsp">
                                    <button class="btn btn-outline-dark" type="submit">討論區大頭貼</button>
                                </form>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                <i class="fa-solid fa-percent fa-lg" style="color: #3b5998;"></i>
                                <a class="btn btn-outline-dark"
                                   href="/CGA104G1/frontend/coupon/Coupon.html">已擁有折價券</a>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                <i class="fa-solid fa-heart fa-lg" style="color: #e894b7;"></i>
                                <a class="btn btn-outline-dark"
                                   href="/CGA104G1/frontend/item/favoriteList.html">查看我的最愛</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">會員姓名</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0"><%=memVO.getMem_name()%>
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">性別</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0"><%=memVO.getMem_sex()%>
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">電子郵件</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0"><%=memVO.getMem_email()%>
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">連絡電話</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0"><%=memVO.getMem_phone()%>
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">通訊地址</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0"><%=memVO.getMem_address()%>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 ">
                        <div class="card mb-4 mb-md-0">
                            <div class="card-body row d-flex justify-content-center align-items-center">
                                <button class="col-md-2 btn btn-outline-warning" id="logout" onclick="logOut()">
                                    安全登出
                                </button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>


<!-- import bootstrap 5.2.1 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
        crossorigin="anonymous"></script>

<!--  NavBar  -->
<script src="<%=request.getContextPath()%>/resources/static/js/navbar.js"></script>
<!--  Footer  -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/static/js/footer.js"></script>
<!-- 導覽列登入狀態 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/static/js/getName.js"></script>
<!--  Cart -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/static/js/cart.js"></script>

</body>
</html>
