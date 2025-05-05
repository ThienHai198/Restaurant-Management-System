package com.restaurant.dao;

import com.restaurant.model.Order;
import com.restaurant.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public void saveOrder(Order order) throws SQLException {
        String query = "INSERT INTO orders (table_id, items, status) VALUES (?, ?, ?)";
        // try (Connection conn = DBConnection.getConnection();
        //      PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        //     stmt.setInt(1, order.getTableId());
        //     stmt.setString(2, order.getItems());
        //     stmt.setString(3, order.getStatus());
        //     stmt.executeUpdate();
        //     try (ResultSet rs = stmt.getGeneratedKeys()) {
        //         if (rs.next()) {
        //             order.setOrderId(rs.getInt(1));
        //         }
        //     }
        // }
    }

    public List<Order> getPendingOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE status = 'PENDING'";
        // try (Connection conn = DBConnection.getConnection();
        //      PreparedStatement stmt = conn.prepareStatement(query);
        //      ResultSet rs = stmt.executeQuery()) {
        //     while (rs.next()) {
        //         Order order = new Order();
        //         order.setOrderId(rs.getInt("order_id"));
        //         order.setTableId(rs.getInt("table_id"));
        //         order.setItems(rs.getString("items"));
        //         order.setStatus(rs.getString("status"));
        //         if (rs.getTimestamp("prep_time") != null) {
        //             order.setPrepTime(rs.getTimestamp("prep_time").toLocalDateTime());
        //         }
        //         orders.add(order);
        //     }
        // }
        return orders;
    }

    public void updateOrderPrepTime(int orderId, LocalDateTime prepTime) throws SQLException {
        String query = "UPDATE orders SET prep_time = ?, status = 'SCHEDULED' WHERE order_id = ?";
        // try (Connection conn = DBConnection.getConnection();
        //      PreparedStatement stmt = conn.prepareStatement(query)) {
        //     stmt.setTimestamp(1, java.sql.Timestamp.valueOf(prepTime));
        //     stmt.setInt(2, orderId);
        //     stmt.executeUpdate();
        // }
    }

    public Order getOrderById(int orderId) throws SQLException {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        // try (Connection conn = DBConnection.getConnection();
        //      PreparedStatement stmt = conn.prepareStatement(query)) {
        //     stmt.setInt(1, orderId);
        //     try (ResultSet rs = stmt.executeQuery()) {
        //         if (rs.next()) {
        //             Order order = new Order();
        //             order.setOrderId(rs.getInt("order_id"));
        //             order.setTableId(rs.getInt("table_id"));
        //             order.setItems(rs.getString("items"));
        //             order.setStatus(rs.getString("status"));
        //             if (rs.getTimestamp("prep_time") != null) {
        //                 order.setPrepTime(rs.getTimestamp("prep_time").toLocalDateTime());
        //             }
        //             return order;
        //         }
        //     }
        // }
        return null;
    }
}