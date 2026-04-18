package com.blizuk.financetracker.repository;

import com.blizuk.financetracker.db.DatabaseManager;
import com.blizuk.financetracker.model.Transaction;
import com.blizuk.financetracker.model.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY created_at DESC";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while(rs.next())
            {
                Transaction tx = new Transaction();

                tx.setId(rs.getLong("id"));
                tx.setAmount(rs.getDouble("amount"));
                tx.setType(TransactionType.valueOf(rs.getString("type")));
                tx.setDescription(rs.getString("description"));
                tx.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                transactions.add(tx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
