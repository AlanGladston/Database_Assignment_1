package retail_store_inventory_management.com.litmus7.managementApp.dto;

import java.sql.Date;

public class Grocery extends Product{
	
	private Date expiryDate;
	private double weightKg;
    
    public Grocery(int productId, String productName, double price, String status, String category,Date expiryDate
    		,double weightKg) {
    	super(productId, productName, price, status, category);
        this.expiryDate = expiryDate;
        this.weightKg = weightKg;
    }

    public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public double getWeightKg() {
		return weightKg;
	}

	public void setWeightKg(double weightKg) {
		this.weightKg = weightKg;
	}
	
	
}
