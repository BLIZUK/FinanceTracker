package com.blizuk.financetracker.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://26.46.33.202:5432/finance";
    private static final String USER = "blizuk";
    private static final String PASSWORD = "200503";

    public static Connection getConnection () throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD );
    }

}
