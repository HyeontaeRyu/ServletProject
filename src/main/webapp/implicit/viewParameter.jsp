<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 4.
  Time: 오후 2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
무엇을 요청하셧습니까?
<b>getParameter() 메소드</b><br>
name 파라미터 출력 : <%= request.getParameter("name") %><br>
address 파라미터 출력 : <%= request.getParameter("address") %><br>

getParameterNames() 메소드<br>
<%
    String[] values = request.getParameterValues("pet");

    if (values != null) {
        for (int i = 0; i < values.length; i++) {

%>
pet 파라미터 출력 : <%= values[i] %><br>
<%
        }
    }
%>

<%
    Enumeration enumData = request.getParameterNames();
    while (enumData.hasMoreElements()) {
        String name = (String) enumData.nextElement();
        String[] value = request.getParameterValues(name);
        for (int i = 0; i < value.length; i++) {
%>

<%= name %> 파라미터 출력 : <%= value[i] %><br>

<%
        }
    }
%>

<b>request.getParameterMap 사용</b><br>
<%
    Map parameterMap = request.getParameterMap();
    for (Object key : parameterMap.keySet()) {
        String[] value = (String[]) parameterMap.get(key);
        for (int i = 0; i < value.length; i++) {
%>
<%= key %> 파라미터 출력 : <%= value[i] %><br>
<%
        }
    }
%>


</body>
</html>
