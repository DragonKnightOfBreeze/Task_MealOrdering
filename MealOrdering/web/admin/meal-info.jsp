<!DOCTYPE html>
<html>
<head>
	<title>管理页 餐品详情</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>


	<script>
		import * as utils from "/mealordering/assets/js/utils";

		//尝试得到get参数
		let data = utils.getParamMap();
		let url = "/mealordering/admin/findMealById";
		//如果没有正确的get参数，则设置为0
		if(data["id"] == null) {
			data["id"] = 0;
		}

		$(function() {
			$("#mo_header").load("/mealordering/admin/mo_header.html");
			$("#mo_background-bar").load("/mealordering/admin/mo_background-bar.html");
			$("#mo_footer").load("/mealordering/admin/mo_footer.html");
			$("#mo_side-menu").load("/mealordering/admin/mo_side-menu.jsp", function() {
				//切换侧边栏激活
				$("#mo_side-menu .nav-link").removeClass("active").eq("1").addClass("active");
			});

			//通过Ajax得到数据，接着插入相应的位置
			$.post(url, data, function(data) {
				generate(data);
			});

			/**
			 * @property {string} data.status
			 * @property {object} data.meal
			 */
			function generate(data) {
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
					//插入数据
					$("#mo_meal-id").val(data.meal.id);
					$("#mo_meal-name").val(data.meal.name);
					$("#mo_meal-price").val(data.meal.price);
					$("#mo_meal-category option").attr("selected", false)
						.filter(`[value='${data.meal.category}']`).attr("selected", true);
					$("#mo_meal-img").attr("src", data.meal.imgUrl);
					$("#mo_meal-img-url").val(data.meal.imgUrl);
					$("#mo_meal-description").text(data.meal.description);
					$("#mo_meal-count").val(data.meal.count);
				}

				//NOTE 为删除按钮配置链接
				let $btnDelete = $(".mo_btn-delete");
				$btnDelete.off("click");
				$btnDelete.on("click", function() {
					//首先进行确认
					if(!confirm("你确定要删除该餐品吗？"))
						return;
					//得到参数（餐品id）
					let id = data.meal.id;
					//发送请求，根据结果弹出提示
					$.post("/mealordering/admin/deleteMeal", {id: id},
						/**@property data.status*/
						function(data) {
							if(data.status === "error") {
								alert("删除餐品失败，未知错误。");
							} else {
								alert("删除餐品成功。");
							}
							location.href = "meal-list.jsp";
						}
					);
				});
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
			<div class="col-sm-3" id="mo_side-menu"></div>

			<!--STEP 内容页面-->
			<div class="col-sm-9">
				<div class="container" id="mo_body-content-admin">
					<!--STEP 标题-->
					<div class="row m-3">
						<div class="col-sm-6">
							<h2>餐品详情</h2>
						</div>
					</div>
					<!--STEP 表格-->
					<div class="row" id="mo_body-info-admin">
						<!--STEP 显示和编辑餐品信息的表单-->
						<!--NOTE 不使用Ajax，提交到Servlet-->
						<form class="m-auto" action="/mealordering/admin/editMeal" method="post" id="mo_form-edit"
						      enctype="multipart/form-data">
							<!--餐品id-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_meal-id">id</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control disabled" id="mo_meal-id" name="id" required>
								</div>
							</div>
							<!--餐品名字-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_meal-name">名字</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_meal-name" name="name" required>
								</div>
							</div>
							<!--餐品价格-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_meal-price">名字</label>
								<div class="input-group col-sm-9">
									<input type="number" class="form-control" id="mo_meal-price" name="price"
									       min="0" max="10000" required>
								</div>
							</div>
							<!--餐品分类-->
							<div class="form-row m-1">
								<label class="col-md-3" for="mo_meal-category">分类</label>
								<div class="col-md-9">
									<select class="form-control" id="mo_meal-category">
										<option name="category" value="所有分类" selected>所有分类</option>
										<option name="category" value="素食">素食</option>
										<option name="category" value="荤食">荤食</option>
										<option name="category" value="甜点">甜点</option>
										<option name="category" value="黑暗料理">黑暗料理</option>
									</select>
								</div>
							</div>
							<!--餐品图片-->
							<div class="form-row m-1">
								<div class="col-sm-9 offset-3">
									<img src="#" class="rounded w-75 h-75 m-auto" id="mo_img" title="餐品图片">
								</div>
							</div>
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_img-url">选择图片</label>
								<div class="col-sm-9">
									<input type="file" class="form-control-file" id="mo_img-url" name="imgUrl" required>
								</div>
							</div>
							<!--餐品描述-->
							<div class="form-row m-1">
								<label for="mo_meal-description">描述</label>
								<textarea class="form-control" id="mo_meal-description" rows="4" name="description">
请输入餐品介绍......
                  </textarea>
							</div>
							<!--餐品数量-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_meal-count">数量</label>
								<div class="input-group col-sm-9">
									<input type="number" class="form-control" id="mo_meal-count" name="count" value="0"
									       min="0" max="10000" required>
								</div>
							</div>
							<!--删除按钮和确定更改按钮-->
							<div class="form-row m-1">
								<div class="col-sm-4">
									<button class="btn btn-danger m-auto" id="mo_btn-delete">删除</button>
								</div>
								<div class="col-sm-4">
									<button class="btn btn-secondary m-auto" onclick="history.go(-1)">返回</button>
								</div>
								<div class="col-sm-4">
									<button type="submit" class="btn btn-primary m-auto">确认更改</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--STEP 页面尾部-->
	<div class="container" id="mo_footer"></div>
</body>
</html>

