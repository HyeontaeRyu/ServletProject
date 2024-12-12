<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 11.
  Time: 오전 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.board.BoardDTO" %>
<%@page import="com.board.BoardDAO" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@include file="view/color.jsp" %>
<html>
<head>
    <title>게시판</title>
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<%
    int num = Integer.parseInt(request.getParameter("num"));
    String pageNum = request.getParameter("pageNum");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    try {
        BoardDAO dbPro = BoardDAO.getInstance();
        BoardDTO article = dbPro.getArticle(num);
        int ref = article.getRef();
        int step = article.getStep();
        int depth = article.getDepth();
%>
<body bgcolor="<%=bodyback_c%>">
<div align="center">
    <b>글 상세 보기</b><br>
    <form>
        <table width="500" border="1" cellpadding="0" cellspacing="0" bgcolor="<%=bodyback_c%>"
               align="center">
            <tr height="30">
                <td align="center" width="125" bgcolor="<%=value_c%>">글 번호</td>
                <td align="center" width="125"><%=article.getNum()%>
                </td>
                <td align="center" width="125" bgcolor="<%=value_c%>">조회수</td>
                <td align="center" width="125"><%=article.getReadCount()%>
                </td>
            </tr>
            <tr height="30">
                <td align="center" width="125" bgcolor="<%=value_c%>">작성자</td>
                <td align="center" width="125"><%=article.getWriter()%>
                </td>
                <td align="center" width="125" bgcolor="<%=value_c%>">작성일</td>
                <td align="center" width="125"><%=article.getRegDate()%>
                </td>
            </tr>

            <tr height="30">
                <td align="center" width="125" bgcolor="<%=value_c%>">글 내용</td>
                <td align="center" width="375" colspan="3">
                    <pre><%=article.getContent()%></pre>
                </td>
            </tr>
            <tr height="30">
                <td colspan="4" align="right" bgcolor="<%=value_c%>">
                    <input type="button" value="글수정"
                           onclick="document.location.href='updateForm.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">
                    &nbsp;&nbsp;&nbsp;&nbsp;

                    <input type="button" value="글삭제"
                           onclick="document.location.href='deleteForm.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">
                    &nbsp;&nbsp;&nbsp;&nbsp;

                    <input type="button" value="글목록"
                           onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>


        </table>
        <%
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </form>
</div>

</body>
</html>
