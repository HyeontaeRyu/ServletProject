package com.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    try (PrintWriter out = response.getWriter()) {
      HttpSession session = request.getSession();


      if (session != null) {
        String sessionID = (String) session.getAttribute("user");
        out.println("<html>");
        out.println("<body>");
        out.println("<table width='300' border='1'>");
        out.println("<tr>");

        out.println(
            "<td width='300' align='center'>");
        out.println("세션 아이디 : " + sessionID);
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println(
            "<td align='center'><a href='#'>회원정보</a></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println(
            "<td align='center'><a href='logout'>로그아웃</a></td>");
        out.println("</tr>");

        out.println("</table></body></html>");

      } else {
        out.println("<html>");
        out.println("<body>");
        out.println("<form action='/loginCheck' method='post'>");
        out.println("<table width='300' border='1'>");

        out.println("<tr>");
        out.println("<th width='100'>아이디</th>");
        out.println("<td width='200'>&nbsp<input type='text' name='id'></input></td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<th width='100'>비밀번호</th>");
        out.println("<td width='200'><input type='password' name='pwd'></input></td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println(
            "<td align='center' colspan='2'><input type='button' value='회원가입'></input></td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println(
            "<td align='center' colspan='2'><input type='submit' value='로그인'></input></td>");
        out.println("</tr>");

        out.println("</table></form></body></html>");
      }
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
