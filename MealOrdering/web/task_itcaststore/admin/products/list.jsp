<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="product" scope="request" type="task_itcaststore.domain.Product"/>
<jsp:useBean id="beanList" scope="request" type="java.util.List<task_itcaststore.domain.Product>"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/style.css"/>
    <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/utils/js/myUtils.js"></script>
    <script src="${pageContext.request.contextPath}/admin/js/public.js"></script>
    <script>
        /**
         * 添加商品。
         */
        function addProduct() {
            window.location.href = "add.jsp";
        }
    </script>
</head>

<body>
<form id="form1" name="form1"
      action="${pageContext.request.contextPath}/manager/findProductsByConditions"
      method="post">
    <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>查询 条 件</strong>
            </td>
        </tr>
        <tr>
            <td>
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tr>
                        <td height="22" align="center" bgColor="#f5fafe" class="ta_01">商品编号</td>
                        <td class="ta_01" bgColor="#ffffff">
                            <input type="text" name="id" size="15" value="" id="form1_id" class="bg"/>
                        </td>
                        <td height="22" align="center" bgColor="#f5fafe" class="ta_01">类别：</td>
                        <td class="ta_01" bgColor="#ffffff">
                            <select name="category" id="category">
                                <option value="" selected="selected">--选择商品类别--</option>
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
                        <td height="22" align="center" bgColor="#f5fafe" class="ta_01">商品名称：</td>
                        <td class="ta_01" bgColor="#ffffff">
                            <input type="text" name="name" size="15" value="" id="form1_userName" class="bg"/>
                        </td>
                        <td height="22" align="center" bgColor="#f5fafe" class="ta_01">价格区间(元)：</td>
                        <td class="ta_01" bgColor="#ffffff">
                            <input type="text" name="minPrice" size="10" value=""/>
                            -
                            <input type="text" name="maxPrice" size="10" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <td width="100" height="22" align="center" bgColor="#f5fafe" class="ta_01"></td>
                        <td class="ta_01" bgColor="#ffffff">
                            <span style="font-family:'宋体',sans-serif; color: red; "> &nbsp;</span>
                        </td>
                        <td align="right" bgColor="#ffffff" class="ta_01">
                            <br/><br/>
                        </td>
                        <td align="right" bgColor="#ffffff" class="ta_01">
                            <button type="submit" id="search" name="search" value="&#26597;&#35810;"
                                    class="button_view">
                                &#26597;&#35810;
                            </button>
                            &emsp;&nbsp;
                            <input type="reset" name="reset" value="&#37325;&#32622;" class="button_view"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3"><strong>商 品 列 表</strong>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="right">
                <button type="button" id="add" name="add" value="&#28155;&#21152;" class="button_add"
                        onclick="addProduct()">
                    &#28155;&#21152;
                </button>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="WIDTH: 100%; WORD-BREAK: break-all; border: 1px solid gray;BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                        <td align="center" width="24%">商品编号</td>
                        <td align="center" width="18%">商品名称</td>
                        <td align="center" width="9%">商品价格</td>
                        <td align="center" width="9%">商品数量</td>
                        <td width="8%" align="center">商品类别</td>
                        <td width="8%" align="center">编辑</td>
                        <td width="8%" align="center">删除</td>
                    </tr>
                    <!--  循环输出所有商品 -->
                    <c:forEach var="p" items="${beanList}">
                        <tr onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="200">${product.id }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">${product.name }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${product.price }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${product.count }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center">${product.category}</td>
                            <td align="center" style="HEIGHT: 22px" width="7%">
                                <a href="${pageContext.request.contextPath}/findProductById?id=${product.id}&type=admin">
                                    <img src="${pageContext.request.contextPath}/admin/images/i_edit.gif" border="0"
                                         style="CURSOR: hand">
                                </a>
                            </td>
                            <td align="center" style="HEIGHT: 22px" width="7%">
                                <a href="${pageContext.request.contextPath}/manager/delProduct?id=${product.id}"
                                   onclick="return p_del()">
                                    <img src="${pageContext.request.contextPath}/admin/images/i_del.gif"
                                         width="16" height="16" border="0" style="CURSOR: hand">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        </TBODY>
    </table>
</form>
</body>
</html>
