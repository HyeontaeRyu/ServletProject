<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 10.
  Time: 오전 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="view/color.jsp" %>
<%
    int num = 0, ref = 1, step = 0, depth = 0;

    try {
        if (request.getParameter("num") != null) {
            num = Integer.parseInt(request.getParameter("num"));
            ref = Integer.parseInt(request.getParameter("ref"));
            step = Integer.parseInt(request.getParameter("step"));
            depth = Integer.parseInt(request.getParameter("depth"));
        }
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style.css" type="text/css">
    <script src="script.js"></script>
</head>
<body bgcolor="<%=bodyback_c%>">
<div align="center">
    <b>글쓰기</b>
</div>
<br>
<form action="writeProc.jsp" method="post" name="writeForm" onsubmit="return writeSave()">
    <%--글 쓰기 버튼을 눌렀을 때 writeProc.jsp로 페이지를 이동 할 때 num, ref, step, depth의 값을 hidden으로 넘김--%>
    <input type="hidden" name="num" value="<%=num%>">
    <input type="hidden" name="ref" value="<%=ref%>">
    <input type="hidden" name="step" value="<%=step%>">
    <input type="hidden" name="depth" value="<%=depth%>">


    <table width="400" border="1" cellpadding="0" cellspacing="0" align="center"
           bgcolor="<%=bodyback_c%>">
        <tr>
            <td align="right" colspan="2" bgcolor="<%=value_c%>"><a href="list.jsp">글 목록</a></td>
        </tr>

        <tr>
            <td width="70" bgcolor="<%=value_c%>" align="center">이름</td>
            <td width="330">
                <input type="text" size="12" maxlength="12" name="writer">
            </td>
        </tr>
        <tr>
            <td width="70" bgcolor="<%=value_c%>" align="center">이메일</td>
            <td width="330">
                <input type="text" size="40" maxlength="40" name="email">
            </td>
        </tr>
        <tr>
            <td width="70" bgcolor="<%=value_c%>" align="center">제목</td>
            <td width="330">
                <% if (request.getParameter("num") == null) { %>
                <input type="text" size="50" maxlength="50" name="subject">
                <% } else { %>
                <input type="text" size="50" maxlength="50" name="subject" value="[답변]">
                <% } %>
            </td>
        </tr>
        <tr>
            <td width="70" bgcolor="<%=value_c%>" align="center">내용</td>
            <td width="330">
                <textarea name="content" cols="50" rows="15"></textarea>
            </td>
        </tr>
        <tr>
            <td width="70" bgcolor="<%=value_c%>" align="center">비밀번호</td>
            <td width="330">
                <input type="password" size="10" maxlength="10" name="pass">
            </td>
        </tr>

        <tr>
            <td colspan="2" bgcolor="<%=value_c%>" align="center">
                <input type="submit" value="글쓰기">
                <input type="reset" value="다시작성">
                <input type="button" value="글 목록" onclick="window.location='list.jsp'">
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
</body>
</html>
