package com.example.course2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private Connection connection;
    private final String url = "jdbc:postgresql://localhost:5432/couremina";
    private final String username = "postgres";
    private final String password = "12qwaszx";

    public Connection getConnection() {
        try {
            return connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
