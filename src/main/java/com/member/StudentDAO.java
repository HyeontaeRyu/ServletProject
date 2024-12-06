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
}
