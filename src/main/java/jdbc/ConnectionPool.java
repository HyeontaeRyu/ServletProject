package jdbc;

import jakarta.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public final class ConnectionPool {

  private static ConnectionPool cp;

  static {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }

  // 사용하지 않은 커넥션 즉, 초기 커넥션을 저장하는 변수
  private ArrayList<Connection> free;
  private ArrayList<Connection> used; // 사용중인 커넥션을 저장하는 변수
  private String url = DBPropertiesUtil.getUrl();
  private String user = DBPropertiesUtil.getUsername();
  private String password = DBPropertiesUtil.getPassword();
  private int initialCons = 10; // 초기 Connection 수
  private int maxCons = 20; // 최대 Connection 수
  private int numCons = 0; // 총 Connection 수

  private ServletContext context;

  // 실제 객체 생성은 여기에서 이루어짐
  private ConnectionPool(ServletContext context) throws SQLException {
    /*
     * 초기 Connection 개수를 각각의 ArrayList에 저장 할 수 있도록
     * 초기 Connection 수만큼 ArrayList 생성.
     */
    this.context = context;
    free = new ArrayList<Connection>(initialCons);
    used = new ArrayList<Connection>(initialCons);
    // initialCons 수만큼 Connection 생성(free)
    while (numCons < initialCons) {
      addConnection();
    }
  }

  // Singleton (생성자 역할)
  public static ConnectionPool getInstance(ServletContext context) {
    try {
      if (cp == null) {
        synchronized (ConnectionPool.class) {
          cp = new ConnectionPool(context);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return cp;
  }

  // free에 Connection 객체를 저장함.
  private void addConnection() throws SQLException {
    free.add(getNewConnection());
  }

  // 새로운 Connection 객체를 생성함.
  private Connection getNewConnection() throws SQLException {
    Connection con = null;
    String realPath = context.getRealPath(DBPropertiesUtil.getTnsAdmin2());
    System.setProperty("oracle.net.tns_admin", realPath);
    System.setProperty("oracle.jdbc.fanEnabled", "false");
    try {
      con = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("About to connect to " + con);
    ++numCons; // Connection 생성 될 때 마다 숫자 증가
    return con;
  }

  /* free에 있는 Connection을 used 로 옮기는 작업 => free ---> used */
  public synchronized Connection getConnection() throws SQLException {
    /*
     * free에 Connection이 없으면 maxCons만큼 Connection을 더 생성
     */
    if (free.isEmpty()) {
      while (numCons < maxCons) {
        addConnection();
      }
    }
    Connection _con;
    _con = free.get(free.size() - 1);
    free.remove(_con);
    used.add(_con);
    return _con;
  }

  // used에 있는 Connection을 free로 반납
  public synchronized void releaseConnection(Connection _con) throws SQLException {
    boolean flag = false;
    if (used.contains(_con)) {
      used.remove(_con);
      numCons--;
      flag = true;
    } else {
      throw new SQLException("ConnectionPool" + "에 있지않네요!!");
    }
    try {
      if (flag) {
        free.add(_con);
        numCons++;
      } else {
        _con.close();
      }
    } catch (SQLException e) {
      try {
        _con.close();
      } catch (SQLException s) {
        e.printStackTrace();
      }
    }
  }

  // 모든 Connection 자원을 반납함.
  // used에 있는 Connection을 모두 삭제
  public void closeAll() {
    for (int i = 0; i < used.size(); i++) {
      Connection _con = (Connection) used.get(i);
      used.remove(i--);
      try {
        _con.close();
      } catch (SQLException sqle) {
        sqle.printStackTrace();
      }
    }

    // free에 있는 Connection을 모두 삭제
    for (int i = 0; i < free.size(); i++) {
      Connection _con = (Connection) free.get(i);
      free.remove(i--);
      try {
        _con.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public int getMaxCons() {
    return maxCons;
  }

  public int getNumCons() {
    return numCons;
  }
}