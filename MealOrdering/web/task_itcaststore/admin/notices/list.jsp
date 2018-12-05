<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="notice" scope="request" type="task_itcaststore.domain.Notice"/>
<jsp:useBean id="noticeList" scope="request" type="java.util.List<task_itcaststore.domain.Notice>"/>

<html>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/admin/js/public.js"></script>
    <script>
        /**
         * 添加公告。
         */
        function addNotice() {
            window.location.href = "add.jsp";
        }
    </script>
</head>

<body>
<br/>
<form id="form1" name="form1" action="" method="post">
    <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
        <tbody>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>公告列表</strong>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="right">
                <button type="button" id="add" name="add" value="&#28155;&#21152;" class="button_add"
                        onclick="addNotice()">
                    &#28155;&#21152;
                </button>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="WIDTH: 100%; WORD-BREAK: break-all; border: 1px solid gray;BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr
                            style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                        <td align="center" width="12%">公告编号</td>
                        <td align="center" width="12%">公告标题</td>
                        <td align="center" width="24%">公告内容</td>
                        <td align="center" width="8%">时间</td>
                        <td width="8%" align="center">编辑</td>
                        <td width="8%" align="center">删除</td>
                    </tr>
                    <c:forEach var="notice" items="${noticeList}">
                        <tr onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="23">${notice.id }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="18%">${notice.title }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${notice.details }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${notice.time }</td>
                            <td align="center" style="HEIGHT: 22px" width="7%">
                                <a href="${pageContext.request.contextPath}/manager/findNoticeById?id=${notice.id}">
                                    <img src="${pageContext.request.contextPath}/admin/images/i_edit.gif" border="0"
                                         style="CURSOR: hand">
                                </a>
                            </td>
                            <td align="center" style="HEIGHT: 22px" width="7%">
                                <a href="${pageContext.request.contextPath}/manager/delNotice?id=${notice.id}">
                                    <img src="${pageContext.request.contextPath}/admin/images/i_del.gif" width="16"
                                         height="16" border="0" style="CURSOR: hand">
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
