<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--CITE 传入：session.onlineUser--%>
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="pageGroup" type="mealordering.domain.PageGroup"--%>
<%--@elvariable id="page" type="java.util.List<mealordering.domain.NormalUser>"--%>
<%--@elvariable id="pageBtnText" type="java.lang.String[]"--%>

<!DOCTYPE html>
<html>
<head>
	<title>管理页面 用户列表</title>
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
			<!--STEP 侧边菜单栏-->
			<div class="col-sm-3">
				<jsp:include page="/mealordering/admin/mo_side-menu.jsp"/>
			</div>
			<%--切换侧边栏导航激活--%>
			<script>
				$(function() {
					$("#mo_side-menu .nav-link").removeClass("active").eq(4).addClass("active");
				})
			</script>

			<!--STEP 内容页面-->
			<div class="col-sm-9">
				<div class="container" id="mo_body-content-admin">
					<!--STEP 标题-->
					<div class="row m-3 ">
						<div class="col-sm-6">
							<h2>用户列表</h2>
						</div>
						<div class="col-sm-5" id="mo_search-by-name">
							<%--STEP 内联搜索表单--%>
							<form class="form-inline" action="<c:url value="/mealordering/admin/search-user"/>" method="get">
								<%--搜索类型--%>
								<input type="hidden" name="searchType" value="byUserName">
								<%--输入用户名--%>
								<div class="form-group">
									<label class="sr-only" for="mo_by-name">名称</label>
									<input type="text" class="form-control" id="mo_by-name" name="name" placeholder="按名字搜索" required>
								</div>
								<%--提交表单--%>
								<button type="submit" class="btn btn-default">
									<span class="fa fa-search"></span>
								</button>
							</form>
						</div>
					</div>
					<hr>
					<!--STEP 表格-->
					<div class="row" id="mo_body-table-admin">
						<table class="table table-bordered table-striped table-hover table-responsive m-auto">
							<thead class="thead-dark text-center">
							<tr>
								<th>id</th>
								<th>用户名</th>
								<th>性别</th>
								<th>邮箱</th>
								<th>手机号码</th>
								<th>类型</th>
								<th>激活状态</th>
								<th>注册时间</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody class="text-center">
							<%--STEP 动态插入数据--%>
							<c:forEach var="user" items="${page}">
								<tr>
									<td>${user.id}</td>
									<td>${user.userName}</td>
									<td>${user.gender}</td>
									<td>${user.email}</td>
									<td>${user.phoneNum}</td>
									<td>${user.type}</td>
									<td>${user.activeState == 0?"未激活":"已激活"}</td>
									<td><fmt:formatDate value="${user.registerTime}"/></td>
									<td>
										<c:url var="deleteUrl" value="/mealordering/admin/delete-user">
											<c:param name="id" value="${user.id}"/>
										</c:url>
										<a class="btn btn-danger mo_btn_delete" href="${deleteUrl}">删除</a>
										<c:url var="detailUrl" value="/mealordering/admin/find-user">
											<c:param name="id" value="${user.id}"/>
										</c:url>
										<a class="btn btn-info mo_btn-detail" href="${detailUrl}">详情</a>
									</td>
								</tr>
							</c:forEach>
							<script>
								$(".mo_btn-delete").click(() => confirm("你确定要删除该用户吗？"));
							</script>
							</tbody>
						</table>
					</div>

					<!--STEP 分页栏-->
					<div class="row" id="mo_page-bar-admin">
						<!--NOTE 动态生成分页栏-->
						<ul class="pagination">
							<c:forEach var="text" items="${pageBtnText}">
								<li class="page-item">
									<c:url var="pageUrl" value="/mealordering/admin/get-page">
										<c:param name="item" value="user"/>
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


	<%--STEP 引入脚本--%>
	<%--引入jQuery和Bootstrap脚本--%>
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
