package com.test2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "ContextFileServlet", value = "/contextFile")
public class ContextFileServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String readFile = "/WEB-INF/testFile.txt";
    InputStream is = getServletContext().getResourceAsStream(readFile);
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    PrintWriter out = response.getWriter();

    out.println("<html><head><title>ContextFileServlet</title></head>");
    out.println("<body>");

    out.println("<h1>ContextFileServlet</h1>");
    out.println("<h3>파일 내용</h3>");

    String str = br.readLine();
    while (str != null) {
      out.println(str + "<br>");
      str = br.readLine();
    }
    br.close();

    out.println("</body></html>");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
