package com.clinic.config;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {

        try {
            Connection connection = DatabaseConnection.getConnection();

            if (connection != null) {
                System.out.println("Database Connected Successfully!");
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
    }
}