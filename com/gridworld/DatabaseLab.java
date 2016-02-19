package com.gridworld;

import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.RowSet;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Tom on 12/25/2015.
 *
 * Show some examples on basic work on database, using JDBC and MySQL
 */
public class DatabaseLab {

    static Properties dbInfo = new Properties();
    static String dbName = "HOTEL_RESERVATION_DB";
    static String propFile = "mysql.properties";

    public static void main(String[] argv) {
        try {
            getDBInfo(propFile);
//            executeUpdateQuery("insert into guest_t values (\"hank\", " +
//                            "\"jeffersson\", 25)");
            executeSelectQuery("select * from guest_t", new SelectPrint());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void getDBInfo(String file) throws IOException {
        InputStream stream = DatabaseLab.class.getResourceAsStream(file);
        Properties prop = new Properties();
        if (stream == null) {
            FileInputStream in = new FileInputStream(file);
            prop.load(in);
        } else {
            prop.load(stream);
        }

//        pass property object to JDBC get connection method
        dbInfo.setProperty("user", prop.getProperty("mysql.username"));
        dbInfo.setProperty("password", prop.getProperty("mysql.password"));
        dbInfo.setProperty("host", prop.getProperty("mysql.host"));

        for (Object val : dbInfo.values()) {
            if (val == null) {
                throw new NullPointerException("Missing value in DB property " +
                        "file");
            }
        }
    }

    public static Connection getDBConnection() throws SQLException {
        String URL = "jdbc:mysql://" + dbInfo.get("host") + "/" + dbName;
//        unnecessary, java can load driver automatically
//        JDBC JAR has to be available on classpath or injected to IDE env
//        String jdbcDriver = "com.mysql.jdbc.Driver";
//        Class.forName(jdbcDriver);
        return DriverManager.getConnection(URL, dbInfo);
    }

    public static void executeUpdateQuery(String query) throws SQLException {
/*  try-with-resources pattern - automatic close on resources that implements
    autoclosable method

    However, do NOT chain resources on single line which prevents embedded
    resource from closing
 */
        try (Connection conn = getDBConnection();
             Statement stmt = conn.createStatement()) {
//            .execute runs queries that return only boolean like UPDATE, INSERT
            stmt.execute(query);
        }
    }

    /*
    I cannot simply wrap code for select query since Connection has to be
    opened while ResultSet is being processed. I wonder how Hibernate
    handles this situation

    I worked it around by using additional class for printing
    */
    public static void executeSelectQuery(String query, SelectPrint pr)
            throws SQLException {
        try (Connection conn = getDBConnection();
             Statement stmt = conn.createStatement()) {
            pr.print(stmt.executeQuery(query));
        }
    }

    /*
 RowSet is better way how to execute queries through JDBC, however it can
 only run queries returning some data set. Update queries returning
 boolean (or number of affected lines) seems to be not supported.
  */
    public static void executeSelectQueryAlt(String sql) {

        String host = "localhost";
        String port = "3306";
        String user = "root";
        String password = "sql12";
        String URI = "jdbc:mysql://" + host + ":" + port +
                "/HOTEL_RESERVATION_DB";

        SelectPrint pr = new SelectPrint();
        try (RowSet rs = new JdbcRowSetImpl()) {
            rs.setUrl(URI);
            rs.setUsername(user);
            rs.setPassword(password);
            rs.setCommand(sql);

            pr.print(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    custom printer for SQL select query
    //    this allows to use wrapper for JDBC connection
    public static class SelectPrint {
        public void print(ResultSet rs) throws SQLException {
            PrintStream out = new PrintStream(System.out);
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                out.println("--> " + name + ", " + age);
            }
        }
    }
}
