package com.rbs.training.supplychain.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.rbs.training.supplychain.DAO.DataBaseConnection;
import com.rbs.training.supplychain.model.ChartOfAccount;
import com.rbs.training.supplychain.model.GeneralLedger;

public class AccountingManagementService {
	
	public List<String> getCOAswiftList()
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		List<String> swiftList=new LinkedList<String>();
		try {
			Connection con = dbobj.getConnection();
			Statement statement = con.createStatement();  
			System.out.println("In getCOAswiftList - After create statement");
			ResultSet resultSet = statement.executeQuery("SELECT productSwiftId FROM ChartOfAccounts");		
			while(resultSet.next())
			{
				swiftList.add(resultSet.getString("productSwiftId"));
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
		return swiftList;
	}
	public List<ChartOfAccount> getCOAList()
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		List<ChartOfAccount> coaList=new LinkedList<ChartOfAccount>();
		try {
			Connection con = dbobj.getConnection();
			Statement statement = con.createStatement();  
			System.out.println("In getCOAswiftList - After create statement");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ChartOfAccounts");		
			while(resultSet.next())
			{
				ChartOfAccount coa=new ChartOfAccount();
				coa.setHead(resultSet.getString("head"));
				coa.setLegalEntity(resultSet.getString("legalEntity"));
				coa.setCountry(resultSet.getString("country"));
				coa.setBranch(resultSet.getString("branch"));
				coa.setProduct(resultSet.getString("product"));
				coa.setCurrency(resultSet.getString("currency"));
				coa.setBook(resultSet.getInt("book"));
				coa.setProductSwiftID(resultSet.getString("productSwiftID"));
				coaList.add(coa);
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
		return coaList;
	}
	public ChartOfAccount getCOA(String swiftID)
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		ChartOfAccount coa=new ChartOfAccount();
		try {
			Connection con = dbobj.getConnection();
			Statement statement = con.createStatement();  
			System.out.println("In getCOAswiftList - After create statement");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ChartOfAccounts where productSwiftID="+swiftID);		
			while(resultSet.next())
			{
				coa.setHead(resultSet.getString("head"));
				coa.setLegalEntity(resultSet.getString("legalEntity"));
				coa.setCountry(resultSet.getString("country"));
				coa.setBranch(resultSet.getString("branch"));
				coa.setProduct(resultSet.getString("product"));
				coa.setCurrency(resultSet.getString("currency"));
				coa.setBook(resultSet.getInt("book"));
				coa.setProductSwiftID(resultSet.getString("productSwiftID"));
				return coa;
			}
			con.close();
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
			System.out.println("before select in viewGList");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM General_Ledger");
			System.out.println("after select in viewGList");
			entries=new ArrayList<GeneralLedger>();
			
			while (resultSet.next()) {
				System.out.println("after select in viewGList");
				GeneralLedger gl=new GeneralLedger();
				gl.setAccountEntryNo(resultSet.getString("Account_Entry_No"));
				gl.setCurrentDate(resultSet.getDate("Current_Date"));
				gl.setPaymentDate(resultSet.getDate("Payment_Date"));
				gl.setTransactionNo(resultSet.getString("Transaction_No"));
				gl.setCustomerAccountNo(resultSet.getString("Customer_Account_No"));
				gl.setSwiftID(resultSet.getString("SWIFTID"));
				gl.setInvoiceNo(resultSet.getString("Invoice_No"));
				gl.setDrOrCr(resultSet.getString("Dr_Cr"));
				gl.setAmount(resultSet.getDouble("Amount"));
				gl.setDueDate(resultSet.getDate("Due_date"));
				System.out.println("WHile loop"+resultSet.getString("Account_Entry_No"));
				System.out.println(gl.toString());
				entries.add(gl);	
			}
			con.close();
			}
			catch(Exception e) {
				System.out.println("Exception " + e.getMessage());
			}
		
			return entries;
		}
	
	public List<GeneralLedger> getEachGLEntryBySearch(String acEntryNo,String transNo,String custAcNo,String swiftID,String invoiceNo,String drCr,String paymentDate,String dueDate) {
		DataBaseConnection dbobj = new DataBaseConnection();
		List<GeneralLedger> entries = null;
		
			try{
				Connection con = dbobj.getConnection();
				Statement statement = 	con.createStatement();
				ResultSet resultSet=null;
				if(paymentDate.equals("")&&dueDate.equals(""))
					resultSet = statement.executeQuery("SELECT * FROM General_Ledger where (Account_Entry_no like'%"+acEntryNo+"%' or Account_Entry_no is null) and "
						+"(transaction_no like '%"+transNo+"%' or transaction_no is null) and "
						+"(customer_account_no like '%"+custAcNo+"%' or customer_account_no is null) and "
						+"(swiftID like '%"+swiftID+"%' or swiftID is null) and "
						+"(invoice_no like '%"+invoiceNo+"%' or invoice_no is null) and "
						+"(dr_cr like '%"+drCr+"%' or dr_cr is null) and "
						+"(payment_date like '%"+paymentDate +"%' or payment_date is null) and "
						+"(due_date like '%"+dueDate+"%' or due_date is null)");
				else if(paymentDate.equals(""))
					resultSet = statement.executeQuery("SELECT * FROM General_Ledger where (Account_Entry_no like'%"+acEntryNo+"%' or Account_Entry_no is null) and "
							+"(transaction_no like '%"+transNo+"%' or transaction_no is null) and "
							+"(customer_account_no like '%"+custAcNo+"%' or customer_account_no is null) and "
							+"(swiftID like '%"+swiftID+"%' or swiftID is null) and "
							+"(invoice_no like '%"+invoiceNo+"%' or invoice_no is null) and "
							+"(dr_cr like '%"+drCr+"%' or dr_cr is null) and "
							+"(payment_date like '%"+paymentDate +"%' or payment_date is null) and "
							+"(due_date = '"+dueDate+"')");
				else if(dueDate.equals(""))
					resultSet = statement.executeQuery("SELECT * FROM General_Ledger where (Account_Entry_no like'%"+acEntryNo+"%' or Account_Entry_no is null) and "
						+"(transaction_no like '%"+transNo+"%' or transaction_no is null) and "
						+"(customer_account_no like '%"+custAcNo+"%' or customer_account_no is null) and "
						+"(swiftID like '%"+swiftID+"%' or swiftID is null) and "
						+"(invoice_no like '%"+invoiceNo+"%' or invoice_no is null) and "
						+"(dr_cr like '%"+drCr+"%' or dr_cr is null) and "
						+"(payment_date = '"+paymentDate +"') and "
						+"(due_date like '%"+dueDate+"%' or due_date is null)");
				else
					resultSet = statement.executeQuery("SELECT * FROM General_Ledger where (Account_Entry_no like'%"+acEntryNo+"%' or Account_Entry_no is null) and "
						+"(transaction_no like '%"+transNo+"%' or transaction_no is null) and "
						+"(customer_account_no like '%"+custAcNo+"%' or customer_account_no is null) and "
						+"(swiftID like '%"+swiftID+"%' or swiftID is null) and "
						+"(invoice_no like '%"+invoiceNo+"%' or invoice_no is null) and "
						+"(dr_cr like '%"+drCr+"%' or dr_cr is null) and "
						+"(payment_date = '"+paymentDate +"') and "
						+"(due_date = '"+dueDate+"')");
			entries=new ArrayList<GeneralLedger>();
			while (resultSet.next()) {
				GeneralLedger gl=new GeneralLedger();
				gl.setAccountEntryNo(resultSet.getString("Account_Entry_No"));
				gl.setCurrentDate(resultSet.getDate("Current_Date"));
				gl.setPaymentDate(resultSet.getDate("Payment_Date"));
				gl.setTransactionNo(resultSet.getString("Transaction_No"));
				gl.setCustomerAccountNo(resultSet.getString("Customer_Account_No"));
				gl.setSwiftID(resultSet.getString("SWIFTID"));
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
	
	public void deleteCOA(List<String> swiftIDList)
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
        	Statement stmt=con.createStatement(); 
        	//String[] chartNamesToDelete=request.getParameterValues("chartGroup");
        	for(String sID:swiftIDList)
        		stmt.executeUpdate("delete from ChartOfAccounts where productSwiftID='"+sID+"'");  
      		con.commit();
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
	}
	public void addCOAService(ChartOfAccount coa)
	{
		DataBaseConnection dbobj = new DataBaseConnection();
		try
	    {
			Connection con = dbobj.getConnection();
			System.out.println("IN addCOAService - after getConnection");
        	Statement stmt=con.createStatement(); 
        	System.out.println("IN addCOAService - after createStatement");
        	/*rs=stmt.executeQuery("select Name from ChartOfAccounts");  
         	
       		while(rs.next())  
       		{
       			//out.print("alert("+rs.getString("Name")+"\t"+request.getParameter("newTextEntry")+");");
       			if(rs.getString("Name").equals(request.getParameter("newTextEntry")))
       			{
       				response.sendRedirect("redirectPage.jsp");
       			}
       		}*/
        	stmt.executeUpdate("insert into ChartOfAccounts values('"+coa.getHead()+"','"+coa.getLegalEntity()+"','"+coa.getCountry()+"','"+coa.getBranch()+"','"+coa.getProduct()+"','"+coa.getCurrency()+"',"+coa.getBook()+",'"+coa.getProductSwiftID()+"')");
      		con.commit();
      		con.close();
	    }

	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
	}
	public   List<String> sanctionedCountries(){  
		 
		 
		 List<String> lstCountries =null;
		 
		 DataBaseConnection dbobj = new DataBaseConnection();
			try
		    {
				Connection con = dbobj.getConnection();
   	  
			   	//Step 3 Create the statement object
			       Statement stmt = con.createStatement();
			       
			       //Step 4 execute query
			       ResultSet rs = stmt.executeQuery("select * from sanctionedCountries");
			       lstCountries = new ArrayList<String>();
			      while(rs.next()){
			       	String Country = rs.getString("countryName");
			       	lstCountries.add(Country);     
			      } 
			       
			       //Step 5 close the connection
			       con.close();
			
			    }
			     catch(Exception e){
			   	  System.out.println("Exception - get rectangle"+e.getMessage());
			     }
			     
     return lstCountries;
}
	 
public   List<String> sanctionedIndividuals(){  
		 
	
		 List<String> lstNames =null;
		 DataBaseConnection dbobj = new DataBaseConnection();
			try
		    {
				Connection con = dbobj.getConnection();
   	  
				   	//Step 3 Create the statement object
				       Statement stmt = con.createStatement();
				       
				       //Step 4 execute query
				
				      
				      ResultSet rs1 = stmt.executeQuery("select * from sanctionedIndividuals");
				      lstNames = new ArrayList<String>();
				     while(rs1.next()){
				      	String name = rs1.getString("IndividualName");
				      	lstNames.add(name);     
				     } 
				       
				       //Step 5 close the connection
				       con.close();
				
				    }
				     catch(Exception e){
				   	  System.out.println("Exception - get rectangle"+e.getMessage());
     }
     
     return lstNames;
}
}
