const navbar =  `<header>
                    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
                        <div class="container-fluid">
                            <a class="navbar-brand" href="/CGA104G1/backend/index.jsp"><img
                                    src="/CGA104G1/backend/asset/img/share_icon/ba-rei 02.png"
                                    width="100px" height="40px"></a>
                                 
                            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                    <li class="nav-item dropdown"><a class="nav-link" href="#" role="button"
                                            aria-expanded="false"> 會員管理 </a>
                                        <ul class="dropdown-menu bg-dark">
                                            <li><a class="dropdown-item bg-dark text-white-50" href="#">會員帳號查詢</a></li>
                                            <li><a class="dropdown-item bg-dark text-white-50" href="#">會員帳號管理</a></li>
                                            <li><a class="dropdown-item bg-dark text-white-50" href="#">認證醫生管理</a></li>
                                        </ul>
                                    </li>
                                    <li class="nav-item dropdown"><a class="nav-link" href="#" role="button"
                                            aria-expanded="false"> 商城管理 </a>
                                        <ul class="dropdown-menu bg-dark">
                                            <li><a class="dropdown-item bg-dark text-white-50" href="/CGA104G1/backend/item/listAllItems.html">商品管理</a></li>
                                            <li><a class="dropdown-item bg-dark text-white-50" href="/CGA104G1/backend/commodityDetails/ListOrderBackend.html">商城訂單管理</a></li>
                                            <li><a class="dropdown-item bg-dark text-white-50" href="/CGA104G1/backend/coupon/listAllCoupon.jsp">折價券管理</a></li>
                                        </ul>
                                    </li>
                                    <li class="nav-item dropdown"><a class="nav-link" href="#" role="button"
                                            aria-expanded="false"> 團購管理 </a>
                                        <ul class="dropdown-menu bg-dark">
                                            <li><a class="dropdown-item bg-dark text-white-50" href="/CGA104G1/backend/Discount/select_page.jsp">團購折扣管理</a></li>
                                            <li><a class="dropdown-item bg-dark text-white-50" href="/CGA104G1/backend/Group_Buy_Item/select_page.jsp">團購商品管理</a></li>
                                            <li><a class="dropdown-item bg-dark text-white-50" href="#">團購訂單管理</a></li>
                                            <li><a class="dropdown-item bg-dark text-white-50" href="#">團購檢舉管理</a></li>
                                        </ul>
                                    </li>
                                    <li class="nav-item dropdown"><a class="nav-link" href="#" role="button"
                                            aria-expanded="false"> 討論區管理 </a>
                                        <ul class="dropdown-menu bg-dark">
                                            <li><a class="dropdown-item bg-dark text-white-50" href="#">文章分類管理</a></li>
                                            <li><a class="dropdown-item bg-dark text-white-50" href="/CGA104G1/backend/article/selectPage.jsp">文章檢舉管理</a></li>
                                        </ul>
                                    </li>
                                    <li class="nav-item dropdown"><a class="nav-link" href="#" role="button"
                                            aria-expanded="false"> 資訊管理 </a>
                                        <ul class="dropdown-menu bg-dark">
                                            <li><a class="dropdown-item bg-dark text-white-50" href="#">最新消息管理</a></li>
                                            <li><a class="dropdown-item bg-dark text-white-50" href="#">Q&A管理</a></li>
                                        </ul>
                                    </li>
                                    <li class="nav-item dropdown"><a class="nav-link" href="#" role="button"
                                            aria-expanded="false"> 員工管理 </a>
                                        <ul class="dropdown-menu bg-dark">
                                            <li><a class="dropdown-item bg-dark text-white-50" href="/CGA104G1/backend/emp/select_page.jsp">員工資料查詢與管理</a></li>
                                            <li><a class="dropdown-item bg-dark text-white-50" href="/CGA104G1/backend/effect/select_page.jsp">權限查詢與管理</a></li>
                                        </ul>
                                    </li>
                                </ul>
                                   <div id="myDropdown"></div>
                            </div>
                        </div>
                    </nav>
                    <div id="none"></div>
                </header>`

document.body.insertAdjacentHTML('afterbegin', navbar);
