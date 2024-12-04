<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 4.
  Time: 오전 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>JSP Script</h2>
<%-- JSP Scriptlet --%>
<%
    String scriptlet = "JSP Scriptlet";
    String comment = "JSP Comment";
    out.println(declaration);
%>
선언문 출력 : <%= declaration %>
선언문 출력 (메소드) : <%= getDeclaration() %>
스크립트릿 출력 : <%= scriptlet %>
<!--html 주석 : <%= comment %> -->
<%-- JSP 주석 : <%= comment %> --%>

<%
    // Java 주석
    /*
     * Java 주석
     * */
%>
<%! // JSP Declaration
    String declaration = "JSP Declaration"; %>
<%! // JSP Declaration Method

    public String getDeclaration() {
        return declaration;
    } %>

</body>
</html>
