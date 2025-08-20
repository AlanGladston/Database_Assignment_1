package retail_store_inventory_management.com.litmus7.managementApp.controller;

import java.util.List;
import java.util.Comparator;

import retail_store_inventory_management.com.litmus7.managementApp.dto.Product;
import retail_store_inventory_management.com.litmus7.managementApp.dto.Response;
import retail_store_inventory_management.com.litmus7.managementApp.service.ProductService;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    public Response<Product> addProduct(Product product) {
        return productService.addProduct(product);
    }

    public Response<List<Product>> viewAllProducts() {
        return productService.getAllProducts();
    }

    public Response<List<Product>> viewProductsByCategory(String category) {
        return productService.getProductsByCategory(category);
    }

    public Response<List<Product>> sortProducts(Comparator<Product> comparator) {
        return productService.sortProducts(comparator);
    }
}
