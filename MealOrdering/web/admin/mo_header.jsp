<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--CITE 传入：session.onlineUser-->
<%--@elvariable id="onlineUser" type="mealordering.domain.User"--%>

<!--STEP 导航栏：网站图标，导航文本，导航栏菜单，显示管理员名字-->
<!--固定在页面顶部，小屏幕上水平导航栏会切换为垂直的-->
<div id="mo_header">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container">
			<!--STEP 导航栏头部-->
			<div class="navbar-header">
				<!--STEP 导航栏网站LOGO-->
				<a class="navbar-brand" href="<c:url value="/mealordering/admin/welcome.jsp"/>">
					<img src="<c:url value="/mealordering/assets/image/logo/logo.jpg"/>"/>
				</a>
			</div>
			<!--STEP 导航栏文本-->
			<span class="navbar-text">Text goes here.</span>
			&emsp;&emsp;&emsp;
			<span class="navbar-text">网上订餐系统管理页面</span>
			&emsp;&emsp;&emsp;
			<!--STEP 导航栏菜单：管理员名字和登出，右对齐-->
			<ul class="navbar-nav navbar-right" id="mo_user-menu">
				<li class="nav-item">
					<a class="nav-link disabled" href="#">
						<span class="fa fa-user-circle"> </span><span id="mo_admin-name">管理员 ${onlineUser.userName}</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="mo_btn-logout" href="<c:url value="/mealordering/logout"/>">
						<span class="fa fa-sign-out"></span> 注销
					</a>
				</li>
			</ul>
		</div>
	</nav>
</div>
