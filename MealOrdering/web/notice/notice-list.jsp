<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--CITE 传入：session.onlineUser--%>
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="pageGroup" type="mealordering.domain.PageGroup"--%>
<%--@elvariable id="page" type="java.util.List<mealordering.domain.Notice>"--%>
<%--@elvariable id="pageBtnText" type="java.lang.String[]"--%>

<!DOCTYPE html>
<html>
<head>
	<title>公告列表</title>
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
		<!--STEP 搜索栏：普通搜索-->
		<jsp:include page="/mealordering/notice/mo_search-bar.jsp"/>

		<div class="row m-3" id="mo_title">
			<h2 class="m-auto text-info">公告列表</h2>
		</div>

		<!--STEP 公告列表，媒体对象-->
		<div class="row m-3 w-100" id="mo_media-list">
			<c:forEach var="notice" items="${page}">
				<div class="row m-auto">
					<div class="media border w-75 m-auto">
						<c:url var="infoUrl" value="/mealordering/notice/find">
							<c:param name="id" value="${notice.id}"/>
						</c:url>
						<div class="media-body">
							<h3 class="media-heading"><a class="btn btn-link" href="${infoUrl}">${notice.title}</a></h3>
							<p>${notice.details}</p>
								<%--详情按钮--%>
							<c:url var="buyUrl" value="/mealordering/meal/find">
								<c:param name="id" value="i${notice.id}"/>
							</c:url>
							<a class="btn btn-info" href="${buyUrl}">详情</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<!--STEP 分页导航-->
		<div class="row m-3" id="mo_page-bar">
			<!--STEP 动态生成分页导航-->
			<ul class="pagination  m-auto">
				<c:forEach var="text" items="${pageBtnText}">
					<li class="page-item">
						<c:url var="pageUrl" value="/mealordering/get-page">
							<c:param name="item" value="notice"/>
							<c:param name="pageIndex" value="${text}"/>
						</c:url>
						<a class="page-link mo_change-page" href="${pageUrl}"> ${text} </a>
					</li>
				</c:forEach>
			</ul>
		</div>

		<!--STEP 页面尾部-->
		<jsp:include page="/mealordering/admin/mo_footer.jsp"/>
</body>
</html>
