<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="http://www.itcast.cn/tag" %>
<jsp:useBean id="order" scope="request" type="task_itcaststore.domain.Order"/>

<html>
<head>
    <title>电子书城</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/main.css"/>
</head>
<body class="main">
<p:user/>
<jsp:include page="head.jsp"/>
<jsp:include page="menuSearch.jsp"/>

<div id="div-page-content">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <td>
                <div style="text-align:right; margin:5px 10px 5px 0">
                    <a href="${pageContext.request.contextPath}/pageIndex.jsp">首页</a>
                    &emsp;&gt;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/client/myAccount.jsp">我的账户</a>
                    &emsp;&gt;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/findOrderByUser">订单查询</a>
                    &emsp;&gt;&nbsp;&nbsp;&nbsp;订单详细信息
                </div>
                <table cellspacing="0" class="info-content">
                    <tr>
                        <td>
                            <table width="100%" border="0" cellspacing="0">
                                <tr>
                                    <td>
                                        <p>订单编号:${order.id}</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table cellspacing="1" class="cart-table">
                                            <tr>
                                                <td width="10%">序号</td>
                                                <td width="40%">商品名称</td>
                                                <td width="10%">价格</td>
                                                <td width="10%">数量</td>
                                                <td width="10%">小计</td>
                                            </tr>
                                        </table>
                                        <c:forEach var="item" items="${order.orderItems}" varStatus="vs">
                                            <table width="100%" border="0" cellspacing="0">
                                                <tr>
                                                    <td width="10%">${vs.count }</td>
                                                    <td width="40%">${item.product.name}</td>
                                                    <td width="10%">${item.product.price }</td>
                                                    <td width="10%">${item.buyCount }</td>
                                                    <td width="10%">${item.buyCount*item.product.price }</td>
                                                </tr>
                                            </table>
                                        </c:forEach>
                                        <table cellspacing="1" class="cart-table">
                                            <tr>
                                                <td style="text-align:right; padding-right:40px;"><span
                                                        style="color:#FF0000">合计：&nbsp;&nbsp;${order.totalPrice}</span>
                                                </td>
                                            </tr>
                                        </table>
                                        <p>
                                            收货地址：${order.receiverAddress }&emsp;<br/>
                                            收货人：&emsp;${order.receiverName }&emsp;<br/>
                                            联系方式：${order.receiverPhone }&emsp;
                                        </p>
                                        <hr>
                                        <c:if test="${order.payState != 1 }">
                                            <p style="text-align:right">
                                                <a href="${pageContext.request.contextPath}/client/pay.jsp?id=${order.id}&totalPrice=${order.totalPrice}">
                                                    <img src="${pageContext.request.contextPath}/client/images/gif53_030.gif"
                                                         width="204" height="51" border="0"/>
                                                </a>
                                            </p>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<jsp:include page="foot.jsp"/>
</body>
</html>
