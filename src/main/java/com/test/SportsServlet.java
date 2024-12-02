package com.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "sport", value = "/sport")
public class SportsServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    String[] sports = request.getParameterValues("sports");
    String gender = request.getParameter("gender");

    response.setContentType("text/html;charset=UTF-8");
    out.println("<html>");
    out.println("<head>");
    out.println("</head>");
    out.println("<body>");

    for (String sport : sports) {
      out.println(sport + "<br>");
    }
    out.println(
        "<br>당신이 선택한 성별은 : " + gender + "<br>");
    out.println("</body>");
    out.println("</html>");

  }
}
