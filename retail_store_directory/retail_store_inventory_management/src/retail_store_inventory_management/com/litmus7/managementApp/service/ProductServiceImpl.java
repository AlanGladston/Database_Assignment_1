package retail_store_inventory_management.com.litmus7.managementApp.service;

import java.util.Comparator;
import java.util.List;

import retail_store_inventory_management.com.litmus7.managementApp.dao.ProductDAO;
import retail_store_inventory_management.com.litmus7.managementApp.dao.ProductDAOImpl;
import retail_store_inventory_management.com.litmus7.managementApp.dto.Product;
import retail_store_inventory_management.com.litmus7.managementApp.dto.Response;
import retail_store_inventory_management.com.litmus7.managementApp.exception.ProductDaoException;

public class ProductServiceImpl implements ProductService {
	private final ProductDAO productDao;

	public ProductServiceImpl(ProductDAO dao) {
	    this.productDao = dao;
	}
	@Override
	public Response<Product> addProduct(Product product) {
		
		// TODO Auto-generated method stub
		 try {
			 	// Imagine DAO layer inserting product into DB
		        ProductDAO productDao = new ProductDAOImpl();
			 	productDao.save(product);

		        return new Response<>(200, "Product added successfully", product);
		    }
		 catch (ProductDaoException e) {  
		        return new Response<>(500, "Failed to add product: " + e.getMessage(), null);
		    }
	}

	@Override
	public  Response<List<Product>> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  Response<List<Product>> getProductsByCategory(String category)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  Response<List<Product>> sortProducts(Comparator<Product> comparator)  {
		// TODO Auto-generated method stub
		return null;
	}

}
