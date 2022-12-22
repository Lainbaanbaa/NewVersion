<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_buy_item.model.*"%>
<%
Group_Buy_ItemService group_Buy_ItemService = new Group_Buy_ItemService();
List<Group_Buy_ItemVO> list = group_Buy_ItemService.getAll();
pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>團購商品管理</title>
<!-- 第9行一定要記得複製!!! -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<!-- 原本的STYLE放這邊(可能需要調一下) -->
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
        
h4 {
	color: blue;
	display: inline;
}

.btnTitle {
	margin-top: 2% !important;
	margin-bottom: 10% !important;
}

select, input {
    	width: 300px;
    	height: 30px;
    	border-radius: 20px;
    	border: none;
    	text-align: center;
    }
    
select:focus, input:focus {
	border: 2px solid pink !important;
}

.btnSmall{
	width: 60px;
	border-radius: 20px !important;
}

.btnIn {
	width: 200px;
	border-radius: 20px !important;
}

.titleIn {
	font-size: 24px;
	color: black;
	font-weight: 700;
}

</style>

</head>
<body bgcolor='white'>

	<!-- 複製起點 -->
	<nav><%@include file="/backend/topNavbar.jsp"%></nav>
	<main>
		<%@include file="/backend/leftside.jsp"%>
		<section>
			<!-- 		把原本body的東西貼到這邊 -->
			<!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
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


								<div class="btnTitle">
								<div class="titleIn">團購商品管理</div><br>
								<button onclick="location.href='<%=request.getContextPath()%>/backend/group_Buy_Item/listAllGroupBuyItem.jsp'" class="btn btn-dark btnIn">檢視所有團購商品</button>
								</div>

								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/Group_Buy_Item/groupBuyItem.do">
									<b>輸入團購商品編號:</b> <input type="text" name="gbitem_id"> <input
										type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" value="送出" class="btn btn-info btnSmall">
								</FORM>

							<jsp:useBean id="gbiSvc" scope="page"
								class="com.group_buy_item.model.Group_Buy_ItemService" />

								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/Group_Buy_Item/groupBuyItem.do">
									<b>選擇團購商品編號:</b> <select size="1" name="gbitem_id">
										<c:forEach var="Group_Buy_ItemVO" items="${list}">
											<option value="${Group_Buy_ItemVO.gbitem_id}">${Group_Buy_ItemVO.gbitem_id}
										</c:forEach>
									</select> <input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" value="送出" class="btn btn-info btnSmall">
								</FORM>

								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/Group_Buy_Item/groupBuyItem.do">
									<b>團購商品選擇:</b>&ensp;&ensp;&ensp;&ensp; <select size="1" name="gbitem_id">
										<c:forEach var="Group_Buy_ItemVO" items="${list}">
											<option value="${Group_Buy_ItemVO.gbitem_id}">${Group_Buy_ItemVO.gbitem_name}
										</c:forEach>
									</select> <input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" value="送出" class="btn btn-info btnSmall">
								</FORM>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/Group_Buy_Item/groupBuyItem.do">
									<b>團購商品類別選擇:</b> <select size="1" name="gbitem_id">
										<c:forEach var="Group_Buy_ItemVO" items="${list}">
											<option value="${Group_Buy_ItemVO.gbitem_id}">${Group_Buy_ItemVO.gbitem_type}

												<c:if test="${Group_Buy_ItemVO.gbitem_type == '1'}">
													<td><c:out value="狗狗飼料"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '2'}">
													<td><c:out value="狗狗罐頭"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '3'}">
													<td><c:out value="狗狗零時"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '4'}">
													<td><c:out value="狗狗用品"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '5'}">
													<td><c:out value="貓咪飼料"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '6'}">
													<td><c:out value="貓咪主食罐"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '7'}">
													<td><c:out value="貓咪副食罐"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '8'}">
													<td><c:out value="貓咪零食"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '9'}">
													<td><c:out value="犬貓凍乾"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '10'}">
													<td><c:out value="貓咪用品"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '11'}">
													<td><c:out value="貓砂"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '12'}">
													<td><c:out value="犬用保健品"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '13'}">
													<td><c:out value="貓用保健品"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '14'}">
													<td><c:out value="冷凍營養鮮食"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '15'}">
													<td><c:out value="保健食品"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '16'}">
													<td><c:out value="飲食/飲水"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '17'}">
													<td><c:out value="寵具/圍籃"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '18'}">
													<td><c:out value="外出用品"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '19'}">
													<td><c:out value="居家清潔"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '20'}">
													<td><c:out value="洗澡/美容"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '21'}">
													<td><c:out value="寵物訓練用品"></td>
													</c:out>
												</c:if>
												<c:if test="${Group_Buy_ItemVO.gbitem_type == '22'}">
													<td><c:out value="除蚤用品"></td>
													</c:out>
												</c:if>
										</c:forEach>
									</select> <input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" value="送出" class="btn btn-info btnSmall">
								</FORM>


						<div class="btnTitle">
								<button onclick="location.href='<%=request.getContextPath()%>/backend/group_Buy_Item/addGroupBuyItem.jsp'" class="btn btn-success btnIn">新增團購商品</button>
						</div>


					</div>
				</section>
			</section>

			<!--main content end-->
		</section>
	</main>
	<!-- 複製終點 -->
</body>
</html>