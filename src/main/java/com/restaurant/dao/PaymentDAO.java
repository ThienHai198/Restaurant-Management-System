package com.restaurant.dao;

import com.restaurant.model.Payment;
import com.restaurant.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {
    public void savePayment(Payment payment) throws SQLException {
        String query = "INSERT INTO payments (order_id, amount, card_details, status) VALUES (?, ?, ?, ?)";
        // try (Connection conn = DBConnection.getConnection();
        //      PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        //     stmt.setInt(1, payment.getOrderId());
        //     stmt.setDouble(2, payment.getAmount());
        //     stmt.setString(3, payment.getCardDetails());
        //     stmt.setString(4, payment.getStatus());
        //     stmt.executeUpdate();
        //     try (ResultSet rs = stmt.getGeneratedKeys()) {
        //         if (rs.next()) {
        //             payment.setPaymentId(rs.getInt(1));
        //         }
        //     }
        // }
    }

    public void updatePaymentStatus(int paymentId, String status) throws SQLException {
        String query = "UPDATE payments SET status = ? WHERE payment_id = ?";
        // try (Connection conn = DBConnection.getConnection();
        //      PreparedStatement stmt = conn.prepareStatement(query)) {
        //     stmt.setString(1, status);
        //     stmt.setInt(2, paymentId);
        //     stmt.executeUpdate();
        // }
    }
}