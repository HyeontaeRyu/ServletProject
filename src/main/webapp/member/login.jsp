<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 6.
  Time: 오후 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
<form action="#" method="post">
    <table width="300" border="1">
        <tr>
            <td colspan="2" align="center">회원 로그인</td>
        </tr>
        <tr>
            <td width="100" align="right"> 아이디</td>
            <td width="200">
                &nbsp;&nbsp;<input type="text" name="id" size="20">
            </td>
        </tr>
        <tr>
            <td width="100" align="right"> 비밀번호</td>
            <td width="200">
                &nbsp;&nbsp;<input type="password" name="pass" size="20">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="로그인">&nbsp;&nbsp;
                <input type="button" value="회원가입" onclick="window.location='regForm.jsp'">&nbsp;&nbsp;
            </td>
        </tr>

    </table>
</form>

</body>
</html>
