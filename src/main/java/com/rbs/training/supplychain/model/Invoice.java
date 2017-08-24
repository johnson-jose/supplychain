package com.rbs.training.supplychain.model;

public class Invoice {
		public double invoiceNo;
		
		public double contractNo;
		public String buyerId;
		public String sellerId;
		public double quantity;
		public String productId;
		public double unitPrice;
		public double grossAmount;
		public double netAmount;
		public double tax;
		public double draftStatus;
		public double approvalStatus;
		public double financingStatus;
		public double getInvoiceNo() {
			return invoiceNo;
		}
		public void setInvoiceNo(double invoiceNo) {
			this.invoiceNo = invoiceNo;
		}
		public double getContractNo() {
			return contractNo;
		}
		public void setContractNo(double contractNo) {
			this.contractNo = contractNo;
		}
		public String getBuyerId() {
			return buyerId;
		}
		public void setBuyerId(String buyerId) {
			this.buyerId = buyerId;
		}
		public String getSellerId() {
			return sellerId;
		}
		public void setSellerId(String sellerId) {
			this.sellerId = sellerId;
		}
		public double getQuantity() {
			return quantity;
		}
		public void setQuantity(double quantity) {
			this.quantity = quantity;
		}
		public String getProductId() {
			return productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public double getUnitPrice() {
			return unitPrice;
		}
		public void setUnitPrice(double unitPrice) {
			this.unitPrice = unitPrice;
		}
		public double getGrossAmount() {
			return grossAmount;
		}
		public void setGrossAmount(double grossAmount) {
			this.grossAmount = grossAmount;
		}
		public double getNetAmount() {
			return netAmount;
		}
		public void setNetAmount(double netAmount) {
			this.netAmount = netAmount;
		}
		public double getTax() {
			return tax;
		}
		public void setTax(double tax) {
			this.tax = tax;
		}
		public double getDraftStatus() {
			return draftStatus;
		}
		public void setDraftStatus(double draftStatus) {
			this.draftStatus = draftStatus;
		}
		public double getApprovalStatus() {
			return approvalStatus;
		}
		public void setApprovalStatus(double approvalStatus) {
			this.approvalStatus = approvalStatus;
		}
		public double getFinancingStatus() {
			return financingStatus;
		}
		public void setFinancingStatus(double financingStatus) {
			this.financingStatus = financingStatus;
		}
		
}

