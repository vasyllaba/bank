package com.solvd.bank.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class is used to create a JDBC
 * connection with MySql DB.
 */

public class DBConnector {
    static ResourceBundle rb = ResourceBundle.getBundle("mysqlcreds");

    private static final String DB_DRIVER = rb.getString("driver");
    private static final String DB_URL = rb.getString("url");
    private static final String DB_USERNAME = rb.getString("login");
    private static final String DB_PASSWORD = rb.getString("password");

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public static String getUrl(){
        return DB_URL;
    }

    public static String getUserName(){
        return DB_USERNAME;
    }

    public static String getPassword(){
        return DB_PASSWORD;
    }

    public static String getDriver(){
        return DB_DRIVER;
    }
}
