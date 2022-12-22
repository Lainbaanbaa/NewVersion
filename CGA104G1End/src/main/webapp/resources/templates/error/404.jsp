<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
EmpService navEmpSvc = new EmpService();
List<EmpVO> empList = navEmpSvc.login((String)session.getAttribute("account"),(String)session.getAttribute("password"));
session.setAttribute("empList", empList);
%> 

<!DOCTYPE html>
<html>


<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>404 Error Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" async src="https://tenor.com/embed.js"></script>
    <style>
    	.gif{
    		display: flex;
    		align-items: center; 
    		justify-content:center;
    	}
    	
    	.btn{
    		margin-bottom: 5px;
    	}
    </style>
</head>


<body>
<div class="d-flex align-items-center justify-content-center vh-100">
    <div class="text-center">
        <h1 class="display-1 fw-bold">404</h1>
        <p class="fs-3"><span class="text-danger">Opps!</span> Page not found.</p>
        <p class="lead">
            The page you're looking for doesn't exist.
        </p>
        
        <c:if test="${empVO == null}">
        	<a href="/CGA104G1/frontend/index.html" class="btn btn-primary">回首頁</a>
		</c:if>
		<c:if test="${empVO != null}">
			<a href="/CGA104G1/backend/index.jsp" class="btn btn-warning">回後台</a>
		</c:if>

        
        
        <div class="gif">
        	<div class="tenor-gif-embed" data-postid="26454424" data-share-method="host" data-aspect-ratio="1"
             data-width="300px"><a href="https://tenor.com/view/cat-meme-xd-haha-cute-bb-cat-gif-26454424">Cat Meme
            GIF</a>from <a href="https://tenor.com/search/cat-gifs">Cat GIFs</a></div>
        </div>
        
    </div>
</div>
</body>


</html>
