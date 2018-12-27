<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!--STEP 侧边菜单-->
<ul class="nav nav-pills flex-column" id="mo_side-menu">
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/mealordering/admin/welcome.jsp"/>">欢迎页</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/mealordering/admin/find-all-meals"/>">餐品管理</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/mealordering/admin/find-all-notices"/>">公告管理</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/mealordering/admin/find-all-orders"/>">订单管理</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/mealordering/admin/find-all-users"/>">用户管理</a>
	</li>
</ul>
