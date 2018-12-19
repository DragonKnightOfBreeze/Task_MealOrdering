<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<script src="_js/head.js"></script>
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
    <!--INFO 导航栏下拉菜单-->
    <!--只在小屏幕上显示-->
    <button class="navbar-toggler" data-toggle="collapse" data-target="#_navbar-menu">
      <span class="navbar-toggler-icon"></span>
    </button>
    <!--INFO 可折叠式导航栏-->
    <div class="collapse navbar-collapse" id="_navbar-menu">
      <!--INFO 导航条菜单-->
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" href="index.jsp">主页</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="meal/mealList.jsp">菜单</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="notice/noticeList.jsp">公告</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#_modal-login-admin" data-toggle="modal">管理</a>
        </li>
      </ul>
    </div>

    <!--INFO 导航栏菜单：注册和登录，右对齐-->
    <ul id="_visitor-menu" class="navbar-nav navbar-right">
      <li class="nav-item active">
        <a class="nav-link" id="_btn-login" href="#_modal-login-reg" data-toggle="modal">
          <span class="glyphicon glyphicon-log-in"></span> 登录
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" id="_btn-register" href="#_modal-login-reg" data-toggle="modal">
          <span class="glyphicon glyphicon-registration-mark"></span> 注册
        </a>
      </li>
    </ul>
    <!--INFO 导航栏菜单：我的账户和登出，右对齐-->
    <ul id="_user-menu" class="navbar-nav navbar-right" style="display:none">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
          <span class="glyphicon glyphicon-user"></span> 我的账户
        </a>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/mealordering/account/index.jsp">
            我的资料
          </a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/mealordering/account/_myCart.jsp">
            我的购物车
          </a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/mealordering/account/_myOrders.jsp">
            我的订单
          </a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/mealordering/settings/information.jsp">
            修改资料
          </a>
        </div>
      </li>
      <li class="nav-item">
        <a id="_btn-logout" class="nav-link" href="${pageContext.request.contextPath}/mealordering/logout">
          <span class="glyphicon glyphicon-log-out"></span> 注销
        </a>
      </li>
    </ul>
  </div>
</nav>

<!--INFO 管理员登录模态框 -->
<!--被模态框标题引用，默认隐藏-->
<div class="modal fade in" id="_modal-login-admin" tabindex="-1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title text-center" id="_mt-login-admin">管理员登录</h4>
        <!--这是模态框的关闭按钮-->
        <button class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <!--INFO 管理员登录表单-->
        <!--NOTE 参数：userName,password-->
        <form action="index.jsp" method="post" id="_form-login-admin">
          <!--这是验证未通过时弹出的警告-->
          <div class="alert alert-warning collapse" id="_alert-login-admin">
            <span class="fa fa-warning fa-fw"></span><span id="_msg-login-admin"></span>
            <button class="close" data-dismiss="alert">&times;</button>
          </div>
          <!--这是表单项-->
          <div class="form-row">
            <label class="col-sm-3" for="_user-name-admin">用户名</label>
            <div class="col-sm-9">
              <div class="input-group-prepend">
                <span class="input-group-text"><span class="fa fa-user fa-fw"></span></span>
              </div>
              <input type="text" class="form-control" id="_user-name-admin" name="userName"
                     placeholder="请输入名字" required>
            </div>
          </div>
          <div class="form-row">
            <label class="col-sm-3" for="_password-admin">密码</label>
            <div class="col-sm-9">
              <div class="input-group-prepend">
                <span class="input-group-text"><span class="fa fa-key fa-fw"></span></span>
              </div>
              <input type="password" class="form-control" id="_password-admin" name="password"
                     placeholder="请输入密码" required>
            </div>
          </div>
          <input type="hidden" name="identity" value="管理员"/>
          <div class="form-row">
            <button type="submit" class="btn btn-primary mx-auto">登录</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<!--INFO 登录与注册模态框 -->
