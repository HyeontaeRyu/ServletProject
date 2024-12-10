package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleJDBCExample {

  public static void main(String[] args) {

    // Oracle 데이터베이스 연결 정보
    String jdbcUrl = DBPropertiesUtil.getUrl(); // URL, 포트 및 SID
    String jdbcUser = DBPropertiesUtil.getUsername(); // Oracle 사용자명
    String jdbcPassword = DBPropertiesUtil.getPassword();
    String TnsAdmin = DBPropertiesUtil.getTnsAdmin();
    // Oracle 비밀번호

    // DB 연결 및 SQL 쿼리 실행
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      // 1. Oracle JDBC 드라이버 로드
      Class.forName("oracle.jdbc.driver.OracleDriver");
      System.setProperty("oracle.net.tns_admin", DBPropertiesUtil.getTnsAdmin());

      // 2. 데이터베이스 연결
      conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
      stmt = conn.createStatement();

      // 3. SQL 쿼리 실행 (tempmember 테이블의 모든 데이터 조회)
      String sql = "SELECT * FROM tempmember";
      rs = stmt.executeQuery(sql);

      // 4. 쿼리 결과 처리
      while (rs.next()) {
        String id = rs.getString("id");
        String passwd = rs.getString("passwd");
        String memNum1 = rs.getString("mem_num1");
        String memNum2 = rs.getString("mem_num2");
        String email = rs.getString("e_mail");
        String phone = rs.getString("phone");
        String zipcode = rs.getString("zipcode");
        String address = rs.getString("address");
        String job = rs.getString("job");

        System.out.println("ID: " + id + ", Passwd: " + passwd + ", MemNum1: " + memNum1 +
            ", MemNum2: " + memNum2 + ", Email: " + email + ", Phone: " + phone +
            ", Zipcode: " + zipcode + ", Address: " + address + ", Job: " + job);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 5. 자원 해제
      try {
        if (rs != null) {
          rs.close();
        }
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
