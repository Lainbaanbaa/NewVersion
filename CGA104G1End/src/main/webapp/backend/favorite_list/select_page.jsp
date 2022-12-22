<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>會員加入商品</title>
</head>
<body>
	<a href='listAllFavorite_list.jsp'>List</a>
	<h3>資料查詢:</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
		
	
	</c:if>
	<div>
		<FORM METHOD="post" ACTION="fav.do">
			<b>輸入會員編號:</b> <input type="text" name="mem_id"> <input
				type="hidden" name="action" value="getOne_For_Display"> <input
				type="submit" value="送出">
		</FORM>
	</div>
	<jsp:useBean id="favorite_listSvc" scope="page"
		class="com.favorite_list.model.Favorite_listService"></jsp:useBean>
	<ul>
		<li>
			<FORM METHOD="post" ACTION="fav.do">
				<b>選擇會員編號:</b> 
				<select size="1" name="mem_id">
					<c:forEach var="favorite_listVO" items="${favorite_listSvc.all}">
						<option value="${favorite_listVO.mem_id}">${favorite_listVO.mem_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="fav.do">
				<b>選擇商品編號:</b> 
				<select size="1" name="mem_id">
					<c:forEach var="favorite_listVO" items="${favorite_listSvc.all}">
						<option value="${favorite_listVO.item_id}">${favorite_listVO.item_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>
	
	<div>
	<a href ='addFavorite.jsp'>Add</a>
	</div>
</body>
</html>