<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="ses" scope="session" type="java.lang.String"/>
<jsp:useBean id="user" scope="session" type="dk_breeze.test.servlet.TestUser"/>
<p>
	<c:out value="${ses}"/>
	<c:out value="${user.name}"/>
</p>

