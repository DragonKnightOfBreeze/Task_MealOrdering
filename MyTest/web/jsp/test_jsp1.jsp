<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="test" uri="utils" %>
<html>
<head>
<title>Title</title>
</head>
<body>
<test:dateFormat format="yyyy-MM-dd HH-mm-ss"/>

<test:forEach id="url" name="all" scope="request">
    <h2>网站：${url}</h2>
</test:forEach>

<test:dynamicAdd num1="1" num2="2" num3="3"/>
</body>
</html>
