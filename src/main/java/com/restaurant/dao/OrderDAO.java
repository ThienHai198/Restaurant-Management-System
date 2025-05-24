package com.restaurant.dao;

import com.restaurant.model.Order;
import com.restaurant.model.OrderItem;
import com.restaurant.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public void saveOrder(Order order, List<OrderItem> items) throws SQLException {
        String orderQuery = "INSERT INTO `Order` (table_id, customer_id, status) VALUES (?, ?, ?)";
        String itemQuery = "INSERT INTO OrderItem (order_id, course_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement orderStmt = conn.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS)) {
                orderStmt.setInt(1, order.getTableId());
                orderStmt.setInt(2, order.getCustomerId());
                orderStmt.setString(3, order.getStatus());
                orderStmt.executeUpdate();

                try (ResultSet rs = orderStmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int orderId = rs.getInt(1);
                        order.setOrderId(orderId);

                        // Lưu danh sách món ăn
                        try (PreparedStatement itemStmt = conn.prepareStatement(itemQuery)) {
                            for (OrderItem item : items) {
                                itemStmt.setInt(1, orderId);
                                itemStmt.setInt(2, item.getCourseId());
                                itemStmt.setInt(3, item.getQuantity());
                                itemStmt.addBatch();
                            }
                            itemStmt.executeBatch();
                        }
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public Order getOrderId(int orderId) {
        String query = "SELECT * FROM `Order` WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setTableId(rs.getInt("table_id"));
                    order.setCustomerId(rs.getInt("customer_id"));
                    order.setStatus(rs.getString("status"));
                    order.setPrepTime(rs.getTimestamp("prep_time").toLocalDateTime());
                    return order;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        throw new UnsupportedOperationException("Unimplemented method 'getOrderId'");
    }
        return null;
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        String query = "SELECT * FROM OrderItem WHERE order_id = ?";
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderItem item = new OrderItem();
                    item.setOrderId(rs.getInt("order_id"));
                    item.setCourseId(rs.getInt("course_id"));
                    item.setQuantity(rs.getInt("quantity"));
                    orderItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return orderItems;
}

    public List<Order> getPendingOrders() throws SQLException {
        List<Order> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `Order` WHERE status = 'pending'");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            list.add(order);
        }
        return list;
    }

     public void updateOrderPrepTime(int orderId, LocalDateTime prepTime) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE `Order` SET prep_time=? WHERE order_id=?");
        ps.setTimestamp(1, Timestamp.valueOf(prepTime));
        ps.setInt(2, orderId);
        ps.executeUpdate();
    }

   
}

