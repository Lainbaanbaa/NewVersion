<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>

<%
    NewsService newsSvc = new NewsService();
    List<NewsVO> list = newsSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Barei後臺首頁</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/news.css">
	<style>
/* 		設定背景顏色 */
		section {
 			height: 100%; 
            background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
            background-color: #FFDEE9;
            background-repeat: no-repeat;
            background-size: cover;
        }
/* 		設定上方滾動公告 */
		.iconfont {
            font-size: 16px;
            font-style: normal;
            -webkit-font-smoothing: antialiased;
            -webkit-text-stroke-width: 0.2px;
            -moz-osx-font-smoothing: grayscale;
        }

        .ad {
            width: 94%;
            background-color: #fff;
            border-radius: 8px;
            box-sizing: border-box;
            padding: 0 20px;
            display: flex;
            align-items: center;
            justify-content: flex-start;
            box-shadow: 2px 1px 8px 1px rgb(228, 232, 235);
            margin: 40px auto;
        }

        .mdi-bell-ring {
            color: #ff6146;
            font-size: 20px;
            margin-right: 10px;
        }

        @keyframes marquee {
            0% {
                transform: translateX(0);
            }

            100% {
                transform: translateX(-100%);
            }
        }

        .content {
            flex: 1;
            overflow: hidden;
            margin-top: 13px;
        }

        .announcement {
            display: block;
            width: auto;
            white-space: nowrap;
            animation: marquee 30s linear infinite;
            padding-left: 105%;
            padding-right: 120%;
            font-size: 18px;
            font-weight: 600;
            color: balck;
        }

        :hover {
            animation-play-state: paused;
        }
        
/*         設定下方靜態公告 */

		.newsTitle{
			width: 100%;
			font-size: 24px;
			font-weight: 700;
			text-align: center;
			margin-bottom: 10px;
		}
		
		.newsHead{
			background-color: black;
			height: 50px;
			display: flex;
			align-items: center;
		}
		
		.nTitle{
			padding-left: 23px;
		}
		
		.nTime{
			padding-right: 12px;
		}
		
		hr{
			margin: 0 !important;
		}
	</style>
</head>
<body>
	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
		
    	
    	
    	<div class="container">
		<div class='ad'>
        <i class="mdi mdi-bell-ring"></i>
        <p class='content'>
            <span class="announcement"> 
            重要公告:為維護乾淨的廁所環境及提高同仁的工作效率，Barei洗手間從今日起不再提供衛生紙，且9:00~9:30如廁禁止攜帶手機，請同仁見諒。&ensp;&ensp;
            本月傑出員工: 03張東穎、05余若華、15賴昭延、19蔡嘉倫、20黃震熊、23許立晟 
            </span>
        </p>
    	</div>
    	
        <div class="newsTitle">Barei最新公告</div>
        <div class="displayBox">
        <div class="newsHead">
            <div class="nTitle">公告標題</div>
            <div class="nTime">發布時間</div>
        </div>

		<c:forEach var="newsVO" items="${list}">
        <div class="accordion accordion-flush" id="accordionFlushExample${newsVO.newsId}">
            <div class="accordion-item">
                <h2 class="accordion-header" id="flush-heading${newsVO.newsId}">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#flush-collapse${newsVO.newsId}" aria-expanded="false" aria-controls="flush-collapse${newsVO.newsId}">
                        <div class="allNews">
                            <div class="Title"><span class="newsTit">${newsVO.newsTitle}</span></div>
                            <div class="Time"><fmt:formatDate value="${newsVO.createTime}" type="both" /></div>
                        </div>
                    </button>
                </h2>

                <div id="flush-collapse${newsVO.newsId}" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
                    data-bs-parent="#accordionFlushExample">
                    <div class="accordion-body"><span class="newsCon">${newsVO.newsContent}</span></div>
                </div>
            </div>
        </div>
        <hr>
        </c:forEach>
        </div>
    </div>
		</section>
	</main>

</body>

</html>