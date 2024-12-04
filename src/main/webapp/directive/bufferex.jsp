<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 4.
  Time: 오전 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page buffer="1kb" autoFlush="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
4kb 버퍼 발생
<%
    for (int i = 0; i < 1024; i++) {
        out.print("1");
    }
%>

</body>
</html>
