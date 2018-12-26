<!DOCTYPE html>
<html>
<head>
  <title>管理页 用户资料</title>
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
		let url = "/mealordering/admin/findUserById";
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
				$("#mo_side-menu .nav-link").removeClass("active").eq("4").addClass("active");
			});

			//通过Ajax得到数据，接着插入相应的位置
			$.post(url, data, function(data) {
				generate(data);
			});

			/**
			 * 生成页面。
			 * @param {string} data.status
			 * @param {object} data.settings
			 */
			function generate(data) {
				if(data.status === "empty") {
					let $content = $("#mo_body-content-admin");
					$content.empty();
					$content.append(`
				<div class="row m-3 text-center text-success">
					<h4>用户数据为空</h4>
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
					$("#mo_id").val(data.user.id);
					$("#mo_user-name").val(data.user.userName);
					$("#mo_password").val(data.user.password);
					$("#mo_gender").val(data.user.gender);
					$("#mo_email").val(data.user.email);
					$("#mo_phone-num").val(data.user.phoneNum);
					$("#mo_introduce").text(data.user.introduce);
					$("#mo_type").val(data.user.type);
					$("#mo_active-state").val(data.user.activeState === 0 ? "未激活" : "已激活");
					let date = utils.formatDate(data.user.registerTime.toString());
					$("#mo_register-time").val(date);
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
              <h2>用户详情</h2>
            </div>
          </div>
		      <!--STEP 表格-->
		      <div class="row" id="mo_body-info-admin">
			      <!--STEP 显示用户信息的表单，不能更改-->
			      <form class="m-auto" action="#" method="post" id="mo_form-edit">
              <!--用户id-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_id">id</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control disabled" id="mo_id">
                </div>
              </div>
              <!--用户名-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_user-name">用户名</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control" id="mo_user-name">
                </div>
              </div>
              <!--用户性别-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_gender">性别</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control" id="mo_gender">
                </div>
              </div>
              <!--用户邮箱-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_email">邮箱地址</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control" id="mo_email">
                </div>
              </div>
              <!--用户手机号码-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_phone-num">手机号码</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control" id="mo_phone-num">
                </div>
              </div>
              <!--用户介绍-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_introduce">介绍</label>
                <div class="input-group col-sm-9">
                  <textarea class="form-control" id="mo_introduce">
这家伙很懒，什么也没留下......
                  </textarea>
                </div>
              </div>
              <!--用户类型-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_type">类型</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control" id="mo_type">
                </div>
              </div>
              <!--用户激活状态-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_active-state">激活状态</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control" id="mo_active-state">
                </div>
              </div>
              <!--用户注册时间-->
              <div class="form-row m-1">
	              <label class="col-sm-3" for="mo_register-time">注册时间</label>
                <div class="input-group col-sm-9">
	                <input type="text" class="form-control" id="mo_register-time">
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


