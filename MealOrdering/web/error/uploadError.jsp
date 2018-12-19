<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>文件上传错误</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <script>
      $("#_btn-back").on("click", function() {
          history.go(-1);
      });
  </script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/mealordering/_head.jsp"/>

  <div class="container text-center" id="_error-info">
    <span>上传文件时发生了错误</span>
    <button class="btn btn-link" id="_btn-back">返回</button>
  </div>

  <jsp:include page="${pageContext.request.contextPath}/mealordering/_foot.jsp"/>
</body>
</html>
