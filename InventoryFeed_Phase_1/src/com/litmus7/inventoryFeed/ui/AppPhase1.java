package com.litmus7.inventoryFeed.ui;

import com.litmus7.inventoryFeed.controller.ProductController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class AppPhase1 {
    private static final Logger log = LogManager.getLogger(AppPhase1.class);

    public static void main(String[] args) {
        try {
            // Root folder
            Path root = Paths.get("inventory-feed");
            Path input = root.resolve("input");
            Path processed = root.resolve("processed");
            Path error = root.resolve("error");

            // Ensure folders exist
            Files.createDirectories(input);
            Files.createDirectories(processed);
            Files.createDirectories(error);

            log.info("Watching folder: {}", input.toAbsolutePath());

            ProductController controller = new ProductController();

            // Scan input folder
            try (Stream<Path> paths = Files.list(input)) {
                paths.forEach(file -> {
                    try {
                        if (Files.isDirectory(file)) {
                            // Skip directories like "data" or "data_1"
                            log.warn("Skipping directory: {}", file.toAbsolutePath());
                            return;
                        }

                        if (!file.toString().toLowerCase().endsWith(".csv")) {
                            // Skip non-CSV files
                            log.warn("Skipping non-CSV file: {}", file.toAbsolutePath());
                            return;
                        }

                        log.info("Processing file: {}", file.getFileName());
                        controller.handleFile(file, processed, error);

                    } catch (Exception e) {
                        log.error("Unexpected error with {}: {}", file.getFileName(), e.getMessage(), e);
                    }
                });
            }

        } catch (Exception e) {
            log.error("Fatal error: {}", e.getMessage(), e);
        }
    }
}
