<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%
  ItemVO itemVO = (ItemVO) request.getAttribute("itemVO"); 
%>
<!DOCTYPE html>
<html>
<head>
<title>商品詳細資料 - listOneItem.jsp</title>

<style>
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
  
  
  
  /*＊＊＊＊＊＊＊＊＊ 圖片相關 ＊＊＊＊＊＊＊＊＊＊＊*/
   .user-img {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            position: relative;
            min-width: 80px;
            background-size: 100%;
        }



        .owl-nav button {
            position: absolute;
            top: 50%;
            transform: translate(0, -50%);
            outline: none;
            height: 25px;
        }

        .owl-nav button svg {
            width: 25px;
            height: 25px;
        }

        .owl-nav button.owl-prev {
            left: 25px;
        }

        .owl-nav button.owl-next {
            right: 25px;
        }

        .owl-nav button span {
            font-size: 45px;
        }

        .product-thumb .item img {
            height: 100px;
        }
</style>

<style>
  img{
  width:200px;
  }
  table {
	width: 1000px;
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
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body bgcolor='white'>




<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table border="1" id="table-1">
		<thead>
			<tr>
				<th>編號</th>
				<th>種類編號</th>
				<th>名稱</th>
				<th>描述</th>
				<th>單價</th>
				<th>數量</th>
				<th>狀態</th>
				<th>上架日期</th>
				<th>下架日期</th>
				<th>圖片</th>
			</tr>
		</thead>
		<tbody>
			
				
					<tr>
						<td>${itemVO.itemId}</td>
						<td>${itemVO.itemtId}</td>
						<td>${itemVO.itemName}</td>
						<td>${itemVO.itemContent}</td>
						<td>${itemVO.itemPrice}</td>
						<td>${itemVO.itemAmount}</td>
						<td>${itemVO.itemStatus}</td>
						<td>${itemVO.itemDate}</td>
						<td>${itemVO.itemEnddate}</td>
						<%-- <img src="GetItemPhoto?action=getAllPhoto&item_id=${itemVO.item_id}"> --%>
						<td id="test">
							
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/item/items"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="oneactid" value="${itemVO.itemId}"> <input
									type="hidden" name="action" value="oneupdate">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/item/items"
								style="margin-bottom: 0px;">
								<input type="submit" value="刪除"> <input type="hidden"
									name="deleteactid" value="${itemVO.itemId}"> <input
									type="hidden" name="action" value="delete">
							</FORM>
						</td>
					</tr>
					
					
				
			
		</tbody>
	</table>

	<script>
	
		get();
		async function get(){
		
			let res=await fetch(`GetItemPhoto?action=getAllPhoto&item_id=${itemVO.itemId}`,{method:'get'});
			data = await res.json();
			data.forEach(e=>{
				$("#test").append(`<img src="data:image/jpeg;base64,\${e.photo}">`);
			})
			
		}
		
	</script>
</body>
</html>