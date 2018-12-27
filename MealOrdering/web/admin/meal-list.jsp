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
	<title>管理页面 餐品列表</title>
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
							<h2>餐品列表</h2>
						</div>
						<div class="col-sm-5">
							<%--STEP 内联表单：按名称搜索--%>
							<form class="form-inline" id="mo_form-search" action="<c:url value="/mealordering/admin/search-meal"/>"
							      method="get">
								<%--搜索类型--%>
								<input type="hidden" name="searchType" value="byName">
								<%--输入餐品名称--%>
								<div class="form-group">
									<label class="sr-only" for="mo_name-by-name">名称</label>
									<input type="text" class="form-control" id="mo_name-by-name" name="name" placeholder="按名称搜索" required>
								</div>
								<%--提交表单--%>
								<button type="submit" class="btn btn-default">
									<span class="fa fa-search"></span>
								</button>
							</form>
						</div>
						<div class="col-sm-1">
							<%--STEP 添加公告按钮--%>
							<button class="btn btn-info" id="mo_btn-add" data-toggle="modal" data-target="#mo_modal-add">
								<span class="fa fa-plus-square"></span>
							</button>
						</div>
					</div>
					<hr>
					<!--STEP 内容-->
					<div class="row m-3" id="mo_body-content-admin">
						<table class="table table-bordered table-striped table-hover table-responsive m-auto">
							<thead class="text-center">
							<tr>
								<th>id</th>
								<th>名字</th>
								<th>价格</th>
								<th>分类</th>
								<th>库存数量</th>
								<th>售出数量</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody class="text-center">
							<%--NOTE 动态插入数据--%>
							<c:forEach var="notice" items="${page}">
								<tr>
									<td>${notice.id}</td>
									<td>${notice.name}</td>
									<td>${notice.price}</td>
									<td>${notice.category}</td>
									<td>${notice.count}</td>
									<td>${notice.soldCount}</td>
									<td>
										<c:url var="deleteUrl" value="/mealordering/admin/delete-meal">
											<c:param name="id" value="${notice.id}"/>
										</c:url>
										<a class="btn btn-danger mo_btn-delete" href="${deleteUrl}">删除</a>
										<c:url var="detailUrl" value="/mealordering/admin/find-meal">
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
						<!--NOTE 动态生成分页导航-->
						<ul class="pagination-lg m-auto">
							<c:forEach var="text" items="${pageBtnText}">
								<li class="page-item">
									<c:url var="pageUrl" value="/mealordering/admin/get-page">
										<c:param name="item" value="meal"/>
										<c:param name="pageIndex" value="${text}"/>
									</c:url>
									<a class="page-link mo_change-page" href="${pageUrl}"> ${text} </a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--STEP 增加餐品的模态框-->
	<div class="modal fade" id="mo_modal-add">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title text-center">增加餐品</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<!--STEP 增加餐品的表单-->
					<form action="<c:url value="/mealordering/admin/add-meal"/>" method="post" id="mo_form-add"
					      enctype="multipart/form-data">
						<!--餐品名字-->
						<div class="form-row m-1">
							<label class="col-sm-3" for="mo_meal-name">名字</label>
							<div class="col-sm-9 input-group">
								<input type="text" class="form-control" id="mo_meal-name"
								       name="name" placeholder="请输入餐品名字" required>
							</div>
						</div>
						<!--餐品价格-->
						<div class="form-row m-1">
							<label class="col-sm-3" for="mo_meal-price">价格</label>
							<div class="col-sm-9 input-group">
								<input type="number" class="form-control" id="mo_meal-price"
								       name="price" min="0" max="10000" placeholder="请输入餐品价格" required>
							</div>
						</div>
						<!--餐品分类-->
						<div class="form-row m-1">
							<label class="col-md-3" for="mo_meal-category">分类</label>
							<div class="input-group col-md-9">
								<select class="form-control" id="mo_meal-category">
									<option name="category" value="所有分类" selected>所有分类</option>
									<option name="category" value="素食">素食</option>
									<option name="category" value="荤食">荤食</option>
									<option name="category" value="甜点">甜点</option>
									<option name="category" value="黑暗料理">黑暗料理</option>
								</select>
							</div>
						</div>
						<!--餐品图片-->
						<div class="form-row m-1">
							<label class="col-sm-3" for="mo_img-url">选择图片</label>
							<div class="col-sm-9">
								<input type="file" class="form-control-file" id="mo_img-url" name="imgUrl" required>
							</div>
						</div>
						<!--餐品描述-->
						<div class="form-row m-1">
							<label for="mo_meal-description">描述</label>
							<textarea class="form-control" id="mo_meal-description" rows="4"
							          name="description">在这里输入餐品描述......</textarea>
						</div>
						<!--餐品数量-->
						<div class="form-row m-1">
							<label class="col-sm-3" for="mo_meal-count">数量</label>
							<div class="col-sm-9 input-group">
								<input type="number" class="form-control" id="mo_meal-count"
								       name="count" min="0" max="10000" placeholder="输入餐品数量" required>
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


	<%--STEP 引入脚本--%>
	<%--引入jQuery和Bootstrap脚本--%>
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<script>
		$(function() {
			//STEP 切换侧边栏导航显示
			$("#mo_side-menu .nav-link").removeClass("active").eq(1).addClass("active");
		});
		//STEP 删除前确认
		$(".mo_btn-delete").click(function() {
			return confirm("你确定要删除该餐品吗？");
		});
	</script>
</body>
</html>



