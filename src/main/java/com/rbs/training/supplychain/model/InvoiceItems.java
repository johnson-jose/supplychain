package com.rbs.training.supplychain.model;

public class InvoiceItems {
	private double itemNo;
	public double getItemNo() {
		return itemNo;
	}
	public void setItemNo(double itemNo) {
		this.itemNo = itemNo;
	}
	public double getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(double invoiceID) {
		this.invoiceID = invoiceID;
	}
	public double getProductID() {
		return ProductID;
	}
	public void setProductID(double productID) {
		ProductID = productID;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public float getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(float grossAmount) {
		this.grossAmount = grossAmount;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public float getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(float netAmount) {
		this.netAmount = netAmount;
	}
	private double  invoiceID;
	private double  ProductID;
	private double quantity ;
	private float grossAmount;
	private float tax;
	private float netAmount;
}

