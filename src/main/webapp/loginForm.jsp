<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 2.
  Time: 오후 4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="login" method="get">
    <fieldset>
        <legend>
            로그인
        </legend>
        <ul>
            <li>
                <label>아이디</label>
                <input type="text" name="userid">
            </li>

            <li>
                <label>비밀번호</label>
                <input type="password" name="userpass">
            </li>

            <li>
                <input type="submit" value="login">
            </li>
        </ul>
    </fieldset>
</form>
</body>
</html>
