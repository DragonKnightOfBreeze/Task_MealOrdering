<jsp:useBean id="user" scope="session" type="task_itcaststore.domain.User"/>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    /**
     * 登出确认。
     * @returns {boolean}
     */
    function confirm_logout() {
        var msg = "您确定要退出登录吗？";
        return confirm(msg) === true;
    }
</script>

<div id="div-head">
    <table class="head-table">
        <tr>
            <!--网站图标-->
            <td>
                <a href="${pageContext.request.contextPath}/pageIndex.jsp">
                    <img src="${pageContext.request.contextPath}/client/images/logo.png" width="200" height="60"/>
                </a>
            </td>
            <!--相关链接-->
            <td style="text-align:right">
                <img src="${pageContext.request.contextPath}/client/images/cart.gif" width="26" height="23"
                     style="margin-bottom:-4px"/>
                &nbsp;
                <a href="${pageContext.request.contextPath}/client/cart.jsp">购物车</a>
                | <a href="#">帮助中心</a>
                | <a href="${pageContext.request.contextPath}/myAccount">我的帐户</a>

                <!--如果已登录，则显示“新用户注册”，否则显示“用户注册”和“欢迎信息”-->
                <c:choose>
                    <c:when test="${empty user}">
                        <a href="${pageContext.request.contextPath}/client/register.jsp">新用户注册</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/logout" onclick="return confirm_logout()">用户退出</a>
                        <br/><br/><br/>
                        欢迎您：${user.userName}
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</div>
