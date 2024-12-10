<%@page import="jdbc.ConnectionPool" %>
<%@page import="java.sql.SQLException" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    // JDBC 드라이버 클래스를 로드합니다.
    Class.forName("oracle.jdbc.driver.OracleDriver");

    // ConnectionPool 객체를 생성하고, 이를 통해 데이터베이스 연결을 가져옵니다.
    ConnectionPool pool = null;
    try {
        pool = ConnectionPool.getInstance(application);
    } catch (Exception e) {
        e.printStackTrace();
    }
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    // 회원 정보 변수 선언
    String id = "", passwd = "", name = "", mem_num1 = "", mem_num2 = "", e_mail = "", phone = "", zipcode = "", address = "", job = "";
    int counter = 0;  // 결과로 반환된 레코드 수를 셀 변수

    try {
        // ConnectionPool에서 연결을 얻습니다.
        conn = pool.getConnection(); // Connection 사용하기 (Free -> Use)

        // Statement 객체를 생성하고, 쿼리를 실행합니다.
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM TEMPMEMBER");  // TEMPMEMBER 테이블에서 데이터를 가져옵니다.
%>

<html>
<head>
    <title>JSP에서 데이터베이스 연동</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFCC">
<h2>JSP scriptlet 에서 Database 연동 예제</h2>
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
        // ResultSet이 null이 아니면 데이터가 있다는 뜻이므로 while문으로 데이터를 읽어옵니다.
        if (rs != null) {
            // 데이터베이스에서 레코드를 하나씩 읽어옵니다.
            while (rs.next()) {
                id = rs.getString("id");          // id 컬럼의 값
                passwd = rs.getString("passwd");  // passwd 컬럼의 값
                name = rs.getString("name");      // name 컬럼의 값
                mem_num1 = rs.getString("mem_num1"); // mem_num1 컬럼의 값
                mem_num2 = rs.getString("mem_num2"); // mem_num2 컬럼의 값
                e_mail = rs.getString("e_mail");  // e_mail 컬럼의 값
                phone = rs.getString("phone");    // phone 컬럼의 값
                zipcode = rs.getString("zipcode"); // zipcode 컬럼의 값
                address = rs.getString("address"); // address 컬럼의 값
                job = rs.getString("job");        // job 컬럼의 값
    %>
    <tr>
        <!-- 데이터베이스에서 조회한 값을 HTML 테이블의 각 셀에 출력 -->
        <td><%=id%>
        </td>
        <td><%=passwd%>
        </td>
        <td><%=name%>
        </td>
        <td><%=mem_num1%>
        </td>
        <td><%=mem_num2%>
        </td>
        <td><%=e_mail%>
        </td>
        <td><%=phone%>
        </td>
        <td><%=zipcode%>/<%=address%>
        </td>
        <td><%=job%>
        </td>
        <%
                    counter++;  // 데이터가 출력될 때마다 카운터를 증가시킵니다.
                } // end of while loop
            } // end of if block
        %>
    </tr>
</table>
<br></br> total records : <%=counter%> <!-- 출력된 데이터 레코드 수를 출력 -->
<%
    } catch (SQLException e) {
        // SQLException이 발생하면 에러 메시지를 출력
        e.printStackTrace();
    } catch (Exception e) {
        // 다른 예외가 발생하면 에러 메시지를 출력
        e.printStackTrace();
    } finally {
        // 데이터베이스 리소스를 닫는 작업
        if (rs != null) {
            try {
                rs.close();  // ResultSet을 닫습니다.
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();  // Statement를 닫습니다.
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                // conn.close(); // 일반적으로는 conn.close()를 사용하지만, ConnectionPool에서는 반납 방식 사용
                pool.releaseConnection(conn);  // Connection을 반환합니다. (Use -> Free)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
%>
</body>
</html>