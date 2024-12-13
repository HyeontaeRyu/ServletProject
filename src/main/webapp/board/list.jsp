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

<%!
    int pageSize = 5; //페이지의 보여줄 레코드 개수를 의미한다.
    SimpleDateFormat sdf =
            new SimpleDateFormat("yyyy-MM-dd HH:mm");

%>
<%
    String pageNum = request.getParameter("pageNum");
    //무엇을 검색할지 받아옴(writer, subject, content)
    String searchWhat = request.getParameter("searchWhat");
    //검색할 내용
    String searchText = request.getParameter("searchText");

    // 파라미터로 받아 온 값을 문자 인코딩 처리함(utf-8)
    if (searchText != null) {
        searchText = new String(searchText.getBytes("utf-8"), "utf-8");
    }

    if (pageNum == null) {
        pageNum = "1";
    }

    //현재 페이지
    int currentPage = Integer.parseInt(pageNum);

    //페이지의 시작행
    int startRow = (currentPage - 1) * pageSize + 1;

    //페이지의 마지막행
    int endRow = currentPage * pageSize;

    int count = 0;
    int number = 0;
    List<BoardDTO> articleList = null;
    BoardDAO dbPro = BoardDAO.getInstance();

    //검색이 아니면 전체 리스트를 보여주고, 검색이면 검색한 내용을 보여줌
    if (searchText == null) {//검색이 아닐 경우

        count = dbPro.getArticleCount();

        if (count > 0) {
            articleList = dbPro.getArticles(startRow, endRow);
        }
    } else {//검색일 경우
        count = dbPro.getArticleCount(searchWhat, searchText);

        if (count > 0) {
            articleList = dbPro.getArticles(searchWhat, searchText, startRow, endRow);
        }
    }

    number = count - (currentPage - 1) * pageSize;
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

    <%
        //페이지 블럭

        if (count > 0) {

            int pageBlock = 5;

            int imsi = count % pageSize == 0 ? 0 : 1;

            int pageCount = count / pageSize + imsi;

            //시작 페이지
            int startPage = (int) ((currentPage - 1) / pageBlock) * pageBlock + 1;
            //마지막 페이지
            int endPage = startPage + pageBlock - 1;
            //마지막으로 보여줄 페이지
            if (endPage > pageCount) {
                endPage = pageCount;
            }

            //페이지 블럭을 이전과 다음으로 처리함

            if (startPage > pageBlock) {
                //검색일 경우와 검색이 아닐경우 페이징 처리
                if (searchText == null) {
    %>
    <a href="list.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
    <%} else { %>
    <a href="list.jsp?pageNum=<%=startPage-pageBlock%>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[이전]</a>

    <%
            }//end else
        }
        for (int i = startPage; i <= endPage; i++) {
            //검색일 경우와 검색이 아닐경우 페이징 처리
            if (searchText == null) {
    %>
    <a href="list.jsp?pageNum=<%=i%>">[<%=i %>]</a>
    <%} else {%>
    <a href="list.jsp?pageNum=<%=i%>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[<%=i %>
        ]</a>
    <% }//end else
    }// end for
        if (endPage < pageCount) {
            //검색일 경우와 검색이 아닐경우 페이징 처리
            if (searchText == null) { //검색이 아닐경우
    %>
    <a href="list.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>
    <%} else { //검색일 경우 %>
    <a href="list.jsp?pageNum=<%=startPage+pageBlock%>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[다음]</a>
    <%
                }//end else
            }//end if
        }
    %>

    <!-- 검색 창 -->
    <form action="list.jsp">
        <select name="searchWhat">
            <option value="writer">작성자</option>
            <option value="subject">제목</option>
            <option value="content">내용</option>
        </select>
        <input type="text" name="searchText">
        <input type="submit" value="검색">


</div>
</body>
</html>
