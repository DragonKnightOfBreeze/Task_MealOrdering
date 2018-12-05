<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="notice" scope="request" type="task_itcaststore.domain.Notice"/>
<jsp:useBean id="productList" scope="request" type="java.util.List<task_itcaststore.domain.Notice>"/>

<html>
<head>
    <title>传智书城首页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/autoplay.css"/>
    <script src="${pageContext.request.contextPath}/client/js/autoplay.js"></script>
</head>

<body class="main">
<jsp:include page="head.jsp"/>
<jsp:include page="menuSearch.jsp"/>

<!--广告-->
<div id="div-ad">
    <img src="${pageContext.request.contextPath}/client/ad/index_ad.jpg"/>
</div>

<!-- 首页轮播图 开始-->
<div id="box-autoplay">
    <div class="list">
        <ul>
            <li><img src="${pageContext.request.contextPath}/client/ad/index_ad1.jpg" width="900" height="335"/>
            </li>
            <li><img src="${pageContext.request.contextPath}/client/ad/index_ad2.jpg" width="900" height="335"/>
            </li>
            <li><img src="${pageContext.request.contextPath}/client/ad/index_ad3.jpg" width="900" height="335"/>
            </li>
            <li><img src="${pageContext.request.contextPath}/client/ad/index_ad4.jpg" width="900" height="335"/>
            </li>
            <li><img src="${pageContext.request.contextPath}/client/ad/index_ad5.jpg" width="900" height="335"/>
            </li>
        </ul>
    </div>
</div>
<!-- 首页轮播图 结束-->
<div id="div-content">
    <table style="width:900px;border-collapse:collapse">
        <tr>
            <!--公告板-->
            <td style="width:497px">
                <img src="${pageContext.request.contextPath}/client/images/billboard.gif" width="497" height="38"
                     title="公告板"/>
                <table cellspacing="0" class="ctl">
                    <tr>
                        <td width="485" height="100%">${notice.details}</td>
                    </tr>
                </table>
            </td>
            <!--本周热卖-->
            <td style="padding:5px 15px 10px 40px">
                <table style="width:100%;border-collapse:collapse">
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/client/images/hotTitle.gif" width="126"
                                 height="29" title="本周热卖"/>
                        </td>
                    </tr>
                </table>
                <table style="width:100%;border-collapse:collapse">
                    <tr>
                        <c:forEach var="product" items="${productList}">
                            <td style="width:80px; text-align:center">
                                <a href="${pageContext.request.contextPath}/findProductById?id=${product[0]}">
                                    <img src="${pageContext.request.contextPath}${product[2]}" width="102"
                                         height="130"/>
                                </a>
                                <br/>
                                <a href="${pageContext.request.contextPath}/findProductById?id=${product[0]}">
                                        ${product[1]}
                                </a>
                                <br/>
                                作者:${product[2] }
                            </td>
                        </c:forEach>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<%@ include file="foot.jsp" %>
</body>
</html>
