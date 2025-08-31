package com.litmus7.inventoryFeed.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.inventoryFeed.dto.Product;

public class ProductDao {
    private static final Logger log = LogManager.getLogger(ProductDao.class);

    private static final String SQL =
        "INSERT INTO inventory (sku, product_name, quantity, price) VALUES (?, ?, ?, ?)";

    public void insert(Connection conn, Product p) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, p.getSku());
            ps.setString(2, p.getProductname());
            ps.setInt(3, p.getQuantity());
            ps.setDouble(4, p.getPrice());
            ps.executeUpdate();
            log.debug("Inserted product {} into DB", p.getSku());
        }
    }
}
