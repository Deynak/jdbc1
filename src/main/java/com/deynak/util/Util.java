package com.deynak.util;


import java.sql.*;

public class Util {

    static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydbtest", "bestuser", "bestuser");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            connection.close();
        }
        return connection;
    }
}
