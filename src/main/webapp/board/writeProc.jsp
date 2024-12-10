<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 10.
  Time: 오전 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.board.BoardDAO" %>
<%@page import="java.sql.Timestamp" %>

<%
    request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="article" class="com.board.BoardDTO" scope="page">
    <jsp:setProperty name="article" property="*"/>
</jsp:useBean>

<%
    article.setRegDate(new Timestamp(System.currentTimeMillis()));
    article.setIp(request.getRemoteAddr());
    BoardDAO dbPro = BoardDAO.getInstance();
    dbPro.insertArticle(article);
    response.sendRedirect("list.jsp");
%>