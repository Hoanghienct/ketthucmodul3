package org.example.ketthucm3.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/TComplexManagement";
        private static final String USER = "root";
        private static final String PASSWORD = "hien2003";

        public static Connection getConnection() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Database connection failed!");
            }
        }
    }


