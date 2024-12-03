package com.test2;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListenerImpl implements ServletContextListener {

  @Override
  public void contextDestroyed(jakarta.servlet.ServletContextEvent sce) {
    System.out.println("ServletContextListener.contextDestroyed()");
  }


  @Override
  public void contextInitialized(jakarta.servlet.ServletContextEvent sce) {
    System.out.println("ServletContextListener.contextInitialized()");
  }


}
