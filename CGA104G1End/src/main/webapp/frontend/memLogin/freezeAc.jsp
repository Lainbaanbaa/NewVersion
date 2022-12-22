<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@page import="com.mem.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Integer mem_id = (Integer) session.getAttribute("mem_id");
    String mem_email = (String) session.getAttribute("mem_email");
    MemService memSvc = new MemService();
    MemVO memVO = memSvc.findByMemId(mem_id);
    session.setAttribute("memVO", memVO);
    session.invalidate();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>notVerify</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    <!-- import font-style -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">

    <!-- import css stylesheet -->

    <!-- import jquery-3.6.0 -->
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>

    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/static/css/main.css"/>
    <style type="text/css">
         body {
            background: rgba(0, 0, 0, .6) url('<%=request.getContextPath()%>/resources/static/image/pexels-wkn-1933464.jpg') no-repeat fixed center center;
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
<%-- <% System.out.print(mem_email); %> --%>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-8 col-lg-5 mx-auto">
            <div class="card border-0 shadow rounded-3 my-5" style="background-color: #0ab4a2">
                <div class="card-body p-4 p-sm-5">
                    <form method="post" action="<%=request.getContextPath()%>/MemServlet">
                        <div style="color:red">您的帳號已被停權,請聯繫管理員</div>
                        <!-- 			系統將在<span style="color: red">120</span>秒後跳轉回首頁!<br> -->


                        <input class="btn btn-light" type="button" value="回到登入頁面"
                               onclick="location.href='<%=request.getContextPath()%>/frontend/memLogin/login.jsp'">

                        <input class="btn btn-light" type="button" value="聯繫客服"
                               onclick="location.href='<%=request.getContextPath()%>/frontend/chat/privateChat.jsp'">


                        <div style="color:red">${msg}</div>


                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
