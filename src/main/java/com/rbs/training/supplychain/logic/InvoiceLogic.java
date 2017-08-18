package com.rbs.training.supplychain.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rbs.training.supplychain.DAO.DataBaseConnection;
import com.rbs.training.supplychain.model.Invoice;

public class InvoiceLogic {
	
	public Invoice search(String invoiceNo) throws ClassNotFoundException, SQLException{
		DataBaseConnection dbobj = new DataBaseConnection();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				int i=0;
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from invoice where invoiceNo=\" "+invoiceNo+" \" ");
				invobj = new Invoice();
				while(rs.next()){
					invobj.setInvoiceNo(rs.getString("invoiceNo"));
					invobj.setBuyerId(rs.getString("buyerId"));
					invobj.setSellerId(rs.getString("sellerId"));
					invobj.setTax(rs.getDouble("tax"));
					
					System.out.println(invobj);
					i++;
			}
			}
			catch(Exception e)
			{							
				System.out.println(e);
			}
		return invobj;			
		
		
		
	}

}
