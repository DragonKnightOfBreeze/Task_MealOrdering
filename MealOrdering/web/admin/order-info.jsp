<!DOCTYPE html>
<html>
<head>
  <title>管理页 订单详情</title>
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
		let url = "/mealordering/admin/findOrderById";
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
				$("#mo_side-menu .nav-link").removeClass("active").eq("3").addClass("active");
			});

			//通过Ajax得到数据，接着插入相应的位置
			$.post(url, data, function(data) {
				generate(data);
			});

			/**
			 * 生成页面。
			 * @param {string} data.status
			 * @param {object} data.order
			 */
			function generate(data) {
				if(data.status === "empty") {
					let $content = $("#mo_body-content-admin");
					$content.empty();
					$content.append(`
				<div class="row m-3 text-center text-success">
					<h4>订单数据为空</h4>
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
					$("#mo_id").val(data.order.id);
					$("#mo_total-price").val(data.order.totalPrice);
					$("#mo_receiver-address").val(data.order.receiverAddress);
					$("#mo_receiver-name").val(data.order.receiverName);
					$("#mo_receiver-phone").val(data.order.receiverPhone);
					$("#mo_pay-state").val(data.order.payState === 0 ? "未支付" : "已支付");
					let date = utils.formatDate(data.order.orderTime.toString());
					$("#mo_order-time").val(date);
					$("#mo_user-id").val(data.order.user.id);
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
	    <div class="col-sm-3" id="mo_side-menu"></div>

	    <!--STEP 内容页面-->
      <div class="col-sm-9">
	      <div class="container" id="mo_body-content-admin">
		      <!--STEP 标题-->
          <div class="row m-3">
            <div class="col-sm-6">
              <h2>订单详情</h2>
            </div>
          </div>
		      <!--STEP 表格-->
		      <div class="row" id="mo_body-info-admin">
			      <!--STEP 显示订单信息的表单，不能更改-->
			      <form class="m-auto" action="#" method="post" id="mo_form-edit">
              <!--订单id-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_id">id</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control disabled" id="mo_id">
                </div>
              </div>
              <!--订单总价-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_total-price">总价</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control disabled" id="mo_total-price">
                </div>
              </div>
              <!--收货人地址-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_receiver-address">收货人地址</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control disabled" id="mo_receiver-address">
                </div>
              </div>
              <!--收货人姓名-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_receiver-name">收货人姓名</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control disabled" id="mo_receiver-name">
                </div>
              </div>
              <!--收货人电话-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_receiver-phone">收货人电话</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control disabled" id="mo_receiver-phone">
                </div>
              </div>
              <!--支付状态-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_pay-state">支付状态</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control disabled" id="mo_pay-state">
                </div>
              </div>
              <!--下单时间-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_order-time">下单时间</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control disabled" id="mo_order-time">
                </div>
              </div>
              <!--用户id-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_user-id">用户id</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control disabled" id="mo_user-id">
                </div>
              </div>

              <!--按钮-->
              <div class="form-row m-1">
                <div class="col-sm-4 offset-4">
                  <button class="btn btn-secondary m-auto" onclick="history.go(-1)">返回</button>
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

