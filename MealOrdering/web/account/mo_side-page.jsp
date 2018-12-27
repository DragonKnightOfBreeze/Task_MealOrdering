<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--CITE 传入：session.onlineUser--%>
<%--@elvariable id="onlineUser" type="mealordering.domain.NormalUser"--%>

<%--STEP 侧边信息栏--%>
<div class="container" id="mo_side-page">
	<div class="card m-auto">
		<img class="card-img-top" src="${onlineUser.imgUrl}" alt="用户头像">
		<div class="card-body">
			<h4 class="card-title font-weight-bold"><c:out value="${onlineUser.userName}">无名氏</c:out></h4>
			<p class="card-text font-weight-light"><c:out value="${onlineUser.introduce}">这家伙很懒，什么都没留下。</c:out></p>
			<a href="<c:url value="/mealordering/settings/edit-information"/>" class="btn btn-primary">编辑资料</a>
		</div>
	</div>
</div>
