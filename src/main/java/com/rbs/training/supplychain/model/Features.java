package com.rbs.training.supplychain.model;
import java.io.File;

public class Features {
	private int f_id;
	private int p_id;
	private int proposal_id;
	private String priority_order;
	private String attachment;
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getProposal_id() {
		return proposal_id;
	}
	public void setProposal_id(int proposal_id) {
		this.proposal_id = proposal_id;
	}
	public String getPriority_order() {
		return priority_order;
	}
	public void setPriority_order(String priority_order) {
		this.priority_order = priority_order;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	
	
}