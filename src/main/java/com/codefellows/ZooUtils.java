package com.codefellows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Properties;

/**
 * Created by bfarr on 6/8/17.
 */
public class ZooUtils {
  private static final String DB_HOST = "seattle-java-501n1-project-mariadb.cdvwhjbcai8i.us-west-2.rds.amazonaws.com:3306";
  private static final String DB_USER = "codefellows";
  private static final String DB_PASS = "codefellows";
  private static final String DB_NAME = "codefellows";

  private static final Logger LOG = LoggerFactory.getLogger(ZooUtils.class);

  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    Connection conn = null;
    Properties connectionProps = new Properties();

    connectionProps.put("user", DB_USER);
    connectionProps.put("password", DB_PASS);

    conn = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/" + DB_NAME, connectionProps);
    LOG.info("Connected to database");

    return conn;
  }

  public static void viewTable(Connection con) throws SQLException {
    Statement stmt = null;
    String query = "SELECT name,age FROM person";

    try {
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String name = rs.getString("name");
        int age = rs.getInt("age");

        LOG.info("name: " + name + " age: " + age);
      }
    } catch (SQLException e ) {
      LOG.error(e.getMessage());
    } finally {
      if (stmt != null) {
        stmt.close();
      }
    }
  }
}
