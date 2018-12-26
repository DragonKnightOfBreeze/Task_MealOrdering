<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--CITE 传入：session.onlineUser,req.user--%>
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="user" type="mealordering.domain.NormalUser"--%>

<!DOCTYPE html>
<html>
<head>
	<title>管理页面 用户详情</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
</head>

<body>
	<!--STEP 页面顶部-->
	<jsp:include page="/mealordering/admin/mo_header.jsp"/>

	<!--STEP 背景图-->
	<jsp:include page="/mealordering/admin/mo_background-bar.jsp"/>

	<!--STEP 页面主体-->
	<div class="container" id="mo_body-admin">
		<div class="row m-3">
			<!--STEP 侧边菜单栏-->
			<div class="col-sm-3">
				<jsp:include page="/mealordering/admin/mo_side-menu.jsp"/>
			</div>
			<%--切换侧边栏导航激活--%>
			<script>
				$(function() {
					$("#mo_side-menu .nav-link").eq(4).click();
				})
			</script>

			<!--STEP 内容页面-->
			<div class="col-sm-9">
				<div class="container" id="mo_body-content-admin">
					<!--STEP 标题-->
					<div class="row m-3">
						<div class="col-sm-6">
							<h2>用户详情</h2>
						</div>
					</div>
					<!--STEP 内容-->
					<div class="row" id="mo_body-info-admin">
						<!--STEP 显示用户信息的表单，不能更改-->
						<form class="m-auto" action="#" method="post" id="mo_form-edit">
							<!--显示用户id-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_id">id</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_id"
									       value="${user.id}" disabled>
								</div>
							</div>
							<!--显示用户名-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_user-name">用户名</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_user-name"
									       value="${user.userName}" disabled>
								</div>
							</div>
							<!--显示用户性别-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_gender">性别</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_gender"
									       value="${user.gender}" disabled>
								</div>
							</div>
							<!--显示用户邮箱-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_email">邮箱地址</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_email"
									       value="${user.email}" disabled>
								</div>
							</div>
							<!--显示用户手机号码-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_phone-num">手机号码</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_phone-num"
									       value="${user.phoneNum}" disabled>
								</div>
							</div>
							<!--显示用户介绍-->
							<div class="form-row m-1">
								<label for="mo_introduce">介绍</label>
								<textarea class="form-control" id="mo_introduce" disabled>${user.introduce}</textarea>
							</div>
							<!--显示用户类型-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_type">类型</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_type"
									       value="${user.type}" disabled>
								</div>
							</div>
							<!--显示用户激活状态-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_active-state">激活状态</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_active-state"
									       value="${user.activeState == 0?"未激活":"已激活"}" disabled>
								</div>
							</div>
							<!--显示用户注册时间-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_register-time">注册时间</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_register-time"
									       value="<fmt:formatDate value="${user.registerTime}"/>" disabled>
								</div>
							</div>
							<!--显示按钮-->
							<div class="form-row m-1">
								<div class="col-sm-4 offset-2">
									<c:url var="deleteUrl" value="/mealordering/admin/delete-notice">
										<c:param name="id" value="${user.id}"/>
									</c:url>
									<a class="btn btn-danger m-auto" id="mo_btn-delete" href="${deleteUrl}">删除</a>
									<script>
										$(".mo_btn-delete").click(() => confirm("你确定要删除该用户吗？"));
									</script>
								</div>
								<div class="col-sm-4">
									<button class="btn btn-secondary m-auto" onclick="history.go(-1)">返回</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!--STEP 页面尾部-->
		<jsp:include page="/mealordering/admin/mo_footer.jsp"/>


		<%--STEP 引入脚本--%>
		<%--引入jQuery和Bootstrap脚本--%>
		<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
