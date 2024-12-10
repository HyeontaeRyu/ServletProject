package jdbc;

import jakarta.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class tempMemberDAO {

  private final String JDBC_DRIVER = DBPropertiesUtil.getDriver();
  private final String JDBC_URL = DBPropertiesUtil.getUrl();
  private final String USER = DBPropertiesUtil.getUsername();
  private final String PASS = DBPropertiesUtil.getPassword();
  private ConnectionPool pool = null;

  public tempMemberDAO(ServletContext context) {
    try {
      // Class.forName(JDBC_DRIVER);
      pool = ConnectionPool.getInstance(context);
    } catch (Exception e) {
      System.out.println("Error : JDBC 드라이버 로딩 실패");
    }
  }

  public ArrayList<tempMemberVO> getMemberList() {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    ArrayList<tempMemberVO> temList = new ArrayList<tempMemberVO>();
    try {
      // conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
      conn = pool.getConnection();
      String strQuery = "select * from tempMember";
      stmt = conn.createStatement();
      rs = stmt.executeQuery(strQuery);
      while (rs.next()) {
        tempMemberVO vo = new tempMemberVO();
        vo.setId(rs.getString("id"));
        vo.setPasswd(rs.getString("passwd"));
        vo.setName(rs.getString("name"));
        vo.setMem_num1(rs.getString("mem_num1"));
        vo.setMem_num2(rs.getString("mem_num2"));
        vo.setEmail(rs.getString("e_mail"));
        vo.setPhone(rs.getString("phone"));
        vo.setZipcode(rs.getString("zipcode"));
        vo.setAddress(rs.getString("address"));
        vo.setJob(rs.getString("job"));
        temList.add(vo);
      }
    } catch (Exception e) {
      System.out.println("Exception" + e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) {
        try {
          // conn.close();
          pool.releaseConnection(conn);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return temList;
  }
}