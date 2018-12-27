<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--CITE 传入：session.onlineUser-->
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="order" type="mealordering.domain.Order"--%>

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
			<div class="col-sm-3">
				<!--STEP 侧边菜单栏-->
				<jsp:include page="/mealordering/admin/mo_side-menu.jsp"/>
			</div>

			<!--STEP 内容页面-->
			<div class="col-sm-9">
				<div class="container" id="mo_body-page-admin">
					<!--STEP 标题-->
					<div class="row m-3" id="mo_body-title-admin">
						<div class="col-sm-6">
							<h2>订单详情</h2>
						</div>
					</div>
					<hr>
					<!--STEP 内容-->
					<div class="row m-3" id="mo_body-content-admin">
						<!--STEP 显示订单信息的表单，不能更改-->
						<form class="m-auto" id="mo_form-info" action="#" method="post">
							<!--显示订单id-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_id">id</label>
								<div class="col-sm-9 input-group">
									<input type="text" class="form-control" id="mo_id"
									       value="${order.id}" disabled>
								</div>
							</div>
							<!--显示订单总价-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_totalPrice">总价</label>
								<div class="col-sm-9 input-group">
									<input type="text" class="form-control" id="mo_totalPrice"
									       value="${order.totalPrice}" disabled>
								</div>
							</div>
							<!--显示收货人地址-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_receiverAddress">收货人地址</label>
								<div class="col-sm-9 input-group">
									<input type="text" class="form-control" id="mo_receiverAddress"
									       value="${order.receiverAddress}" disabled>>
								</div>
							</div>
							<!--显示收货人姓名-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_receiverName">收货人姓名</label>
								<div class="col-sm-9 input-group">
									<input type="text" class="form-control" id="mo_receiverName"
									       value="${order.receiverName}" disabled>
								</div>
							</div>
							<!--显示收货人电话-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_receiverPhone">收货人电话</label>
								<div class="col-sm-9 input-group">
									<input type="number" class="form-control" id="mo_receiverPhone"
									       value="${order.receiverPhone}" disabled>
								</div>
							</div>
							<!--显示支付状态-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_pay-state">支付状态</label>
								<div class="col-sm-9 input-group">
									<input type="text" class="form-control" id="mo_pay-state"
									       value="${order.payState == 0?"未支付":"已支付"}" disabled>
								</div>
							</div>
							<!--显示下单时间-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_order-time">下单时间</label>
								<div class="col-sm-9 input-group">
									<input type="text" class="form-control disabled" id="mo_order-time"
									       value="<fmt:formatDate value="${order.orderTime}"/>" disabled>
								</div>
							</div>
							<!--显示用户id-->
							<div class="form-row m-1">
								<label class="col-sm-3" for="mo_user-id">用户id</label>
								<div class="col-sm-9 input-group">
									<input type="text" class="form-control disabled" id="mo_user-id"
									       value="<fmt:formatDate value="${order.user.id}"/>" disabled>
								</div>
							</div>
							<!--返回按钮-->
							<div class="form-row m-1">
								<div class="col-sm-4 offset-2">
									<button class="btn btn-secondary m-auto" onclick="history.go(-1)">返回</button>
								</div>
								<div class="col-sm-4">
									<c:url var="deleteUrl" value="/mealordering/admin/delete-order">
										<c:param name="id" value="${order.id}"/>
									</c:url>
									<a class="btn btn-danger mo_btn-delete m-auto" href="${deleteUrl}">删除</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--STEP 页面尾部-->
	<jsp:include page="/mealordering/admin/mo_footer.jsp"/>

	<!--STEP 引入脚本-->
	<!--引入jQuery和Bootstrap脚本-->
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<script>
		$(function() {
			//STEP 切换侧边栏导航显示
			$("#mo_side-menu .nav-link").eq(3).addClass("active");
			//STEP 删除前确认
			$(".mo_btn-delete").click(function() {
				return confirm("你确定要删除该订单吗？");
			});
		});
	</script>
</body>
</html>
