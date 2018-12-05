<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="product" scope="request" type="task_itcaststore.domain.Product"/>

<html>
<head>
    <link href="${pageContext.request.contextPath}/admin/css/style.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/admin/js/public.js"></script>
    <script src="${pageContext.request.contextPath}/admin/js/check.js"></script>
    <script>
        /**
         * 设置类别的默认值。
         * @param t
         */
        function setProductCategory(t) {
            var category = document.getElementById("category");
            var ops = category.options;
            for (var i = 0; i < ops.length; i++) {
                if (ops[i].value === t) {
                    ops[i].selected = true;
                    return;
                }
            }
        }
    </script>
</head>

<body onload="setProductCategory('${product.category}')">
<form id="userAction_save_do" name="form1"
      action="${pageContext.request.contextPath}/manager/editProduct" method="post"
      enctype="multipart/form-data">
    <input type="hidden" name="id" value="${product.id}"/> &nbsp;
    <table cellSpacing="1" cellPadding="5" width="100%" align="center"
           bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26">
                <strong>编辑商品</strong>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">商品名称：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="name" class="bg" value="${product.name }"/>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">商品价格：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="price" class="bg" value="${product.price }"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">商品数量：</td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="count" class="bg" value="${product.count}"/>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">商品类别：</td>
            <td class="ta_01" bgColor="#ffffff">
                <select name="category" id="category">
                    <option value="">--选择商品类加--</option>
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
            <td class="ta_01" bgColor="#ffffff" colSpan="3">
                <input type="file" name="upload" size="30" value=""/></td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">商品描述：</td>
            <td class="ta_01" bgColor="#ffffff" colSpan="3">
                <textarea name="description" cols="30" rows="3" style="WIDTH: 96%">${product.description}</textarea>
            </td>
        </tr>
        <tr>
            <td align="center" colSpan="4" class="sep1">
                <img src="${pageContext.request.contextPath}/admin/images/shim.gif">
            </td>
        </tr>
        <tr>
            <td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
                <input type="submit" class="button_ok" value="确定">
                <span style="font-family:'宋体',sans-serif; ">&emsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="reset" value="重置" class="button_cancel"/>
                <span style="font-family:'宋体',sans-serif; ">&emsp;&nbsp;&nbsp;&nbsp;</span>
                <input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
                <span id="label1"> </span>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
