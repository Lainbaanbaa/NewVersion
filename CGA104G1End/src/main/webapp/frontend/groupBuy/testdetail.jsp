<%@page import="com.news.controller.newsServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_buy_item.model.*"%>
<%@ page import="com.group_buy_item_picture.model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!--     <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script> -->
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

<!--     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
 -->

<!-- import bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">

<!-- improt font-style -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap"
	rel="stylesheet">

<!-- import css stylesheet -->
<link rel="stylesheet" href="./css/style.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.css">

<!-- import jquery-3.6.0 -->
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- import slick-carousel -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.js"></script>

<!-- import icon -->
<script src="https://kit.fontawesome.com/b5ef6b60f3.js"
	crossorigin="anonymous"></script>

<!--item_detail.css	-->
<link rel="stylesheet" type="text/css"
	href="../../resources/static/css/item_detail.css">

<!-- slider -->
<link href="https://cdn.bootcss.com/flexslider/2.6.3/flexslider.min.css"
	rel="stylesheet">
<script
	src="https://cdn.bootcss.com/flexslider/2.6.3/jquery.flexslider-min.js"></script>


<!--	sweetalert-->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>


<body>


	<header>

		<!-- 導覽列 -->
		<nav class="navbar navbar-expand-lg">
			<div class="container-fluid">
				<a class="navbar-brand" href="#"> <!-- 網站 logo --> <img
					src="../../resources/static/image/Ba-Rei-logo3.png" alt="Logo"
					width="200px" height="10%" class="d-inline-block align-text-top">
				</a>

				<!-- 右側 toggle 按鈕 -->
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<!-- 導覽列內容 -->
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">首頁</a></li>
						<li class="nav-item"><a class="nav-link" href="#">毛孩の商城</a></li>
						<li class="nav-item"><a class="nav-link" href="#">團購底加啦</a></li>
						<li class="nav-item"><a class="nav-link" href="#">家長討論區</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> <i
								class="fa-regular fa-user"></i> 會員中心
						</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">成為會員 / 登入會員</a></li>
								<li><a class="dropdown-item" href="#">查看購物車</a></li>
								<li><a class="dropdown-item" href="#">追蹤訂單</a></li>
								<li><a class="dropdown-item" href="#">團購訂單</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item" href="#">會員中心首頁</a></li>
							</ul></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> <i
								class="fa-regular fa-paper-plane"></i> 客服專區
						</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">關於我們</a></li>
								<li><a class="dropdown-item" href="#">常見問題 Q&A</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item" href="#">客服專區首頁</a></li>
							</ul></li>
					</ul>

					<form class="d-flex" role="search">
						<input class="form-control me-2" type="search"
							placeholder="今天你想來點..." aria-label="Search" id="search_bar">
						<button class="btn btn-outline-success" type="submit"
							id="search_btn">Search</button>
					</form>


				</div>
				<div>
					<a href="cart.html"
						style="text-decoration: none; margin-left: 10px; color: #484747">
						<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30"
							fill="currentColor" class="bi bi-cart3" viewBox="0 0 16 16"
							style="margin-bottom: 10px">
                            <path
								d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
                        </svg> <span class='badge badge-warning'
						id='lblCartCount'> 5 </span>


					</a>
				</div>
			</div>
		</nav>
	</header>

	<main>
		<div class="section product-detail">
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<span class="onsale" id="onsale">Sale!</span> <a
							class="prettyphoto" data-rel="prettyPhoto[gallery]"
							href="images/shop/shop_6.jpg"> <img alt="" class="fullwidth"
							id="product-img">
						</a>
						<div class="flexslider">
							<ul class="slides"></ul>
						</div>
						<div>
							<img class=""
								data-src="<%=request.getContextPath()%>/groupBuyItemPicture/groupBuyItemPictureGetOne.do?gbitem_id=${Group_Buy_ItemVO.gbitem_id}"
								src="<%=request.getContextPath()%>/groupBuyItemPicture/groupBuyItemPictureGetOne.do?gbitem_id=${Group_Buy_ItemVO.gbitem_id}">
						</div>
					</div>
					<div class="col-sm-6">
						<h1 id="product-title"></h1>

						<div class="price mt-1" id="product-price"></div>

						<div id="product-content"></div>

						<div class="mb-3"></div>
						<p class="stock in-stock">最低開團商品數量</p>
						<div>${Group_BuyVO.gb_min}</div>
						<p class="stock in-stock">已下定團購商品數量</p>
						<div>
							<td>${Group_BuyVO.gb_amount}</td>
						</div>
						<form class="cart">

							<div id="product-amount"></div>
							<a href="#" class="btn btn-rounded btn-dark" id="addToCart"
								onclick="addToCart()"><span>Add to cart</span> </a> <a href="#"
								class="btn btn-rounded btn-dark" onclick="addToTrace()"
								id="Trace"><span>trace</span></a>

						</form>
						<div class="product_meta">
							<span class="posted_in">Categories: <a href="#">Bottom</a>,
								<a href="#">Clothing</a></span> <span class="tagged_as">Tags: <a
								href="#">Art</a>, <a href="#">Design</a>, <a href="#">Music</a>,
								<a href="#">Photography</a></span>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="commerce-tabs tabs mt-5 mb-4">
							<!-- Nav tabs -->
							<div class="nav-tabs-wrapper">
								<ul class="nav nav-tabs tabs text-center">
									<li><a class="active" href="#tab-content-1"
										data-toggle="tab">Description</a></li>
									<li><a href="#tab-content-2" data-toggle="tab">Reviews</a>
									</li>
								</ul>
							</div>
							<!-- Tab panes -->
							<div class="tab-content">

								<div class="tab-pane fade" id="tab-content-2"></div>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="related-products">
							<h2>Related Products</h2>
							<div class="row">
								<div class="col-sm-3">
									<div class="product-item">
										<div class="thumb">
											<a href="shop-detail.html"> <img
												src="http://tk-themes.net/html-heli/demo/images/shop/shop_1.jpg"
												alt="">
											</a>
											<div class="extra">
												<div>
													<p>
														<i class="pe-7s-cart"></i> <a href="#" class="btn-shop">Add
															to cart</a>
													</p>
													<p>
														<i class="pe-7s-search"></i> <a class="btn-shop"
															href="shop-detail.html">View item</a>
													</p>
												</div>
											</div>
										</div>
										<a href="shop-detail.html">
											<h3 class="text-center">Basic Crop-top</h3>
										</a>
										<div class="info text-center">
											<span class="price">$20.00</span>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="product-item">
										<div class="thumb">
											<a href="shop-detail.html"> <img
												src="http://tk-themes.net/html-heli/demo/images/shop/shop_2.jpg"
												alt="">
											</a>
											<div class="extra">
												<div>
													<p>
														<i class="pe-7s-cart"></i> <a href="#" class="btn-shop">Add
															to cart</a>
													</p>
													<p>
														<i class="pe-7s-search"></i> <a class="btn-shop"
															href="shop-detail.html">View item</a>
													</p>
												</div>
											</div>
										</div>
										<a href="shop-detail.html">
											<h3 class="text-center">Black One-piece</h3>
										</a>
										<div class="info text-center">
											<span class="price">$20.00</span>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="product-item">
										<div class="thumb">
											<a href="shop-detail.html"> <img
												src="http://tk-themes.net/html-heli/demo/images/shop/shop_3.jpg"
												alt="">
											</a>
											<div class="extra">
												<div>
													<p>
														<i class="pe-7s-cart"></i> <a href="#" class="btn-shop">Add
															to cart</a>
													</p>
													<p>
														<i class="pe-7s-search"></i> <a class="btn-shop"
															href="shop-detail.html">View item</a>
													</p>
												</div>
											</div>
										</div>
										<a href="shop-detail.html">
											<h3 class="text-center">Blue dress</h3>
										</a>
										<div class="info text-center">
											<span class="price">$20.00</span>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="product-item">
										<div class="thumb">
											<a href="shop-detail.html"> <img
												src="http://tk-themes.net/html-heli/demo/images/shop/shop_4.jpg"
												alt="">
											</a>
											<div class="extra">
												<div>
													<p>
														<i class="pe-7s-cart"></i> <a href="#" class="btn-shop">Add
															to cart</a>
													</p>
													<p>
														<i class="pe-7s-search"></i> <a class="btn-shop"
															href="shop-detail.html">View item</a>
													</p>
												</div>
											</div>
										</div>
										<a href="shop-detail.html">
											<h3 class="text-center">Gray sweater</h3>
										</a>
										<div class="info text-center">
											<span class="price">$20.00</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!-- import main.js -->
	<script src="./js/main.js"></script>

	<!-- import bootstrap 5.2.1 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
		crossorigin="anonymous"></script>
	<!-- 
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="	sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script> -->
	<script>
		/*  $(function () {
		     $(".flexslider").flexslider({
		         animation: "slide",
		         touch: true
		     });
		 });   */
	</script>
	<script type="text/javascript">
		let queryString = window.location.search;
		let urlParams = new URLSearchParams(queryString);
		let gbitem_id = urlParams.get("gbitem_id")

		// let bidProductVO = 0;
		// let bidRecord = 0;
		$
				.ajax({
					url : `/CGA104G1/Group_Buy_Item/groupBuyItem.do?gbitem_id=${gbitem_id}`,
					type : "POST",
					success : function(data) {
						showonegroupbuy(data);
					}
				})

		// 	function showonegroupbuy(data) {

		// 		let all = JSON.parse(data)
		// 		    //  bidProductVO = all.bidProductVO;
		//             // bidRecordVO = all.bidRecordVO;
		//             // let productVO = all.productVO;
		//             // let gameTypeVO = all.gameTypeVO;
		//             // let gamePlatformTypeVO = all.gamePlatformTypeVO;
		//             // let gameCompanyVO = all.gameCompanyVO;
		//             // let bidPrice = "目前尚未有出價紀錄";
		//             // if (bidRecordVO) {
		//             //     bidPrice = bidRecordVO.bidPrice;
		//             // }
		//             // let bidLaunchedTime = moment(bidProductVO.bidLaunchedTime).format('YYYY-MM-DD HH:mm:ss');
		//             // let bidSoldTime = moment(bidProductVO.bidSoldTime).format('YYYY-MM-DD HH:mm:ss');

		//             //取出圖片
		//             .ajax({
		//             url: `/CGA104G1/groupBuyItemPicture/groupBuyItemPictureGetAll.do?=${gbitem_id}`,
		//             type: "POST",
		//             success: function (data) {
		//                 showAllPic(data);
		//             }
		//         })

		//         function showAllPic(data) {
		//             let pics = JSON.parse(data);
		//             let div = document.querySelector(".slides");
		//             let bidProdPicHTML = "";
		//             bidProdPicHTML += `
		//         <div class="carousel-item active">
		//             <img src="/CGA104G1/groupBuyItemPicture/groupBuyItemPictureGetOne.do?gbitem_id=${pics[0].gbitem_id}" class="d-block w-100" alt="...">
		//         </div>
		//         `;
		//             for (let i = 1; i < pics.length; i++) {
		//                 bidProdPicHTML += `
		//             <div class="carousel-item">
		//                 <img src="/CGA104G1/groupBuyItemPicture/groupBuyItemPictureGetOne.do?gbitem_id=${pics[i].gbitem_id}" class="d-block w-100" alt="...">
		//             </div>
		// 			`;
		//             }
		//             div.innerHTML = bidProdPicHTML;
		//         }
		// 	}
	</script>

	<script>
		//         let object = {};
		//         let partstr = sessionStorage.getItem("part");
		//         let part = partstr ? JSON.parse(partstr) : [];

		//         (() => {

		//             const itemId = sessionStorage.getItem('itemId'); /* 在sessionstorage裡抓到key='itemId'的value */
		//             console.log(itemId);
		//             let pjName = getProjectName();
		//             fetch(`${pjName}/item/items?action=getOne_For_DisplayJS&itemId=${itemId}`, { method: 'get' })  /* servlet執行完後將傳回的字串轉成promise */
		//                 .then(resp => resp.json())	/* 將拿到的promise物件轉成js物件 */
		//                 .then(item => { /* 物件本人 */

		//                     $("#product-title").text(item.itemName);
		//                     $("#product-price").html(`<p id="price">${item.itemPrice}</p>`);
		//                     $("#product-content").html(`<p>${item.itemContent}</p>`)
		//                     $("#product-amount").html(`
		//    							<input type="number" step="1" min="1" max="${item.itemAmount}" name="quantity"
