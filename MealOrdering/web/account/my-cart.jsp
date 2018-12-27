<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--CITE 传入：session.onlineUser-->
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="cart" type="java.util.Map<mealordering.domain.Meal,java.lang.Integer>"--%>

<!DOCTYPE html>
<html>
<head>
	<title>我的购物车</title>
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
		<div class="row m-3">
			<!--STEP 侧边菜单栏-->
			<div class="col-sm-4">
				<jsp:include page="/mealordering/account/mo_side-page.jsp"/>
			</div>

			<!--STEP 用户主页-->
			<div class="col-sm-8">
				<div class="container" id="mo_body-content">
					<div class="row m-3">
						<h2 class="btn btn-info disabled">吃货感悟</h2>
					</div>
					<hr>
					<div class="row m-3">
						<c:forEach var="entry" items="${cart}">
						<div class="media border m-1">
							<c:url var="infoUrl" value="/mealordering/meal/find">
								<c:param name="id" value="${entry.key.id}"/>
							</c:url>
							<a href="${infoUrl}">
								<img class="rounded-circle m-3 align-self-lg-end" src="<c:out value="${entry.key.imgUrl}">#</c:out>">
							</a>
							<div class="media-body">
								<div class="container">
									<div class="row">
										<h3 class="media-heading"><a class="btn btn-link" href="${infoUrl}">${entry.key.name}</a>
											<small>
												<span class="badge badge-primary">分类：<c:out value="${entry.key.category}">所有分类</c:out></span>
												<span class="badge badge-secondary">库存：<c:out value="${entry.key.count}">0</c:out></span>
											</small>
										</h3>
									</div>
									<div class="row">
										<form class="form-inline" action="<c:url value="/mealordering/account/edit-cart"/>" method="post">
											<!--餐品id-->
											<input type="hidden" name="id" value="${entry.key.id}"/>
											<!--餐品数量-->
											<div class="form-group">购买数量
												<input type="number" class="form-control mo_count" name="count"
												       value="${entry.value}" min="0" max="10000" required>
												<!--餐品总计价格-->
												<input type="hidden" value="${entry.key.price}" disabled/>
											</div>
											<div class="form-group">总计
												<input type="number" class="form-control mo_totalCount"
												       value="${entry.value}" disabled>
											</div>
											<!--提交表单-->
											<button type="submit" class="btn btn-default">更新数量</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
					</c:forEach>
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
			//实时更新总价
			$(function() {
				$(".mo_count").change(function() {
					$(this).parent().parent().find(".mo_totalCount").val(parseInt($(this).val()) + parseInt($(this).nextSibling().val()));
				});
			});
		</script>
</body>
</html>
