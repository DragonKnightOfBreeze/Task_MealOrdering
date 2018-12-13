<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>电子书城</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/main.css"/>
    <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/changeSecond.js"></script>
</head>

<body class="main">
<div id="div-content">
    <table width="850px" border="0" cellspacing="0">
        <tr>
            <td style="padding:30px; text-align:center">
                <table width="60%" border="0" cellspacing="0" style="margin-top:70px">
                    <tr>
                        <td style="width:98px">
                            <img src="${pageContext.request.contextPath}/admin/images/error.jpg" width="128"
                                 height="128"/>
                        </td>
                        <td style="padding-top:30px">
                            <span style="font-weight:bold; color:#ff0000">权限不足,请登录后操作</span><br/>
                            <a href="${pageContext.request.contextPath}/pageIndex.jsp">
                                <span id="second">5</span>秒后自动为您转跳回首页
                            </a>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
