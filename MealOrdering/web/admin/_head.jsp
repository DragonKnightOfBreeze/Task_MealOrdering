<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<script>
    $("#_btn-logout").on("click", function() {
        return confirm("你确定要注销当前账户吗？");
    })
</script>

<!--INFO 头部，导航栏：网站图标，导航文本，导航栏菜单，登录注册导航表单-->
<!--固定在页面顶部-->
<!-- 小屏幕上水平导航栏会切换为垂直的 -->
<nav class="navbar navbar-expand-sm bg-dark navbar-dark" id="_head">
  <div class="container">
    <!--INFO 导航栏头部-->
    <div class="navbar-header">
      <!--INFO 导航栏网站LOGO-->
      <a class="navbar-brand" href="#">
        <img src="${pageContext.request.contextPath}/mealordering/_image/logo/logo.jpg"/>
      </a>
    </div>
    <!--INFO 导航栏文本-->
    <span class="navbar-text">Text goes here.</span>
    &emsp;&emsp;&emsp;
    <span class="navbar-text">网上订餐系统管理页面</span>

    <!--INFO 导航栏菜单：管理员名字和登出，右对齐-->
    <ul id="_user-menu" class="navbar-nav navbar-right">
      <li class="nav-item">
        <a class="nav-link disabled" href="#">
          <jsp:useBean id="user" scope="session" type="mealordering.domain.User"/>
          <span class="glyphicon glyphicon-user"></span> 管理员 ${user.userName}
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" id="_btn-logout" href="${pageContext.request.contextPath}/mealordering/logout">
          <span class="glyphicon glyphicon-log-out"></span> 注销
        </a>
      </li>
    </ul>
  </div>
</nav>
