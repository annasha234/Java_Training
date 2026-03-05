package org.example;
import java.sql.*;
public class DatabaseConfig {
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/capgemini","root","root12");
    }
}
