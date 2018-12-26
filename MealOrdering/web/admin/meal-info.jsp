<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--CITE 传入：session.onlineUser,req.meal--%>
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="meal" type="mealordering.domain.Meal"--%>

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
					$("#mo_side-menu .nav-link").removeClass("active").eq(1).addClass("active");
				})
			</script>

			<!--STEP 内容页面-->
			<div class="col-sm-9">
				<div class="container" id="mo_body-content-admin">
					<!--STEP 标题-->
					<div class="row m-3">
						<div class="col-sm-6">
							<h2>餐品详情</h2>
						</div>
					</div>
					<!--STEP 内容-->
					<div class="row" id="mo_body-info-admin">
						<!--STEP 显示和编辑餐品信息的表单-->
						<%--NOTE 包含图片，注意设置enctype--%>
						<form class="m-auto" action="<c:url value="/mealordering/admin/edit-meal"/>" method="post"
						      id="mo_form-edit" enctype="multipart/form-data">
							<!--显示餐品id-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_id">id</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_id"
									       value="${meal.id}" disabled>
									<input type="hidden" name="id" value="${meal.id}"/>
								</div>
							</div>
							<!--显示餐品名字-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_name">名字</label>
								<div class="input-group col-sm-9">
									<input type="text" class="form-control" id="mo_name" name="name"
									       value="${meal.name}" required>
								</div>
							</div>
							<!--显示餐品价格-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_price">价格</label>
								<div class="input-group col-sm-9">
									<input type="number" class="form-control" id="mo_price" name="price"
									       min="0" max="10000" value="${meal.price}" required>
								</div>
							</div>
							<!--显示餐品分类-->
							<div class="form-row m-1">
								<label class="col-md-3" for="mo_category">分类</label>
								<div class="col-md-9">
									<select class="form-control" id="mo_category">
										<option name="category" value="所有分类" ${(meal.category=="所有分类")?"selected":""}>所有分类</option>
										<option name="category" value="素食" ${meal.category=="素食"?"selected":""}>素食</option>
										<option name="category" value="荤食" ${meal.category=="荤食"?"selected":""}>荤食</option>
										<option name="category" value="甜点" ${meal.category=="甜点"?"selected":""}>甜点</option>
										<option name="category" value="黑暗料理" ${meal.category=="黑暗料理"?"selected":""}>黑暗料理</option>
									</select>
								</div>
							</div>
							<!--显示餐品图片-->
							<div class="form-row m-1">
								<div class="col-sm-9 offset-3">
									<img src="${meal.imgUrl}" class="rounded w-75 h-75 m-auto" id="mo_img" title="餐品图片">
								</div>
							</div>
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_imgUrl">选择图片</label>
								<div class="col-sm-9">
									<input type="file" class="form-control-file" id="mo_imgUrl" name="imgUrl"
									       value="${meal.imgUrl}">
								</div>
							</div>
							<%--当选择新的图片式改变显示的图片--%>
							<script>
								$("#mo_imgUrl").change(function() {
									$("#mo_img").attr("src", $(this).val());
								});
							</script>
							<!--显示餐品描述-->
							<div class="form-row m-1">
								<label for="mo_description">描述</label>
								<textarea class="form-control" id="mo_description" rows="4"
								          name="description">${meal.description}</textarea>
							</div>
							<!--显示餐品数量-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_count">数量</label>
								<div class="input-group col-sm-9">
									<input type="number" class="form-control" id="mo_count" name="count"
									       min="0" max="10000" value="${meal.count}" required>
								</div>
							</div>
							<!--显示餐品售出数量-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_soldCount">售出数量</label>
								<div class="input-group col-sm-9">
									<input type="number" class="form-control" id="mo_soldCount" name="count"
									       min="0" max="10000" value="${meal.soldCount}" required>
								</div>
							</div>
							<!--删除、返回以及确认更改按钮-->
							<div class="form-row m-1">
								<div class="col-sm-4">
									<c:url var="deleteUrl" value="/mealordering/admin/delete-meal">
										<c:param name="id" value="${meal.id}"/>
									</c:url>
									<a class="btn btn-danger m-auto mo_btn-delete" href="${deleteUrl}">删除</a>
									<script>
										$(".mo_btn-delete").click(() => confirm("你确定要删除该餐品吗？"));
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

			<!--STEP 页面尾部-->
			<jsp:include page="/mealordering/admin/mo_footer.jsp"/>


			<%--STEP 引入脚本--%>
			<%--引入jQuery和Bootstrap脚本--%>
			<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
			<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>

