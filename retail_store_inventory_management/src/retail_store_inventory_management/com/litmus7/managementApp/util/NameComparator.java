package retail_store_inventory_management.com.litmus7.managementApp.util;

import java.util.Comparator;

import retail_store_inventory_management.com.litmus7.managementApp.dto.Product;

public class NameComparator implements Comparator<Product> {
	public int compare(Product product1, Product product2) {
		return product1.getProductName().compareToIgnoreCase(product2.getProductName());
	}
}