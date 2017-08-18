package com.rbs.training.supplychain.ContractManagementOne_chennai;

public class Product {
	private int product_id;
	private int seller_id;
	private String product_name;
	
	public int getProduct_id(){
		return product_id;
	}
	public void setProduct_id(int product_id){
		this.product_id = product_id;
	}
	
	public int getSeller_id(){
		return seller_id;
	}
	public void setSeller_id(int seller_id){
		this.seller_id = seller_id;
	}
	
	public String getProduct_name(){
		return product_name;
	}
	public void setProduct_name(String product_name){
		this.product_name = product_name;
	}
}
