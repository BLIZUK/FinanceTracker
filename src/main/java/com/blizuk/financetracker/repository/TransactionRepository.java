package com.blizuk.financetracker.repository;

// import com.blizuk.financetracker.db.DatabaseManager; // ВОССТАНОВИТЬ В РЕЛИЗ
import com.blizuk.financetracker.db.DatabaseManagerMock; // МОК БД
import com.blizuk.financetracker.model.Transaction;
import com.blizuk.financetracker.model.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    public void save(Transaction tx) {
        String sql = "INSERT INTO transactions (user_id, amount, type, category_id, description, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManagerMock.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, tx.getUserId());
            stmt.setDouble(2, tx.getAmount());
            stmt.setString(3, tx.getType().name());
            stmt.setLong(4, tx.getCategoryId());
            stmt.setString(5, tx.getDescription());
            stmt.setTimestamp(6, Timestamp.valueOf(tx.getCreatedAt()));

            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY created_at DESC";

        try (Connection conn = DatabaseManagerMock.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transaction tx = new Transaction(rs.getLong("id"),
                        rs.getDouble("amount"), TransactionType.valueOf(rs.getString("type")),
                        rs.getLong("categoryId"), rs.getString("description"),
                        rs.getTimestamp("created_at").toLocalDateTime());

                transactions.add(tx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void del(int id) {
        String sql = "DELETE FROM transactions WHERE id = ?";
        try (Connection conn = DatabaseManagerMock.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Установка значения параметра (например, id = 1)
            stmt.setInt(1, id);

            // Выполнение запроса
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Запись успешно удалена!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
