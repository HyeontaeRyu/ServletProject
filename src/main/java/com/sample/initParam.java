package com.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "initParam", value = "/initParam",
    initParams = {
        @WebInitParam(name = "tel", value = "010-1234-5678"),
        @WebInitParam(name = "email", value = "hong@naver.com")
    }
)
public class initParam extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private String company;
  private String manager;
  private String tel;
  private String email;

  public void init() throws ServletException {
    // ServletContext 객체를 이용하여 web.xml 에 설정된 초기화 파라미터를 가져온다.
    company = getServletContext().getInitParameter("company");
    manager = getServletContext().getInitParameter("manager");

    // ServletConfig 객체를 이용하여 web.xml 에 설정된 초기화 파라미터를 가져온다.
    tel = getServletConfig().getInitParameter("tel");
    email = getServletConfig().getInitParameter("email");
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
      out.println("<html><head><title>initParam</title></head>");
      out.println("<body>");
      out.println("<li>회사명 : " + company + "</li>");
      out.println("<li>담당자 : " + manager + "</li>");
      out.println("<li>전화번호 : " + tel + "</li>");
      out.println("<li>이메일 : " + email + "</li>");
      out.println("</body></html>");
    } finally {
      out.close();
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
