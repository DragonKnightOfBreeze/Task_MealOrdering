<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="pageBean" scope="request" type="task_itcaststore.domain.PageBean"/>

<html>
<head>
    <title>商品列表</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}client//css/main.css"/>
</head>

<body class="main">
<jsp:include page="head.jsp"/>
<jsp:include page="menuSearch.jsp"/>

<div id="div-page-content">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <td>
                <div style="text-align:right; margin:5px 10px 5px 0">
                    <a href="${pageContext.request.contextPath}/pageIndex.jsp">首页</a>&emsp;&gt;&emsp;全部商品&emsp;&gt;&emsp;图书列表
                </div>
                <table cellspacing="0" class="list-content">
                    <tr>
                        <td>
                            <h1>商品目录</h1>
                            <hr/>
                            <h1>全部商品</h1>&emsp;共${pageBean.totalCount}种商品
                            <hr/>
                            <div style="margin-top:20px; margin-bottom:5px">
                                <img src="${pageContext.request.contextPath}/client/css/main.css" width="100%"
                                     height="38"/>
                            </div>

                            <table cellspacing="0" class="book-list">
                                <tr>
                                    <c:forEach var="product" items="${pageBean.productList}" varStatus="vs">
                                        <td>
                                            <div class="div-book-pic">
                                                <p>
                                                    <a href="${pageContext.request.contextPath}/findProductById?id=${product.id}"><img
                                                            src="${pageContext.request.contextPath}${product.imgUrl}"
                                                            width="115" height="129" border="0"/> </a>
                                                </p>
                                            </div>
                                            <div class="div-list-title">
                                                <a href="${pageContext.request.contextPath}/findProductById?id=${product.id}">书名： ${product.name}<br/>售价：￥${product.price}
                                                </a>
                                            </div>
                                        </td>
                                        <%-- <c:if test="${vs.count%4==0}">
                                        </c:if> --%>
                                    </c:forEach>
                                </tr>
                            </table>

                            <div class="pagination">
                                <ul>
                                    <c:if test="${pageBean.pageIndex!=1}">
                                        <li class="nextPage">
                                            <a href="${pageContext.request.contextPath}/menuSearch?pageIndex=${pageBean.pageIndex-1}&textfield=${pageBean.searchField}">&lt;&lt;上一页</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${pageBean.pageIndex==1}">
                                        <li class="disablepage">&lt;&lt;上一页</li>
                                    </c:if>
                                    <c:forEach begin="1" end="${pageBean.pageCount}" var="pageNum">

                                        <c:if test="${pageNum==pageBean.pageIndex}">
                                            <li class="current-page">${pageNum }</li>
                                        </c:if>
                                        <c:if test="${pageNum!=pageBean.pageIndex}">
                                            <li>
                                                <a href="${pageContext.request.contextPath}/menuSearch?pageIndex=${pageNum}&textfield=${pageBean.searchField}">${pageNum}</a>
                                            </li>
                                        </c:if>

                                    </c:forEach>

                                    <c:if test="${pageBean.pageIndex==pageBean.pageCount||pageBean.pageCount==0}">
                                        <li class="disablepage">下一页 &gt;&gt;</li>
                                    </c:if>

                                    <c:if test="${pageBean.pageIndex!=pageBean.pageCount&&pageBean.pageCount!=0}">
                                        <li class="next-page">
                                            <a href="${pageContext.request.contextPath}/menuSearch?pageIndex=${pageBean.pageIndex+1}&textfield=${pageBean.searchField}">下一页&gt;&gt;</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
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
