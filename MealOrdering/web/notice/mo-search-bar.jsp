<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!--STEP 页面导航：公告搜索-->
<div class="container mo_search-bar">
	<div class="row">
		<div class="col-sm-5 offset-7">
			<form class="form-inline" action="<c:url value="/mealordering/notice/search"/>" method="get">
				<%--搜索类型--%>
				<input type="hidden" name="searchType" value="byTitle">
				<%--输入公告标题--%>
				<div class="form-group">
					<label class="sr-only" for="mo_title">标题</label>
					<input type="text" class="form-control" id="mo_title" name="Title" placeholder="请输入公告标题" required>
				</div>
				<%--提交表单--%>
				<button type="submit" class="btn btn-default">
					<span class="fa fa-search"></span>
				</button>
			</form>
		</div>
	</div>
</div>
