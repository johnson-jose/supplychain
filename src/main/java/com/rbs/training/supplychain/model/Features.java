package com.rbs.training.supplychain.model;
import java.io.File;

public class Features {
	private int feature_id;
	private int product_id;
	private String priority_order;
	private String specification;
	private File attachment;
	
	public int getFeature_id(){
		return feature_id;
	}
	public void setFeature_id(int feature_id){
		this.feature_id = feature_id;
	}
	public int getProduct_id(){
		return product_id;
	}
	public void setProduct_id(int product_id){
		this.product_id = product_id;
	}
	public String getPriority_order(){
		return priority_order;
	}
	public void setPriority_order(String priority_order){
		this.priority_order = priority_order;
	}
	public void setSpecification(String specification){
		this.specification = specification;
	}
	public String getSpecification(){
		return specification;
	}
	public File getAttachment(){
		return attachment;
	}
	public void setAttachment(File attachment){
		this.attachment = attachment;
	}
}
