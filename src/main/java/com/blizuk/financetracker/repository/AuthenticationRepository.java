package com.blizuk.financetracker.repository;


import com.blizuk.financetracker.db.DatabaseManagerMock;
import com.blizuk.financetracker.model.User;
import com.blizuk.financetracker.model.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AuthenticationRepository {
    public void save (User u)
    {
         String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
         try (Connection conn = DatabaseManagerMock.getConnection();
              PreparedStatement stmt = conn.prepareStatement(sql)) {

             stmt.setString(1, u.getUserName());
             stmt.setString(2, u.getPassword());
             stmt.setString(3, u.getRole().name());

             stmt.executeUpdate();

         }catch (SQLException e){
             e.printStackTrace();
         }
    }
}
