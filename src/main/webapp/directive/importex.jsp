<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 4.
  Time: 오전 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Calendar cal = Calendar.getInstance();

%>
오늘 날짜는 <%= cal.get(Calendar.YEAR) %>년 <%= cal.get(Calendar.MONTH) + 1 %>월 <%= cal.get(
        Calendar.DATE) %>일 입니다.
</body>
</html>
