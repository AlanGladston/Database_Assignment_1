package com.litmus7.inventoryFeed.util;

import com.litmus7.inventoryFeed.dto.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    private static final Logger log = LogManager.getLogger(CsvReader.class);

    public static List<Product> readProducts(Path file) throws IOException {
        List<Product> products = new ArrayList<>();


        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // skip header line
                    continue;
                }

                String[] tokens = line.split(",");
                if (tokens.length != 4) {
                    log.error("Invalid row in {}: {}", file.getFileName(), line);
                    throw new IOException("Invalid row format: " + line);
                }

                try {
                    int sku = Integer.parseInt(tokens[0].trim());
                    String name = tokens[1].trim();
                    int qty = Integer.parseInt(tokens[2].trim());
                    double price = Double.parseDouble(tokens[3].trim());

                    products.add(new Product(sku, name, qty, price));
                } catch (NumberFormatException e) {
                    log.error("Invalid number in {}: {}", file.getFileName(), line);
                    throw new IOException("Invalid number format in: " + line, e);
                }
            }
        }

        log.info("Read {} products from {}", products.size(), file.getFileName());
        return products;
    }
}