package com.rbs.training.supplychain.ContractManagementOne_chennai;
import java.io.File;

public class Features {
	private int feature_id;
	private int proposal_id;
	private char priority_order;
	private String specification;
	private File attachment;
	
	public int getFeature_id(){
		return feature_id;
	}
	public void setFeature_id(int feature_id){
		this.feature_id = feature_id;
	}
	public int getProposal_id(){
		return proposal_id;
	}
	public void setProposal_id(int proposal_id){
		this.proposal_id = proposal_id;
	}
	public char getPriority_order(){
		return priority_order;
	}
	public void setPriority_order(char priority_order){
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
