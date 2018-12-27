<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--CITE 传入：session.onlineUser-->
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="pageGroup" type="mealordering.domain.PageGroup"--%>
<%--@elvariable id="page" type="java.util.List<mealordering.domain.Order>"--%>
<%--@elvariable id="pageBtnText" type="java.lang.String[]"--%>

<!DOCTYPE html>
<html>
<head>
	<title>管理页面 订单列表</title>
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
			<!--STEP 用户订单列表-->
			<div class="col-sm-8">
				<div class="container" id="mo_body-content">
					<div class="row m-3">
						<h2 class="btn btn-info disabled">吃货感悟</h2>
					</div>
					<hr>
					<!--STEP 表格-->
					<div class="row m-3" id="mo_body-table">
						<table class="table table-bordered table-striped table-hover table-responsive m-auto">
							<thead class="thead-dark text-center">
							<tr>
								<th>id</th>
								<th>总价</th>
								<th>收货人地址</th>
								<th>收货人姓名</th>
								<th>收货人号码</th>
								<th>支付状态</th>
								<th>下单时间</th>
								<th>用户id</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody class="text-center">
							<!--STEP 动态插入数据-->
							<c:forEach var="order" items="${page}">
								<tr>
									<td>${order.id}</td>
									<td>${order.totalPrice}</td>
									<td>${order.receiverAddress}</td>
									<td>${order.receiverName}</td>
									<td>${order.receiverPhone}</td>
									<td>${order.payState == 0?"未支付":"已支付"}</td>
									<td><fmt:formatDate value="${order.orderTime}"/></td>
									<td>${order.user.id}</td>
									<td>
										<c:url var="detailUrl" value="/mealordering/account/find-order">
											<c:param name="id" value="${order.id}"/>
										</c:url>
										<a class="btn btn-info mo_btn-detail" href="${detailUrl}">详情</a>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
					<!--STEP 分页导航-->
					<div class="row m-3" id="mo_page-bar-admin">
						<ul class="pagination  m-auto">
							<!--NOTE 动态生成分页导航-->
							<c:forEach var="text" items="${pageBtnText}">
								<li class="page-item">
									<c:url var="pageUrl" value="/mealordering/admin/get-page">
										<c:param name="item" value="order"/>
										<c:param name="pageIndex" value="${text}"/>
									</c:url>
									<a class="page-link mo_change-page" href="${pageUrl}">${text}</a>
								</li>
							</c:forEach>
						</ul>
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
</body>
</html>

