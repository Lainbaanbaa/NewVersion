<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
EmpService navEmpSvc = new EmpService();
List<EmpVO> empList = navEmpSvc.login((String)session.getAttribute("account"),(String)session.getAttribute("password"));
session.setAttribute("empList", empList);
%> 

<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
        crossorigin="anonymous">

    <!-- import jquery-3.6.0 -->
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>

    <style>
		.dropdown:hover .dropdown-menu {
		    display: block;
		    margin-top: 0;
		}
		
		ul.navbar-nav a:hover {
		    color: #FFC107 !important;
		}
		
		#none {
		    height: 66px;
		}
		          
		body { 
 		   	background-repeat: no-repeat !important;  
 		    background-attachment: fixed !important;  
 		    background-position: center !important;  
 		    background-size: cover !important;  
		    background-color: #f5fcff !important; 
		    background-blend-mode: multiply !important; 
		} 
					  
		 .log {
		  	text-decoration: none;
		}
		
		.name {
			color: white;
		}
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container-fluid">
        
            <a class="navbar-brand" href="<%=request.getContextPath()%>/backend/index.jsp"><img
                    src="<%=request.getContextPath()%>/backend/asset/img/share_icon/ba-rei 02.png"
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
                            <li><a class="dropdown-item bg-dark text-white-50" href="<%=request.getContextPath()%>/frontend/mem/listAllMem.jsp">會員帳號管理</a></li>
                            <li><a class="dropdown-item bg-dark text-white-50" href="<%=request.getContextPath()%>/backend/qualified_doctor/listAllqualified_doctor.jsp">認證醫生管理</a></li>
                        </ul>
                    </li>
                    
                    <li class="nav-item dropdown"><a class="nav-link" href="#" role="button"
                            aria-expanded="false"> 商城管理 </a>
                        <ul class="dropdown-menu bg-dark">
                            <li><a class="dropdown-item bg-dark text-white-50" href="<%=request.getContextPath()%>/backend/item/listAllItems.html">商品管理</a></li>
                            <li><a class="dropdown-item bg-dark text-white-50" href="#">商城訂單管理</a></li>
                            <li><a class="dropdown-item bg-dark text-white-50" href="<%=request.getContextPath()%>/backend/coupon/listAllCoupon.jsp">折價券管理</a></li>
                        </ul>
                    </li>
                    
                    <li class="nav-item dropdown"><a class="nav-link" href="#" role="button"
                            aria-expanded="false"> 團購管理 </a>
                        <ul class="dropdown-menu bg-dark">
                            <li><a class="dropdown-item bg-dark text-white-50" href="<%=request.getContextPath()%>/backend/discount/select_page.jsp">團購折扣管理</a></li>
                            <li><a class="dropdown-item bg-dark text-white-50" href="<%=request.getContextPath()%>/backend/group_Buy_Item/select_page.jsp">團購商品管理</a></li>
                            <li><a class="dropdown-item bg-dark text-white-50" href="<%=request.getContextPath()%>/backend/group_buy_order/select_page.jsp">團購訂單管理</a></li>
                        </ul>
                    </li>
                    
                    <li class="nav-item dropdown"><a class="nav-link" href="#" role="button"
                            aria-expanded="false"> 貼文管理 </a>
                        <ul class="dropdown-menu bg-dark">
                            <li><a class="dropdown-item bg-dark text-white-50" href="<%=request.getContextPath()%>/backend/article/selectPage.jsp">討論區管理</a></li>
                            <li><a class="dropdown-item bg-dark text-white-50" href="<%=request.getContextPath()%>/backend/news/newsIndex.jsp">最新消息</a></li>
                        </ul>
                    </li>
                    
                    <li class="nav-item dropdown"><a class="nav-link" href="/CGA104G1/NameServlet?userName=admin" role="button"
                            aria-expanded="false"> 客服中心 </a>
                    </li>
                    
                      
                    <li class="nav-item dropdown"><a class="nav-link" href="#" role="button"
                            aria-expanded="false"> 員工管理 </a>
                        <ul class="dropdown-menu bg-dark">
                            <li><a class="dropdown-item bg-dark text-white-50" href="<%=request.getContextPath()%>/backend/emp/select_page.jsp">員工資料查詢與管理</a></li>
                            <li><a class="dropdown-item bg-dark text-white-50" href="<%=request.getContextPath()%>/backend/effect/select_page.jsp">權限查詢與管理</a></li>
                        </ul>
                    </li>  
                     								  
                </ul>
                <c:if test="${empVO == null}">
                <a class="d-flex log" href="<%=request.getContextPath()%>/backend/login/backLogin.jsp">
					<button class="btn btn-warning">員工登入</button>
				</a>
				</c:if>
				<c:if test="${empVO != null}">
					<c:forEach var="empVO" items="${empList}" begin="0" end="0" >
	              		<span class="name">${empVO.emp_name}&ensp;您好</span>&ensp;
					</c:forEach>
					<FORM ACTION="/CGA104G1/backend/login/LoginServlet" method="post">
						<input type="hidden" name="action" value="logout">
						<button class="btn btn-warning" type=submit>登出</button>
  					</FORM>
				</c:if>
             </div>
         </div>
     </nav>
     <div id="none"></div>
 </body>

  </html>