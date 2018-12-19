<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<script>
    //DONE 限制最低价格总是小于最高价格
    $("#_min-num,#_max-num").on("change",
        function(e) {
            let $min = $("#_min-num");
            let $max = $("#_max-num");
            if($min.val() > $max.val()) {
                e.val($max.val());
            }
        }
    );
</script>

<div class="row">
  <!--INFO 页面导航：所有餐品，按照分类-->
  <div class="col-sm-7">
    <ul class="nav nav-tabs" id="_nt-category">
      <li class="nav-item">
        <a class="nav-link active"
           href="${pageContext.request.contextPath}/mealordering/searchMealsByCategory?category=所有餐品">全部餐品</a>
      </li>
      <li class="nav-item">
        <a class="nav-link"
           href="${pageContext.request.contextPath}/mealordering/searchMealsByCategory?category=素食">素食</a>
      </li>
      <li class="nav-item">
        <a class="nav-link"
           href="${pageContext.request.contextPath}/mealordering/searchMealsByCategory?category=荤食">荤食</a>
      </li>
      <li class="nav-item">
        <a class="nav-link"
           href="${pageContext.request.contextPath}/mealordering/searchMealsByCategory?category=甜点">甜点</a>
      </li>
      <li class="nav-item">
        <a class="nav-link"
           href="${pageContext.request.contextPath}/mealordering/searchMealsByCategory?category=黑暗料理">黑暗料理</a>
      </li>
    </ul>
  </div>
  <!--INFO 搜索表单-->
  <div class="col-md-5">
    <form class="form-inline" action="${pageContext.request.contextPath}/mealordering/searchMeals" method="post">
      <div class="form-group">
        <label class="sr-only" for="name">名称</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="请输入餐品名称">
      </div>
      <button type="submit" class="btn btn-default">
        <span class="fa fa-search"></span>
      </button>
      <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="_modal-advance-search">
        <span class="fa fa-star"></span>高级搜索
      </button>
    </form>
  </div>
</div>

<!--INFO 高级搜索模态框 -->
<!--被模态框标题引用，默认隐藏-->
<div class="modal fade in" id="_modal-advance-search">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title text-center" id="_mt-advance-search">高级搜索</h4>
        <!--这是模态框的关闭按钮-->
        <button class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <!--INFO 高级搜索表单-->
        <!--NOTE 参数：name,minPrice,maxPrice,category-->
        <form action="${pageContext.request.contextPath}/mealordering/searchMealsByConditions" method="post"
              id="_form-advance-search">
          <div class="form-row">
            <label class="col-md-3" for="_name2">餐品名称</label>
            <div class="col-md-9">
              <input type="text" class="form-control" id="_name2" name="name"
                     placeholder="请输入餐品名称...">
            </div>
          </div>
          <div class="form-row">
            <label class="col-md-3" for="_min-num">最低价格</label>
            <div class="col-md-3">
              <input type="number" class="form-control" id="_min-num" name="minPrice"
                     min="0" max="10000">
            </div>
            <label class="col-md-3" for="_max-num">最高价格</label>
            <div class="col-md-3">
              <input type="number" class="form-control" id="_max-num" name="maxPrice"
                     min="0" max="10000">
            </div>
          </div>
          <div class="form-row">
            <label class="col-md-3" for="_category">餐品分类</label>
            <div class="col-md-9">
              <select class="form-control" id="_category">
                <option selected>所有餐品</option>
                <option>素食</option>
                <option>荤食</option>
                <option>甜点</option>
                <option>黑暗料理</option>
              </select>
            </div>
          </div>
          <div class="form-row">
            <button type="submit" class="btn btn-primary mx-auto">搜索！！</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
