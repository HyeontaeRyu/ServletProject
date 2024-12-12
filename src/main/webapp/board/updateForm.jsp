<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 11.
  Time: 오전 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.board.BoardDTO" %>
<%@page import="com.board.BoardDAO" %>
<%@include file="view/color.jsp" %>
<html>
<head>
    <title>수정</title>
    <link rel="stylesheet" href="style.css" type="text/css"/>
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
    <b>글 수정</b><br>
    <form action="updateProc.jsp?pageNum=<%=pageNum%>" method="post" name="writeForm"
          onsubmit="return writeSave()">
        <table width="430" border="1" cellspacing="0" cellpadding="0" bgcolor="<%=bodyback_c%>"
               align="center">
            <tr>
                <td width="100" bgcolor="<%=value_c%>" align="center">이름</td>
                <td width="330" align="left">
                    <input type="text" size="10" maxlength="10" name="writer"
                           value="<%=article.getWriter()%>">
                    <input type="hidden" name="num" value="<%=article.getNum()%>">
                </td>
            </tr>
            <tr>
                <td width="100" bgcolor="<%=value_c%>" align="center">제목</td>
                <td width="330" align="left">
                    <input type="text" size="40" maxlength="50" name="subject"
                           value="<%=article.getSubject()%>">
                </td>
            </tr>
            <tr>
                <td width="100" bgcolor="<%=value_c%>" align="center">이메일</td>
                <td width="330" align="left">
                    <input type="text" size="40" maxlength="40" name="email"
                           value="<%=article.getEmail()%>">
                </td>
            </tr>
            <tr>
                <td width="100" bgcolor="<%=value_c%>" align="center">내용</td>
                <td width="330" align="left">
                    <textarea rows="15" cols="50"
                              name="content"><%=article.getContent()%></textarea>
                </td>
            </tr>
            <tr>
                <td width="100" bgcolor="<%=value_c%>" align="center">비밀번호</td>
                <td width="330" align="left">
                    <input type="password" size="8" maxlength="12" name="pass">
                </td>
            </tr>
            <tr>
                <td colspan="2" bgcolor="<%=value_c%>" align="center">
                    <input type="submit" value="글 수정">
                    <input type="reset" value="다시 작성">
                    <input type="button" value="글 목록"
                           onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
                    <input type="button" value="글 삭제"
                           onclick="document.location.href='deleteForm.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">
                    <input type="button" value="취소" onclick="javascript:history.back()">
                </td>
            </tr>

        </table>

    </form>
    <%
        } catch
        (
                Exception
                        e
        ) {
        }
    %>
</div>

</body>
</html>
