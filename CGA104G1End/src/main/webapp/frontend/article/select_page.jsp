<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.article.model.*" %>

<%
    ArticleService articleSvc = new ArticleService();
    List<ArticleVO> list = articleSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Barei論壇首頁</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
            crossorigin="anonymous">
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.1.js"
            integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/article.css">

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/static/css/main.css"/>
    <style>
        #dropdownMenuLink {
            display: none;
        }
    </style>

</head>

<body>
<div class="searchBlock">
    <form method="post" action="/CGA104G1/ArticleServlet" name="form2">
        <img src="<%=request.getContextPath() %>/frontend/article/img/search.png" style="width:30px; height:30px;">
        <span class="searchTitle">查詢專區</span>
        <div style="display: inline-block; width:100px"></div>
        <div class="sortSearch">
            分類
            <select name="sort_id" class="select">
                <option value="" ${(memVO.mem_status=="")? 'selected':'' } >請選擇</option>
                <option value="1" ${(memVO.mem_status==0)? 'select':'' } >閒聊</option>
                <option value="2" ${(memVO.mem_status==1)? 'select':'' } >發問</option>
                <option value="3" ${(memVO.mem_status==2)? 'select':'' } >醫生專欄</option>
            </select>
        </div>
        <div style="display: inline-block; width:200px"></div>
        <div class="artSearch">
            標題
            <input type="text" name="article_title" class="searchInput" value="">
            <input type="hidden" name="action" value="ArtSearch">
            <input type="submit" class="btn btn-success btnSmall" value="查詢">
        </div>
    </form>
</div>

<div class="container">
    <div class="displayBox">
        <div class="title">
            <div class="push titlesty">推</div>
            <div class="sort titlesty">分類</div>
            <div class="atitle titlesty">標題</div>
            <div class="author titlesty">作者</div>
            <div class="ptime titlesty">發文時間</div>
        </div>
        <jsp:useBean id="article_sorttypeSvc" scope="page" class="com.article_sorttype.model.Article_sorttypeService"/>
        <div style="display:none">
            <%@ include file="page1.file" %>
        </div>
        <c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
            <div class="evenarticle">
                <div class="push">${articleVO.getArtLike()}</div>
                <div class="sort">【${articleVO.article_sorttypeVO.sort_content}】</div>
                <div class="atitle">
                    <form method="post" action="/CGA104G1/ArticleServlet">
                        <input type="hidden" name="article_id" value="${articleVO.article_id}">
                        <input type="hidden" name="action" value="getOne_For_Display">
                        <input class="atitle_but" type="submit" value="${articleVO.article_title}">
                    </form>

                </div>
                <div class="author">${articleVO.memVO.mem_account}</div>
                <div class="ptime">${articleVO.article_publish}</div>
            </div>
        </c:forEach>
        <div class="page">
            <%@ include file="page2.file" %>
        </div>
    </div>
</div>

<div class="container2">
    <div class="form">
        <form action="<%=request.getContextPath() %>/frontend/article/addPic.jsp">
            <button class="btn btn-dark text-nowrap act" type="submit">新增大頭貼</button>
        </form>
        <form action="<%=request.getContextPath() %>/frontend/article/addArticle.jsp">
            <button class="btn btn-success text-nowrap act" type="submit">我要發文</button>
        </form>
        <form action="<%=request.getContextPath() %>/frontend/chat/groupChat.jsp">
            <button class="btn btn-warning text-nowrap act" type="submit">加入聊天室</button>
        </form>
    </div>
    <div class="ad">
        <a href="<%=request.getContextPath() %>/frontend/item/shop.html">
            <img src="<%=request.getContextPath() %>/frontend/article/img/ad.PNG" style="width:320px; height: 320px;">
        </a>
    </div>
</div>

<!--  NavBar  -->
<script src="<%=request.getContextPath() %>/resources/static/js/navbar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/getName.js"></script>
<!--  Cart -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/cart.js"></script>


</body>


</html>
