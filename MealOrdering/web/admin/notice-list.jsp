<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--CITE 传入：session.onlineUser-->
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>
<%--@elvariable id="pageGroup" type="mealordering.domain.PageGroup"--%>
<%--@elvariable id="page" type="java.util.List<mealordering.domain.Notice>"--%>
<%--@elvariable id="pageBtnText" type="java.lang.String[]"--%>

<!DOCTYPE html>
<html>
<head>
	<title>管理页面 公告列表</title>
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
			<!--STEP 内容页面-->
			<div class="col-sm-9">
				<div class="container" id="mo_body-page-admin">
					<!--STEP 标题-->
					<div class="row m-3" id="mo_body-title-admin">
						<div class="col-sm-6">
							<h2>公告列表</h2>
						</div>
						<div class="col-sm-5">
							<!--STEP 内联搜索表单-->
							<form class="form-inline" id="mo_form-search" action="<c:url value="/mealordering/admin/search-notice"/>"
							      method="get">
								<!--搜索类型-->
								<input type="hidden" name="searchType" value="byTitle">
								<!--输入用户名-->
								<div class="form-group">
									<label class="sr-only" for="mo_by-title">名称</label>
									<input type="text" class="form-control" id="mo_by-title" name="title" placeholder="按标题搜索" required>
								</div>
								<!--提交表单-->
								<button type="submit" class="btn btn-default">
									<span class="fa fa-search"></span>
								</button>
							</form>
						</div>
						<!--STEP 添加公告按钮-->
						<div class="col-sm-1" id="mo_btn-add">
							<button class="btn btn-info"
							        data-toggle="modal" data-target="#mo_modal-add">增加
							</button>
						</div>
					</div>
					<hr>
					<!--STEP 内容-->
					<div class="row" id="mo_body-content-admin">
						<table class="table table-bordered table-striped table-hover table-responsive m-auto">
							<thead class="text-center">
							<tr>
								<th>id</th>
								<th>标题</th>
								<th>详情</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody class="text-center">
							<!--NOTE 动态插入数据-->
							<c:forEach var="notice" items="${page}">
								<tr>
									<td>${notice.id}</td>
									<td>${notice.title}</td>
									<td>${notice.details}</td>
									<td><fmt:formatDate value="${notice.time}"/></td>
									<td>
										<c:url var="deleteUrl" value="/mealordering/admin/delete-notice">
											<c:param name="id" value="${notice.id}"/>
										</c:url>
										<a class="btn btn-danger mo_btn-delete" href="${deleteUrl}">删除</a>
										<c:url var="detailUrl" value="/mealordering/admin/find-notice">
											<c:param name="id" value="${notice.id}"/>
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
						<!--STEP 动态生成分页导航-->
						<ul class="pagination-lg  m-auto">
							<c:forEach var="text" items="${pageBtnText}">
								<li class="page-item">
									<c:url var="pageUrl" value="/mealordering/admin/get-page">
										<c:param name="item" value="notice"/>
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

	<!--STEP 创建公告的模态框-->
	<div class="modal fade" id="mo_modal-add">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title text-center">创建公告</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<!--STEP 创建公告的表单-->
					<form id="mo_form-add" action="<c:url value="/mealordering/admin/add-notice"/>" method="post">
						<!--公告标题-->
						<div class="form-row m-1">
							<label class="col-sm-3" for="mo_title">标题</label>
							<div class="col-sm-9 input-group">
								<input type="text" class="form-control" id="mo_title"
								       name="title" value="公告标题" placeholder="请输入公告标题" required>
							</div>
						</div>
						<!--公告详情-->
						<div class="form-row m-1">
							<label class="col-sm-3" for="mo_details">详情</label>
							<div class="col-sm-9 input-group">
								<textarea class="form-control" id="mo_details" rows="4"
								          name="details">在这里输入公告详情......</textarea>
							</div>
						</div>
						<!--提交按钮-->
						<div class="form-row m-1">
							<button type="submit" class="btn btn-primary m-auto">提交</button>
						</div>
					</form>
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
	<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
	<!--引入jQuery验证插件，包括本地化和附加方法-->
	<script src="<c:url value="/mealordering/framework/jquery-validation/jquery.validate.min.js"/>"></script>
	<script src="<c:url value="/mealordering/framework/jquery-validation/localization/messages_zh.min.js"/>"></script>
	<script src="<c:url value="/mealordering/framework/jquery-validation/additional-methods.min.js"/>"></script>
	<script>
		$(function() {
			//STEP 切换侧边栏导航显示
			$("#mo_side-menu .nav-link").eq(2).addClass("active");
			//STEP 删除前确认
			$(".mo_btn-delete").click(function() {
				return confirm("你确定要删除该公告吗？");
			});
		});
	</script>
</body>
</html>

