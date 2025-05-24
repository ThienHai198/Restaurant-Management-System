package com.restaurant.dao;

import com.restaurant.model.Table;
import com.restaurant.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableDAO {
    public List<Table> getAvailableTables() throws SQLException {
        List<Table> tables = new ArrayList<>();
        String query = "SELECT * FROM tables WHERE is_occupied = FALSE";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Table table = new Table();
                table.setTableId(rs.getInt("table_id"));
                table.setTableNumber(rs.getInt("table_number"));
                table.setOccupied(rs.getBoolean("is_occupied"));
                tables.add(table);
            }
        }
        return tables;
    }

    public void occupyTable(int tableId) throws SQLException {
        String query = "UPDATE tables SET is_occupied = TRUE WHERE table_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tableId);
            stmt.executeUpdate();
        }
    }
}