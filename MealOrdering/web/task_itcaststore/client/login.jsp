<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>传智书城登录页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/main.css"/>
    <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/checkForm.js"></script>
</head>

<body class="main">
<jsp:include page="head.jsp"/>
<jsp:include page="menuSearch.jsp"/>
<!--登录页面-->
<div id="div-content">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <table style="width:900px;border-collapse:collapse">
            <tr>
                <td style="padding:30px">
                    <div style="height:470px">
                        <strong>&nbsp;&nbsp;首页&nbsp;&raquo;&nbsp;个人用户登录</strong>
                        <div>
                            <table style="width:85%;border-collapse:collapse">
                                <tr>
                                    <td>
                                        <div id="login-div">
                                            <table style="width:100%;border-collapse:collapse">
                                                <tr>
                                                    <td style="text-align:center; padding-top:20px">
                                                        <img src="${pageContext.request.contextPath}/client/images/loginTitle.gif"
                                                             width="150" height="30" title="个人用户登录"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="text-align:center;padding-top:20px;">
                                                        <span style="color: #ff0000; ">${requestScope.register_message}</span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="text-align:center">
                                                        <table style="width:80%;border-collapse:collapse;margin:15px auto auto auto;">
                                                            <tr>
                                                                <td style="text-align:right; padding-top:5px; width:25%">
                                                                    用户名：
                                                                </td>
                                                                <td style="text-align:left">
                                                                    <input name="userName" type="text"
                                                                           class="textInput"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="text-align:right; padding-top:5px">
                                                                    密&emsp;码：
                                                                </td>
                                                                <td style="text-align:left">
                                                                    <input name="password" type="password"
                                                                           class="textInput"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2" style="text-align:center">
                                                                    <input type="checkbox" name="checkbox"
                                                                           value="checkbox01"/>记住用户名&nbsp;&nbsp;
                                                                    <input type="checkbox" name="checkbox"
                                                                           value="checkbox02"/>自动登录
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2"
                                                                    style="padding-top:10px; text-align:center">
                                                                    <input type="image"
                                                                           onclick="return checkForm(true)"
                                                                           src="${pageContext.request.contextPath}/client/images/loginButton.gif"
                                                                           width="90" height="30"/>
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td colspan="2" style="padding-top:10px">
                                                                    <img src="${pageContext.request.contextPath}/client/images/loginLine.gif"
                                                                         width="241" height="10"/>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>

                                    <!--还没有注册？-->
                                    <td style="text-align:left; padding-top:30px; width:60%">
                                        <h1>您还没有注册？</h1>
                                        <p>注册新用户，享受更优惠价格!</p>
                                        <p>千种图书，供你挑选！注册即享受丰富折扣和优惠，便宜有好货！超过万本图书任您选。</p>
                                        <p>超人气社区！精彩活动每一天。买卖更安心！支付宝交易超安全。</p>
                                        <p style="text-align:left">
                                            <a href="${pageContext.request.contextPath}/client/register.jsp">
                                                <img src="${pageContext.request.contextPath}/client/images/signupButton.gif"
                                                     width="135" height="33" title="注册新用户"/>
                                            </a>
                                        </p>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
<jsp:include page="foot.jsp"/>
</body>
</html>
