<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!--STEP 侧边菜单-->
<ul class="nav nav-pills flex-column mx-3">
	<li class="nav-item">
		<a class="nav-link active" href="<c:url value="/mealordering/admin/welcome.jsp"/>">欢迎页</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/mealordering/admin/meal-list.jsp"/>">餐品管理</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/mealordering/admin/notice-list.jsp"/>">公告管理</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/mealordering/admin/order-list.jsp"/>">订单管理</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/mealordering/admin/user-list.jsp"/>">用户管理</a>
	</li>
</ul>
