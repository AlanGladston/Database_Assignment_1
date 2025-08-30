package com.litmus7.inventoryFeed.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnection {
    private static final Logger log = LogManager.getLogger(DBConnection.class);

    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASS = "Example@2025#";

    public static Connection getConnection() throws SQLException {
        log.debug("Opening DB connection to {}", URL);
        return DriverManager.getConnection(URL, USER, PASS);
    }
}