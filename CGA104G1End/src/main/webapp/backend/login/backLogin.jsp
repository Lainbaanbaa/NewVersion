<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
	
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <style>
        body {
            background-image: url(../asset/img/share_icon/back.jpg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
            background-size: cover;
            background-color: rgba(29, 29, 29, 0.7);
            background-blend-mode: multiply;
        }

        body,
        html {
            height: 100%;
        }

        .background {
            width: 50%;
            height: 50%;
            background-color: white;
            background-color: rgba(29, 29, 29, 0.7);
            background-blend-mode: multiply;
        }

        .container {
            padding-left: 5%;
            padding-right: 5%;
            padding-bottom: 3%;
        }

        .forget {
            font-size: 16px;
            color: #986800;
        }

        .form-label {
            color: white;
        }

        .text {
            color: white;
        }
    </style>
</head>

<body>
    <div class="container h-100 background">
        <div class="row h-100 justify-content-center align-items-center">
            <div></div>
            <div><img src="<%=request.getContextPath()%>/backend/asset/img/share_icon/logo.png" width="100%" height="100%"></div>
            <form action="LoginServlet" method="post">
                <div class="mb-3">
                    <label for="account" class="form-label">帳號</label>
                    <input type="text" name="account" value="account" class="form-control" id="account" placeholder="請輸入帳號">
                </div>
                <div> </div>
                <div class="mb-3">
                    <label for="password" class="form-label">密碼</label>
                    <input type="password" name="password" value="password" class="form-control" id="password" placeholder="請輸入密碼">
                </div>
                 <div> <font color=red>${errorMsgs.account}</font> </div>
                 <div> <font color=red>${errorMsgs.password}</font> </div>
                <br>
                <br>
                <div class="d-grid gap-2">
                 <input  name="action" value="login" type="hidden">
                    <button   class="btn btn-outline-warning" type="submit">員工登入</button>
                </div>
            </form>
        </div>
    </div>
</body>

</html>