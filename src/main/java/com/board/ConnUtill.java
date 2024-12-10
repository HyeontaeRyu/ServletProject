package com.board;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnUtill {

  private static DataSource ds;

  static {
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      ds = (DataSource) envContext.lookup("jdbc/myoracle");
    } catch (NamingException e) {
      throw new RuntimeException(e);
    }

  }

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }

}
