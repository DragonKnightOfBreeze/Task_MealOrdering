<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--CITE 传入：session.onlineUser,req.meal-->
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="notice" type="mealordering.domain.Notice"--%>

<!DOCTYPE html>
<html>
<head>
	<title>公告详情</title>
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
		<!--STEP 搜索栏：普通搜索-->
		<jsp:include page="/mealordering/notice/mo_search-bar.jsp"/>

		<div class="row m-3" id="mo_title">
			<h2 class="m-auto text-info">公告详情</h2>
		</div>

		<!--STEP 公告详情，卡片-->
		<div class="row m-3" id="mo_card">
			<div class="card m-auto p-3">
				<div class="card-body">
					<h3 class="card-title"><c:out value="${notice.title}">公告标题</c:out>
					</h3>
					<p class="card-text"><c:out value="${notice.details}">公告详情。</c:out></p>
					<!--返回按钮-->
					<button class="float-right btn-secondary" onclick="history.go(-1)">返回</button>
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
