package com.member;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class StudentDAO {

  private Connection getConnection() {
    Connection conn = null;
    try {
      Context init = new InitialContext();
      Context env = (Context) init.lookup("java:comp/env");
      DataSource ds = (DataSource) env.lookup("jdbc/myoracle");
      conn = ds.getConnection();
      System.out.println("DBCP 연결 성공");

    } catch (Exception e) {
      System.out.println("DBCP 연결 실패");
    }
    return conn;
  }

  public boolean idCheck(String id) {
    boolean result = true;
    String sql = "select * from student where id=?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();
      if (!rs.next()) {
        result = false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  public Vector<ZipCodeDTO> zipCodeRead(String dong) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Vector<ZipCodeDTO> vecList = new Vector<ZipCodeDTO>();

    try {
      conn = getConnection();
      String strQuery = "select * from zipcode where dong like '" + dong + "%'";
      pstmt = conn.prepareStatement(strQuery);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        ZipCodeDTO dto = new ZipCodeDTO();
        dto.setZipcode(rs.getString("zipcode"));
        dto.setSido(rs.getString("sido"));
        dto.setGugun(rs.getString("gugun"));
        dto.setDong(rs.getString("dong"));
        dto.setRi(rs.getString("ri"));
        dto.setBunji(rs.getString("bunji"));
        vecList.add(dto);
      }
      return vecList;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public boolean memberInsert(StudentDTO dto) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    boolean flag = false;

    try {
      conn = getConnection();
      String strQuery = "insert into student values(?,?,?,?,?,?,?,?,?,?,?)";
      pstmt = conn.prepareStatement(strQuery);
      pstmt.setString(1, dto.getId());
      pstmt.setString(2, dto.getPass());
      pstmt.setString(3, dto.getName());
      pstmt.setString(4, dto.getPhone1());
      pstmt.setString(5, dto.getPhone2());
      pstmt.setString(6, dto.getPhone3());
      pstmt.setString(7, dto.getEmail());
      pstmt.setString(8, dto.getZipcode());
      pstmt.setString(9, dto.getAddress1());
      pstmt.setString(10, dto.getAddress2());
      int count = pstmt.executeUpdate();
      if (count > 0) {
        flag = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null) {
          pstmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return flag;
  }

  public int loginCheck(String id, String pass) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int result = -1;

    try {
      conn = getConnection();
      String strQuery = "select pass from student where id=?";
      pstmt = conn.prepareStatement(strQuery);
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        if (rs.getString("pass").equals(pass)) {
          result = 1;
        } else {
          result = 0;
        }
      } else {
        result = -1;
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  public StudentDTO getMember(String id) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StudentDTO dto = new StudentDTO();

    try {
      conn = getConnection();
      String strQuery = "select * from student where id=?";
      pstmt = conn.prepareStatement(strQuery);
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        dto.setId(rs.getString("id"));
        dto.setPass(rs.getString("pass"));
        dto.setName(rs.getString("name"));
        dto.setPhone1(rs.getString("phone1"));
        dto.setPhone2(rs.getString("phone2"));
        dto.setPhone3(rs.getString("phone3"));
        dto.setEmail(rs.getString("email"));
        dto.setZipcode(rs.getString("zipcode"));
        dto.setAddress1(rs.getString("address1"));
        dto.setAddress2(rs.getString("address2"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return dto;
  }

  public void updateMember(StudentDTO dto) {
    Connection con = null;
    PreparedStatement pstmt = null;

    String sql = "update student set pass=?, phone1=?, phone2=?, phone3=?, "
        + "email=?, zipcode=?, address1=? address2=? where id=?";

    try {

      con = getConnection();
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, dto.getPass());
      pstmt.setString(2, dto.getPhone1());
      pstmt.setString(3, dto.getPhone2());
      pstmt.setString(4, dto.getPhone3());
      pstmt.setString(5, dto.getEmail());
      pstmt.setString(6, dto.getZipcode());
      pstmt.setString(7, dto.getAddress1());
      pstmt.setString(8, dto.getAddress2());
      pstmt.setString(9, dto.getId());

      pstmt.executeUpdate();

    } catch (Exception s) {
      s.printStackTrace();
    } finally {
      if (pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException ex) {
        }
      }
      if (con != null) {
        try {
          con.close();
        } catch (SQLException ex) {
        }
      }
    }

  }// end updateMember()


  /*
   * 회월탈퇴 버튼을 클릭하면 회원을 비밀번호를 입력받아 데이터베이스의 비밀번호와
   * 일치하는지를 비교하여 일치하여 일치하면 삭제 일치하지 않으면 비밀번호오류
   *
   * 일치하게 되면 회원탈락처리, 그렇지 않으면 비밀번호가 틀렸습니다를 출력
   */


  public int deleteMember(String id, String pass) {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String dbpass = ""; // db에 저장된 비밀번호를 의미함

    int result = -1;

    try {

      con = getConnection();
      String sql1 = "select pass from student where id=?";
      pstmt = con.prepareStatement(sql1);
      pstmt.setString(1, id);

      rs = pstmt.executeQuery();

      if (rs.next()) {

        dbpass = rs.getString("pass");
        if (dbpass.equals(pass)) { //본인이 맞다면
          String sql2 = "delete from student where id=?";
          pstmt = con.prepareStatement(sql2);
          pstmt.setString(1, id);
          pstmt.executeUpdate();
          result = 1; //회원탈퇴 성공

        } else {
          result = 0;
        }
      }
    } catch (Exception s) {
      s.printStackTrace();
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ex) {
        }
      }
      if (pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException ex) {
        }
      }
      if (con != null) {
        try {
          con.close();
        } catch (SQLException ex) {
        }
      }
    }

    return result;
  }
}