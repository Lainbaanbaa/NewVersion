<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>This is the Home page for BA_REI ITEM: Home</p>
  <ul>
  		<li><a href='listAllItems.jsp'>List</a> all Items.  <br><br></li>
  		<li>
    		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item/items" >
        		<b>輸入商品編號:</b>
        		<input type="text" name="item_id">
        		<input type="hidden" name="action" value="getOne_For_Display">

        		<input type="submit" value="送出">
    		</FORM>
  		</li>
  		<br>

  		 <jsp:useBean id="itemTypeSvc" scope="page" class="com.itemType.model.ItemTypeDAO1" />

  		<li>
			<b>商品種類</b>
			<select name="itemType">
				<c:forEach var="type" items="${itemTypeSvc.all}">
					<option value="${type.itemtId}">${type.itemtName}
				</c:forEach>
			</select>
		</li>
	</ul>
	<h3>商品管理</h3>
	<ul>
  		<li><a href='addItem.jsp'>Add</a> a new Item.</li>
	</ul>

	  <%-- <jsp:useBean id="dao" scope="page" class="com.item.model.ItemDAO" /> --%>


	<script>

		/* const itemType = document.querySelector('#itemType');
		fetch('xxx')
			.then(resp => resp.json())
			.then(typeList => {
				let htmlStr = '';
				for (let type of typeList) {
					html =+ `<option value="\${type.itemt_id}">\${type.itemt_name}</option>`;
				}
				itemType.innerHTML = htmlStr;
			}); */
	</script>
</body>
</html>
