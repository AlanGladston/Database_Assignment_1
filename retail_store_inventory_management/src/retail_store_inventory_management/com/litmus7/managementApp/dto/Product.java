package retail_store_inventory_management.com.litmus7.managementApp.dto;

public abstract class Product {
	private int productId;
	private String productName;
	private double price;
	private String status;
	private String category;
	
	
	
	public Product(int productId, String productName, double price, String status, String category) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.status = status;
		this.category = category;
	}
	
	
	//getters and setters of all instance variables
	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	
	
	
}
