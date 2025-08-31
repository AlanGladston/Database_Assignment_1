package com.litmus7.inventoryFeed.service;


import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.inventoryFeed.dao.ProductDao;
import com.litmus7.inventoryFeed.dto.Product;
import com.litmus7.inventoryFeed.response.Response;
import com.litmus7.inventoryFeed.util.CsvReader;
import com.litmus7.inventoryFeed.util.DBConnection;
import com.litmus7.inventoryFeed.util.FileMover;

public class ProductService {
    private static final Logger log = LogManager.getLogger(ProductService.class);
    private final ProductDao dao = new ProductDao();

    public Response processFile(Path file, Path processedDir, Path errorDir) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            List<Product> products = CsvReader.readProducts(file);
            for (Product p : products) {
                dao.insert(conn, p);
            }

            conn.commit();
            FileMover.moveFile(file, processedDir);
            log.info("Committed transaction for {}", file.getFileName());
            return Response.ok("File processed: " + file.getFileName());
        } catch (Exception e) {
            log.error("Error processing {}: {}", file.getFileName(), e.getMessage());
            try {
                Connection conn = DBConnection.getConnection();
                conn.rollback();
            } catch (SQLException ignored) {}
            try { FileMover.moveFile(file, errorDir); } catch (Exception ignored) {}
            return Response.error("Failed processing: " + file.getFileName());
        }
    }
}