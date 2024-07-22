package com.deynak.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydbtest";
        String user = "bestuser";
        String password = "bestuser";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
            // Your database interaction logic here
            if (connection != null) {
                System.out.println("Connection successful");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            connection.close();
        }
        return connection;
    }
}
