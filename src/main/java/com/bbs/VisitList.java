package com.bbs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet("/VisitList")
public class VisitList extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
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
    String walletPath = getServletContext().getRealPath("/WEB-INF/lib/Wallet_maindb");
    System.setProperty("oracle.net.tns_admin", walletPath);
    System.setProperty("oracle.jdbc.fanEnabled", "false");
    System.setProperty("oracle.net.ssl_server_dn_match", "true");
    System.setProperty("oracle.net.ssl_version", "1.2");

    try (PrintWriter out = response.getWriter()) {
      out.println("<html>");
      out.println("<head><title>방명록</title></head>");
      out.println("<body>");
      String sql = "SELECT NO, WRITER, MEMO, REGDATE FROM VISIT ORDER BY NO DESC";

      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      try {
        Class.forName("oracle.jdbc.OracleDriver");

        conn = DriverManager.getConnection(url, username, password);

        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
          int no = rs.getInt("NO");
          String writer = rs.getString("WRITER");
          String memo = rs.getString("MEMO");
          Date regdate = rs.getDate("REGDATE");

          out.println("<table border=1 align=center width=500>");
          out.println("<tr>");
          out.println("<th width=50>번호</th><td width=50>" + no + "</td>");
          out.println("<th width=70>작성자</th><td width=180>" + writer + "</td>");
          out.println("<th width=70>작성일</th><td width=100>" + regdate + "</td>");
          out.println(
              "<th width=50>내용</th><td colspan=5>&nbsp;<textarea name='memo' rows='3' cols='50'>"
                  + memo + "</textarea></td>");

          out.println("</table>");
          out.println("<p>");
        }

      } catch (ClassNotFoundException | SQLException e) {
        throw new RuntimeException(e);
      } finally {
        try {
          if (rs != null) {
            out.println("<table border=1 align=center>");
            out.println("<tr align=center>");
            out.println("<td>번호</td><td>작성자</td><td>내용</td><td>작성일</td>");
            out.println("</tr>");
            while (rs.next()) {
              out.println("<tr>");
              out.println("<td>" + rs.getInt("NO") + "</td>");
              out.println("<td>" + rs.getString("WRITER") + "</td>");
              out.println("<td>" + rs.getString("MEMO") + "</td>");
              out.println("<td>" + rs.getString("REGDATE") + "</td>");
              out.println("</tr>");
            }
            out.println("</table>");
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
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

      out.println("<p align=center><a href=/bbs/write.html>글쓰기</p>");
      out.println("</body></html>");

    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }
}
