/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection createConnection() throws SQLException {
        try {
            // You have mysql-connector-j in Libraries, so use MySQL driver + URL
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/smart_agri",
                "root",
                "274123"
            );
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found! Add mysql-connector-j to Libraries.", e);
        }
    }
}
