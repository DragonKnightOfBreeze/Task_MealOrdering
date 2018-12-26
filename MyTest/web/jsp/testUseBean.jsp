<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<title>Title</title>
</head>
<body>
	<%--这样可以显示正确的内容，并且可以确定正确的范围和类型--%>

	<%--在包含的页中可以直接使用useBean调用session属性--%>
	<%--而在外面还可以重复使用useBean调用session顺序--%>

	<%--使用type属性来指定对session属性的转型，可以自动转型，使用不同的父子类型--%>

	<%--使用class属性则会先实例化，然后可以使用jsp:setProperty，当然也可以正确接收内容--%>
	<jsp:useBean id="fox" scope="request" class="java.lang.String"/>
	<c:out value="${fox}"/>

	<jsp:include page="testIncludeSession.jsp"/>

	<jsp:useBean id="ses" scope="session" type="java.lang.String"/>
	<p>
		<c:out value="${ses}"/>
	</p>

	<jsp:useBean id="user" scope="session" type="dk_breeze.test.servlet.TestAdmin"/>
	<p>
		<c:out value="${user.ad}"/>
	</p>

	<jsp:useBean id="admin" scope="session" class="dk_breeze.test.servlet.TestAdmin"/>
	${empty admin.name}
</body>
</html>
