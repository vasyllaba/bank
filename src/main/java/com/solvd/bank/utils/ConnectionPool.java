package com.solvd.bank.utils;

import com.solvd.bank.configuration.DBConnector;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl(DBConnector.getUrl());
        ds.setUsername(DBConnector.getUserName());
        ds.setPassword(DBConnector.getPassword());
        ds.setMinIdle(2);
        ds.setMaxIdle(5);
        ds.setMaxOpenPreparedStatements(50);
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
