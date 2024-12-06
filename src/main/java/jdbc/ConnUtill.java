package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConnUtill {

  public static Connection getConnection() {
    Connection conn = null;
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
      conn = ds.getConnection();
      System.out.println("데이터베이스 연결 성공!!!");
    } catch (SQLException s) {
      System.out.println("데이터베이스 연결 실패!!!");

    } catch (NamingException e) {
      throw new RuntimeException(e);
    }
    return conn;

  }

}
