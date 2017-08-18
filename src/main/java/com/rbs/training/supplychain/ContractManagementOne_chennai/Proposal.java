package com.rbs.training.supplychain.ContractManagementOne_chennai;

public class Proposal {
	private int proposal_id;
	private int buyer_id;
	private int bid_seller_id;
	private int product_id;
	private String status;
	private String description;
	private char buyer_status;
	private int quantity;
	
	Product product;
	
	public int getProposal_id(){
		return proposal_id;
	}
	public void setProposal_id(int proposal_id){
		this.proposal_id = proposal_id;
	}
	public int getBuyer_id(){
		return buyer_id;
	}
	public void setBuyer_id(int buyer_id){
		this.buyer_id = buyer_id;
	}
	public int getBid_seller_id(){
		return bid_seller_id;
	}
	public void setBid_seller_id(int bid_seller_id){
		this.bid_seller_id = bid_seller_id;
	}
	public int getProduct_id(){
		return product_id;
	}
	public void setProduct_id(int product_id){
		this.product_id = product_id;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public char getBuyer_status(){
		return buyer_status;
	}
	public void setBuyer_status(char buyer_status){
		this.buyer_status = buyer_status;
	}
	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
}
