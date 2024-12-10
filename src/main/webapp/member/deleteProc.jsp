<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <jsp:useBean id="dao" class="com.member.StudentDAO" />
<!DOCTYPE html>
<html>
<head>

<title>회원탈퇴</title>
</head>
<%
 String id = (String)session.getAttribute("loginID");
 String pass = request.getParameter("pass");
 
 int check = dao.deleteMember(id,pass);
 
 if(check==1){
	 session.invalidate();
	 
	 %>
<meta charset="UTF-8" http-equiv="refresh" content="3;url=login.jsp">
<body>
<div align="center">
<font size="5" face="궁세처">
회원정보가 삭제 되었습니다.<br>
안녕히가세요.<br>
3초 후 로그인페이지로 이동합니다.
</font>
</div>
<%}else{ %>
<script type="text/javascript">
alert("비밀번호 안 맞음");
history.go(-1;)
</script>

<%} %>
</body>
</html>