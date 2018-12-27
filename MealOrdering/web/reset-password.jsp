<%--TODO--%>

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
</head>

<body>
	<!--STEP 页面顶部-->
	<jsp:include page="/mealordering/mo_header.jsp"/>

	<!--STEP 背景图-->
	<jsp:include page="/mealordering/mo_background-bar.jsp"/>

	<!--STEP 页面主体-->
	<div class="container" id="mo_body">
		<div class="row  m-3" id="mo_body-title">
			<h2 class="m-auto text-success">重置密码</h2>
		</div>
		<hr>
		<div class="row m-3" id="mo_body-content">
			<!--STEP 步骤导航-->
			<ul class="nav nav-pills" id="mo_np-reset-psw">
				<li class="nav-item">
					<a class="nav-link active disabled">发送重置密码邮件</a>
				</li>
				<li class="nav-item">
					<a class="nav-link disabled">填写新密码</a>
				</li>
				<li class="nav-item">
					<a class="nav-link disabled">重置密码成功</a>
				</li>
			</ul>
			<div class="tab-content" id="mo_tc-reset-psw">
				<!--STEP 分页：发送重置密码邮件-->
				<div class="tab-pane container active" id="mo_tp-find-psw">
					<form class="m-auto" id="mo_form-find-psw">
						<!--验证未通过时弹出的警告-->
						<div class="alert alert-warning" id="mo_alert-find-psw" style="display: none"></div>
						<!--输入邮箱-->
						<div class="form-group">
							<label for="mo_email-find-psw">填写邮箱：</label>
							<input type="email" class="form-control" id="mo_email-find-psw" name="email"
							       placeholder="请填写你的邮箱" required autofocus>
						</div>
						<div class="form-row">
							<input type="submit" class="form-control btn btn-primary mx-auto" value="获取重置密码邮件">
						</div>
					</form>
				</div>
				<!--STEP 分页：填写新密码-->
				<div class="tab-pane container" id="mo_tp-change-psw">
					<form class="m-auto" id="mo_form-change-psw">
						<!--验证未通过时弹出的警告-->
						<div class="alert alert-warning" id="mo_alert-change-psw" style="display: none"></div>
						<!--输入密码-->
						<div class="form-group">
							<label for="mo_password-change-psw">填写新的密码：</label>
							<input type="text" class="form-control" id="mo_password-change-psw" name="password"
							       placeholder="请填写你的新密码" required autofocus>
						</div>
						<div class="form-group">
							<label for="mo_password-change-psw">再次确认密码：</label>
							<input type="text" class="form-control" id="mo_rePassword-change-psw" name="rePassword"
							       placeholder="请再次确认密码" required>
						</div>
						<div class="form-row">
							<input type="submit" class="btn btn-primary m-auto" value="更新密码">
						</div>
					</form>
				</div>
				<!--STEP 分页：重置密码成功-->
				<div class="tab-pane container" id="mo_tp-success">
					<div class="row m-3">
						<h2 class="m-auto text-success">重置密码成功！</h2>
					</div>
					<hr>
					<div class="row m-3">
						<a class="btn btn-link m-auto " href="<c:url value="/mealordering/index.jsp"/>">
							点击此处返回首页。
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--STEP 页面尾部-->
	<jsp:include page="/mealordering/mo_footer.jsp"/>


	<!--STEP 引入脚本-->
	<!--引入jQuery和Bootstrap脚本-->
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
	<!--引入jQuery验证插件，包括本地化和附加方法-->
	<script src="<c:url value="/mealordering/framework/jquery-validation/jquery.validate.min.js"/>"></script>
	<script src="<c:url value="/mealordering/framework/jquery-validation/localization/messages_zh.min.js"/>"></script>
	<script src="<c:url value="/mealordering/framework/jquery-validation/additional-methods.min.js"/>"></script>
	<script>
		$(function() {
			const warn = '<span class="fa fa-warning fa-fw"></span>';
			//STEP 验证邮箱输入
			let $formFindPsw = $("#mo_form-find-psw");
			let $alertFindPsw = $("#mo_alert-find-psw");
			$formFindPsw.validate({
				submitHandler: () => $formFindPsw.submit(),
				rules: {
					email: {required: true, email: true}
				},
				messages: {
					email: {required: "邮箱地址不能为空！", email: "邮箱地址格式不正确！"}
				},
				errorPlacement: function(error, element) {
					$alertFindPsw.html(warn).append(error);
				},
				errorContainer: $alertFindPsw
			});

			//STEP 验证密码输入
			let $formChangePsw = $("#mo_form-change-psw");
			let $alertChangePsw = $("#mo_alert-change-psw");
			$formChangePsw.validate({
				submitHandler: () => $formChangePsw.submit(),
				rules: {
					password: {required: true, pattern: ".{6,16}"},
					rePassword: {required: true, equalTo: "#mo_rePassword-change-psw"}
				},
				messages: {
					password: {required: "密码不能为空！", pattern: "密码格式不正确！"},
					rePassword: {required: "确认密码不能为空！", equalTo: "两次输入的密码不一致！"}
				},
				errorPlacement: function(error, element) {
					$alertChangePsw.html(warn).append(error);
				},
				errorContainer: $alertChangePsw
			});
		});
	</script>

</body>
</html>
