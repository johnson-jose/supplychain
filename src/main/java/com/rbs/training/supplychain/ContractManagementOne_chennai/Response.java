package com.rbs.training.supplychain.ContractManagementOne_chennai;

public class Response {
	private int response_id;
	private int feature_id;
	private char response_status;
	private int seller_id;
	
	public int getResponse_id(){
		return response_id;
	}
	public void setResponse_id(int response_id){
		this.response_id = response_id;
	}
	public int getFeature_id(){
		return feature_id;
	}
	public void setFeature_id(int feature_id){
		this.feature_id = feature_id;
	}
	public char getResponse_status(){
		return response_status;
	}
	public void setResponse_status(char response_status){
		this.response_status = response_status;
	}
}