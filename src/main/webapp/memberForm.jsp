<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 3.
  Time: 오전 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1> 회원 정보 </h1>
<form action="member" method="post">
    <fieldset>
        <legend>
            회원가입
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
                <label>이름</label>
                <input type="text" name="username">
            </li>

            <li>
                <label>이메일</label>
                <input type="text" name="email">
            </li>

            <li>
                <label>주소</label>
                <input type="text" name="address">
            </li>

            <li>
                <input type="submit" value="가입">
            </li>
        </ul>
    </fieldset>

</body>
</html>
