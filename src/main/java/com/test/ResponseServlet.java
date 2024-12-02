package com.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "response", value = "/response")
public class ResponseServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    Date date = new Date();
    out.println("<html>");
    out.println("<head><title>오늘의 날짜</title></head>");

    out.println("<body>");

    out.println("My First Servlet");
    out.println("<hr color='red'>");
    out.println("<br/>");
    out.println("오늘의 날짜는 : " + date);
    out.print("<br>");
    out.println("오늘의 날짜는 : " + new java.util.Date());
    out.println("</body>");
    out.println("</html>");
  }

  @Override
  public void init() throws ServletException {
    System.out.println("init 메서드 호출");
  }

  public void initMethod() {
    System.out.println("initMethod 메서드 호출");
  }

  public void clean() {
    System.out.println("clean 메서드 호출");
  }
}