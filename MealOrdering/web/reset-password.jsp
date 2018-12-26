<%--INFO 搁置--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>重置密码</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<!--NOTE 不需要重复引入jQuery验证插件-->
	<script src="<c:url value="/mealordering/assets/js/reset-password.js"/>"></script>
	<script>

		$(function() {
			$("#mo_header").load("/mealordering/mo_header.html");
			$("#mo_background-bar").load("/mealordering/mo_background-bar.html");
			$("#mo_footer").load("/mealordering/mo_footer.html");

			let $navLinks = $("#mo_np-reset-psw .nav-link");
			let $tabPanes = $("#mo_tc-reset-psw").children();

			//DONE 第一步：发送邮件
			//STEP 使用jQuery验证插件
			$("#mo_form-find-psw").validate({
				submitHandler: function(form) {
					//激活导航栏和分页面板，进入到下一步，而非提交表单
					$navLinks.removeClass("active").eq(1).addClass("active");
					$tabPanes.removeClass("active").eq(1).addClass("active");
				},
				rules: {
					email: {
						required: true,
						email: true
					},
				},
				messages: {
					email: {
						required: "请填写你的邮箱地址！",
						email: "邮箱地址格式不正确！"
					}
				},
				errorPlacement: function(error, element) {
					error.appendTo($("#mo_msg-find-psw"));
				},
				errorContainer: "#mo_alert-find-psw",
				success: "valid"
			});

			//第二步：修改密码
			//STEP 使用jQuery验证插件
			$("#mo_form-change-psw").validate({
				submitHandler: function(form) {
					//激活导航栏和分页面板，进入到下一步，而非提交表单
					$navLinks.removeClass("active").eq(2).addClass("active");
					$tabPanes.removeClass("active").eq(2).addClass("active");
				},
				rules: {
					password: {
						required: true,
						//6到16位的任意非空白符字符
						pattern: ".{6,16}",
					},
					rePassword: {
						required: true,
						equalTo: "mo_password-change-psw"
					}
				},
				messages: {
					password: {
						required: "请输入新密码！",
						pattern: "密码格式不正确！"
					},
					rePassword: {
						required: "请再次确认你的密码！",
						equalTo: "两次输入的密码格式不一致！"
					}
				},
				errorPlacement: function(error, element) {
					error.appendTo($("#_msg-find-psw"));
				},
				errorContainer: "#mo_alert-find-psw",
				success: "valid"
			});


		});


	</script>
</head>

<body>
	<div id="mo_header"></div>

	<div id="mo_background-bar"></div>

	<div class="container" id="mo_body">
		<!--STEP 步骤导航-->
		<ul class="nav nav-pills" id="mo_np-reset-psw">
			<li class="nav-item">
				<a class="nav-link active disabled" href="#">发送重置密码邮件</a>
			</li>
			<li class="nav-item">
				<a class="nav-link disabled" href="#">填写新密码</a>
			</li>
			<li class="nav-item">
				<a class="nav-link disabled" href="#">重置密码成功</a>
			</li>
		</ul>
		<div class="tab-content" id="mo_tc-reset-psw">
			<!--STEP 分页：发送重置密码邮件-->
			<div class="tab-pane container active" id="mo_tp-find-psw">
				<form action="#" method="post" id="mo_form-find-psw">
					<!--这是验证未通过时弹出的警告-->
					<div class="alert alert-warning" id="mo_alert-find-psw" style="display: none">
						<span class="fa fa-warning fa-fw"></span><span id="mo_msg-find-psw"></span>
						<button class="close" data-dismiss="alert">&times;</button>
					</div>
					<!--这是表单项-->
					<div class="form-group">
						<label for="mo_email-find-psw">填写邮箱：</label>
						<input type="email" class="form-control" id="mo_email-find-psw" name="email"
						       placeholder="请填写你的邮箱" required autofocus>
					</div>
					<input type="hidden" name="step" value="step1">
					<div class="form-row">
						<input type="submit" class="form-control btn btn-primary mx-auto" value="获取重置密码邮件">
					</div>
				</form>
			</div>
			<!--STEP 分页：填写新密码-->
			<div class="tab-pane container" id="mo_tp-change-psw">
				<form action="#" method="post" id="mo_form-change-psw">
					<!--这是验证未通过时弹出的警告-->
					<div class="alert alert-warning" id="mo_alert-change-psw" style="display: none">
						<span class="fa fa-warning fa-fw"></span><span id="mo_msg-change-psw"></span>
						<button class="close" data-dismiss="alert">&times;</button>
					</div>
					<!--这是表单项-->
					<div class="form-group">
						<label for="mo_password-change-psw">填写新的密码：</label>
						<input type="text" class="form-control" id="mo_password-change-psw" name="password"
						       placeholder="请填写你的新密码" required autofocus>
						<input type="text" class="form-control" id="mo_re-password-change-psw" name="rePassword"
						       placeholder="再次确认密码" required>
					</div>
					<input type="hidden" name="step" value="step2">
					<div class="form-row">
						<input type="submit" class="btn btn-primary mx-auto" value="更新密码">
					</div>
				</form>
			</div>
			<!--STEP 分页：重置密码成功-->
			<div class="tab-pane container" id="mo_success info">
				<div class="row m-3 text-center text-success">
					<h2 class="m-auto">重置密码成功！</h2>
				</div>
				<hr>
				<div class="row m-3 text-center text-success">
					<a class="btn btn-link  m-auto " href="<c:url value="/mealordering/index.jsp"/>">
						<span id="mo_second">5</span>秒后自动为您跳转到首页。
					</a>
				</div>
			</div>
		</div>
	</div>

	<div id="mo_footer"></div>
</body>
</html>
