package retail_store_inventory_management.com.litmus7.managementApp.dto;

public class Clothing extends Product{
	private String size;
	private String material;
	
	public Clothing(int productId, String productName, double price, String status, String category,String size
			,String material) {
		super(productId, productName, price, status, category);
		this.size = size;
		this.material = material;
	
	}
	
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}

}
