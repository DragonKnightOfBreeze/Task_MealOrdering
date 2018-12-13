<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>支付成功</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/main.css"/>
    <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/changeSecond.js"></script>
</head>

<body class="main" onload="changeSecond()">
<jsp:include page="head.jsp"/>
<jsp:include page="menuSearch.jsp"/>
<div id="div-content">
    <table style="width:850px;border:0;border-collapse:collapse">
        <tr>
            <td style="padding:30px; text-align:center">
                <table style="width:60%;border:0;border-collapse:collapse;margin-top:70px">
                    <tr>
                        <td style="width:98px">
                            <img src="${pageContext.request.contextPath}/client/images/IconText_WebDev_009.jpg"
                                 width="128" height="128"/>
                        </td>
                        <td style="padding-top:30px">
                            <span style="font-weight:bold;color:#FF0000">支付成功</span><br/>
                            <a href="${pageContext.request.contextPath}/pageIndex.jsp">
                                <span id="second">5</span>秒后自动为您跳转到首页
                            </a>
                        </td>
                    </tr>
                </table>
                <h1>&nbsp;</h1>
            </td>
        </tr>
    </table>
</div>
<jsp:include page="foot.jsp"/>
</body>
</html>
