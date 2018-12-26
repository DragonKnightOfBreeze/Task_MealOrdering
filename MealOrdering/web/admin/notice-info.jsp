<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--CITE 传入：session.onlineUser--%>
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="notice" type="mealordering.domain.Notice"--%>

<!DOCTYPE html>
<html>
<head>
	<title>管理页面 公告详情</title>
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
					$("#mo_side-menu .nav-link").removeClass("active").eq(2).addClass("active");
				})
			</script>

			<!--STEP 内容页面-->
			<div class="col-sm-9">
				<div class="container" id="mo_body-content-admin">
					<!--STEP 标题-->
					<div class="row m-3">
						<div class="col-sm-6">
							<h2>公告详情</h2>
						</div>
					</div>
					<!--STEP 信息-->
					<div class="row m-3" id="mo_body-info-admin">
						<!--STEP 显示和编辑公告信息的表单-->
						<form class="m-auto" action="<c:url value="/mealordering/admin/edit-notice"/>" method="post"
						      id="mo_form-edit">
							<!--显示公告id-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_id">id</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control disabled" id="mo_id"
									       value="${notice.id}" disabled>
									<input type="hidden" name="id" value="${notice.id}"/>
								</div>
							</div>
							<!--显示和输入公告标题-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_title">标题</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_title"
									       name="title" value="${notice.title}" required>
								</div>
							</div>
							<!--显示和输入公告详情-->
							<div class="form-row m-1">
								<label for="mo_details">详情</label>
								<textarea class="form-control" id="mo_details" rows="4"
								          name="details">${notice.details}</textarea>
							</div>
							<!--删除、返回以及确认更改按钮-->
							<div class="form-row m-1">
								<div class="col-sm-4">
									<c:url var="deleteUrl" value="/mealordering/admin/delete-notice">
										<c:param name="id" value="${notice.id}"/>
									</c:url>
									<a class="btn btn-danger m-auto mo_btn-delete" href="${deleteUrl}">删除</a>
									<script>
										$(".mo_btn-delete").click(() => confirm("你确定要删除该公告吗？"));
									</script>
								</div>
								<div class="col-sm-4">
									<button class="btn btn-secondary m-auto" onclick="history.go(-1)">返回</button>
								</div>
								<div class="col-sm-4">
									<button type="submit" class="btn btn-primary m-auto" id="mo_btn-submit">更新</button>
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
