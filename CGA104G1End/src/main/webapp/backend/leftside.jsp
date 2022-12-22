<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/templates/assets/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/templates/assets/vendors/mdi/css/materialdesignicons.min.css">
	<style>
		ul.nav{
			padding-top: 5px !important;
		}
		ul.nav a:hover {
		    color: #FFC107 !important;
		}
		
		[aria-expanded="true"] > .menu-title{
			color: white !important;
		}
	</style>
</head>

<body>

        <nav class="sidebar sidebar-offcanvas" id="sidebar">
            <ul class="nav">
                <li class="nav-item menu-items">
                    <a class="nav-link" data-bs-toggle="collapse" href="#member" aria-expanded="false"
                        aria-controls="member">
                        <span class="menu-icon">
                            <i class="mdi mdi-account-circle"></i>
                        </span>
                        <span class="menu-title">會員管理</span>
                        <i class="menu-arrow"></i>
                    </a>
                    <div class="collapse" id="member">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/mem/select_page.jsp"> 會員帳號管理 </a></li>
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/qualified_doctor/listAllqualified_doctor.jsp"> 認證醫生管理 </a></li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item menu-items">
                    <a class="nav-link" data-bs-toggle="collapse" href="#store" aria-expanded="false"
                        aria-controls="store">
                        <span class="menu-icon">
                            <i class="mdi mdi-cart"></i>
                        </span>
                        <span class="menu-title">商城管理</span>
                        <i class="menu-arrow"></i>
                    </a>
                    <div class="collapse" id="store">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/item/listAllItems.html"> 商品管理 </a></li>
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/commodityDetails/ListOrderBackend.html"> 商城訂單管理 </a></li>
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/coupon/listAllCoupon.jsp"> 折價券管理 </a></li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item menu-items">
                    <a class="nav-link" data-bs-toggle="collapse" href="#groupbuy" aria-expanded="false"
                        aria-controls="groupbuy">
                        <span class="menu-icon">
                            <i class="mdi mdi-cat"></i>
                        </span>
                        <span class="menu-title">團購管理</span>
                        <i class="menu-arrow"></i>
                    </a>
                    <div class="collapse" id="groupbuy">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/discount/select_page.jsp"> 團購折扣管理 </a></li>
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/group_Buy_Item/select_page.jsp"> 團購商品管理 </a></li>
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/group_buy_order/select_page.jsp"> 團購訂單管理 </a></li>
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/group_buy/select_page.jsp"> 團購團管理 </a></li>
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/group_buy_report/listAllGroup_buy_Report.jsp"> 團購檢舉 </a></li>
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/group_join/select_page.jsp"> 參團修改 </a></li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item menu-items">
                    <a class="nav-link" data-bs-toggle="collapse" href="#article" aria-expanded="false"
                        aria-controls="article">
                        <span class="menu-icon">
                            <i class="mdi mdi-library"></i>
                        </span>
                        <span class="menu-title">文章管理</span>
                        <i class="menu-arrow"></i>
                    </a>
                    <div class="collapse" id="article">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/article/selectPage.jsp"> 討論區管理 </a></li>
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/news/newsIndex.jsp"> 最新消息 </a></li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item menu-items">
                    <a class="nav-link" data-bs-toggle="collapse" href="#emp" aria-expanded="false"
                        aria-controls="emp">
                        <span class="menu-icon">
                            <i class="mdi mdi-face"></i>
                        </span>
                        <span class="menu-title">員工管理</span>
                        <i class="menu-arrow"></i>
                    </a>
                    <div class="collapse" id="emp">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/emp/select_page.jsp"> 員工資料查詢與管理 </a></li>
                            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/backend/effect/select_page.jsp"> 權限查詢與管理 </a></li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item menu-items">
                    <a class="nav-link"
                        href="/CGA104G1/NameServlet?userName=admin">
                        <span class="menu-icon">
                            <i class="mdi mdi-comment-processing"></i>
                        </span>
                        <span class="menu-title">客服中心</span>
                    </a>
                </li>
            </ul>
        </nav>  
        <script src="<%=request.getContextPath()%>/resources/templates/assets/vendors/js/vendor.bundle.base.js"></script>
</body>

</html>