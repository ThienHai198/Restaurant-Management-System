package com.restaurant.dao;

import com.restaurant.model.MenuItem;
import com.restaurant.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    public List<MenuItem> getAllMenuItems() throws SQLException {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM menu_items";
        // try (Connection conn = DBConnection.getConnection();
        //      PreparedStatement stmt = conn.prepareStatement(query);
        //      ResultSet rs = stmt.executeQuery()) {
        //     while (rs.next()) {
        //         MenuItem item = new MenuItem();
        //         item.setId(rs.getInt("id"));
        //         item.setName(rs.getString("name"));
        //         item.setPrice(rs.getDouble("price"));
        //         item.setCategory(rs.getString("category"));
        //         menuItems.add(item);
        //     }
        // }
        return menuItems;
    }

    public void addMenuItem(MenuItem item) throws SQLException {
        String query = "INSERT INTO menu_items (name, price, category) VALUES (?, ?, ?)";
        // try (Connection conn = DBConnection.getConnection();
        //      PreparedStatement stmt = conn.prepareStatement(query)) {
        //     stmt.setString(1, item.getName());
        //     stmt.setDouble(2, item.getPrice());
        //     stmt.setString(3, item.getCategory());
        //     stmt.executeUpdate();
        // }
    }
}