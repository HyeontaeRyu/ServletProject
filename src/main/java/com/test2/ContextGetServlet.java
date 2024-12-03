package com.test2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ContextGetServlet", value = "/contextGet")
public class ContextGetServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String name = (String) getServletContext().getAttribute("name");
    int age = (int) getServletContext().getAttribute("age");
    String tel = (String) getServletContext().getAttribute("tel");
    String email = (String) getServletContext().getAttribute("email");

    response.setContentType("text/html;charset=UTF-8");

    try (PrintWriter out = response.getWriter()) {
      out.println("<html><head><title>ContextGetServlet</title></head>");
      out.println("<body>");
      out.println("<h1>ContextGetServlet</h1>");
      out.println("<h3>ServletContext 데이터</h3>");
      out.println("<li>이름 : " + name + "</li>");
      out.println("<li>나이 : " + age + "</li>");
      out.println("<li>전화번호 : " + tel + "</li>");
      out.println("<li>이메일 : " + email + "</li>");
      out.println("</body></html>");
    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
