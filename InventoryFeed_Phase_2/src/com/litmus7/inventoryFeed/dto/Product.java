	package com.litmus7.inventoryFeed.dto;

public class Product {
	 private int sku;
	    private String productName;
	    private int quantity;
	    private double price;

	    public Product(int sku, String productName, int quantity, double price) {
	        this.sku = sku;
	        this.productName = productName;
	        this.quantity = quantity;
	        this.price = price;
	    }

	    public int getSku() { return sku; }
	    public String getProductname() { return productName; }
	    public int getQuantity() { return quantity; }
	    public double getPrice() { return price; }
}
