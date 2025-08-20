package java_emp_mgt.com.litmus7.employeemanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database credentials and URL
    private static final String URL = "jdbc:mysql://localhost:3306/employees"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "Example@2025#"; 
    
    // Private constructor to prevent instantiation
    private DBConnection() {}


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
