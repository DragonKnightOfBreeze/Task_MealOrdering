<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>文件上传错误</title>
  <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
  <script>
      $("#btn_back").on("click", function() {
          history.go(-1);
      });
  </script>
</head>

<body>
  <jsp:include page="/_head.jsp"/>

  <span>上传附件错误</span>
  <button id="_btn_back">onclick="history.go(-1)"返回</button>

  <jsp:include page="/_foot.jsp"/>
</body>
</html>
