<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 4.
  Time: 오전 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<% response.setStatus(HttpServletResponse.SC_OK); %>
<html>
<head>
    <title>Title</title>
</head>
<body>
요청 처리 중 오류가 발생했습니다. <br>
에러 타입 : <%=exception.getClass().getName()%> <br>
에러 메시지 : <b><%=exception.getMessage()%>
</b> <br>
</body>
</html>
