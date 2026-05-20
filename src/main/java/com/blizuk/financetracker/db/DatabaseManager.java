package com.blizuk.financetracker.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseManager.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("Не удалось найти файл database.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтение конфигурации БД", e);
        }
    }


    public static Connection getConnection () throws SQLException
    {
        return DriverManager.getConnection(
                properties.getProperty("dbMock.url"),
                properties.getProperty("dbMock.user"),
                properties.getProperty("dbMock.password")
        );
    }
}
