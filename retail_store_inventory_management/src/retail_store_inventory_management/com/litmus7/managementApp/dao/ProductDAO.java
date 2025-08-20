package retail_store_inventory_management.com.litmus7.managementApp.dao;

import java.util.List;

import retail_store_inventory_management.com.litmus7.managementApp.dto.Product;
import retail_store_inventory_management.com.litmus7.managementApp.exception.ProductDaoException;


public interface ProductDAO {
	void save(Product product) throws ProductDaoException ;
	List<Product> findAll() throws ProductDaoException ;
	List<Product> findByCategory(String category) throws ProductDaoException;
	
}
