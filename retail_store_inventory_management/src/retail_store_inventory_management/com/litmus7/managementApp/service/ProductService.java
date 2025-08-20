package retail_store_inventory_management.com.litmus7.managementApp.service;

import java.util.Comparator;
import java.util.List;

import retail_store_inventory_management.com.litmus7.managementApp.dto.Product;
import retail_store_inventory_management.com.litmus7.managementApp.dto.Response;

public interface ProductService {
	public  Response<Product> addProduct(Product product);
	public  Response<List<Product>> getAllProducts();
	public  Response<List<Product>> getProductsByCategory(String category);
	public  Response<List<Product>> sortProducts( Comparator<Product> comparator);
}
