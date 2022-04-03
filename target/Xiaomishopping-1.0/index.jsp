<%--
  Created by IntelliJ IDEA.
  User: 17824
  Date: 2022/3/10
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: 17824
  Date: 2022/3/23
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String base = request.getContextPath() + "/";
  String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + base;
%>
<html>
<head>
  <title>跳转页面</title>
</head>
<body>
<script>
  <%--document.location.href = "${pageContext.request.contextPath}/admin/login";--%>
</script>
</body>
</html>
