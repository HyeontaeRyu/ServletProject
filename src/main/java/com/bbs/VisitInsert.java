package com.bbs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet(name = "VisitInsert", value = "/VisitInsert")
public class VisitInsert extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Properties properties = new Properties();
    try (InputStream input = getServletContext().getResourceAsStream(
        "/WEB-INF/conf/db.properties")) {
      if (input == null) {
        throw new IOException("Unable to find db.properties");
      }
      properties.load(input);
    }
    String driver = properties.getProperty("driver");
    String url = properties.getProperty("url");
    String username = properties.getProperty("username");
    String password = properties.getProperty("password");
    request.setCharacterEncoding("utf-8");
    String walletPath = getServletContext().getRealPath("/WEB-INF/lib/Wallet_maindb");
    System.setProperty("oracle.net.tns_admin", walletPath);
    System.setProperty("oracle.jdbc.fanEnabled", "false");
    System.setProperty("oracle.net.ssl_server_dn_match", "true");
    System.setProperty("oracle.net.ssl_version", "1.2");

    String writer = request.getParameter("writer");
    String memo = request.getParameter("memo");

    String sql = "insert into VISIT(no, writer, memo, regdate) values(VISIT_SEQ.nextval, ?, ?, sysdate)";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      Class.forName("oracle.jdbc.OracleDriver");

      conn = DriverManager.getConnection(url, username, password);

      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, writer);
      pstmt.setString(2, memo);

      pstmt.executeUpdate();


    } catch (ClassNotFoundException | SQLException e) {
      throw new RuntimeException(e);
    } finally {

      try {
        if (pstmt != null) {
          pstmt.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    response.sendRedirect("VisitList");
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }


}
