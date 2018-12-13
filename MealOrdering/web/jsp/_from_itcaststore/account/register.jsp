<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>传智书城注册页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/main.css"/>
    <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/client/js/checkForm.js"></script>

    <script>
        /**
         * 改变验证码图片中的文字。
         */
        function changeImage() {
            $("#img").attr("src", "${pageContext.request.contextPath}/imageCode?time=" + new Date().getTime());
        }
    </script>
</head>

<body class="main">
<%@include file="head.jsp" %>
<%@include file="menuSearch.jsp" %>

<div id="div-content">
    <form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return checkForm();">
        <table style="width:850px;border-collapse:collapse">
            <tr>
                <td style="padding: 30px">
                    <h1>新用户注册</h1>
                    <table class="upline" style="width:70%;border-spacing:2px;">
                        <!--邮箱-->
                        <tr>
                            <td style="text-align: right; width: 20%">邮箱：</td>
                            <td>
                                <input type="text" class="textInput" id="email" name="email"
                                       onkeyup="checkEmail();"/>
                            </td>
                            <td colspan="2">
                                <span id="emailMsg"></span><span style="color: #999999; ">&nbsp;请输入有效的邮箱地址</span>
                            </td>
                        </tr>
                        <!--用户名-->
                        <tr>
                            <td style="text-align: right">用户名：</td>
                            <td>
                                <input type="text" class="textInput" id="userName" name="userName"
                                       onkeyup="checkUserName();"/>
                            </td>
                            <td colspan="2">
                                <span id="userNameMsg"></span><span
                                    style="color: #999999; ">&nbsp;字母数字下划线1到10位, 不能是数字开头</span>
                            </td>
                        </tr>
                        <!--密码-->
                        <tr>
                            <td style="text-align: right">密码：</td>
                            <td>
                                <input type="password" class="textInput" id="password" name="password"
                                       onkeyup="checkPassword();"/>
                            </td>
                            <td>
                                <span id="passwordMsg"></span><span
                                    style="color: #999999; ">&nbsp;密码请设置6-16位字符</span>
                            </td>
                        </tr>
                        <!--确认密码-->
                        <tr>
                            <td style="text-align: right">确认密码：</td>
                            <td>
                                <input type="password" class="textInput" id="rePassword"
                                       onkeyup="checkRePassword();"/>
                            </td>
                            <td>
                                <span id="rePasswordMsg"></span>&nbsp;
                            </td>
                        </tr>
                        <!--性别-->
                        <tr>
                            <td style="text-align: right">性别：</td>
                            <td colspan="2">&nbsp;&nbsp;
                                <input type="radio" name="gender" value="男" checked="checked"/>男&emsp;
                                <input type="radio" name="gender" value="女"/>女
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <!--联系电话-->
                        <tr>
                            <td style="text-align: right">联系电话：</td>
                            <td colspan="2">
                                <input type="text" class="textInput" name="phoneNum" style="width: 350px"/>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <!--个人介绍-->
                        <tr>
                            <td style="text-align: right">个人介绍：</td>
                            <td colspan="2">
                                <textarea class="textarea" name="introduce"></textarea>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                    <!--验证码-->
                    <h1>注册验证</h1>
                    <table class="upline" style="width:80%;border:0;border-spacing:2px;">
                        <tr>
                            <td style="text-align:right; width: 20%">输入验证码：</td>
                            <td style="width: 50%">
                                <input type="text" class="textInput"/>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td style="text-align: right; width: 20%;">&nbsp;</td>
                            <td rowspan="2" style="width: 50%">
                                <img id="img" class="textInput" src="${pageContext.request.contextPath}/checkImage"
                                     width="180" height="30" style="height: 30px;"/>&nbsp;&nbsp;
                                <a href="javascript:void(0);" onclick="changeImage()">看不清，换一张</a>
                            </td>
                        </tr>
                    </table>
                    <!--同意并提交-->
                    <table style="width:70%;border:0;border-collapse:collapse">
                        <tr>
                            <td style="padding-top:10px; text-align:center">
                                <input type="image" onclick="return checkForm(false)"
                                       src="${pageContext.request.contextPath}/client/images/signup.gif" width="140"
                                       height="35"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</div>

<%@ include file="foot.jsp" %>
</body>
</html>
