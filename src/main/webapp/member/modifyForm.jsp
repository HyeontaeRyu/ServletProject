<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.member.*" %>
<jsp:useBean id="dao" class="com.member.StudentDAO"/>
<%-- <jsp:useBean id="vo" class="com.member.StudentVO" /> --%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
    <link type="text/css" rel="stylesheet" href="style.css">
    <script type="text/javascript" src="script.js"></script>
    <%
        String loginID = (String) session.getAttribute("loginID");
        StudentDTO vo = dao.getMember(loginID);
    %>
</head>
<body>
<form action="modifyProc.jsp" name="regForm" method="post">
    <table>
        <tr>
            <td colspan="2" align="center">회원 수정 정보 입력</td>
        </tr>
        <tr>
            <td align="right">아이디</td>
            <td><%=vo.getId() %>
            </td>
        </tr>
        <tr>
            <td align="right">비밀번호</td>
            <td>
                <input type="password" name="pass" value="<%=vo.getPass()%>">
            </td>
        </tr>

        <tr>
            <td align="right">비밀번호 확인</td>
            <td>
                <input type="password" name="repass" value="<%=vo.getPass()%>">
            </td>
        </tr>

        <tr>
            <td align="right">이름</td>
            <td><%=vo.getName() %>
            </td>
        </tr>

        <tr>
            <td align="right">전화번호</td>
            <td>
                <input type="text" name="phone1" size="4" value="<%=vo.getPhone1()%>">-
                <input type="text" name="phone2" size="5" value="<%=vo.getPhone2()%>">-
                <input type="text" name="phone3" size="5" value="<%=vo.getPhone3()%>">
            </td>
        </tr>

        <tr>
            <td align="right">이메일</td>
            <td>
                <input type="text" name="email" value="<%=vo.getEmail()%>">
            </td>
        </tr>

        <tr>
            <td align="right">우편번호</td>
            <td>
                <input type="text" name="zipcode" value="<%=vo.getZipcode()%>">
                <input type="button" value="찾기" onclick="zipCheck()">
            </td>
        </tr>

        <tr>
            <td align="right">주소</td>
            <td>
                <input type="text" name="address1" size="80" value="<%=vo.getAddress1()%>">
            </td>
        </tr>

        <tr>
            <td align="right">상세 주소</td>
            <td>
                <input type="text" name="address2" size="50" value="<%=vo.getAddress2()%>">
            </td>
        </tr>

        <tr>
            <td align="center" colspan="2">
                <input type="button" value="정보수정" onclick="updateCheck()">&nbsp;&nbsp;
                <input type="button" value="취소" onclick="javascript:window.location='login.jsp'">
            </td>
        </tr>

    </table>
</form>
</body>
</html>