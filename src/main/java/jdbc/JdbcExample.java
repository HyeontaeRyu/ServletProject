package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcExample {

  public static void main(String[] args) {

    String driver = "";
    String url = "";
    String username = "";
    String password = "";
    Properties properties = new Properties();
    try (InputStream input = JdbcExample.class.getClassLoader()
        .getResourceAsStream("db.properties")) {
      if (input == null) {
        System.out.println("Sorry, unable to find db.properties");
        return;
      }
      properties.load(input);
      System.setProperty("oracle.net.tns_admin", DBPropertiesUtil.getTnsAdmin());

      // 프로퍼티 값 읽기
      driver = properties.getProperty("driver");
      url = properties.getProperty("url");
      username = properties.getProperty("username");
      password = properties.getProperty("password");

      System.out.println("Driver: " + driver);
      System.out.println("URL: " + url);
      System.out.println("Username: " + username);
      System.out.println("Password: " + password);

    } catch (IOException ex) {
      ex.printStackTrace();
    }

    // 데이터베이스 연결을 위한 변수 선언
    // 비밀번호

    // DB 연결, 쿼리 실행, 결과 처리
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      // 1. 드라이버 로드 (Oracle JDBC 드라이버 예시)
      Class.forName(driver);

      // 2. 데이터베이스 연결
      conn = DriverManager.getConnection(url, username, password);
      stmt = conn.createStatement();

      // 3. SQL 쿼리 실행
      String sql = "SELECT * FROM tempMember";
      rs = stmt.executeQuery(sql);

      // 4. 결과 처리
      while (rs.next()) {
        String id = rs.getString("id");
        String name = rs.getString("name");
        String email = rs.getString("e_mail");
        System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
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
