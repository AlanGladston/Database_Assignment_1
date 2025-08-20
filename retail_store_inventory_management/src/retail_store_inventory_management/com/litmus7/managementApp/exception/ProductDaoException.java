package retail_store_inventory_management.com.litmus7.managementApp.exception;

public class ProductDaoException extends Exception{
	public ProductDaoException(String message,Throwable cause) {
		super(message,cause);
	}
	public ProductDaoException(String message) {
		super(message);
	}

}
