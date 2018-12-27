<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--CITE 传入：req.weekHotMeals,req.notice-->
<%--@elvariable id="notice" type="mealordering.domain.Notice"--%>
<%--@elvariable id="weekHotMeals" type="java.util.List<mealordering.domain.WeekHotMeal>"--%>

<!DOCTYPE html>
<html>
<head>
	<title>网上订餐系统首页</title>
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
		<jsp:include page="meal/mo_search-bar.jsp"/>

		<!--STEP 轮播图：固定广告，自动切换-->
		<div class="row m-3" id="mo_ad">
			<div class="carousel slide m-auto w-75" id="mo_carousel" data-ride="carousel">
				<!--指示符-->
				<ol class="carousel-indicators">
					<li data-target="#mo_carousel" data-slide-to="0" class="active"></li>
					<li data-target="#mo_carousel" data-slide-to="1"></li>
					<li data-target="#mo_carousel" data-slide-to="2"></li>
				</ol>
				<!--轮播图片-->
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<img src="assets/image/meal_img/food10.jpg"/>
						<div class="carousel-caption">
							<p>Lorem ipsumdolor sit amet</p>
						</div>
					</div>
					<div class="carousel-item">
						<img src="assets/image/meal_img/food11.jpg">
						<div class="carousel-caption">
							<p>Lorem ipsumdolor sit amet</p>
						</div>
					</div>
					<div class="carousel-item">
						<img src="assets/image/meal_img/food12.jpg">
						<div class="carousel-caption">
							<p>Lorem ipsumdolor sit amet</p>
						</div>
					</div>
				</div>
				<!--左右切换按钮-->
				<a class="carousel-control-prev" href="#mo_carousel" data-slide="prev">
					<span class="carousel-control-prev-icon"></span>
				</a>
				<a class="carousel-control-next" href="#mo_carousel" data-slide="next">
					<span class="carousel-control-next-icon"></span>
				</a>
			</div>
		</div>

		<!--STEP 推荐内容：包括最近的公告、本周推荐-->
		<div class="row m-3" id="mo_recommend">
			<!--STEP 最近的公告-->
			<div class="col-md-6 border" id="mo_latest-notice">
				<div class="container">
					<div class="row m-3">
						<h2>最近公告
							<a class="float-right" href="<c:url value="/mealordering/notice/find-all"/>">
								<span class="fa fa-plus"></span>
							</a>
						</h2>
					</div>
					<hr>
					<div class="row  m-3">
						<article>
							<h3>
								<c:out value="${notice.title}">公告标题</c:out>
								<small><fmt:formatDate value="${notice.time}"/></small>
							</h3>
							<p><c:out value="${notice.details}">公告内容。</c:out></p>
						</article>
					</div>
				</div>
			</div>

			<!--STEP 本周推荐-->
			<div class="col-md-6  border" id="mo_week-hot-meals">
				<div class="container">
					<div class="row m-3">
						<h2>本周推荐
							<a class="float-right" href="<c:url value="/mealordering/meal/find-all"/>">
								<span class="fa fa-plus"></span>
							</a>
						</h2>
					</div>
					<hr>
					<div class="row m-3">
						<div class="media border p-3 w-100 mo_media-week-hot">
							<img src="${weekHotMeals[0].imgUrl}" class="mr-3 mt-3 img-thumbnail" style="width:60px;">
							<div class="media-body">
								<h3><c:out value="${weekHotMeals[0].name}">餐品名称</c:out>
									<small>销售总量：${weekHotMeals[0].salesCount}</small>
								</h3>
								<p><c:out value="${weekHotMeals[0].description}">餐品描述。</c:out></p>
							</div>
						</div>
					</div>
					<div class="row m-3">
						<div class="media border p-3 w-100 mo_media-week-hot">
							<img src="${weekHotMeals[1].imgUrl}" class="mr-3 mt-3 img-thumbnail" style="width:60px;">
							<div class="media-body">
								<h3><c:out value="${weekHotMeals[1].name}">餐品名称</c:out>
									<small>销售总量：${weekHotMeals[1].salesCount}</small>
								</h3>
								<p><c:out value="${weekHotMeals[1].description}">餐品描述。</c:out></p>
							</div>
						</div>
					</div>
				</div>
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
</body>
</html>
