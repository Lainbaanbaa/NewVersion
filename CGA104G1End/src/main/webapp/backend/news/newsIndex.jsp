<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>


<%
    NewsService newsSvc = new NewsService();
    List<NewsVO> list = newsSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>最新公告管理</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backendStyle.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/news.css">
</head>

<body>
<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
			<div class="none"></div>
    <div class="container">
        <div class="btnArea">
            <button class="btn btn-success text-nowrap addNews" type="submit">新增公告</button>
        </div>

        <div class="none"></div>
        <div class="addArea"></div>
        <div class="displayBox">
        <div class="newsHead">
            <div class="nTitle">公告標題</div>
            <div class="nEmp">發布員工</div>
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
                            <div class="Emp">${newsVO.empVO.emp_name}</div>
                            <div class="Time"><fmt:formatDate value="${newsVO.createTime}" type="both" /></div>
                        </div>
                    </button>
                </h2>

                <div id="flush-collapse${newsVO.newsId}" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
                    data-bs-parent="#accordionFlushExample">
                    <div class="btnArea">
                        <form method="post" action="/CGA104G1/newsServlet">
                        	<input type="hidden" name="newsId" value="${newsVO.newsId}">
                        	<input type="hidden" name="action" value="delete">
                        	<button class="btn btn-outline-danger text-nowrap" type="submit">刪除公告</button>
                        </form>
                    </div>
                    <div class="accordion-body"><span class="newsCon">${newsVO.newsContent}</span></div>
                </div>
            </div>
        </div>
        </c:forEach>
        </div>
    </div>
		</section>
	</main>
    

    <script src="<%=request.getContextPath() %>/ckeditor5/build/ckeditor.js"></script>
    <script>

        // 點擊新增公告觸發動作
        $('.addNews').click(function () {
            $('.addArea').html(`<div class="form">
			<form method="post" action="/CGA104G1/newsServlet" name="form1">					
				
				<div class="ntitle">
					<div class="titleBlock">新增公告</div>
                    <span class="addWord">公告標題</span><br>
					<input type="text" class="addTitle" name="newsTitle" placeholder="請輸入公告標題" value="${param.newsTitle}" required>
				</div>
				<div class="addContent">
					<span class="addWord">公告內容</span><br>
					<textarea name="newsContent" class="editor" value="${param.newsContent}"></textarea>
				</div>
				<input type="hidden" name="empId" value="<c:forEach var="empVO" items="${empList}" begin="0" end="0" >${empVO.emp_id}</c:forEach>">
                <div class="none"></div>
				<input type="hidden" name="action" value="insert">
                <div class="btnArea">
				<button class="btn btn-info" value="送出新增" type="submit">確認發布</button>
                </div>
                </form>
            <div class="none"></div>
			</div>
		</div>
		`);

            ClassicEditor
                .create(document.querySelector('.editor'), {
                    cloudServices: {
                    	tokenUrl: 'https://93940.cke-cs.com/token/dev/91a66de3b51363daded4bc4c728bc0c521dfcfff6dc0a75d8f54767efd03?limit=10',
                        uploadUrl: 'https://93940.cke-cs.com/easyimage/upload/'
                    },
                    toolbar: {
                        items: [
                            'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList',
                            '|', 'alignment', 'outdent', 'indent', '|', 'fontSize', 'fontColor',
                            '|', 'imageUpload', 'blockQuote', 'insertTable', 'mediaEmbed',
                            'undo', 'redo'
                        ]
                    }
                })
                .then(editor => {
                    console.log(editor);
                });
            $('.addNews').hide();
        })
    </script>
</body>

</html>