<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.group_buy.model.*" %>
<%
    Group_BuyService group_BuyService = new Group_BuyService();
    List<Group_BuyVO> list = group_BuyService.getAll();
    pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>GroupBuy: Home</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
    <style>
        section {
            height: 100%;
            background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
            background-color: #FFDEE9;
            background-repeat: no-repeat;
            background-size: cover;
        }

        #main-content {
            display: flex;
            margin-left: 10%;
            text-align: center !important;
            justify-content: center;
        }

        .wrapper {
            margin-top: 40px;
        }

        .btnIn {
            width: 60px;
            border-radius: 20px !important;
        }

        h3 {
            font-weight: 700 !important;
        }

        .search {
            margin-bottom: 15px;
        }

        select, input {
            width: 200px;
            height: 30px;
            border-radius: 20px;
            border: none;
        }

        select:focus, input:focus {
            border: 2px solid pink !important;
        }

        .btnL {
            width: 180px;
            height: 30px;
            border-radius: 20px !important;
        }
    </style>
</head>
<body>
<nav>
    <%@include file="/backend/topNavbar.jsp" %>
</nav>
<main>
    <%@include file="/backend/leftside.jsp" %>
    <section>
        <!--main content start-->
        <section id="main-content">
            <section class="wrapper">
                <div id="gbi-select">
                    <%-- 錯誤表列 --%>
                    <c:if test="${not empty errorMsgs}">
                        <font style="color: red">請修正以下錯誤:</font>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li style="color: red">${message}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <table>
                        <!-- 					<li><a href='listAllGroupBuy.jsp' action="">List</a> all Discount. <br> -->
                        <!-- 					<br></li> -->

                        <h3>團購團管理</h3>
                        <div class="search">
                            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/GroupBuyServlet">
                                <b>輸入團購團編號:</b>
                                <input type="text" name="gb_id">
                                <input type="hidden" name="action" value="getOne_For_Display">
                                <input type="submit" value="送出" class="btn btn-info btnIn">
                            </FORM>
                        </div>

                        <%-- 					<jsp:useBean id="gbSvc" scope="page"class="com.group_buy.model.Group_BuyService" /> --%>

                        <div class="search">
                            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/GroupBuyServlet">
                                <b>選擇團購團編號:</b> <select size="1" name="gb_id">
                                <c:forEach var="Group_BuyVO" items="${list}">
                                <option value="${Group_BuyVO.gb_id}">${Group_BuyVO.gb_id}
                                    </c:forEach>
                            </select>
                                <input type="hidden" name="action" value="getOne_For_Display">
                                <input type="submit" value="送出" class="btn btn-info btnIn">
                            </FORM>
                        </div>

                        <div class="search">
                            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/GroupBuyServlet">
                                <b>選擇團購團:</b>&emsp;&emsp; <select size="1" name="gb_id">
                                <c:forEach var="Group_BuyVO" items="${list}">
                                <option value="${Group_BuyVO.gb_id}">${Group_BuyVO.gb_name}
                                    </c:forEach>
                            </select>
                                <input type="hidden" name="action" value="getOne_For_Display">
                                <input type="submit" value="送出" class="btn btn-info btnIn">
                            </FORM>
                        </div>


                        <button onclick="location.href='<%=request.getContextPath()%>/backend/group_buy/addGroupBuy.jsp'"
                                class="btn btn-success btnL">新增團購團
                        </button>
                        &ensp;&ensp;
                        <button onclick="location.href='<%=request.getContextPath()%>/backend/group_buy/listAllGroupBuy.jsp'"
                                class="btn btn-dark btnL">查詢所有團購團
                        </button>
                    </table>
                </div>
            </section>
        </section>

        <!--main content end-->
    </section>
</main>


</body>
</html>
