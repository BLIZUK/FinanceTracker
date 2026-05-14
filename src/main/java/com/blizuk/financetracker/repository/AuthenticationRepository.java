package com.blizuk.financetracker.repository;


import com.blizuk.financetracker.db.DatabaseManagerMock;
import com.blizuk.financetracker.model.User;
import com.blizuk.financetracker.model.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthenticationRepository {

    public void save(User u) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManagerMock.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getUserName());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getRole().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findUser(String name)
    {
        String sql = "SELECT  EXISTS (SELECT 1 FROM users WHERE username = ?)";

        try ( Connection conn = DatabaseManagerMock.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return rs.getBoolean(1);
                }

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
