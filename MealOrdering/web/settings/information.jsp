<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>设置页面</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/css/bootstrap.min.css">

	<script>
		$(function() {
			//DONE 拦截用户注册，验证输入
			//STEP 使用jQuery验证插件
			$("#mo_form-edit").validate({
					submitHandler: function(form) {
						$(form).submit();
					},
					rules: {
						userName: {
							required: true,
							//1到10位的字母、数字、下划线
							pattern: "[a-zA-Z_]\\w{0,9}"
						},
						password: {
							required: true,
							//6到16位的任意非空白符字符
							pattern: ".{6,16}"
						},
						rePassword: {
							required: true,
							equalTo: "#mo_password-edit"
						},
						email: {
							required: true,
							email: true
						},
						phoneNum: {
							required: true,
							rangelength: [11, 11]
						}
					},
					messages: {
						userName: {
							required: "用户名必须填写！",
							pattern: "用户名格式不正确！"
						},
						password: {
							required: "用户密码必须填写！",
							pattern: "用户密码格式不正确！"
						},
						rePassword: {
							required: "请再次确认你的密码！",
							equalTo: "两次输入的密码不一致！"
						},
						email: {
							required: "用户邮箱必须填写！",
							email: "邮箱格式不正确！"
						},
						phoneNum: {
							required: "用户手机号码必须填写！",
							rangelength: "手机号码格式不正确！"
						}
					},
					errorPlacement: function(error, element) {
						error.appendTo($("#mo_msg-edit"));
					},
					errorContainer: "#mo_alert-edit",
					success: "valid"
				}
			);
		});

	</script>
</head>

<body>
	<!--STEP 页面顶部-->
	<jsp:include page="/mealordering/mo_header.jsp"/>

	<!--STEP 背景图-->
	<jsp:include page="/mealordering/mo_background-bar.jsp"/>

	<%--STEP 页面主体--%>
	<div class="container" id="mo_body">
		<div class="row">
			<!--STEP 侧边菜单栏-->
			<div class="col-sm-3" id="mo_side-menu"></div>

			<!--STEP 用户资料页面-->
			<div class="col-sm-9" id="mo_body-content">

				<div class="row mx-3">
					<blockquote><h2>我的资料</h2></blockquote>
				</div>
				<div class="row">
					<form class="w-100 h-100" method="post" action="/mealordering/settings/editInformation" id="mo_form-edit">
						<div class="row">
							<div class="col-sm-7">
								<input type="hidden" id="mo_id-edit" name="id" value="">
								<div class="form-group">
									<label for="mo_user-name-edit">用户名</label>
									<input type="text" class="form-control" id="mo_user-name-edit" name="userName" title="用户名" required>
								</div>
								<div class="form-group">
									<label for="mo_password-edit">用户密码</label>
									<input type="password" class="form-control" id="mo_password-edit" name="password" title="用户密码"
									       required>
								</div>
								<div class="form-group">
									<label for="mo_re-password-edit">确认密码</label>
									<input type="password" class="form-control" id="mo_re-password-edit" name="rePassword" title="确认密码"
									       required>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label">
										<input class="form-check-input mo_gender-edit" type="checkbox" name="gender" value="男性" checked>男性
									</label>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label">
										<input class="form-check-input mo_gender-edit" type="checkbox" name="gender" value="女性">女性
									</label>
								</div>
								<div class="form-group">
									<label for="mo_email-edit">邮箱地址</label>
									<input type="password" class="form-control" id="mo_email-edit" name="email" title="邮箱地址" required>
								</div>
								<div class="form-group">
									<label for="mo_phone-num-edit">手机号码</label>
									<input type="password" class="form-control" id="mo_phone-num-edit" title="手机号码" required>
								</div>
								<div class="form-group">
									<label for="mo_introduce-edit">个人介绍</label>
									<textarea class="form-control" id="mo_introduce-edit" rows="4"
									          name="introduce">这个人很懒，什么也没留下...</textarea>
								</div>
								<!--NOTE 这是验证未通过时弹出的警告-->
								<div class="alert alert-warning" id="mo_alert-edit" style="display: none;">
									<span class="fa fa-warning fa-fw"></span>
									<span id="mo_msg-edit"></span>
									<button class="close" data-dismiss="alert">&times;</button>
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-primary mx-auto">更新资料</button>
								</div>
							</div>

							<div class="col-sm-5">
								<div class="form-group">
									<div class="form-row">
										<label for="mo_img-url-edit">我的头像</label>
									</div>
									<div class="form-row">
										<img src="#" class="rounded w-75 h-75 mx-auto" id="mo_img-edit" title="我的头像">
									</div>
									<div class="form-row">
										<input type="file" class="form-control-file" id="mo_img-url-edit" value="上传头像">
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!--STEP 页面尾部-->
	<jsp:include page="/mealordering/mo_footer.jsp"/>


	<%--STEP 引入脚本--%>
	<%--引入jQuery和Bootstrap脚本--%>
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>