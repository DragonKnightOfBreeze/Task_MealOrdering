<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>Title</title>
</head>
<body>
  <jsp:include page="_head.jsp"/>

  <jsp:include page="_backgroundBar.jsp"/>

  <%--<jsp:include page="_sideNav.jsp"/>--%>

  <div class="container" id="_body">
    <!--INFO 侧边栏-->
    <div class="col-sm-3" id="_sideNav">

    </div>
    <!--INFO 列表和信息页面-->
    <div class="col-sm-9" id="_body-content">

    </div>
  </div>

  <jsp:include page="_foot.jsp"/>
</body>
</html>
