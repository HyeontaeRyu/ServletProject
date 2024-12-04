<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 4.
  Time: 오후 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="/error/error.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
name 파라미터 값 :
<%= request.getParameter("name").toUpperCase() %>

</body>
</html>
