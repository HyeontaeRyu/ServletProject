<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 11.
  Time: 오후 4:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.board.BoardDAO" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="article" class="com.board.BoardDTO" scope="page">
    <jsp:setProperty name="article" property="*"/>
</jsp:useBean>
<%
    int num = Integer.parseInt(request.getParameter("num"));
    String pageNum = request.getParameter("pageNum");
    String pass = request.getParameter("pass");

    BoardDAO dbPro = BoardDAO.getInstance();

    int check = dbPro.deleteArticle(num, pass);
    if (check == 1) {
%>
<meta http-equiv="refresh" content="0;url=list.jsp?pageNum=<%=pageNum%>">
<%
} else {
%>
<script type="text/javascript">
  alert("비밀번호가 맞지 않습니다");
  history.go(-1);
</script>
<%
    }
%>

<html>
<head>
    <title></title>
</head>
<body>

</body>
</html>
