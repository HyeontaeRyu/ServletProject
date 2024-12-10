<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 9.
  Time: 오전 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");

%>
<jsp:useBean id="dao" class="com.member.StudentDAO"/>
<jsp:useBean id="dto" class="com.member.StudentDTO"/>
<jsp:setProperty name="dto" property="*"/>

<%
    boolean flag = dao.memberInsert(dto);
%>

<html>
<head>
    <title>회원가입 확인</title>
    <link rel="stylesheet" href="style.css">
</head>
<body bgcolor="#ffffcc">
<div align="center">
    <%
        if (flag) {
            out.println("<b>회원 가입을 축하드립니다.</b><br>");
            out.println("<a href='login.jsp'>로그인</a>");
        } else {
            out.println("<b>회원 가입에 실패하였습니다.</b><br>");
            out.println("<a href='regForm.jsp'>회원가입</a>");
        }
    %>
</div>
</body>
</html>
