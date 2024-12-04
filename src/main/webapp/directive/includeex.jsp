<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 4.
  Time: 오후 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>include 지시어를 활용한 예제</h2>
<%
    String name = "홍길동";
%>
<%@ include file="top.jsp" %>
------------------------
<%@ include file="bottom.jsp" %>

</body>
</html>