<!--被模态框标题引用，默认隐藏-->
<div class="modal fade in" id="_modal-login-reg">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title text-center" id="_mt-login-reg">登录 & 注册</h4>
        <!--这是模态框的关闭按钮-->
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <!--INFO 登录与注册页面-->
        <ul class="nav nav-pills nav-fill">
          <li><a href="#_page-login" data-toggle="tab">登录</a></li>
          <li><a href="#_page-register" data-toggle="tab">注册</a></li>
        </ul>

        <div class="tab-content">
          <div class="tab-pane active" id="_page-login">
            <!--INFO 登录表单-->
            <!--NOTE 参数：userName,password-->
            <form action="index.jsp" method="post" id="_form-login">
              <!--这是验证未通过时弹出的警告-->
              <div class="alert alert-warning collapse" id="_alert-login">
                <span class="fa fa-warning fa-fw"></span><span id="_msg-login"></span>
                <button class="close" data-dismiss="alert">&times;</button>
              </div>
              <!--这是表单项-->
              <div class="form-row">
                <label class="col-sm-3" for="_user-name">用户名</label>
                <div class="col-sm-9">
                  <div class="input-group-prepend">
                    <span class="input-group-text"><span class="fa fa-user fa-fw"></span></span>
                  </div>
                  <input type="text" class="form-control" id="_user-name" name="userName"
                         placeholder="请输入用户名" required>
                </div>
              </div>
              <div class="form-row">
                <label class="col-sm-3" for="_password">密码</label>
                <div class="col-sm-9">
                  <div class="input-group-prepend">
                    <span class="input-group-text"><span class="fa fa-user fa-key"></span></span>
                  </div>
                  <input type="password" class="form-control" id="_password" name="password"
                         placeholder="请输入密码" required>
                </div>
              </div>
              <div class="form-check-inline">
                <label class="form-check-label col-sm-6">
                  <input class="form-check-input" type="checkbox" name="rememberLogin">记住登录状态
                </label>
                <div class="col-sm-6">
                  <a class="btn btn-link float-right" id="_btn_forgot-psw" href="resetPassword.jsp">我忘记了密码</a>
                </div>
              </div>
              <input type="hidden" name="identity" value="管理员"/>
              <div class="form-row">
                <button type="submit" class="btn btn-primary mx-auto">登录</button>
              </div>
            </form>
          </div>

          <div class="tab-pane" id="_page-register">
            <!--INFO 注册表单-->
            <!--NOTE 参数：userName,password,imgUrl,gender,email,phoneNum,introduce-->
            <!--TODO 可以上传用户头像，使用smartUpload接受-->
            <form role="form" action="index.jsp" method="post" id="_form-register"
                  enctype="multipart/form-data">
              <!--这是验证未通过时弹出的警告-->
              <div class="alert alert-warning collapse" id="_alert-register">
                <span class="fa fa-warning fa-fw"></span>
                <span id="_msg-register"></span>
                <button class="close" data-dismiss="alert">&times;</button>
              </div>
              <div class="form-row">
                <label class="col-sm-3" for="_user-name-reg">用户名</label>
                <div class="col-sm-9">
                  <div class="input-group-prepend">
                    <span class="input-group-text"><span class="fa fa-user fa-fw"></span></span>
                  </div>
                  <input type="text" class="form-control" id="_user-name-reg" name="userName"
                         placeholder="请输入用户名" required>
                </div>
              </div>
              <div class="form-row">
                <label class="col-sm-3" for="_password-reg">密码</label>
                <div class="col-sm-9">
                  <div class="input-group-prepend">
                    <span class="input-group-text"><span class="fa fa-user fa-fw"></span></span>
                  </div>
                  <input type="password" class="form-control" id="_password-reg" name="password"
                         placeholder="请输入密码" required>
                </div>
              </div>
              <div class="form-row">
                <label class="col-sm-3" for="_re-password-reg">确认密码</label>
                <div class="col-sm-9">
                  <div class="input-group-prepend">
                    <span class="input-group-text"><span class="fa fa-user fa-fw"></span></span>
                  </div>
                  <input type="password" class="form-control" id="_re-password-reg"
                         placeholder="请再次确认密码" required>
                </div>
              </div>
              <div class="form-row">
                <div class="col-sm-6 offset-3">
                  <input type="file" class="form-control-file" id="_img-url" name="imgUrl"
                         placeholder="请选择你的头像" required>
                </div>
                <label class="col-sm-3" for="_img-url">选择头像</label>
              </div>
              <div class="form-check-inline">
                <label class="form-check-label">
                  <input class="form-check-input" type="checkbox" name="gender" value="男性" checked>男性
                </label>
              </div>
              <div class="form-check-inline">
                <label class="form-check-label">
                  <input class="form-check-input" type="checkbox" name="gender" value="女性">女性
                </label>
              </div>
              <div class="form-row">
                <label class="col-sm-3" for="_email-reg">邮箱地址</label>
                <div class="col-sm-9">
                  <div class="input-group-prepend">
                    <span class="input-group-text"><span class="fa fa-envelope fa-fw"></span></span>
                  </div>
                  <input type="email" class="form-control" id="_email-reg" name="email"
                         placeholder="请输入你的邮箱地址" required>
                </div>
              </div>
              <div class="form-row">
                <label class="col-sm-3" for="_phone-num-reg">手机号码</label>
                <div class="col-sm-9">
                  <div class="input-group-prepend">
                    <span class="input-group-text"><span class="fa fa-phone fa-fw"></span></span>
                  </div>
                  <input type="number" class="form-control" id="_phone-num-reg" name="phoneNum"
                         minlength="11" maxlength="11" placeholder="请输入你的手机号码" required>
                </div>
              </div>
              <div class="form-row">
                <label class="col-sm-3" for="_introduce-reg">个人介绍</label>
                <div class="col-sm-9">
                  <div class="input-group-prepend">
                    <span class="input-group-text"><span class="fa fa-heart fa-fw"></span></span>
                  </div>
                  <textarea class="form-control" id="_introduce-reg" name="introduce" cols="4">
                      这个人很懒，什么也没留下...
                    </textarea>
                </div>
              </div>
              <div class="form-row">
                <label class="form-check-label col-sm-6">
                  <input class="form-check-input" checked type="checkbox" name="agreeRule">我已阅读并同意XXX
                </label>
              </div>
              <div class="form-row">
                <button type="submit" class="btn btn-primary mx-auto">注册</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


