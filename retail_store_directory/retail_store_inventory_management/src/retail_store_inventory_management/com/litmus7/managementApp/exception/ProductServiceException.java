package retail_store_inventory_management.com.litmus7.managementApp.exception;

public class ProductServiceException extends Exception{
	public ProductServiceException(String message,Throwable cause) {
		super(message,cause);
	}
	public ProductServiceException(String message) {
		super(message);
	}
}
