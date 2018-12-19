<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <title>网上订餐系统首页</title>
  <!--自定义格式-->
  <link rel="stylesheet" href="_css/index.css">
  <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <script src="_js/index.js"></script>
</head>
<body>
  <jsp:include page="_head.jsp"/>

  <jsp:include page="_backgroundBar.jsp"/>

  <!--INFO 页面主体：导航条，轮播图，最近的公告，本周推荐-->
  <div id="_body" class="container">
    <jsp:include page="meal/_searchBar.jsp"/>

    <!--INFO 首页-->
    <!--INFO 轮播图：广告，具有切换效果-->
    <div class="carousel slide" data-ride="carousel" id="_carousel">
      <!--指示符-->
      <ol class="carousel-indicators">
        <li data-target="#_carousel" data-slide-to="0" class="active"></li>
        <li data-target="#_carousel" data-slide-to="1"></li>
        <li data-target="#_carousel" data-slide-to="2"></li>
      </ol>
      <!--轮播图片-->
      <div class="carousel-inner" role="listbox">
        <div class="carousel-item active">
          <img src="_image/meal_img/food10.jpg"/>
          <div class="carousel-caption">
            <p>Lorem ipsumdolor sit amet</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="_image/meal_img/food11.jpg">
          <div class="carousel-caption">
            <p>Lorem ipsumdolor sit amet</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="_image/meal_img/food12.jpg">
          <div class="carousel-caption">
            <p>Lorem ipsumdolor sit amet</p>
          </div>
        </div>
      </div>
      <!--左右切换按钮-->
      <a class="carousel-control-prev" href="#_carousel" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
      </a>
      <a class="carousel-control-next" href="#_carousel" data-slide="next">
        <span class="carousel-control-next-icon"></span>
      </a>
    </div>
    <div class="row">
      <!--INFO 最近公告-->
      <div class="border col-md-6">
        <h3>最近公告
          <a class="float-right" href="notice/noticeList.jsp"><span class="glyphicon glyphicon-plus"></span></a>
        </h3>
        <hr>
        <article id="_art-latest-notice">
          <h4>公告标题</h4>
          <p>公告内容。</p>
        </article>
      </div>
      <!--INFO 本周推荐-->
      <div class="border col-md-6">
        <h3>本周推荐
          <a class="float-right" href="meal/mealList.jsp"><span class="glyphicon glyphicon-plus"></span></a>
        </h3>
        <hr>
        <div class="media border col-md-3 p-3 _media-week-hot">
          <img src="_image/meal_img/food04.jpg" class="mr-3 mt-3 img-thumbnail" style="width:60px;">
          <div class="media-body">
            <h4>餐品名称
              <small>销售总量：<span></span></small>
            </h4>
            <p>餐品描述</p>
          </div>
        </div>
        <div class="media border col-md-3 p-3 _media-week-hot">
          <img src="_image/meal_img/food05.jpg" class="mr-3 mt-3 img-thumbnail" style="width:60px;">
          <div class="media-body">
            <h4>餐品名称
              <small>销售总量：<span></span></small>
            </h4>
            <p>餐品描述</p>
          </div>
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="_foot.jsp"/>
</body>
</html>
