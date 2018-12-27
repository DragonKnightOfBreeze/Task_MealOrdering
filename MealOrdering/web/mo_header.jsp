<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--CITE 传入：session.onlineUser--%>
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>

<!--STEP 导航栏：网站图标，导航文本，导航栏菜单，登录注册导航表单-->
<!--固定在页面顶部，小屏幕上水平导航栏会切换为垂直的-->
<div id="mo_header">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container">
			<!--STEP 导航栏头部-->
			<div class="navbar-header">
				<!--导航栏网站LOGO-->
				<a class="navbar-brand" href="<c:url value="/mealordering/index"/>">
					<img src="<c:url value="/mealordering/assets/image/logo/logo.jpg"/>"/>
				</a>
			</div>
			<!--导航栏文本-->
			<span class="navbar-text">Text goes here.</span>
			&emsp;&emsp;&emsp;
			<!--导航栏下拉菜单，只在小地图上显示-->
			<button class="navbar-toggler" data-toggle="collapse" data-target="#mo_navbar-menu">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!--可折叠式导航栏菜单-->
			<div class="collapse navbar-collapse" id="mo_navbar-menu">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link active" href="<c:url value="/mealordering/index"/>">主页</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value="/mealordering/meal/find-all"/>">菜单</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value="/mealordering/notice/find-all"/>">公告</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#mo_modal-login-admin" data-toggle="modal">管理</a>
					</li>
				</ul>
			</div>

			<%--判断是否有用户登录，显示不同的菜单--%>
			<c:choose>
			<c:when test="${empty onlineUser}">
			<!--STEP 导航栏菜单：注册和登录，右对齐-->
			<ul class="navbar-nav navbar-right" id="mo_visitor-menu">
				<li class="nav-item active">
					<a class="nav-link" id="mo_nav-login" href="#mo_modal-login-reg" data-toggle="modal">
						<span class="fa fa-sign-in"></span> 登录
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="mo_nav-reg" href="#mo_modal-login-reg" data-toggle="modal">
						<span class="fa fa-sign-out"></span> 注册
					</a>
				</li>
			</ul>
			</c:when>
			<c:otherwise>
			<!--STEP 导航栏菜单：我的账户和登出，右对齐-->
			<ul class="navbar-nav navbar-right" id="mo_user-menu">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" data-toggle="dropdown">
						<span class="fa fa-user"></span>${onlineUser.userName}
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="<c:url value="/mealordering/account/home.jsp"/>">我的资料</a>
						<a class="dropdown-item" href="<c:url value="/mealordering/account/my-cart.jsp"/>">我的购物车</a>
						<a class="dropdown-item" href="<c:url value="/mealordering/account/find-order-by-user"/>">我的订单</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="<c:url value="/mealordering/settings/information.jsp"/>">修改资料</a>
					</div>
				</li>
				<li class="nav-item">
					<a id="mo_btn-logout" class="nav-link" href="<c:url value="/mealordering/logout"/>">
						<span class="fa fa-sign-out"></span> 注销
					</a>
				</li>
			</ul>
			</c:otherwise>
			</c:choose>
	</nav>
</div>

<!--STEP 模态框：管理员登录 -->
<!--被模态框标题引用，默认隐藏-->
<div class="modal fade in" id="mo_modal-login-admin">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!--模态框的标题-->
				<h4 class="modal-title text-center" id="mo_mt-login-admin">管理员登录</h4>
				<!--模态框的关闭按钮-->
				<button class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<!--STEP 表单：管理员登录-->
				<%--CITE POST 传出：userName,password--%>
				<form action="<c:url value="/mealordering/login-admin"/>" method="post" id="mo_form-login-admin">
					<!--表单输入-->
					<%--输入用户名--%>
					<div class="form-row m-1">
						<label class="col-sm-3" for="mo_userName-login-admin">用户名</label>
						<div class="col-sm-9 input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><span class="fa fa-user fa-fw"></span></span>
							</div>
							<input type="text" class="form-control" id="mo_userName-login-admin" name="userName"
							       placeholder="请输入名字" required>
						</div>
					</div>
					<%--输入密码--%>
					<div class="form-row m-1">
						<label class="col-sm-3" for="mo_password-login-admin">密码</label>
						<div class="col-sm-9 input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><span class="fa fa-key fa-fw"></span></span>
							</div>
							<input type="password" class="form-control" id="mo_password-login-admin" name="password"
							       placeholder="请输入密码" required>
						</div>
					</div>
					<%--提交表单--%>
					<div class="form-row m-1">
						<button type="submit" class="btn btn-primary m-auto">登录</button>
					</div>
					<!--验证未通过时弹出的警告-->
					<%--TODO jQuery验证插件使用时仍然有问题--%>
					<div class="alert alert-warning" id="mo_alert-login-admin" style="display: none">
						<span class="fa fa-warning fa-fw"></span><span id="mo_msg-login-admin"></span>
						<button class="close" data-dismiss="alert">&times;</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!--STEP 模态框：普通用户登录与注册 -->
