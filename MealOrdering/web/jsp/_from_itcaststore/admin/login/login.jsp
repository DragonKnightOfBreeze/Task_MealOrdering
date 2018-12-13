<%@ page contentType="text/html; charset=UTF-8" %>

<script>
    function init() {
        document.form1.loginName.focus();
    }
</script>

<html>
<head>
    <link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css">
</head>

<body onload="init()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <table width="452" height="290" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td bgcolor="#FFFFFF">
                        <table width="452" height="290" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td height="74">
                                    <img src="${pageContext.request.contextPath}/admin/images/logintitle.gif">
                                </td>
                            </tr>
                            <tr>
                                <td align="center" valign="bottom"
                                    background="${pageContext.request.contextPath}/admin/images/loginBg.gif">
                                    <form id="loginAction_home" name="form1"
                                          action="${pageContext.request.contextPath}/admin/login/home.jsp"
                                          target="_parent" method="post">
                                        <table border="0" align="center" cellpadding="2" cellspacing="0">
                                            <tr align="center">
                                                <td height="30" colspan="2" style="border-bottom: 1px dotted #cccccc">
                                                    <strong style="font-size: 14px;">请登录</strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="30" nowrap>
                                                    <span style="color: #000F60; "><strong>用户名：</strong> </span>
                                                </td>
                                                <td>
                                                    <input type="text" name="loginName" value="" id="logonName"
                                                           class="text" style="width: 160px;"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="30" nowrap>
                                                    <strong><span style="color: #000F60; ">密码： </span> </strong>
                                                </td>
                                                <td>
                                                    <input type="password" name="logonPwd" id="logonPwd" class="text"
                                                           style="width: 160px;"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="30" nowrap colspan="2">
                                                    <strong><span style="color: red; "></span> </strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="30">
                                                </td>
                                                <td>
                                                    <input type="submit" name="submit" value="&#30331;&#24405;"
                                                           class="buttoninput"/>

                                                    <input type="reset" name="reset" value="&#21462;&#28040;"
                                                           class="buttoninput"/>

                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td height="30" align="center">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="23" align="center"></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
