package com.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardDAO {

  private static BoardDAO instance = null;

  private BoardDAO() {
  }

  public static BoardDAO getInstance() {
    if (instance == null) {
      synchronized (BoardDAO.class) {
        if (instance == null) {
          instance = new BoardDAO();
        }
      }
    }
    return instance;
  }

  public void insertArticle(BoardDTO article) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    int num = article.getNum();
    int ref = article.getRef();
    int step = article.getStep();
    int depth = article.getDepth();
    int number = 0;
    String sql = "";

    try {
      conn = ConnUtill.getConnection();
      pstmt = conn.prepareStatement("select max(num) from board");
      rs = pstmt.executeQuery();

      if (rs.next()) {
        number = rs.getInt(1) + 1;
      } else {
        number = 1;
      }

      if (number != 0) {
        sql = "update board set step=step+1 where ref=? and step>?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, ref);
        pstmt.setInt(2, step);
        pstmt.executeUpdate();
        step = step + 1;
        depth = depth + 1;
      } else {
        ref = number;
        step = 0;
        depth = 0;
      }

      sql =
          "insert into BOARD(num, writer, email, subject, pass, readcount, ref, step, depth, content, ip) "
              + "values(BOARD_SEQ.nextval,?,?,?,?,?,?,?,?,?,?) ";

      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, article.getWriter());
      pstmt.setString(2, article.getEmail());
      pstmt.setString(3, article.getSubject());
      pstmt.setString(4, article.getPass());
      pstmt.setInt(5, 0);
      pstmt.setInt(6, ref);
      pstmt.setInt(7, step);
      pstmt.setInt(8, depth);
      pstmt.setString(9, article.getContent());
      pstmt.setString(10, article.getIp());
      pstmt.executeUpdate();


    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {
          e.printStackTrace();
        }

        if (pstmt != null) {
          try {
            pstmt.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }

        if (conn != null) {
          try {
            conn.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }


      }
    }

  }
}
