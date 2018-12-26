<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>啊啊啊啊啊</title>
</head>
<body>
	<p>fafafaf发发发发风3</p>
	<form action="<c:url value="/test/testEncoding3"/>" method="get">
		<input type="text" name="name" value="你好" disabled>
		<input type="submit" value="提交">
	</form>

	<p>fafafaf发发发发风3</p>
	<form action="<c:url value="/test/testEncoding3"/>" method="post">
		<input type="text" name="name" value="你好">
		<input type="submit" value="提交">
	</form>

	<p>fafafaf发发发发风3</p>
	<form action="<c:url value="/test/jsp/testEncoding2.jsp"/>" method="get">
		<input type="text" name="name" value="你好">
		<input type="submit" value="提交">
	</form>

	<p>fafafaf发发发发风3</p>
	<form action="<c:url value="/test/jsp/testEncoding2.jsp"/>" method="post">
		<input type="text" name="name" value="你好">
		<input type="submit" value="提交">
	</form>
</body>
</html>
