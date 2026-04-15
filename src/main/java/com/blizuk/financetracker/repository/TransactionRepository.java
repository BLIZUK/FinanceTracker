package com.blizuk.financetracker.repository;

import com.blizuk.financetracker.db.DatabaseManager;
import com.blizuk.financetracker.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TransactionRepository {

    public void save(Transaction tx) {
        String sql = "INSERT INTO transactions (amount, type, category_id, description, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, tx.getAmount());
            stmt.setString(2, tx.getType().name());
            stmt.setLong(3, tx.getCategoryId());
            stmt.setString(4, tx.getDescription());
            stmt.setTimestamp(5, Timestamp.valueOf(tx.getCreatedAt()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
