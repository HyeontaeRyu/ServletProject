package com.test2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(name = "ContextSetServlet", value = "/contextSet")
public class ContextSetServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String name = "홍길동";
    int age = 20;
    String tel = "010-1234-5678";
    String email = "hong@naver.com";

    // ServletContext 객체를 이용하여 데이터를 저장한다.
    getServletContext().setAttribute("name", name);
    getServletContext().setAttribute("age", age);
    getServletContext().setAttribute("tel", tel);
    getServletContext().setAttribute("email", email);

    response.setContentType("text/html;charset=UTF-8");

    response.getWriter().println(
        "<html><head><title>ContextSetServlet</title></head><body><a href='/contextGet'>데이터 확인</a></body></html>");

    String writeFile = "/WEB-INF/testFile2.txt";
    String realPath = getServletContext().getRealPath(writeFile);
    String content = "이름 : " + name + "\n나이 : " + age + "\n전화번호 : " + tel + "\n이메일 : " + email;

    try (FileWriter fw = new FileWriter(realPath, false)) {
      fw.write(content);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
