<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>未登陆错误</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <script>
      import * as utils from "../_js/utils";

      $(function() {
          utils.countDown($("#_second"), "index.html");
      });
  </script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/mealordering/_head.jsp"/>

  <div class="container text-center" id="_error-info">
    <span>未登陆,请登录后进行操作</span><br/>
    <a class="btn btn-link" href="${pageContext.request.contextPath}/mealordering/index.html">
      <span id="_second">5</span>秒后自动为您跳转到首页。
    </a>
  </div>

  <jsp:include page="${pageContext.request.contextPath}/mealordering/_foot.jsp"/>
</body>
</html>
