<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="http://www.itcast.cn/tag" %>

<html>
<head>
    <title>我的购物车</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/main.css"/>
    <script src="${pageContext.request.contextPath}/client/js/cart.js"></script>

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
                    &emsp;&gt;&emsp;购物车
                </div>
                <table class="info-content">
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/client/ad/page_ad.jpg" width="900"
                                 height="89" title="广告"/>
                            <table style="width:100%;border-collapse:collapse">
                                <tr>
                                    <td>
                                        <img src="${pageContext.request.contextPath}/client/images/buy1.gif"
                                             width="635" height="38" title="购物车"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table class="cart-table">
                                            <tr>
                                                <td style="width:10%">序号</td>
                                                <td style="width:30%">商品名称</td>
                                                <td style="width:10%">价格</td>
                                                <td style="width:20%">&emsp;&emsp;数量</td>
                                                <td style="width:10%">库存</td>
                                                <td style="width:10%">小计</td>
                                                <td style="width:10%">取消</td>
                                            </tr>
                                        </table>

                                        <!-- 循环输出商品信息 -->
                                        <c:set var="total" value="0"/>
                                        <c:forEach var="entry" items="${sessionScope.cart}" varStatus="vs">
                                            <table width="100%" border="0" cellspacing="0">
                                                <tr>
                                                    <td width="10%">${vs.count}</td>
                                                    <td width="30%">${entry.key.name}</td>
                                                    <td width="10%">${entry.key.price}</td>
                                                    <td width="20%">
                                                        <!-- 减少商品数量 -->
                                                        <input type="button" value='-' style="width:20px"
                                                               onclick="changeProductNum('${entry.value-1}','${entry.key.count}','${entry.key.id}')">
                                                        <!-- 商品数量显示 -->
                                                        <input name="text" type="text" value="${entry.value}"
                                                               style="width:40px;text-align:center"/>
                                                        <!-- 增加商品数量 -->
                                                        <input type="button" value='+' style="width:20px"
                                                               onclick="changeProductNum('${entry.value+1}','${entry.key.count}','${entry.key.id}')">
                                                    </td>
                                                    <td width="10%">${entry.key.count}</td>
                                                    <td width="10%">${entry.key.price*entry.value}</td>
                                                    <td width="10%">
                                                        <!-- 删除商品 -->
                                                        <a href="${pageContext.request.contextPath}/changeCart?id=${entry.key.id}&count=0"
                                                           style="color:#FF0000; font-weight:bold"
                                                           onclick="return cart_del()">X</a>
                                                    </td>
                                                </tr>
                                            </table>
                                            <c:set var="total" value="${total+entry.key.price*entry.value}"/>
                                        </c:forEach>

                                        <!--尾随信息-->
                                        <table class="cart-table">
                                            <tr>
                                                <td style="text-align:right; padding-right:40px;">
                                                    <span style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;${total}元</span>
                                                </td>
                                            </tr>
                                        </table>
                                        <div style="text-align:right; margin-top:10px">
                                            <!--继续购物 -->
                                            <a href="${pageContext.request.contextPath}/showProductsByPage">
                                                <img src="${pageContext.request.contextPath}/client/images/gwc_jx.gif"
                                                     style="border:0" title="继续购物"/>
                                            </a>
                                            &emsp;
                                            <!--结账 -->
                                            <a href="${pageContext.request.contextPath}/client/order.jsp"
                                               title="结账">
                                                <img src="${pageContext.request.contextPath}/client/images/gwc_buy.gif"
                                                     style="border:0" title="结账"/>
                                            </a>
                                        </div>
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
