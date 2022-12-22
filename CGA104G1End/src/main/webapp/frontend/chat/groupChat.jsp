<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Barei線上群聊</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.1.js"
            integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="../../resources/static/css/main.css"/>
    <style>
        #dropdownMenuLink {
            display: none;
        }

        /* 設定網頁背景 */
        body {
            background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
            background-color: #FFDEE9;
            background-repeat: no-repeat;
            background-size: cover;
        }


        /* 設定聊天室頂端 */
        .head {
            display: flex;
            white-space: nowrap;
            justify-content: center;
            align-items: center;
        }

        #topic {
            font-size: 24px;
            font-weight: 700;
        }

        .status {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .statusOutput {
            font-size: 14px;
            font-weight: 700;
            color: green;
        }

        /* 設定聊天框與訊息框container */
        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }


        /* 設定聊天顯示框 */
        #messagesArea {
            resize: none;
            width: 75%;
            height: 400px;
            color: white;
            font-weight: 500;
        }

        #messagesArea {
            background-image: url(<%=request.getContextPath()%>/frontend/chat/img/chatBG.jpg);
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            background-color: rgba(20, 20, 20, 0.7);
            background-blend-mode: multiply;
            opacity: 60%;
        }

        /* 設定訊息輸入處 */
        .input-area {
            width: 75%;
            padding: 10px 0;
            display: flex;
        }

        #userName {
            width: 100px;
            text-align: left;
        }

        #message {
            width: 80%;
        }

        .btn {
            height: 30px;
            padding: 0 10px;
            text-align: right !important;
        }

        .input {
            position: relative;
            display: inline-block;
            border-radius: 20px;
        }

        input, input:focus {
            border-radius: 20px;
            border: 2px solid pink !important;
            text-align: center;
        }
    </style>
</head>

<body onload="connect();" onunload="disconnect();">
<div class="head">
    <img src="<%=request.getContextPath()%>/frontend/chat/img/logo.png" alt="Barei" style="width: 100px; height:40px">
    <span id="topic">毛家長聊天室</span><br>

</div>

<div class="status">
    <h3 id="statusOutput" class="statusOutput"></h3>
</div>

<div class="container">
    <textarea id="messagesArea" class="panel message-area" readonly></textarea>
    <div class="panel input-area">
        <input id="userName" class="text-field input" type="text" placeholder="暱稱"/>&ensp;
        <input id="message" class="text-field input" type="text" placeholder="講點話吧!"
               onkeydown="if (event.keyCode == 13) sendMessage();"/>&ensp;
        <input type="submit" id="sendMessage" class="button btn btn-info" value="送出" onclick="sendMessage();"/>
        <!-- <input type="button" id="connect" class="button" value="連線" onclick="connect();" /> -->
        <!-- <input type="button" id="disconnect" class="button" value="離開" onclick="disconnect();" /> -->
    </div>
</div>


</body>

<script>
    var MyPoint = "/GroupChat/gallon";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

    var statusOutput = document.getElementById("statusOutput");
    var webSocket;

    function connect() {
        webSocket = new WebSocket(endPointURL);

        webSocket.onopen = function (event) {
            updateStatus("已連線");
            document.getElementById('sendMessage').disabled = false;
            document.getElementById('connect').disabled = true;
            document.getElementById('disconnect').disabled = false;
        };

        webSocket.onmessage = function (event) {
            var messagesArea = document.getElementById("messagesArea");
            var jsonObj = JSON.parse(event.data);
            var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
            messagesArea.value = messagesArea.value + message;
            messagesArea.scrollTop = messagesArea.scrollHeight;
        };

        webSocket.onclose = function (event) {
            updateStatus("連線已中斷");
        };
    }

    var inputUserName = document.getElementById("userName");
    inputUserName.focus();

    function sendMessage() {
        var userName = inputUserName.value.trim();
        if (userName === "") {
            alert("請輸入暱稱!");
            inputUserName.focus();
            return;
        }

        var inputMessage = document.getElementById("message");
        var message = inputMessage.value.trim();
        console.log(message);

        if (message === "") {
            alert("請輸入訊息!");
            inputMessage.focus();
        } else {
            var jsonObj = {
                "userName": userName,
                "message": message
            };
            webSocket.send(JSON.stringify(jsonObj));
            inputMessage.value = "";
            inputMessage.focus();
        }
    }

    function disconnect() {
        webSocket.close();
        document.getElementById('sendMessage').disabled = true;
        document.getElementById('connect').disabled = false;
        document.getElementById('disconnect').disabled = true;
    }

    function updateStatus(newStatus) {
        statusOutput.innerHTML = newStatus;
    }
</script>


<!--  NavBar  -->
<script src="../../resources/static/js/navbar.js"></script>
<!--  Footer  -->
<script src="../../resources/static/js/footer.js"></script>
</body>

<script type="text/javascript" src="../../resources/static/js/getName.js"></script>
<!--  Cart -->
<script type="text/javascript" src="../../resources/static/js/cart.js"></script>
</html>
