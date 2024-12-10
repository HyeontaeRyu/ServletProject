<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 6.
  Time: 오후 3:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="dao" class="com.member.StudentDAO"/>
<%
    String id = request.getParameter("id");
//    out.prinln(id);
    boolean check = dao.idCheck(id);
%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="style.css">
</head>
<body bgcolor="#ffffcc">
<br>
<div align="center">
    <b><%=id%>
    </b>
    <%
        if (check) {
            out.println("는 이미 존재하는 아이디 입니다.");
        } else {
            out.println("는 사용 가능한 아이디 입니다.");
        }
    %>

    <a href="#" onclick="self.close()">닫기</a>
</div>
</body>
</html>
