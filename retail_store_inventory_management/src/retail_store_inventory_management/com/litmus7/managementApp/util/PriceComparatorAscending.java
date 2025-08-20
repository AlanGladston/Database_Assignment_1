package retail_store_inventory_management.com.litmus7.managementApp.util;

import java.util.Comparator;

import retail_store_inventory_management.com.litmus7.managementApp.dto.Product;


public class PriceComparatorAscending implements Comparator<Product>{
	public int compare(Product t1,Product t2) {
		return Double.compare(t1.getPrice(),t2.getPrice());
	}

}
