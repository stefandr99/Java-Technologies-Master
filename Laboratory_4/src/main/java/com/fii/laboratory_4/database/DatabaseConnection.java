package com.fii.laboratory_4.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "0000");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }
}
