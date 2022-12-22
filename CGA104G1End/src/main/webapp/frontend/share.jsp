<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>Barei ServerSide</title>

<!-- import bootstrap 5.2.1 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

<!--   <!-- import font-style --> -->
<!--   <link rel="preconnect" href="https://fonts.googleapis.com"> -->
<!--   <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin> -->
<!--   <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet"> -->

  <!-- import css stylesheet -->
  <link rel="stylesheet" href="#">

  <!-- import jquery-3.6.0 -->
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

  <!-- import icon -->
  <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>

  <!-- import css RWD -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/asset/css/share.css">
</head>
<body>
<header>

  <!-- 導覽列 -->
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <!-- 網站 logo -->
        <img src="<%=request.getContextPath()%>/backend/asset/img/GB_ICON/Ba-Rei-logo4.png" alt="Logo" width="100px" height="10%"
             class="d-inline-block align-text-top">
      </a>

      <!-- 右側 toggle 按鈕 -->
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
              aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- 導覽列內容 -->
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">首頁</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">毛孩の商城</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">團購底加啦</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">家長討論區</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
              <i class="fa-regular fa-user"></i>
              會員中心
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">成為會員 / 登入會員</a></li>
              <li><a class="dropdown-item" href="#">查看購物車</a></li>
              <li><a class="dropdown-item" href="#">追蹤訂單</a></li>
              <li><a class="dropdown-item" href="#">團購訂單</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><a class="dropdown-item" href="#">會員中心首頁</a></li>
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
              <i class="fa-regular fa-paper-plane"></i>
              客服專區
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">關於我們</a></li>
              <li><a class="dropdown-item" href="#">常見問題 Q&A</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><a class="dropdown-item" href="#">客服專區首頁</a></li>
            </ul>
          </li>
        </ul>
        <form class="d-flex" role="search">
          <input class="form-control me-2" type="search" placeholder="今天你想來點..." aria-label="Search"
                 id="search_bar">
          <button class="btn btn-outline-success" type="submit" id="search_btn">Search</button>
        </form>
      </div>

    </div>
  </nav>
</header>
<!--header end-->
<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<aside class="aside">
        <button type="button" class="btn_hamburger">按鈕</button>

        <nav class="nav">
            <ul class="nav_list">
                <li><a href="#">員工管理系統</a></li>
                <li><a href="#">員工權限</a></li>
                <li><a href="#">員工功能</a></li>
                <li><a href="#">最新消息</a></li>
                <li><a href="#">最新消息相片</a></li>
                <li><a href="#">折價券管理系統</a></li>
                <li><a href="#">團購商品折扣</a></li>
        	</ul>
        </nav>


  </aside>


<!--sidebar end-->

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
	<!--main content start-->
		<!-- 		<section id="main-content"> -->
		<!-- 			<section class="wrapper"> -->

		<!-- 				<div class="row"> -->

		<!-- 					<div class="col-lg-9 main-chart"> -->

		<!-- <!-- 						內容寫在這裡面 -->

		<!-- 					</div> -->
		<!-- <!-- 					/col-lg-9 END SECTION MIDDLE -->

		<!-- 				</div> -->
		<!-- 			</section> -->
		<!-- 		</section> -->

		<!--main content end-->


<!-- 	RWD -->
  	<script>
        $(function () {

            $("button.btn_hamburger").on("click", function () {
                $("aside.aside").toggleClass("-on");
            });

        });
    </script>

  <!-- import main.js -->
  <script src="#"></script>
  
  <!-- import bootstrap 5.2.1 -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
          crossorigin="anonymous"></script>

</body>
</html>