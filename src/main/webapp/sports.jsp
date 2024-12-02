<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 2.
  Time: 오후 5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>좋아하는 운동</h1>
<form action="sport" method="post">
    <fieldset>
        <legend>
            좋아하는 운동 및 성별
        </legend>
        <ul>
            <li>
                <label>야구</label>
                <input type="checkbox" name="sports" value="야구">
                <label>축구</label>
                <input type="checkbox" name="sports" value="축구">
                <label>농구</label>
                <input type="checkbox" name="sports" value="농구">
            </li>
            <li>
                <label>남자</label>
                <input type="radio" name="gender" value="남자" checked="checked">
                <label>여자</label>
                <input type="radio" name="gender" value="여자">
            </li>
            <li>
                <input type="submit" value="전송">
            </li>
        </ul>
    </fieldset>
</form>
</body>
</html>
