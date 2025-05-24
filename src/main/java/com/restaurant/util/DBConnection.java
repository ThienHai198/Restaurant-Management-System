package com.restaurant.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/restaurant";
    private static final String USER = "root";
    private static final String PASS = "haitanthong198";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
