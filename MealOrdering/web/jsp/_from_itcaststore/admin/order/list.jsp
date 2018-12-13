<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="orderList" scope="request" type="java.util.List<task_itcaststore.domain.Order>"/>

<html>
<head>
    <link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/utils/js/myUtils.js"></script>
    <script src="${pageContext.request.contextPath}/admin/js/public.js"></script>
</head>
<body>
<br/>
<form id="form1" name="form1" action="${pageContext.request.contextPath}/manager/findOrdersByConditions" method="post">
    <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>查 询 条 件</strong>
            </td>
        </tr>
        <tr>
            <td>
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tr>
                        <td height="22" align="center" bgColor="#f5fafe" class="ta_01">
                            订单编号
                        </td>
                        <td class="ta_01" bgColor="#ffffff">
                            <input type="text" name="id" size="15" value="" id="form1_id" class="bg"/>
                        </td>
                        <td height="22" align="center" bgColor="#f5fafe" class="ta_01">
                            收件人：
                        </td>
                        <td class="ta_01" bgColor="#ffffff">
                            <input type="text" name="receiverName" size="15" value="" id="form1_userName"
                                   class="bg"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="100" height="22" align="center" bgColor="#f5fafe" class="ta_01"></td>
                        <td class="ta_01" bgColor="#ffffff">
                            <span style="font-family:'宋体',sans-serif; color: red; "> &nbsp;</span>
                        </td>
                        <td align="right" bgColor="#ffffff" class="ta_01">
                            <br/><br/>
                        </td>
                        <td align="right" bgColor="#ffffff" class="ta_01">
                            <button type="submit" id="search" name="search" value="&#26597;&#35810;"
                                    class="button_view">
                                &#26597;&#35810;
                            </button> &emsp;&nbsp;
                            <input type="reset" name="reset" value="&#37325;&#32622;" class="button_view"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>订 单 列 表</strong>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="right"></td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="WIDTH: 100%; WORD-BREAK: break-all; border: 1px solid gray;BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                        <td align="center" width="20%">订单编号</td>
                        <td align="center" width="10%">收件人</td>
                        <td align="center" width="15%">地址</td>
                        <td align="center" width="10%">联系电话</td>
                        <td width="11%" align="center">总价</td>
                        <td width="8%" align="center">所属用户</td>
                        <td width="10%" align="center">订单状态</td>
                        <td width="7%" align="center">查看</td>
                        <td width="7%" align="center">删除</td>
                    </tr>
                    <c:forEach var="order" items="${orderList}">
                        <tr onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">${order.id}</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${order.receiverName}</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="15%">${order.receiverAddress }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="10%">${order.receiverPhone }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center">${order.totalPrice}</td>
                            <td width="8%" align="center">${order.user.userName}</td>
                            <td width="10%" align="center">${order.payState==0?"未支付":"已支付"}</td>
                            <td align="center" style="HEIGHT: 22px">
                                <a href="${pageContext.request.contextPath}/findOrderById?id=${order.id}&type=admin">
                                    <img src="${pageContext.request.contextPath}/admin/images/button_view.gif"
                                         border="0" style="CURSOR: hand">
                                </a>
                            </td>
                            <td align="center" style="HEIGHT: 22px">
                                <!--  已支付，弹出提示框由操作人确认 -->
                                <c:if test="${order.payState!=0 }">
                                    <a href="${pageContext.request.contextPath}/delOrderById?id=${order.id}&type=admin"
                                       onclick="return confirmWarn('你确定要删除订单吗？')">
                                        <img src="${pageContext.request.contextPath}/admin/images/i_del.gif" width="16"
                                             height="16" border="0" style="CURSOR: hand">
                                    </a>
                                </c:if>
                                <!--  未支付 ，不能删除-->
                                <c:if test="${order.payState==0 }">
                                    <a href="javascript:alert('不能删除未支付订单')">
                                        <img src="${pageContext.request.contextPath}/admin/images/i_del.gif" width="16"
                                             height="16" border="0" style="CURSOR: hand">
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        </TBODY>
    </table>
</form>
</body>
</html>
