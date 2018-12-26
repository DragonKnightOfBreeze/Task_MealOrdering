<!DOCTYPE html>
<html>
<head>
	<title>管理页 订单列表</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>

	<script>
		import * as utils from "/mealordering/assets/js/utils";
		import * as global from "/mealordering/assets/js/global";

		let curPageIndex = 1;
		//尝试得到get参数
		let data = utils.getParamMap();
		let url = "/mealordering/admin/findAllOrders";

		$(function() {
			$("#mo_header").load("/mealordering/admin/mo_header.html");
			$("#mo_background-bar").load("/mealordering/admin/mo_background-bar.html");
			$("#mo_footer").load("/mealordering/admin/mo_footer.html");
			$("#mo_side-menu").load("/mealordering/admin/mo_side-menu.jsp", function() {
				//切换侧边栏激活
				$("#mo_side-menu .nav-link").removeClass("active").eq("3").addClass("active");
			});

			//通过Ajax得到数据，接着插入相应的位置
			$.post(url, data, function(data) {
				generate(data);
			});

			/**
			 * 生成页面
			 * @property {string} data.status
			 * @property {object[]} data.page
			 * @property {string[]} data.pageBtnText
			 * @property {int} data.pageIndex
			 * @property {int} data.pageCount
			 */
			function generate(data) {
				//NOTE 如果没有数据，则显示提示
				if(data.status === "empty") {
					let $content = $("#mo_body-content-admin");
					$content.empty();
					$content.append(`
				<div class="row m-3 text-center text-success">
					<h4>餐品数据为空</h4>
				</div>
				<hr>
				<div class="row m-3 text-center text-success">
					<p>看看其他内容吧......</p>
				</div>
            `);
				}
				//NOTE 如果发生错误，则跳转到错误页
				else if(data.status === "error") {
					location.href = "../error/unexpected-error.jsp";
				}
				//NOTE 如果一切正常，则插入数据
				else {
					//更改当前索引
					curPageIndex = data.pageIndex;

					//插入数据
					let $tbody = $("#mo_body-table-admin tbody");
					$tbody.empty();
					for(const order of data.page) {
						let state = order.paystate === 0 ? "未支付" : "已支付";
						let date = utils.formatDate(order.orderTime);
						$tbody.append(`
								<tr class="mo_col-meal">
									<td>${order.id}</td>
									<td>${order.totalPrice}</td>
									<td>${order.receiverAddress}</td>
									<td>${order.receiverName}</td>
									<td>${order.receiverPhone}</td>
									<td>${state}</td>
									<td>${date}</td>
									<td>${order.user.id}</td>
									<td>
										<button class="btn btn-info mo_btn-detail">详情</button>
									</td>
								</tr>
							`);
					}

					//为详情按钮配置链接
					let $btnDetail = $(".mo_btn-detail");
					$btnDetail.off("click");
					$btnDetail.on("click", function() {
						//得到参数（餐品id）
						let id = $(this).parent().parent().find(":first-child").text();
						//跳转到餐品详情页
						location.href = `order-info.jsp`;
					});


					//插完数据后就生成分页栏
					let $pagination = $("#mo_page-bar-admin .pagination");
					$pagination.empty();
					global.generatePageBar($pagination, data.pageBtnText, data.pageIndex, data.pageCount);

					//为分页栏“上一页”按钮配置链接
					let $prePage = $("#mo_pre-page");
					$prePage.off("click");
					$prePage.on("click", function() {
						$.post("/mealordering/getPage", {pageIndex: curPageIndex - 1}, function(data) {
								generate(data);
							}
						);
					});
					//为分页栏“页数”按钮配置链接
					let $changePage = $(".mo_change-page");
					$changePage.off("click");
					$changePage.on("click", function() {
						$.post("/mealordering/getPage", {pageIndex: $(this).text()}, function(data) {
								generate(data);
							}
						);
					});
					//为分页栏“下一页”按钮配置链接
					let $nextPage = $("#mo_next-page");
					$nextPage.off("click");
					$nextPage.on("click", function() {
						$.post("/mealordering/getPage", {pageIndex: curPageIndex + 1}, function(data) {
								generate(data);
							}
						);
					});

				}
			}
		});
	</script>
</head>

<body>
	<!--STEP 页面顶部-->
	<div id="mo_header"></div>

	<!--STEP 背景图-->
	<div class="container-fluid" id="mo_background-bar"></div>

	<!--STEP 页面主体-->
	<div class="container" id="mo_body-admin">
		<div class="row">
			<!--STEP 侧边菜单栏-->
			<div class="col-sm-3">
				<div class="container" id="mo_side-menu"></div>
			</div>

			<!--STEP 内容页面-->
			<div class="col-sm-9">
				<div class="container" id="mo_body-content-admin">
					<!--STEP 标题-->
					<div class="row m-3 ">
						<div class="col-sm-6">
							<h2>公告列表</h2>
						</div>
					</div>
					<hr>
					<!--STEP 表格-->
					<div class="row" id="mo_body-table-admin">
						<table class="table table-bordered table-striped table-hover table-responsive m-auto">
							<thead class="thead-dark text-center">
							<tr>
								<th>id</th>
								<th>总价</th>
								<th>收货人地址</th>
								<th>收货人姓名</th>
								<th>收货人号码</th>
								<th>支付状态</th>
								<th>下单时间</th>
								<th>用户id</th>
								<th>操作</th>
							</tr>
							</thead>
							<!--NOTE 动态生成-->
							<tbody class="text-center"></tbody>
						</table>
					</div>

					<!--STEP 分页栏-->
					<div class="row" id="mo_page-bar-admin">
						<!--NOTE 动态生成-->
						<ul class="pagination"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--STEP 页面尾部-->
	<div class="container" id="mo_footer"></div>
</body>
</html>

