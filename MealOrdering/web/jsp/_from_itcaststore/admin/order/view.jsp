<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="order" scope="request" type="task_itcaststore.domain.Order"/>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/style.css">
    <script src="${pageContext.request.contextPath}/admin/js/public.js"></script>
</head>

<body>
<table cellSpacing="1" cellPadding="5" width="100%" align="center"
       bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
    <tr>
        <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26">
            <strong>订单详细信息</strong>
        </td>
    </tr>
    <tr>
        <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">订单编号：</td>
        <td class="ta_01" bgColor="#ffffff">${order.id}</td>
        <td align="center" bgColor="#f5fafe" class="ta_01">所属用户：</td>
        <td class="ta_01" bgColor="#ffffff">${order.user.userName }</td>
    </tr>
    <tr>
        <td align="center" bgColor="#f5fafe" class="ta_01">收件人：</td>
        <td class="ta_01" bgColor="#ffffff">${order.receiverName }</td>
        <td align="center" bgColor="#f5fafe" class="ta_01">联系电话：</td>
        <td class="ta_01" bgColor="#ffffff">${order.receiverPhone }</td>
    </tr>
    <tr>
        <td align="center" bgColor="#f5fafe" class="ta_01">送货地址：</td>
        <td class="ta_01" bgColor="#ffffff">${order.receiverAddress}</td>
        <td align="center" bgColor="#f5fafe" class="ta_01">总价：</td>
        <td class="ta_01" bgColor="#ffffff">${order.totalPrice }</td>
    </tr>
    <tr>
        <td align="center" bgColor="#f5fafe" class="ta_01">下单时间：</td>
        <td class="ta_01" bgColor="#ffffff" colSpan="3">${order.orderIime}</td>
    </tr>
    <tr>
        <td class="ta_01" align="center" bgColor="#f5fafe">商品信息</td>
        <td class="ta_01" bgColor="#ffffff" colSpan="3">
            <table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
                   style="WIDTH: 100%; WORD-BREAK: break-all; border: 1px solid gray;BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                    <td align="center" width="7%">序号</td>
                    <td width="8%" align="center">商品</td>
                    <td align="center" width="18%">商品编号</td>
                    <td align="center" width="10%">商品名称</td>
                    <td align="center" width="10%">商品价格</td>
                    <td width="7%" align="center">购买数量</td>
                    <td width="7%" align="center">商品类别</td>
                    <td width="31%" align="center">商品描述</td>
                </tr>
                <c:forEach var="item" items="${order.orderItemList}" varStatus="vs">
                    <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #eeeeee">
                        <td align="center" width="7%">${vs.count }</td>
                        <td width="8%" align="center">
                            <img src="${pageContext.request.contextPath}${item.product.imgUrl}" width="50" height="50">
                        </td>
                        <td align="center" width="18%">${item.product.id }</td>
                        <td align="center" width="10%">${item.product.name }</td>
                        <td align="center" width="10%">${item.product.price }</td>
                        <td width="7%" align="center">${item.buyCount }</td>
                        <td width="7%" align="center">${item.product.category }</td>
                        <td width="31%" align="center">${item.product.description}</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <td align="center" colSpan="4" class="sep1"><img
                src="${pageContext.request.contextPath}/admin/images/shim.gif">
        </td>
    </tr>
    <tr>
        <td class="ta_01" style="WIDTH: 100%" align="right" bgColor="#f5fafe" colSpan="4">
            <span style="font-family:'宋体',sans-serif; ">&emsp;&nbsp;&nbsp;&nbsp;</span>
            <input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
            <span id="label1"></span>
        </td>
    </tr>
</table>
</body>
</html>
