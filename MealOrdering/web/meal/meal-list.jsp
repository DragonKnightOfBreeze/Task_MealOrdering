<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--CITE 传入：session.onlineUser--%>
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="pageGroup" type="mealordering.domain.PageGroup"--%>
<%--@elvariable id="page" type="java.util.List<mealordering.domain.Meal>"--%>
<%--@elvariable id="pageBtnText" type="java.lang.String[]"--%>

<!DOCTYPE html>
<html>
<head>
	<title>餐品列表</title>
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
		<!--STEP 搜索栏：具有普通搜索、分类搜索、高级搜索等功能-->
		<jsp:include page="/mealordering/meal/mo_search-bar.jsp"/>

		<div class="row m-3" id="mo_title">
			<h2 class="m-auto text-info">餐品列表</h2>
		</div>

		<!--STEP 餐品列表，媒体对象-->
		<div class="row m-3 w-100" id="mo_media-list">
			<c:forEach var="meal" items="${page}">
				<div class="media border m-1">
					<c:url var="infoUrl" value="/mealordering/meal/find">
						<c:param name="id" value="${meal.id}"/>
					</c:url>
					<a href="${infoUrl}">
						<img class="rounded-circle m-3 align-self-lg-end" src="<c:out value="${meal.imgUrl}">#</c:out>">
					</a>
					<div class="media-body">
						<h3 class="media-heading"><a class="btn btn-link" href="${infoUrl}">${meal.name}</a>
							<small>
								<span class="badge badge-primary">分类：<c:out value="${meal.category}">所有分类</c:out></span>
								<span class="badge badge-secondary">库存：<c:out value="${meal.count}">0</c:out></span>
								<span class="badge badge-secondary">已售出：<c:out value="${meal.soldCount}">0</c:out></span>
							</small>
						</h3>
						<p>${meal.description}</p>
						<em class="text-info">￥ <c:out value="${meal.price}">0</c:out></em>
							<%--购买按钮--%>
						<c:url var="buyUrl" value="/mealordering/account/add-to-cart">
							<c:param name="id" value="i${meal.id}"/>
						</c:url>
						<a class="btn btn-primary" href="${buyUrl}">购买</a>
					</div>
				</div>
			</c:forEach>
		</div>

		<!--STEP 分页栏-->
		<div class="row m-3" id="mo_page-bar">
			<!--STEP 动态生成分页栏-->
			<ul class="pagination">
				<c:forEach var="text" items="${pageBtnText}">
					<li class="page-item">
						<c:url var="pageUrl" value="/mealordering/get-page">
							<c:param name="item" value="meal"/>
							<c:param name="pageIndex" value="${text}"/>
						</c:url>
						<a class="page-link mo_change-page" href="${pageUrl}"> ${text} </a>
					</li>
				</c:forEach>
			</ul>
		</div>

		<!--STEP 页面尾部-->
		<jsp:include page="/mealordering/mo_footer.jsp"/>


		<%--STEP 引入脚本--%>
		<%--引入jQuery和Bootstrap脚本--%>
		<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
