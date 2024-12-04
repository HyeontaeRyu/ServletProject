<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 4.
  Time: 오후 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
클라이언트 ip : <%= request.getRemoteAddr() %><br>
요청 정보의 길이 : <%= request.getContentLength() %><br>
요청 정보의 인코딩 : <%= request.getCharacterEncoding() %><br>
요청 정보의 타입 : <%= request.getContentType() %><br>
요청 정보의 프로토콜 : <%= request.getProtocol() %><br>
요청 정보의 방식 : <%= request.getMethod() %><br>
요청 url : <%= request.getRequestURL() %><br>
요청에 대한 uri : <%= request.getRequestURI() %><br>
Context 경로 : <%= request.getContextPath() %><br>
서버 이름 : <%= request.getServerName() %><br>
서버 포트 : <%= request.getServerPort() %><br>
</body>
</html>
