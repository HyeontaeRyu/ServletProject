<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 11.
  Time: 오후 4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.board.BoardDTO" %>
<%@page import="com.board.BoardDAO" %>
<%@include file="view/color.jsp" %>
<html>
<head>
    <title>삭제</title>
    <link rel="stylesheet" href="style.css" type="text/css">
    <script type="text/javascript" src="script.js"></script>
</head>
<%
    int num = Integer.parseInt(request.getParameter("num"));
    String pageNum = request.getParameter("pageNum");
    try {
        BoardDAO dbPro = BoardDAO.getInstance();
        BoardDTO article = dbPro.getArticle(num);
%>
<body bgcolor="<%=bodyback_c%>">
<div align="center">
    <b>글 삭제</b>
    <form action="deleteProc.jsp?pageNum=<%=pageNum%>" method="post" name="delForm"
          onsubmit="return deleteSave()">
        <table width="360" border="1" cellspacing="0" cellpadding="0" align="center">
            <tr height="30">
                <td align="center" bgcolor="<%=value_c%>">
                    <b>비밀번호를 입력해 주세요.</b>
                </td>
            </tr>
            <tr height="30">
                <td align="center">
                    비밀번호 <input type="password" name="pass" id="" size="8" maxlength="12">
                    <input type="hidden" name="num" value="<%=num%>">
                </td>
            </tr>
            <tr height="30">
                <td align="center">
                    <input type="submit" value="글 삭제">
                    <input type="button" value="글 목록"
                           onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
                </td>
            </tr>
        </table>
    </form>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>
