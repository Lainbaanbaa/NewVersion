<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mem.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
	<style>
		.dropdown:hover .dropdown-menu {
		    display: block;
		    margin-top: 0;
		}
		
		.log{
			text-decoration: none;
		}
		
		.upPic {
			width: 50px;
			height: 50px;
		}
	</style>
</head>
<body>
	<header style="margin-bottom: 10%; z-index: 99999999">

    <nav class="navbar navbar-expand-lg fixed-top navbar-light
     bg-light static-top mb-5 shadow nav">
     
        <div class="container-fluid">
            <a class="navbar-brand" href="/CGA104G1/frontend/index.html">
                <!-- 網站 logo -->
                <img src="/CGA104G1/resources/static/image/ba-rei%2002.png" alt="Logo" width="200px" height="10%"
                     class="d-inline-block align-text-top">
            </a>

  <div class="float-end">
            <!-- 右側 toggle 按鈕 -->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation" style="margin-right: 10px;">
                <span class="navbar-toggler-icon"></span>
            </button>
                <a href="/CGA104G1/frontend/item/cart.html" style="text-decoration: none; color: #484747" id="myCart">
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor"
                         class="bi bi-cart3" viewBox="0 0 16 16" style="margin-bottom: 5px;">
                        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                    </svg>
                    <span class='badge badge-warning' id='lblCartCount'></span>
                </a>
            </div>
            <!-- 導覽列內容 -->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/frontend/index.html">首頁</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/frontend/item/shop.html">毛孩の商城</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/frontend/groupBuy/listallgroupbuy.html">團購底加啦</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/frontend/article/select_page.jsp">家長討論區</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            <i class="fa-regular fa-user"></i>
                            會員中心
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/memLogin/login.jsp">成為會員 / 登入會員</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/commodityDetails/OrderDetail.html">追蹤訂單</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/group_buy_order/select_page.jsp">團購訂單</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/coupon/Coupon.html">查看折價券</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/coupon/GetCoupon.html">領取折價券</a></li>
                             <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/item/favoriteList.html">我的最愛</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/mem/mem_index.jsp">會員中心首頁</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                       <a class="nav-link" href="<%=request.getContextPath()%>/frontend/question/question.jsp">常見問題Q&A</a>
                   	</li>
                </ul>
                <c:if test="${memVO == null}">
            		<a class="d-flex log" href="<%=request.getContextPath()%>/frontend/memLogin/login.jsp">
					<button class="btn btn-info">會員登入</button>
					</a>
				</c:if>
				
				<c:if test="${memVO != null}">
					<div class="identity">
					<c:if test="${memVO.article_identityVO.article_pic!=null}">
					${memVO.article_identityVO.article_pic}
					</c:if>
             		<span class="name">${memVO.mem_name}&ensp;您好</span>&ensp;
					<FORM ACTION="/CGA104G1/MemServlet" method="post" style="display:inline-block">
						<input type="hidden" name="action" value="logout">
						<button class="btn btn-info" type=submit>登出</button>
					</FORM>
					</div>
				</c:if>
            </div>
            
        </div>
    </nav>

</header>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>