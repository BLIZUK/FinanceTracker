package com.blizuk.financetracker.repository;


import com.blizuk.financetracker.db.DatabaseManager;
import com.blizuk.financetracker.model.User;
import com.blizuk.financetracker.model.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthenticationRepository {

    public void save(User u) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getUserName());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getRole().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsByUsername(String username) {
        String sql = "SELECT  EXISTS (SELECT 1 FROM users WHERE username = ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User authenticateUser(String username, String password) {
        String sql = "SELECT id, username, password, role FROM users WHERE username = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    if (password.equals(rs.getString("password"))) {
                        User user = new User(
                                rs.getString("username"),
                                UserRole.valueOf(rs.getString("role"))
                        );
                        user.setId(rs.getLong("id"));
                        return user;
                    } else {
                        return null; // если неверен пароль
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // если не найден
    }
}
