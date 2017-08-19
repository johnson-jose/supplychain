package com.rbs.training.supplychain.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.rbs.training.supplychain.DAO.DataBaseConnection;

public class UpdateSellerResponseStatus {
	public void updateSellerResponseStatus(int seller_id){
	try{
	DataBaseConnection dao = new DataBaseConnection();
	Connection con = dao.getConnection();
	Statement st = con.createStatement();
	int res = st.executeUpdate("update proposal_sellers_bid set status=? where seller_id=?;");

	}
	catch(Exception e){
		System.out.println(e);
	}
	}
}

