package com.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

  public int getArticleCount() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    int x = 0;

    try {
      conn = ConnUtill.getConnection();
      pstmt = conn.prepareStatement("select count(*) from board");
      rs = pstmt.executeQuery();

      if (rs.next()) {
        x = rs.getInt(1);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return x;
  }

  public List<BoardDTO> getArticles() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<BoardDTO> articleList = null;

    String sql = null;

    try {
      conn = ConnUtill.getConnection();
      sql = "select * from (select * from board order by num desc)";

      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        articleList = new ArrayList<BoardDTO>();

        do {
          BoardDTO article = new BoardDTO();
          article.setNum(rs.getInt("num"));
          article.setWriter(rs.getString("writer"));
          article.setEmail(rs.getString("email"));
          article.setSubject(rs.getString("subject"));
          article.setPass(rs.getString("pass"));
          article.setRef(rs.getInt("ref"));
          article.setStep(rs.getInt("step"));
          article.setDepth(rs.getInt("depth"));
          article.setReadCount(rs.getInt("readcount"));
          article.setRegDate(rs.getTimestamp("regdate"));
          article.setContent(rs.getString("content"));
          article.setIp(rs.getString("ip"));

          articleList.add(article);
        } while (rs.next());
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return articleList;
  }

  public BoardDTO getArticle(int num) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    BoardDTO article = null;

    try {
      conn = ConnUtill.getConnection();
      String sql = "update board set readcount=readcount+1 where num=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, num);
      pstmt.executeUpdate();

      pstmt = conn.prepareStatement("select * from board where num=?");
      pstmt.setInt(1, num);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        article = new BoardDTO();
        article.setNum(rs.getInt("num"));
        article.setWriter(rs.getString("writer"));
        article.setEmail(rs.getString("email"));
        article.setSubject(rs.getString("subject"));
        article.setPass(rs.getString("pass"));
        article.setRef(rs.getInt("ref"));
        article.setStep(rs.getInt("step"));
        article.setDepth(rs.getInt("depth"));
        article.setReadCount(rs.getInt("readcount"));
        article.setRegDate(rs.getTimestamp("regdate"));
        article.setContent(rs.getString("content"));
        article.setIp(rs.getString("ip"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return article;
  }

  public BoardDTO updateGetArticle(int num) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    BoardDTO article = null;

    try {
      conn = ConnUtill.getConnection();
      pstmt = conn.prepareStatement("select * from board where num=?");
      pstmt.setInt(1, num);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        article = new BoardDTO();
        article.setNum(rs.getInt("num"));
        article.setWriter(rs.getString("writer"));
        article.setEmail(rs.getString("email"));
        article.setSubject(rs.getString("subject"));
        article.setPass(rs.getString("pass"));
        article.setRef(rs.getInt("ref"));
        article.setStep(rs.getInt("step"));
        article.setDepth(rs.getInt("depth"));
        article.setReadCount(rs.getInt("readcount"));
        article.setRegDate(rs.getTimestamp("regdate"));
        article.setContent(rs.getString("content"));
        article.setIp(rs.getString("ip"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return article;
  }

  public int updateArticle(BoardDTO article) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "";
    String sql2 = "";
    int result = -1;
    String pass = "";
    try {
      int num = article.getNum();
      conn = ConnUtill.getConnection();
      sql = "select pass from board where num=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, num);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        pass = rs.getString("pass");
        if (pass.equals(article.getPass())) {
          sql2 = "update board set writer=?, email=?, subject=?, content=? where num=?";
          pstmt = conn.prepareStatement(sql2);
          pstmt.setString(1, article.getWriter());
          pstmt.setString(2, article.getEmail());
          pstmt.setString(3, article.getSubject());
          pstmt.setString(4, article.getContent());
          pstmt.setInt(5, article.getNum());
          pstmt.executeUpdate();
          return 1;
        } else {
          return 0;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }

  public int deleteArticle(int num, String pass) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "";
    String dbpass = "";
    int result = -1;

    try {
      conn = ConnUtill.getConnection();
      sql = "select pass from board where num=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, num);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        dbpass = rs.getString("pass");
        if (dbpass.equals(pass)) {
          sql = "delete from board where num=?";
          pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1, num);
          pstmt.executeUpdate();
          return 1;
        } else {
          return 0;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }

  public int getArticleCount(String what, String content) {

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int x = 0;

    try {
      con = ConnUtill.getConnection();
      //String sql="select count(*) from board";

      String sql = "select count(*) from board where " + what + " like '%" + content + "%'";

      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        x = rs.getInt(1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ss) {
        }
      }
      if (pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException ss) {
        }
      }
      if (con != null) {
        try {
          con.close();
        } catch (SQLException ss) {
        }
      }
    }
    return x;
  } //end getArticleCount


  /*
   * 검색할 내용을 리스트로 받아옴
   * 검색조건, 검색내용, 시작번호, 끝번호) 시작번호와 끝번호는 페이징 처리용
   */
  public List<BoardDTO> getArticles(String what, String content, int start, int end) { // 1

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<BoardDTO> articleList = null;

    try {
      con = ConnUtill.getConnection();
      // 2
      //String sql="select * from board order by num desc";
      String sql = "select * from ("
          + "select rownum rnum, num, writer, email, subject, "
          + "pass, regdate, readcount, ref, step, depth, content, ip from ("
          + "select * from board where "
          + what + " like '%" + content + "%' order by ref desc, step asc)) "
          + "where rnum >= ? and rnum <= ?";

      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, start);
      pstmt.setInt(2, end);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        articleList = new ArrayList<BoardDTO>(end - start + 1);

        do {
          BoardDTO article = new BoardDTO();
          article.setNum(rs.getInt("num"));
          article.setWriter(rs.getString("writer"));
          article.setEmail(rs.getString("email"));
          article.setSubject(rs.getString("subject"));
          article.setPass(rs.getString("pass"));
          article.setRegDate(rs.getTimestamp("regdate"));
          article.setReadCount(rs.getInt("readcount"));
          article.setRef(rs.getInt("ref"));
          article.setStep(rs.getInt("step"));
          article.setDepth(rs.getInt("depth"));
          article.setContent(rs.getString("content"));
          article.setIp(rs.getString("ip"));
          articleList.add(article);
        } while (rs.next());
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ss) {
        }
      }
      if (pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException ss) {
        }
      }
      if (con != null) {
        try {
          con.close();
        } catch (SQLException ss) {
        }
      }
    }
    return articleList;
  }

}
