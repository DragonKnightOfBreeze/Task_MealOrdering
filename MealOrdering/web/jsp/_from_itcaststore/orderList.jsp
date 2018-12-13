<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="http://www.itcast.cn/tag" %>
<jsp:useBean id="orderList" scope="request" type="java.util.List<task_itcaststore.domain.Order>"/>

<html>
<head>
    <title>电子书城</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/main.css"/>
    <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/utils/js/myUtils.js"></script>
</head>

<body class="main">
<p:user/>
<jsp:include page="head.jsp"/>
<jsp:include page="menuSearch.jsp"/>
<div id="div-page-content">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <td width="25%">
                <table width="100%" border="0" cellspacing="0" style="margin-top:30px">
                    <tr>
                        <td class="list-title">我的帐户</td>
                    </tr>
                    <tr>
                        <td class="list-td">
                            <img src="${pageContext.request.contextPath}/client/images/icon1.png" width="15"
                                 height="10"/>
                            &emsp;
                            <a href="${pageContext.request.contextPath}/client/modifyUserInfo.jsp">用户信息修改</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="list-td">
                            <img src="${pageContext.request.contextPath}/client/images/icon2.png" width="15"
                                 height="10"/>
                            &emsp;
                            <a href="${pageContext.request.contextPath}/findOrderByUser">订单查询</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="list-td">
                            <img src="${pageContext.request.contextPath}/client/images/icon3.png" width="15"
                                 height="10"/>
                            &emsp;
                            <a href="${pageContext.request.contextPath}/logout"
                               onclick="return confirmWarn('确认退出？')">用戶退出</a>
                        </td>
                    </tr>
                </table>
            </td>
            <td>
                <div style="text-align:right; margin:5px 10px 5px 0">
                    <a href="${pageContext.request.contextPath}/pageIndex.jsp">首页</a>
                    &emsp;&gt;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/client/myAccount.jsp">&nbsp;我的帐户</a>
                    &emsp;&gt;&emsp;
                    订单查询
                </div>
                <table cellspacing="0" class="info-content">
                    <tr>
                        <td style="padding:20px"><p><strong>我的订单</strong></p>
                            <p>
                                共有<span style="color:#FF0000">${orderList.size()}</span>订单
                            </p>
                            <table width="100%" border="0" cellspacing="0" class="table-open">
                                <tr>
                                    <td bgcolor="#A3E6DF" class="table-open-td01">订单号</td>
                                    <td bgcolor="#A3D7E6" class="table-open-td01">收件人</td>
                                    <td bgcolor="#A3CCE6" class="table-open-td01">订单时间</td>
                                    <td bgcolor="#A3B6E6" class="table-open-td01">状态</td>
                                    <td bgcolor="#A3E2E6" class="table-open-td01">操作</td>
                                </tr>
                                <c:forEach var="order" items="${orderList}">
                                    <tr>
                                        <td class="table-open-td02">${order.id}</td>
                                        <td class="table-open-td02">${order.receiverName }</td>
                                        <td class="table-open-td02">${order.orderIime}</td>
                                        <td class="table-open-td02">${order.payState==0?"未支付":"已支付"}</td>
                                        <td class="table-open-td03">
                                            <a href="${pageContext.request.contextPath}/findOrderById?id=${order.id}">查看</a>&nbsp;&nbsp;
                                            <c:if test="${order.payState==0 }">
                                                <a href="${pageContext.request.contextPath}/delOrderById?id=${order.id}"
                                                   onclick="return confirmWarn('你确定要删除该订单吗？')">刪除</a>
                                            </c:if>
                                            <c:if test="${order.payState!=0 }">
                                                <a href="${pageContext.request.contextPath}/delOrderById?id=${order.id}&type=client"
                                                   onclick="return confirmWarn('你确定要删除该订单吗？')">刪除</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<%@ include file="foot.jsp" %>
</body>
</html>
