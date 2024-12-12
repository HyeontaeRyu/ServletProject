<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 10.
  Time: 오후 4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.board.BoardDAO" %>
<%@page import="com.board.BoardDTO" %>
<%@page import="java.util.*" %>

<%@page import="java.text.SimpleDateFormat" %>
<%@include file="view/color.jsp" %>

<%
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%
    int count = 0;
    int number = 0;
    List<BoardDTO> articleList = null;
    BoardDAO dbPro = BoardDAO.getInstance();
    count = dbPro.getArticleCount();
    if (count > 0) {
        articleList = dbPro.getArticles();
    }
    number = count;

%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>">
<div align="center"><b>글 목록(전체 글 : <%=count%>)</b>
    <table width="700">
        <tr align="right" bgcolor="<%=value_c%>">
            <td><a href="writeForm.jsp">글쓰기</a></td>
        </tr>
    </table>
    <%
        if (count == 0) {
    %>
    <table width="700" border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center">등록된 글이 없습니다.</td>
        </tr>
    </table>
    <%
    } else {
    %>
    <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
        <tr height="30" bgcolor="<%=value_c%>">
            <td align="center" width="50">번호</td>
            <td align="center" width="250">제목</td>
            <td align="center" width="100">작성자</td>
            <td align="center" width="150">작성일</td>
            <td align="center" width="50">조회수</td>
            <td align="center" width="100">IP</td>
        </tr>
        <%
            for (int i = 0; i < articleList.size(); i++) {
                BoardDTO article = articleList.get(i);
        %>
        <tr>
            <td align="center" width="50"><%=number--%>
            </td>
            <td width="250"><a
                    href="content.jsp?num=<%=article.getNum()%>&pageNum=1;">
                <%=article.getSubject()%>
            </a>
                <% if (article.getReadCount() >= 20) { %>
                <img src="img/hot.gif" width="20" height="9">
                <% } %>
            </td>
            <td align="center" width="100">
                <a href="mailto:<%=article.getEmail()%>"><%=article.getWriter()%>
                </a>
            </td>
            <td align="center" width="150"><%=sdf.format(article.getRegDate())%>
            </td>
            <td align="center" width="50"><%=article.getReadCount()%>
            </td>
            <td align="center" width="100"><%=article.getIp()%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }
    %>
</div>
</body>
</html>
