package com.litmus7.inventoryFeed.ui;

import com.litmus7.inventoryFeed.controller.ProductController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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

            // Create a fixed thread pool (5 threads)
            ExecutorService executor = Executors.newFixedThreadPool(5);

            // Scan input folder and submit tasks
            try (Stream<Path> paths = Files.list(input)) {
                paths.forEach(file -> {
                    try {
                        if (Files.isDirectory(file)) {
                            log.warn("Skipping directory: {}", file.toAbsolutePath());
                            return;
                        }

                        if (!file.toString().toLowerCase().endsWith(".csv")) {
                            log.warn("Skipping non-CSV file: {}", file.toAbsolutePath());
                            return;
                        }

                        // Submit task to thread pool
                        executor.submit(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    log.info("Started processing in thread: {} for file: {}",
                                             Thread.currentThread().getName(), file.getFileName());
                                    controller.handleFile(file, processed, error);
                                    log.info("Finished processing file: {}", file.getFileName());
                                } catch (Exception e) {
                                    log.error("Thread error for file {}: {}", file.getFileName(), e.getMessage(), e);
                                }
                            }
                        });

                    } catch (Exception e) {
                        log.error("Unexpected error with {}: {}", file.getFileName(), e.getMessage(), e);
                    }
                });
            }

            
            executor.shutdown();
            try {
                if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                    log.warn("Timeout reached before finishing all tasks.");
                }
            } catch (InterruptedException e) {
                log.error("Executor interrupted: {}", e.getMessage(), e);
            }

        } catch (Exception e) {
            log.error("Fatal error: {}", e.getMessage(), e);
        }
    }
}
