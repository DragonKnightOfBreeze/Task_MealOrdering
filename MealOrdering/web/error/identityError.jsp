<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>权限错误</title>
  <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
  <script>
      import * as utils from "/js/utils";

      $(function() {
          utils.countDown($("#second"), "index.jsp");
      });
  </script>
</head>

<body>
  <jsp:include page="/_head.jsp"/>

  <span>权限不足,请登录后进行操作</span><br/>
  <a href="${pageContext.request.contextPath}/index.jsp">
    <span id="second">5</span>秒后自动为您跳转到首页。
  </a>

  <jsp:include page="/_foot.jsp"/>
</body>
</html>
