<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/style.css">
</head>
<body>
<form id="userAction_save_do" name="form1" action="${pageContext.request.contextPath}/manager/addNotice" method="post">
    &nbsp;
    <table cellSpacing="1" cellPadding="5" width="100%" align="center"
           bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
                height="26"><strong>添加公告</strong>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">公告标题：</td>
            <td class="ta_01" bgColor="#ffffff" colSpan="3">
                <input type="text" name="title" class="bg" maxlength="10"/>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">公告内容：</td>
            <td class="ta_01" bgColor="#ffffff" colSpan="3">
                <textarea name="details" cols="30" rows="3" style="WIDTH: 96%"></textarea>
            </td>
        </tr>
        <tr>
            <td align="center" colSpan="4" class="sep1">
                <img src="${pageContext.request.contextPath}/admin/images/shim.gif">
            </td>
        </tr>

        <tr>
            <td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
                <input type="submit" class="button_ok" value="确定"/>
                <span style="font-family:'宋体',sans-serif; ">&emsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="reset" value="重置" class="button_cancel">
                <span style="font-family:'宋体',sans-serif; ">&emsp;&nbsp;&nbsp;&nbsp;</span>
                <input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
                <span id="label1"></span>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
