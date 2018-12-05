<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/style.css">
</head>

<body>
<form id="userAction_save_do" name="form1"
      action="${pageContext.request.contextPath}/manager/addProduct" method="post"
      enctype="multipart/form-data">
    &nbsp;
    <table cellSpacing="1" cellPadding="5" width="100%" align="center"
           bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
                height="26"><strong><strong>添加商品</strong> </strong>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">商品名称：</td>
            <td class="ta_01" bgColor="#ffffff"><input type="text" name="name" class="bg"/></td>
            <td align="center" bgColor="#f5fafe" class="ta_01">商品价格：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="price" class="bg"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">商品数量：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="count" class="bg"/>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">商品类别：</td>
            <td class="ta_01" bgColor="#ffffff">
                <select name="category" id="category">
                    <option value="" selected="selected">--选择商品类加--</option>
                    <option value="文学">文学</option>
                    <option value="生活">生活</option>
                    <option value="计算机">计算机</option>
                    <option value="外语">外语</option>
                    <option value="经营">经营</option>
                    <option value="励志">励志</option>
                    <option value="社科">社科</option>
                    <option value="学术">学术</option>
                    <option value="少儿">少儿</option>
                    <option value="艺术">艺术</option>
                    <option value="原版">原版</option>
                    <option value="科技">科技</option>
                    <option value="考试">考试</option>
                    <option value="生活百科">生活百科</option>
                </select>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">商品图片：</td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <input type="file" name="upload" size="30" value=""/>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">商品描述：</td>
            <td class="ta_01" bgColor="#ffffff" colSpan="3">
                <textarea name="description" cols="30" rows="3" style="WIDTH: 96%"></textarea>
            </td>
        </tr>
        <tr>
            <td align="center" colSpan="4" class="sep1"><img
                    src="${pageContext.request.contextPath}/admin/images/shim.gif">
            </td>
        </tr>
        <tr>
            <td class="ta_01" style="WIDTH: 100%" align="center"
                bgColor="#f5fafe" colSpan="4">
                <input type="submit" class="button_ok" value="确定">
                <span style="font-family:'宋体',sans-serif; ">&emsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="reset" value="重置" class="button_cancel">
                <span style="font-family:'宋体',sans-serif; ">
						&emsp;&nbsp;&nbsp;&nbsp;
					</span>
                <input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
                <span id="label1"></span>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
