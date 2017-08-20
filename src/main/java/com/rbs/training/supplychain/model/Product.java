package com.rbs.training.supplychain.model;

public class Product {
	private int product_id;
	private int proposal_id;
	private String product_name;
	private int category_id;
	private int quantity;
	
	
	public int getProduct_id(){
		return product_id;
	}
	public void setProduct_id(int product_id){
		this.product_id = product_id;
	}
	
	public int getProposal_id(){
		return proposal_id;
	}
	public void setProposal_id(int proposal_id){
		this.proposal_id = proposal_id;
	}
	
	public String getProduct_name(){
		return product_name;
	}
	public void setProduct_name(String product_name){
		this.product_name = product_name;
	}
	
	public int getCategory_id(){
		return category_id;
	}
	public void setCategory_id(int category_id){
		this.category_id = category_id;
	}
	
	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
}
