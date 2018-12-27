<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--CITE 传入：session.onlineUser-->
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="pageGroup" type="mealordering.domain.PageGroup"--%>
<%--@elvariable id="page" type="java.util.List<mealordering.domain.Meal>"--%>
<%--@elvariable id="pageBtnText" type="java.lang.String[]"--%>
<%--@elvariable id="categoryOrd" type="int"--%>

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
		<input type="hidden" id="mo_categoryOrd" value="${categoryOrd}"/>

		<div class="row m-3" id="mo_title">
			<h2 class="m-auto text-info">餐品列表</h2>
		</div>

		<!--STEP 餐品列表，媒体对象-->
		<div class="row m-3" id="mo_media-list">
			<div class="container m-auto">
				<c:forEach var="meal" items="${page}">
					<div class="row m-2">
						<div class="media border w-75 m-auto">
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
								<!--购买按钮-->
								<c:url var="buyUrl" value="/mealordering/meal/add-to-cart">
									<c:param name="id" value="i${meal.id}"/>
								</c:url>
								<a class="btn btn-primary" href="${buyUrl}">购买</a>
								<!--详情按钮-->
								<c:url var="buyUrl" value="/mealordering/meal/find">
									<c:param name="id" value="i${meal.id}"/>
								</c:url>
								<a class="btn btn-info" href="${buyUrl}">详情</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<!--STEP 分页导航-->
	<div class="row m-3" id="mo_page-bar">
		<!--STEP 动态生成分页导航-->
		<ul class="pagination  m-auto">
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
		$(function() {
			//STEP 切换分页导航显示
			//TODO
			try {
				const categoryOrd = parseInt($("#mo_categoryOrd").val());
				$("#mo_nt-category .nav-link").eq(categoryOrd).addClass("active");
			} catch(e) {
			}
		});
	</script>
</body>
</html>
