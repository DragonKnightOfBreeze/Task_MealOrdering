<%@ page contentType="text/html; charset=UTF-8" %>
<%--@elvariable id="orderId" type="java.lang.String"--%>
<%--@elvariable id="totalPrice" type="java.lang.String"--%>

<html>
<head>
    <title>确认支付</title>
</head>

<body>
<!-- 确认支付的表单 -->
<form action="${pageContext.request.contextPath}/changeOrderState" method="post">
    <h3>订单号：${orderId}，付款金额 ：${totalPrice}</h3>
    <input type="hidden" name="orderId" value="${orderId}"/>
    <input type="submit" value="确认支付"/>
</form>
</body>
</html>
