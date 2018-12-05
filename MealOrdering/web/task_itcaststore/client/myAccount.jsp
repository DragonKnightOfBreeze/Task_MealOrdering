<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="p" uri="http://www.itcast.cn/tag" %>

<html>
<head>
    <title>我的账号</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/main.css"/>
    <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/utils/js/myUtils.js"></script>
</head>

<body class="main">
<p:user/>
<jsp:include page="head.jsp"/>
<jsp:include page="menuSearch.jsp"/>

<div id="div-page-content">
    <table style="width:100%;border-collapse:collapse">
        <tr>
            <!--基本信息-->
            <td style="width:25%;">
                <table style="width:100%;border-collapse:collapse;margin-top:30px">
                    <tr>
                        <td class="list-title">我的帐户</td>
                    </tr>
                    <tr>
                        <td class="list-td">
                            <img src="${pageContext.request.contextPath}/client/images/icon1.png" width="15"
                                 height="10"/>&emsp;
                            <a href="${pageContext.request.contextPath}/client/modifyUserInfo.jsp">用户信息修改</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="list-td">
                            <img src="${pageContext.request.contextPath}/client/images/icon2.png" width="15"
                                 height="10"/>&emsp;
                            <a href="${pageContext.request.contextPath}/findOrderByUser">订单查询</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="list-td">
                            <img src="${pageContext.request.contextPath}/client/images/icon3.png" width="15"
                                 height="10"/>&emsp;
                            <a href="${pageContext.request.contextPath}/logout"
                               onclick="return confirmWarn('确认退出？')">用戶退出</a>
                        </td>
                    </tr>
                </table>
            </td>

            <!--地址和背景-->
            <td>
                <div style="text-align:right; margin:5px 10px 5px 0">
                    <a href="${pageContext.request.contextPath}/pageIndex.jsp">首页</a>&emsp;&gt;&emsp;
                    我的帐户&emsp;&gt;&emsp;欢迎
                </div>
                <table class="info-content">
                    <tr>
                        <td style="padding:20px"><p>
                            <img src="${pageContext.request.contextPath}/client/images/myAd.jpg" width="631"
                                 height="436"/>
                        </p>
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
