package com.rbs.training.supplychain.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rbs.training.supplychain.DAO.DataBaseConnection;
import com.rbs.training.supplychain.model.ChartOfAccount;
import com.rbs.training.supplychain.model.GeneralLedger;

public class AccountingManagementService {
	public ChartOfAccount getChartOfAccountValues(String ProductSwiftID) {
		DataBaseConnection dbobj = new DataBaseConnection();
		ChartOfAccount coa = null;
			try {
				Connection con = dbobj.getConnection();
				Statement statement = con.createStatement();       
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ChartOfAccounts where ProductSwiftID='"+ProductSwiftID+"'");
			coa=new ChartOfAccount();
			if (resultSet.next()) {
				coa.setHead(resultSet.getString("Head"));
				coa.setLegalEntity(resultSet.getString("LegalEntity"));
				coa.setCountry(resultSet.getString("Country"));
				coa.setBranch(resultSet.getString("Branch"));
				coa.setProduct(resultSet.getString("Product"));
				coa.setCurrency(resultSet.getString("Currency"));
				coa.setBook(resultSet.getInt("Book"));
				coa.setProductSwiftID(resultSet.getString("ProductSwiftID"));			
			}
			}
			catch(Exception e) {
				System.out.println("Exception " + e.getMessage());
			}
			return coa;
		}
	public List<GeneralLedger> getEachGLEntry() {
		DataBaseConnection dbobj = new DataBaseConnection();
		List<GeneralLedger> entries = null;
			try {
				Connection con = dbobj.getConnection();
			Statement statement = 	con.createStatement();       
			ResultSet resultSet = statement.executeQuery("SELECT * FROM General_Ledger");
			entries=new ArrayList<GeneralLedger>();
			GeneralLedger gl=new GeneralLedger();
			while (resultSet.next()) {
				gl.setAccountEntryNo(resultSet.getString("Account_Entry_No"));
				gl.setCurrentDate(resultSet.getDate("Current_Date"));
				gl.setPaymentDate(resultSet.getDate("Payment_Date"));
				gl.setTransactionNo(resultSet.getString("Transaction_No"));
				gl.setCustomerAccountNo(resultSet.getString("Customer_Account_No"));
				gl.setInvoiceNo(resultSet.getString("Invoice_No"));
				gl.setDrOrCr(resultSet.getString("Dr_Cr"));
				gl.setAmount(resultSet.getDouble("Amount"));
				gl.setDueDate(resultSet.getDate("Due_date"));
				
				entries.add(gl);			
			}
			}
			catch(Exception e) {
				System.out.println("Exception " + e.getMessage());
			}
			return entries;
		}

}
