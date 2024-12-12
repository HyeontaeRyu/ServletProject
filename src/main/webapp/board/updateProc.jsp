<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 11.
  Time: 오후 12:50
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

    String pageNum = request.getParameter("pageNum");
    BoardDAO dbpro = BoardDAO.getInstance();

    int check = dbpro.updateArticle(article);

    if (check == 1) {
%>
<meta http-equiv="refresh" content="0;url=list.jsp?pageNum=<%=pageNum%>">

<%} else {// 비밀번호 오류 %>
<script type="text/javascript">
  alert("비밀번호가 맞지 않습니다");

  history.go(-1);

</script>
<%} %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

</body>
</html>