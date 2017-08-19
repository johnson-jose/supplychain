package com.rbs.training.supplychain.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rbs.training.supplychain.DAO.*;
import com.rbs.training.supplychain.model.*;

@Service("invoiceServiceObj")
public class InvoiceService {
	public Invoice search(String invoiceNo){
		DataBaseConnection dbobj = new DataBaseConnection();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from invoice where invoiceNo='"+invoiceNo+"'");
				invobj = new Invoice();
				System.out.println(rs.toString());
				while(rs.next()){
					invobj.setInvoiceNo(rs.getString("invoiceNo"));
					invobj.setBuyerId(rs.getString("buyerId"));
					invobj.setSellerId(rs.getString("sellerId"));
					invobj.setTax(rs.getDouble("tax"));
					System.out.println(invobj.toString());
			}
			}
			catch(Exception e)
			{							
				System.out.println(e);
			}
		return invobj;			
	}
	public List<Invoice> listAllInvocies(String id){
		DataBaseConnection dbobj = new DataBaseConnection();
		List<Invoice> lst = new ArrayList<Invoice>();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from invoice where sellerid='"+id+"'");
				invobj = new Invoice();
				while(rs.next()){
					invobj.setInvoiceNo(rs.getString("invoiceNo"));
					invobj.setBuyerId(rs.getString("buyerId"));
					invobj.setSellerId(rs.getString("sellerId"));
					invobj.setTax(rs.getDouble("tax"));
					System.out.println(invobj.toString());
					lst.add(invobj);
			}
			}
			catch(Exception e)
			{							
				System.out.println(e.getMessage());
			}
		return lst;			
	}
	
	
	

}
