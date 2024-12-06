<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>

<%
    try {
        Context initContext = null;
        try {
            initContext = new InitialContext();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = null;
        try {
            ds = (DataSource) envContext.lookup("jdbc/myoracle");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        Connection conn = ds.getConnection();
        System.out.println("데이터베이스 연결 성공!!!");
    } catch (SQLException s) {
        s.printStackTrace();
    }


%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

</body>
</html>