// 							value="1" title="Qty" class="input-text qty text"
// 							id="quantity" size="4"
// 							pattern="[0-9]*" inputmode="numeric">
		//    							`)

		//                     object.itemId = sessionStorage.getItem('itemId');

		//                     object.price = item.itemPrice;
		//                     object.name = item.itemName;
		//                     object.content = item.itemContent;
		//                 })

		//         })();

		//         // getItem();
		//         // async function getItem(){
		//         // 	const itemId = sessionStorage.getItem('itemId'); /* 在sessionstorage裡抓到key='itemId'的value */
		//         // 	let res=await fetch(`/BA_REI/item/items?action=getOne_For_DisplayJS&itemId=${itemId}`,{method:'get'})  /* servlet執行完後將傳回的字串轉成promise */
		//         // 		let item=await res.json();
		//         //
		//         //
		//         //
		//         // 				$("#product-title").text(item.itemName);
		//         // 				$("#product-price").append(`<ins id="price">${item.itemPrice}</ins>`);
		//         // 				$("#product-content").append(`<p>${item.itemContent}</p>`)
		//         // 				$("#product-amount").append(`
		//         // 					<input type="number" step="1" min="1" max="${item.itemAmount}" name="quantity"
//         // 					value="1" title="Qty" class="input-text qty text"
//         // 					id="quantity" size="4"
//         // 					pattern="[0-9]*" inputmode="numeric">
		//         // 					`)
		//         //
		//         // 				object.itemId=sessionStorage.getItem('itemId');
		//         //
		//         // 				object.price=item.itemPrice;
		//         // 				object.name=item.itemName;
		//         //
		//         //
		//         // }

		//         getPhoto();
		//         async function getPhoto() {
		//             let item_id = sessionStorage.getItem('itemId');
		//             let pjName = getProjectName();
		// //             let res = await fetch(`${pjName}/item/GetItemPhoto?action=getAllPhoto&item_id=` + item_id, { method: 'get' });
		//             let res = await fetch(`${pjName}/groupBuyItemPicture/groupBuyItemPictureGetAll.do?gbitem_id=` + 1, { method: 'get' });
		//             data = await res.json();
		//             let count = 0;

		//             data.forEach(e => {

		//                 if (count === 0) {
		//                     object.photo = e.photo;

		//                 }
		//                 $(".slides").append(`
		// 							<li><img src="data:image/jpeg;base64,${e.photo}" /></li>
		// 	  					`);
		//                 count++;
		//             })
		//             $(".flexslider").flexslider({
		//                 animation: "slide",
		//                 touch: true
		//             });

		//         }

		//         function addToCart() {
		//             object.quantity = +$("#quantity").val();
		//             part.push(object);
		//             sessionStorage.setItem("part" + '', JSON.stringify(part));

		//             Swal.fire({
		//                 icon: 'success',
		//                 title: '加入成功',
		//                 showConfirmButton: false,
		//                 timer: 1500
		//             })
		//             // sessionStorage.setItem(object.itemId+'',JSON.stringify(object));

		//         }
		//         function addToTrace() {

		//         }

		//         function getProjectName() {
		//             let path = window.location.pathname;
		//             let webCtx = path.substring(0, path.indexOf('/', 1));
		//             return webCtx;
		//         }
	</script>


</body>

</html>