<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="product" scope="request" type="task_itcaststore.domain.Product"/>

<html>
<head>
    <title>商品信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/main.css"/>
</head>
<body class="main">
<jsp:include page="head.jsp"/>
<jsp:include page="menuSearch.jsp"/>
<div id="div-page-content">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <td>
                <!--商品基本信息-->
                <div style="text-align:right; margin:5px 10px 5px 0">
                    <a href="${pageContext.request.contextPath}/pageIndex.jsp">首页</a>
                    &emsp;&gt;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/showProductsByPage?category=${product.category}">&nbsp;${product.category}</a>
                    &emsp;&gt;&emsp;${product.name}
                </div>
                <!--商品信息-->
                <table class="info-content">
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/client/ad/page_ad.jpg" width="900"
                                 height="84"/>
                            <table style="width:100%;border-collapse:collapse">
                                <tr>
                                    <td style="width:30%">
                                        <div class="div-bookcover">
                                            <p>
                                                <img src="${pageContext.request.contextPath}${product.imgUrl}"
                                                     width="213" height="269" border="0"/>
                                            </p>
                                        </div>
                                        <div style="text-align:center; margin-top:10px">
                                            <a href="${pageContext.request.contextPath}/addCart?id=${product.id}">
                                                <img src="${pageContext.request.contextPath}/client/images/buyButton.gif"
                                                     width="100" height="25" title="购买该商品"/>
                                            </a>
                                        </div>
                                    </td>
                                    <td style="padding:20px 5px 5px 5px">
                                        <img src="${pageContext.request.contextPath}/client/images/miniIcon3.gif"
                                             width="16" height="16"/>
                                        <span class="book-name">&nbsp;${product.name}</span>
                                        <hr/>
                                        售价：<span style="color: #FF0000; ">￥${product.price}</span>
                                        <hr/>
                                        类别：${product.category }
                                        <hr/>
                                        <p><b>内容简介：</b></p>
                                        ${product.description}
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<jsp:include page="foot.jsp"/>
</body>
</html>
