<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!--STEP 页面导航：分类导航-->
<div class="container mo_search-bar">
	<div class="row">
		<div class="col-sm-7">
			<ul class="nav nav-tabs" id="mo_nt-category">
				<li class="nav-item">
					<c:url var="url1" value="/mealordering/meal/search">
						<c:param name="searchType" value="byCategory"/>
						<c:param name="category" value="所有分类"/>
					</c:url>
					<a class="nav-link" href="${url1}">所有分类</a>
				</li>
				<li class="nav-item">
					<c:url var="url2" value="/mealordering/meal/search">
						<c:param name="searchType" value="byCategory"/>
						<c:param name="category" value="素食"/>
					</c:url>
					<a class="nav-link" href="${url2}">素食</a>
				</li>
				<li class="nav-item">
					<c:url var="url3" value="/mealordering/meal/search">
						<c:param name="searchType" value="byCategory"/>
						<c:param name="category" value="荤食"/>
					</c:url>
					<a class="nav-link" href="${url3}">荤食</a>
				</li>
				<li class="nav-item">
					<c:url var="url4" value="/mealordering/meal/search">
						<c:param name="searchType" value="byCategory"/>
						<c:param name="category" value="甜点"/>
					</c:url>
					<a class="nav-link" href="${url4}">甜点</a>
				</li>
				<li class="nav-item">
					<c:url var="url5" value="/mealordering/meal/search">
						<c:param name="searchType" value="byCategory"/>
						<c:param name="category" value="黑暗料理"/>
					</c:url>
					<a class="nav-link" href="${url5}">黑暗料理</a>
				</li>
			</ul>
		</div>

		<div class="col-md-5 float-right">
			<!--STEP 内联表单：普通搜索-->
			<%--CITE GET 传出：searchType,name--%>
			<form class="form-inline" action="<c:url value="/mealordering/meal/search"/>" method="get">
				<%--搜索类型--%>
				<input type="hidden" name="searchType" value="byName"/>
				<%--输入餐品名称--%>
				<div class="form-group">
					<label class="sr-only" for="mo_name-search">名称</label>
					<input type="text" class="form-control" id="mo_name-search" name="name"
					       placeholder="请输入餐品名称" required>
				</div>
				<%--提交表单--%>
				<button type="submit" class="btn btn-default">
					<span class="fa fa-search"></span>
				</button>
				<%--高级搜索按钮--%>
				<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="mo_modal-adv-search">
					<span class="fa fa-star"></span>高级搜索
				</button>
			</form>
		</div>
	</div>

	<!--STEP 模态框：高级搜索 -->
	<!--被模态框标题引用，默认隐藏-->
	<div class="modal fade in" id="mo_modal-adv-search">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title text-center" id="mo_mt-adv-search">高级搜索</h4>
					<button class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<!--STEP 内联表单：高级搜索-->
					<!--CITE GET 传出：searchType,name,minPrice,maxPrice,category-->
					<form action="<c:url value="/mealordering/meal/search"/>" method="get">
						<%--搜索类型--%>
						<input type="hidden" name="searchType" value="byConditions"/>
						<%--输入餐品名称--%>
						<div class="form-row m-1">
							<label class="col-md-3" for="mo_name-adv-search">餐品名称</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="mo_name-adv-search" name="name"
								       placeholder="请输入餐品名称...">
							</div>
						</div>
						<%--输入最低价格和最高价格--%>
						<div class="form-row m-1">
							<label class="col-md-3" for="mo_minPrice-adv-search">最低价格</label>
							<div class="col-md-3">
								<input type="number" class="form-control" id="mo_minPrice-adv-search" name="minPrice"
								       min="0" max="10000">
							</div>
							<label class="col-md-3" for="mo_maxPrice-adv-search">最高价格</label>
							<div class="col-md-3">
								<input type="number" class="form-control" id="mo_maxPrice-adv-search" name="maxPrice"
								       min="0" max="10000">
							</div>
						</div>
						<%--限制最低价格总是小于最高价格--%>
						<script>
							$("#mo_min-num,#mo_max-num").change(function() {
								let $min = $("#mo_minPrice-adv-search");
								let $max = $("#mo_maxPrice-adv-search");
								$min.val(Math.min(parseInt($min.val()), parseInt($max.val())));
							});
						</script>
						<%--选择餐品分类--%>
						<div class="form-row m-1">
							<label class="col-md-3" for="mo_category-adv-search">餐品分类</label>
							<div class="col-md-9">
								<select class="form-control" id="mo_category-adv-search">
									<option name="category" value="所有分类" selected>所有分类</option>
									<option name="category" value="素食">素食</option>
									<option name="category" value="荤食">荤食</option>
									<option name="category" value="甜点">甜点</option>
									<option name="category" value="黑暗料理">黑暗料理</option>
								</select>
							</div>
						</div>
						<%--提交表单--%>
						<div class="form-row m-1">
							<button type="submit" class="btn btn-primary mx-auto">搜索！！</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
