<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 4.
  Time: 오후 2:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
폼에 데이터를 입력 한 후 '전송' 버튼을 클릭하세요.
<form action="viewParameter.jsp" method="post">
    이름 : <input type="text" name="name" size="10"><br>
    주소 : <input type="text" name="address" size="30"><br>
    좋아하는 동물 :
    <input type="checkbox" name="pet" value="dog">개
    <input type="checkbox" name="pet" value="cat">고양이
    <input type="checkbox" name="pet" value="pig">돼지<br>
    <input type="submit" value="전송">
</form>
</body>
</html>
