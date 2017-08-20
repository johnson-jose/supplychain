package com.rbs.training.supplychain.model;

public class ProductInvoice {
<<<<<<< HEAD
	private String productId;
	private String invoiceNo;
	private double price;
	private String sellerId;
=======
	public String productId;
	public String invoiceNo;
	public double price;
	public String sellerId;
>>>>>>> branch 'master' of https://github.com/johnson-jose/supplychain.git
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
}
