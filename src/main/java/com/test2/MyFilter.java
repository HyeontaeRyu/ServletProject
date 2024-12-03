package com.test2;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class MyFilter implements Filter {

  @Override
  public void init(FilterConfig config) throws ServletException {
    System.out.println("MyFilter.init()");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    System.out.println("MyFilter.doFilter() - before");
    request.setCharacterEncoding("UTF-8");
    chain.doFilter(request, response);
    System.out.println("MyFilter.doFilter() - after");
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
