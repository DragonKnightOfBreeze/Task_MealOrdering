<!DOCTYPE html>
<html>
<head>
	<title>管理页 公告详情</title>
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
		let url = "/mealordering/admin/findNoticeById";
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
				$("#mo_side-menu .nav-link").removeClass("active").eq("2").addClass("active");
			});

			//通过Ajax得到数据，接着插入相应的位置
			$.post(url, data, function(data) {
				generate(data);
			});

			/**
			 * 生成页面。
			 * @property {string} data.status
			 * @property {object} data.notice
			 */
			function generate(data) {
				if(data.status === "empty") {
					let $content = $("#mo_body-content-admin");
					$content.empty();
					$content.append(`
				<div class="row m-3 text-center text-success">
					<h4>公告数据为空</h4>
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
					$("#mo_notice-id").val(data.notice.id);
					$("#mo_notice-title").val(data.notice.title);
					$("#mo_notice-details").text(data.notice.details);
				}

				//NOTE 为删除按钮配置链接
				let $btnDelete = $("#mo_btn-delete");
				$btnDelete.off("click");
				$btnDelete.on("click", function() {
					//首先进行确认
					if(!confirm("你确定要删除该公告吗？"))
						return;
					//发送请求，根据结果弹出提示
					$.post("/mealordering/admin/deleteNotice",
						{id: data.notice.id},
						/**@property data.status*/
						function(data) {
							if(data.status === "error") {
								alert("删除公告失败，未知错误。");
							} else {
								alert("删除公告成功。");
							}
							location.href = "notice-list.jsp";
						}
					);
				});

				//NOTE 为确认编辑按钮配置链接
				let $btnSummit = $("#mo_btn-submit");
				$btnSummit.off("click");
				$btnSummit.on("click", function() {
					//发送请求，根据结果弹出提示
					$.post("/mealordering/admin/editNotice",
						{id: data.notice.id},
						/**@property data.status*/
						function(data) {
							if(data.status === "error") {
								alert("编辑公告失败，未知错误。");
							} else {
								alert("编辑公告成功。");
							}
							location.href = "notice-list.jsp";
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
							<h2>公告详情</h2>
						</div>
					</div>
					<!--STEP 表格-->
					<div class="row" id="mo_body-info-admin">
						<!--STEP 显示和编辑公告信息的表单-->
						<form class="m-auto" action="#" method="post" id="mo_form-edit">
							<!--公告id-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_notice-id">id</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control disabled" id="mo_notice-id" name="id" required>
								</div>
							</div>
							<!--公告标题-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_notice-title">标题</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_notice-title"
									       name="title" value="公告标题" required>
								</div>
							</div>
							<!--公告详情-->
							<div class="form-row m-1">
								<label for="mo_notice-details">详情</label>
								<textarea class="form-control" id="mo_notice-details" rows="4" name="details">
Text goes here.
                  </textarea>
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
									<button class="btn btn-primary m-auto" id="mo_btn-submit">确认更改</button>
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

