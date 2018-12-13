<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="http://www.itcast.cn/tag" %>
<jsp:useBean id="user" scope="request" type="task_itcaststore.domain.User"/>
<jsp:useBean id="cart" scope="request" type="java.util.Map<task_itcaststore.domain.Product,java.lang.Integer>"/>

<html>
<head>
    <title>电子书城</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/main.css"/>
    <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/checkOrder.js"></script>
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
                    <a href="${pageContext.request.contextPath}/client/cart.jsp">&nbsp;购物车</a>
                    &emsp;&gt;&emsp;订单
                </div>
                <form id="orderForm" action="${pageContext.request.contextPath}/createOrder" method="post">
                    <table cellspacing="0" class="info-content">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0">
                                    <tr>
                                        <td><img src="${pageContext.request.contextPath}/client/images/buy2.gif"
                                                 width="635" height="38"/>
                                            <p>你好，${user.userName}！欢迎您来到网上书城结算中心</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <table cellspacing="1" class="cart-table">
                                                <tr>
                                                    <td width="10%">序号</td>
                                                    <td width="40%">商品名称</td>
                                                    <td width="10%">价格</td>
                                                    <td width="10%">类别</td>
                                                    <td width="10%">数量</td>
                                                    <td width="10%">小计</td>
                                                </tr>
                                            </table>
                                            <c:set var="totalPrice" value="0"/>
                                            <c:forEach var="entry" items="${cart}" varStatus="vs">
                                                <table width="100%" border="0" cellspacing="0">
                                                    <tr>
                                                        <td width="10%">${vs.count}</td>
                                                        <td width="40%">${entry.key.name}</td>
                                                        <td width="10%">${entry.key.price}</td>
                                                        <td width="10%">${entry.key.category}</td>
                                                        <td width="10%">
                                                            <input name="text" type="text" value="${entry.value}"
                                                                   style="width:20px" readonly="readonly"/>
                                                        </td>
                                                        <td width="10%">${entry.key.price*entry.value}</td>
                                                    </tr>
                                                </table>
                                                <c:set var="totalPrice"
                                                       value="${totalPrice+entry.key.price*entry.value}"/>
                                            </c:forEach>

                                            <table cellspacing="1" class="cart-table">
                                                <tr>
                                                    <td style="text-align:right; padding-right:40px;"><span
                                                            style="color:#FF0000">合计：&nbsp;&nbsp;${totalPrice}元</span>
                                                        <input type="hidden" name="totalPrice" value="${totalPrice}">
                                                    </td>
                                                </tr>
                                            </table>
                                            <p>
                                                收货地址：<input id="receiverAddress" name="receiverAddress" type="text"
                                                            value="" style="width:350px"
                                                            onkeyup="checkReceiverAddress();"/>
                                                &emsp;
                                                <span id="receiverAddressMsg"></span>
                                                <br/>
                                                收货人：&emsp;<input id="receiverName" name="receiverName" type="text"
                                                                 value="${user.userName}" style="width:150px"
                                                                 onkeyup="checkReceiverName();"/>
                                                <span id="receiverNameMsg"></span>
                                                &emsp;
                                                <br/>
                                                联系方式：<input type="text" id="receiverPhone" name="receiverPhone"
                                                            value="${user.phoneNum}" style="width:150px"
                                                            onkeyup="checkReceiverPhone();"/>
                                                <span id="receiverPhoneMsg"></span>
                                                &emsp;
                                            </p>
                                            <hr/>
                                            <p style="text-align:right">
                                                <img src="${pageContext.request.contextPath}/client/images/gif53_029.gif"
                                                     width="204" height="51" border="0" onclick="checkOrder();"/>
                                            </p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </form>
            </td>
        </tr>
    </table>
</div>
<jsp:include page="foot.jsp"/>
</body>
</html>
