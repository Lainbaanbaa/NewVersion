const navbar = `
<header style="margin-bottom: 10%; z-index: 999">

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
                    aria-expanded="false" aria-label="Toggle navigation" style="margin-right: 10px;z-index: 1000">
                <span class="navbar-toggler-icon" style="z-index: -9999"></span>
            </button>
               
            </div>
            <!-- 導覽列內容 -->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/CGA104G1/frontend/index.html">首頁</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/CGA104G1/frontend/item/shop.html">毛孩の商城</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/CGA104G1/frontend/groupBuy/listallgroupbuy.html">團購底加啦</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/CGA104G1/frontend/article/select_page.jsp">家長討論區</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            
                            會員中心
                        </a>
                        <ul class="dropdown-menu" id="memDrop">
                            <li><a class="dropdown-item" href="/CGA104G1/frontend/commodityDetails/OrderDetail.html">追蹤訂單</a></li>
                            <li><a class="dropdown-item" href="/CGA104G1/frontend/group_buy_order/select_page.jsp">團購訂單</a></li>
                            <li><a class="dropdown-item" href="/CGA104G1/frontend/coupon/Coupon.html">查看折價券</a></li>
                            <li><a class="dropdown-item" href="/CGA104G1/frontend/coupon/GetCoupon.html">領取折價券</a></li>
                             <li><a class="dropdown-item" href="/CGA104G1/frontend/item/favoriteList.html">我的最愛</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/CGA104G1/frontend/mem/mem_index.jsp">會員中心首頁</a></li>
                        </ul>
                    </li>
                        <li class="nav-item">
                        <a class="nav-link" href="/CGA104G1/frontend/question/question.jsp">常見問題 Q&A</a>
                    </li>
                </ul>
                <div id="memberName"></div>
                <div id="memberImg"></div>
                <li class="dropstart">
                <ul class="mb-2 mb-lg-0">
                 <a href="/CGA104G1/frontend/memLogin/login.jsp" style="text-decoration: none; color: #484747" 
                 role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                <i class="fa-regular fa-user fa-lg" style="padding: 0 10px"></i>
                </a> 
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuLink" id="myDropdown">
                </ul>
                </li>
                
                
                <a href="/CGA104G1/frontend/item/cart.html" style="text-decoration: none; color: #484747" id="myCart">
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor"
                         class="bi bi-cart3" viewBox="0 0 16 16" style="margin-bottom: 5px;">
                        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                    </svg>
                    <span class='badge badge-warning' id='lblCartCount'></span>
                </a>
                </ul>
            </div>
        </div>
    </nav>

</header>
`


document.body.insertAdjacentHTML('afterbegin', navbar);
