<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--CITE 传入：session.onlineUser,req.meal--%>
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="meal" type="mealordering.domain.Meal"--%>

<!DOCTYPE html>
<html>
<head>
	<title>餐品详情</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/css/bootstrap.min.css">

	<%--STEP 引入脚本--%>
	<%--引入jQuery和Bootstrap脚本--%>
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>

<body>
	<!--STEP 页面顶部-->
	<jsp:include page="/mealordering/mo_header.jsp"/>

	<!--STEP 背景图-->
	<jsp:include page="/mealordering/mo_background-bar.jsp"/>

	<!--STEP 页面主体-->
	<div class="container" id="mo_body">
		<!--STEP 搜索栏：具有普通搜索、分类搜索、高级搜索等功能-->
		<jsp:include page="/mealordering/meal/mo_search-bar.jsp"/>

		<div class="row m-3" id="mo_title">
			<h2 class="m-auto text-info">餐品详情</h2>
		</div>

		<!--STEP 餐品详情，卡片-->
		<div class="row m-3" id="mo_card">
			<div class="card m-auto p-3">
				<img class="card-img-top" src="<c:out value="${meal.imgUrl}">#</c:out>" alt="餐品图片">
				<div class="card-body">
					<h3 class="card-title"><c:out value="${meal.name}">餐品名称</c:out>
						<small>
							<span class="badge badge-primary">分类：<c:out value="${meal.category}">所有分类</c:out></span>
							<span class="badge badge-secondary">库存：<c:out value="${meal.count}">0</c:out></span>
							<span class="badge badge-secondary">已售出：<c:out value="${meal.soldCount}">0</c:out></span>
						</small>
					</h3>
					<p class="card-text"><c:out value="${meal.description}">餐品描述。</c:out></p>
					<%--返回、购买按钮--%>
					<c:url var="buyUrl" value="/mealordering/meal/add-to-cart">
						<c:param name="id" value="i${meal.id}"/>
					</c:url>
					<a class="btn btn-primary" href="${buyUrl}">购买</a>
					<button class="btn btn-secondary float-right" onclick="history.go(-1)">返回</button>
				</div>
			</div>
		</div>
	</div>

	<!--STEP 页面尾部-->
	<jsp:include page="/mealordering/mo_footer.jsp"/>
</body>
</html>
