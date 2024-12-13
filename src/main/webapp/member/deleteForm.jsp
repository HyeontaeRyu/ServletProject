<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원탈퇴</title>
    <script type="text/javascript" src="script.js"></script>
</head>
<body onload="begin()">
<form action="deleteProc.jsp" method="post" name="myForm" onsubmit="return checkIt()">

    <table width="260" border="1" align="center">
        <tr>
            <td colspan="2" align="center">
                <b>회원탈퇴</b>
            </td>
        </tr>

        <tr>
            <td width="150"><b>비밀번호 입력</b></td>
            <td width="110">
                <input type="password" name="pass" size="15">
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="회원탈퇴">
                <input type="button" value="취소"
                       onclick="javascript:window.location='login.jsp'">
            </td>
        </tr>

    </table>


        <%
//페이지 블럭

if(count > 0) {

	int pageBlock = 5;

	int imsi = count % pageSize == 0 ? 0 : 1;

	int pageCount = count/pageSize + imsi;

	//시작 페이지
	int startPage = (int)((currentPage -1) / pageBlock) * pageBlock + 1;
	//마지막 페이지
	int endPage = startPage + pageBlock -1;
	//마지막으로 보여줄 페이지
	if (endPage > pageCount) {
		endPage = pageCount;
	}

	//페이지 블럭을 이전과 다음으로 처리함

	if(startPage > pageBlock) {
		//검색일 경우와 검색이 아닐경우 페이징 처리
		if(searchText == null){
	%>
    <a href="list.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
        <%}else{ %>
    <a href="list.jsp?pageNum=<%=startPage-pageBlock%>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[이전]</a>

        <%
		}//end else
	}
	for(int i = startPage; i <= endPage; i++)
	{
		//검색일 경우와 검색이 아닐경우 페이징 처리
		if(searchText == null){
	%>
    <a href="list.jsp?pageNum=<%=i%>">[<%=i %>]</a>
        <%}else{%>
    <a href="list.jsp?pageNum=<%=i%>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[<%=i %>
        ]</a>
        <%		}//end else
	}// end for
	if(endPage < pageCount) {
		//검색일 경우와 검색이 아닐경우 페이징 처리
		if(searchText == null){ //검색이 아닐경우
	%>
    <a href="list.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>
        <%}else{ //검색일 경우 %>
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


    </form>
</body>
</html>