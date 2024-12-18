package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/final_project";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";  // Update with your correct password

    // Method to establish and return a database connection
    public static Connection getConnection() throws SQLException {
        try {
            // Establish the connection to the database
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (SQLException e) {
            // Handle any SQL exceptions
            throw new SQLException("Error connecting to the database: " + e.getMessage());
        }
    }
}
