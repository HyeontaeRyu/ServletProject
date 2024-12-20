<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.*, jdbc.*" %>

<%
    ServletContext context = application;
    jdbc.tempMemberDAO dao = new jdbc.tempMemberDAO(context);
    session.setAttribute("dao", dao);
%>

<html>
<head>
    <title>JSP에서 데이터베이스 연동</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFCC">
<h2>Connection Pool 과 Beans를 사용한 DataBase 연동 예제</h2>
<br></br>
<h3>회원정보</h3>
<table bordercolor="#0000ff" border="1">
    <tr>
        <td><strong>ID</strong></td>
        <td><strong>PASSWD</strong></td>
        <td><strong>NAME</strong></td>
        <td><strong>MEM_NUM1</strong></td>
        <td><strong>MEM_NUM2</strong></td>
        <td><strong>E_MAIL</strong></td>
        <td><strong>PHONE</strong></td>
        <td><strong>ZIPCODE/ADDRESS</strong></td>
        <td><strong>JOB</strong></td>
    </tr>
    <%
        ArrayList<tempMemberVO> vlist = dao.getMemberList();
        int counter = vlist.size();
        for (int i = 0; i < vlist.size(); i++) {
            tempMemberVO vo = vlist.get(i);
    %>
    <tr>
        <td><%=vo.getId()%>
        </td>
        <td><%=vo.getPasswd()%>
        </td>
        <td><%=vo.getName()%>
        </td>
        <td><%=vo.getMem_num1()%>
        </td>
        <td><%=vo.getMem_num2()%>
        </td>
        <td><%=vo.getEmail()%>
        </td>
        <td><%=vo.getPhone()%>
        </td>
        <td><%=vo.getZipcode()%>/<%=vo.getAddress()%>
        </td>
        <td><%=vo.getJob()%>
        </td>
        <%
            }
        %>
    </tr>
</table>
<br></br> total records :
<%= counter %>
</body>
</html>