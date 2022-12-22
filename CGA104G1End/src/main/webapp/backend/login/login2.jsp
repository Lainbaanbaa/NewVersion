<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body> 
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<form action="/CGA104G1/LoginServlet" method="post">
		
			<table border=1>
				<tr>
					<td colspan=2>
						<p align=center>
							輸入<b>(測試登入)</b>:<br> 
							帳號:<b>tomcat</b><br>
							密碼:<b>tomcat</b><br>
					</td>
				</tr>

				<tr>
					<td>
						<p align=right>
							<b>account:</b>
					</td>
					<td>
						<p>
							<input type=text name="account" value="account" size=15>
					</td>
				</tr>

				<tr>
					<td>
						<p align=right>
							<b>password:</b>
					</td>
					<td>
						<p>
							<input type=password name="password" value="password" size=15>
					</td>
				</tr>


				<tr>
					<td colspan=2 align=center>
						<input type="hidden" name="action" value="login">
							<input type=submit value="  ok   ">
						
					</td>
				</tr>
			</table>
	</form>

</body>
</html>