<!--被模态框标题引用，默认隐藏-->
<div class="modal fade in" id="mo_modal-login-reg">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title text-center" id="mo_mt-login-reg">登录 & 注册</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<!--STEP 导航：登录与注册-->
				<ul class="nav nav-pills nav-fill">
					<li class="nav-item">
						<a class="nav-link active" id="mo_nl-login" href="#mo_tp-login" data-toggle="tab">登录</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" id="mo_nl-reg" href="#mo_tp-reg" data-toggle="tab">注册</a>
					</li>
				</ul>

				<div class="tab-content">
					<div class="tab-pane container active" id="mo_tp-login">
						<!--STEP 表单：登录-->
						<%--CITE POST 传出：userName,password,rememberLogin:string--%>
						<form action="<c:url value="/mealordering/login"/>" method="post" id="mo_form-login">
							<!--表单输入-->
							<%--输入用户名--%>
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_userName-login">用户名</label>
								<div class="col-sm-9 input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><span class="fa fa-user fa-fw"></span></span>
									</div>
									<input type="text" class="form-control" id="mo_userName-login" name="userName"
									       placeholder="请输入用户名" required>
								</div>
							</div>
							<%--输入密码--%>
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_password-login">密码</label>
								<div class="col-sm-9 input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><span class="fa fa-key"></span></span>
									</div>
									<input type="password" class="form-control" id="mo_password-login" name="password"
									       placeholder="请输入密码" required>
								</div>
							</div>
							<%--记住登录状态/忘了密码--%>
							<div class="form-row m-1">
								<label class="form-check-label col-sm-6">
									<input class="form-check-input" type="radio" name="rememberLogin" value="true">记住登录状态
								</label>
								<div class="col-sm-6">
									<a class="btn btn-link float-right" href="<c:url value="/mealordering/resetPassword"/>">我忘记了密码</a>
								</div>
							</div>
							<%--提交表单--%>
							<div class="form-row m-1">
								<button type="submit" class="btn btn-primary m-auto">登录</button>
							</div>
							<!--验证未通过时弹出的警告-->
							<div class="alert alert-warning" id="mo_alert-login" style="display: none">
								<span class="fa fa-warning fa-fw"></span><span id="mo_msg-login"></span>
								<button class="close" data-dismiss="alert">&times;</button>
							</div>
						</form>
					</div>

					<div class="tab-pane container" id="mo_tp-reg">
						<!--STEP 表单：注册-->
						<%--CITE POST MP 传出：userName,password,rePassword,imgUrl,gender,email,phoneNum,description--%>
						<form action="<c:url value="/mealordering/register"/>" method="post" id="mo_form-reg"
						      enctype="multipart/form-data">
							<!--表单输入-->
							<%--输入用户名--%>
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_userName-reg">用户名</label>
								<div class="col-sm-9 input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><span class="fa fa-user fa-fw"></span></span>
									</div>
									<input type="text" class="form-control" id="mo_userName-reg" name="userName"
									       placeholder="请输入用户名" required>
								</div>
							</div>
							<%--输入用户密码--%>
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_password-reg">密码</label>
								<div class="col-sm-9 input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><span class="fa fa-key fa-fw"></span></span>
									</div>
									<input type="password" class="form-control" id="mo_password-reg" name="password"
									       placeholder="请输入密码" required>
								</div>
							</div>
							<%--确认用户密码--%>
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_rePassword-reg">确认密码</label>
								<div class="col-sm-9 input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><span class="fa fa-key fa-fw"></span></span>
									</div>
									<input type="password" class="form-control" id="mo_rePassword-reg" name="rePassword"
									       placeholder="请再次确认密码" required>
								</div>
							</div>
							<%--选择头像--%>
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_imgUrl-reg">选择头像</label>
								<div class="col-sm-9">
									<input type="file" class="form-control-file" id="mo_imgUrl-reg" name="imgUrl"
									       placeholder="选择">
								</div>
							</div>
							<%--选择用户性别--%>
							<div class="form-row m-1">
								<div class="form-check-inline">
									<label class="form-check-label">
										<input class="form-check-input" type="radio" name="gender" value="男性" checked>男性
									</label>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label">
										<input class="form-check-input" type="radio" name="gender" value="女性">女性
									</label>
								</div>
							</div>
							<%--输入邮箱地址--%>
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_email-reg">邮箱地址</label>
								<div class="col-sm-9 input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><span class="fa fa-envelope fa-fw"></span></span>
									</div>
									<input type="email" class="form-control" id="mo_email-reg" name="email"
									       placeholder="请输入你的邮箱地址" required>
								</div>
							</div>
							<%--输入手机号码--%>
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_phonNum-reg">手机号码</label>
								<div class="col-sm-9 input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><span class="fa fa-phone fa-fw"></span></span>
									</div>
									<input type="tel" class="form-control" id="mo_phonNum-reg" name="phoneNum"
									       placeholder="请输入你的手机号码" required>
								</div>
							</div>
							<%--输入个人介绍--%>
							<div class="form-row m-1">
								<label for="mo_introduce-reg">个人介绍</label>
								<textarea class="form-control" id="mo_introduce-reg" rows="4"
								          name="introduce">这个人很懒，什么也没留下...</textarea>
							</div>
							<%--是否同意条款--%>
							<div class="form-row m-1">
								<div class="form-check-inline">
									<label class="form-check-label">
										<input class="form-check-input" id="mo_agree-reg" type="radio" checked>我已阅读并同意XXX条款
									</label>
								</div>
							</div>
							<%--提交表单--%>
							<div class="form-row m-1">
								<button type="submit" class="btn btn-primary m-auto">注册</button>
							</div>
							<!--验证未通过时弹出的警告-->
							<div class="alert alert-warning" id="mo_alert-reg" style="display: none;">
								<span class="fa fa-warning fa-fw"></span>
								<span id="mo_msg-reg"></span>
								<button class="close" data-dismiss="alert">&times;</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%--STEP 点击登录/注册按钮时，切换显示登录/注册分页--%>
<script>
	$("#mo_nav-login").click(function() {
		$("#mo_nl-login").click();
	});
	$("#mo_nav-reg").click(function() {
		$("#mo_nl-reg").click();
	});
</script>






