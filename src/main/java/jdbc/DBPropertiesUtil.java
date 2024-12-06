package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertiesUtil {

  private static Properties properties = new Properties();

  static {
    try (InputStream input = DBPropertiesUtil.class.getClassLoader()
        .getResourceAsStream("db.properties")) {
      if (input == null) {
        System.out.println("Sorry, unable to find db.properties");
      }
      properties.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static String getDriver() {
    return properties.getProperty("driver");
  }

  public static String getUrl() {
    return properties.getProperty("url");
  }

  public static String getUsername() {
    return properties.getProperty("username");
  }

  public static String getPassword() {
    return properties.getProperty("password");
  }

  public static String getTnsAdmin() {
    return properties.getProperty("TNS_ADMIN");
  }

  public static String getTnsAdmin2() {
    return properties.getProperty("TNS_ADMIN2");
  }
}