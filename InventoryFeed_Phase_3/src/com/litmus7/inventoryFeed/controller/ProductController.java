package com.litmus7.inventoryFeed.controller;


import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.inventoryFeed.response.Response;
import com.litmus7.inventoryFeed.service.ProductService;

public class ProductController {
    private static final Logger log = LogManager.getLogger(ProductController.class);
    private final ProductService service = new ProductService();

    public Response handleFile(Path file, Path processedDir, Path errorDir) {
        log.info("Handling file {}", file.getFileName());
        return service.processFile(file, processedDir, errorDir);
    }
}
