<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.discount.model.*" %>
<%@ page import="com.group_buy_item.model.*" %>

<%
    DiscountService discountSvc = new DiscountService();
    Integer gbitem_id = (Integer) session.getAttribute("gbitem_id");
    List<DiscountVO> list = discountSvc.getoneGbitem_id((Integer) session.getAttribute("gbitem_id"));
    pageContext.setAttribute("list", list);
%>


<%@include file="/backend/backNavbar.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>所有團購折扣</title>
    <!-- import jquery-3.6.0 -->
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
    <!-- import font-style -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">

    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/static/css/main.css"/>
    <style type="text/css">

        #dropdownMenuLink {
            display: none;
        }

        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }

        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }

        h4 {
            color: blue;
            display: inline;
        }
    </style>

    <style>
        table {
            width: 800px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        table, th, td {
            border: 1px solid #CCCCFF;
        }

        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body>
<a href="select_page.jsp">回團購商品選擇</a>
<table>
    <tr>
        <th>商品</th>
        <th>折扣</th>
        <th>團購數量</th>

    </tr>

    <c:forEach var="DiscountVO" items="${list}">
        <tr>
            <td>${gbitem_name}</td>
            <td>${DiscountVO.discount_nar}</td>
            <td>${DiscountVO.discount_minamount}</td>


            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/discount/DiscountServlet"
                      style="margin-bottom: 0px;">
                    <input type="submit" value="選擇折扣">
                    <input type="hidden" name="discount_id" value="${DiscountVO.discount_id}">
                    <input type="hidden" name="action" value="getOne_For_Display_ByUser"></FORM>
            </td>
        </tr>
    </c:forEach>
</table>
<!--  NavBar  -->
<script src="<%=request.getContextPath() %>/resources/static/js/navbar.js"></script>
<!--  Footer  -->
<script src="<%=request.getContextPath() %>/resources/static/js/footer.js"></script>


<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/getName.js"></script>
<!--  Cart -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/static/js/cart.js"></script>
</body>

</html>
