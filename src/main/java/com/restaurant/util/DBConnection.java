package com.restaurant.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {



    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/";
    static final String USER = "root";
    static final String Pass = "haitanthong198";

    public static void main(String[] args)
    {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, Pass);
        Statement stmt = conn.createStatement();)
        {
            String sql1 ="USE restaurant";
            stmt.execute(sql1);

            System.out.println("Database connected successfully...");
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

}
