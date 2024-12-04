<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 4.
  Time: 오후 3:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Enumeration enumData = request.getHeaderNames();
    while (enumData.hasMoreElements()) {
        String headerName = (String) enumData.nextElement();
        String headerValue = request.getHeader(headerName);
%>
<%= headerName %> : <%= headerValue %><br>

<%
    }
%>
</body>
</html>
