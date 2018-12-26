<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>激活失败</title>
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

	<%--STEP 页面主体--%>
	<div class="container" id="mo_fail-info">
		<div class="row m-3">
			<h2 class="m-auto  text-warning">激活失败！</h2>
		</div>
		<hr>
		<div class="row m-3">
			<a class="btn m-auto btn-link text-warning" href="<c:url value="/mealordering/index.jsp"/>">
				<span id="mo_second">5</span>秒后自动为您跳转到首页。
			</a>
		</div>
	</div>

	<!--STEP 页面尾部-->
	<jsp:include page="/mealordering/mo_footer.jsp"/>


	<%--STEP 引入脚本--%>
	<%--引入jQuery和Bootstrap脚本--%>
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<%--引入自定义脚本--%>
	<script src="<c:url value="/mealordering/assets/js/countDown.js"/>"></script>

</body>
</html>

