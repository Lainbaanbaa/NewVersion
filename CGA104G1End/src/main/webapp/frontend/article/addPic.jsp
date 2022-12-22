<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>討論區上傳大頭貼</title>
    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.5/croppie.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.5/croppie.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../resources/static/css/main.css"/>
    <style>
        .none {
            height: 80px;
        }

        .flex-container {
            width: 100%;
            display: inline-flex;
            flex-direction: row;
            padding: 10px;
        }

        #dropdownMenuLink {
            display: none;
        }

        #oldImg {
            width: 45%;
        }

        #flow {
            width: 10%;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 50px;
            font-weight: 900;
            color: orange;
        }

        #newImg,
        .but {
            width: 45%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .form1 {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px;
        }

        #logo {
            width: 100px;
            height: 40px;
        }
    </style>
</head>

<body>
<!--     <nav class="navbar navbar-expand-lg bg-light fixed-top"> -->
<!-- 		<div class="container-fluid"> -->
<!-- 			<a class="navbar-brand" href="select_page.jsp"><img id="logo" src="img/logo.png"></a> -->
<!-- 			<div class="collapse navbar-collapse" id="navbarSupportedContent"> -->
<!-- 				<ul class="navbar-nav me-auto mb-2 mb-lg-0"> -->
<!-- 				</ul> -->
<!-- 				<form class="d-flex" role="search"> -->
<!-- 					<input class="form-control me-2" type="search" -->
<!-- 						placeholder="請輸入文章編號"> -->
<!-- 					<button class="btn btn-outline-info text-nowrap" type="submit">查詢</button> -->
<!-- 				</form> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</nav> -->
<!--     <div class="none"></div> -->
<div class="flex-container">
    <div class="but"><label class="btn btn-info"><input id="upload_img" style="display:none;" type="file"
                                                        accept="image/*"><i class="fa fa-photo"></i> 上傳圖片</label>
    </div>
    <div id="flow"></div>
    <div class="but">
        <button id="crop_img" class="btn btn-info"><i class="fa fa-scissors"></i> 裁剪成大頭貼</button>
    </div>
</div>
<div></div>

<div class="flex-container">
    <div id="oldImg"></div>
    <div id="flow">→</div>
    <div id="newImg"></div>
</div>
<div id="newImgInfo"></div>
<form method="post" class="form1" action="/CGA104G1/Article_identityServlet">
    <input type="hidden" name="action" value="insert">
    <input type="hidden" name="mem_id" value="${memVO.mem_id}">
    <textarea class="picHtml" name="article_pic" style="display: none;"></textarea>
    <button type="submit" class="btn btn-success">確認送出</button>
</form>

<script>
    (function ($) {
        var width_crop = 100, // 圖片裁切寬度 px 值
            height_crop = 100, // 圖片裁切高度 px 值
            type_crop = "circle", // 裁切形狀: square 為方形, circle 為圓形
            width_preview = 200, // 預覽區塊寬度 px 值
            height_preview = 200, // 預覽區塊高度 px 值
            compress_ratio = 0.85, // 圖片壓縮比例 0~1
            type_img = "png", // 圖檔格式 jpeg png webp
            oldImg = new Image(),
            myCrop, file, oldImgDataUrl;

        // 裁切初始參數設定
        myCrop = $("#oldImg").croppie({
            viewport: { // 裁切區塊
                width: width_crop,
                height: height_crop,
                type: type_crop
            },
            boundary: { // 預覽區塊
                width: width_preview,
                height: height_preview
            }
        });

        function readFile(input) {
            if (input.files && input.files[0]) {
                file = input.files[0];
            } else {
                alert("瀏覽器不支援此功能！建議使用最新版本 Chrome");
                return;
            }

            if (file.type.indexOf("image") == 0) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    oldImgDataUrl = e.target.result;
                    oldImg.src = oldImgDataUrl; // 載入 oldImg 取得圖片資訊
                    myCrop.croppie("bind", {
                        url: oldImgDataUrl
                    });
                };

                reader.readAsDataURL(file);
            } else {
                alert("您上傳的不是圖檔！");
            }
        }

        function displayCropImg(src) {
            var html = "<img class='upPic' src='" + src + "' />";
            $("#newImg").html(html);
            $(".picHtml").html(html);
        }

        $("#upload_img").on("change", function () {
            $("#oldImg").show();
            readFile(this);
        });

        oldImg.onload = function () {
            var width = this.width,
                height = this.height,
                fileSize = Math.round(file.size / 1000),
                html = "";
            $("#oldImg").before(html);
        };

        function displayNewImgInfo(src) {
            var html = "",
                filesize = src.length * 0.75;

            // html += "<p>裁切圖片尺寸 " + width_crop + "x" + height_crop + "</p>";
            // html += "<p>檔案大小約 " + Math.round(filesize / 1000) + "k</p>";
            $("#newImgInfo").html(html);
        }

        $("#crop_img").on("click", function () {
            myCrop.croppie("result", {
                type: "canvas",
                format: type_img,
                quality: compress_ratio
            }).then(function (src) {
                displayCropImg(src);
                displayNewImgInfo(src);
            });
        });
    })(jQuery);
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
