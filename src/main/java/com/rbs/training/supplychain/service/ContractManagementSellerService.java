package com.rbs.training.supplychain.service;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rbs.training.supplychain.DAO.DataBaseConnection;
import com.rbs.training.supplychain.DAO.DatabaseConnectionPostgreSQL;
import com.rbs.training.supplychain.model.Features;
import com.rbs.training.supplychain.model.Proposal;
import com.rbs.training.supplychain.model.Proposal_Sellers_Bid;


 @Service("contractmanagementsellerservice")
 
public class ContractManagementSellerService {
	 static DatabaseConnectionPostgreSQL dbobj;
	 static Connection con;
	 
	/*service to fetch all the request for proposals(rfps)*/
	 
 /*service 1*/
	 public List<Proposal_Sellers_Bid> listAllProposals(int seller_id) throws SQLException{
	 	
	 		//int seller_id=1;
			dbobj = new DatabaseConnectionPostgreSQL();
			List<Proposal_Sellers_Bid> lst = new ArrayList<Proposal_Sellers_Bid>();
			Proposal_Sellers_Bid Proposal_Sellers_Bid_obj = null;
			try{
				con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from \"Proposal_sellers_bid\" where seller_id='"+seller_id+"'");
				//ResultSet rs = stmt.executeQuery("select * from proposal_sellers_bid where seller_id='"+seller_id+"'");
				/*Proposal_Sellers_Bid_obj = new Proposal_Sellers_Bid();*/
			
				while(rs.next()){
					Proposal_Sellers_Bid_obj = new Proposal_Sellers_Bid();
					Proposal_Sellers_Bid_obj.setProposal_id(rs.getInt("proposal_id"));
					Proposal_Sellers_Bid_obj.setSeller_id(rs.getInt("seller_id"));
					Proposal_Sellers_Bid_obj.setCost_avail(rs.getInt("cost_avail"));
					lst.add(Proposal_Sellers_Bid_obj);
					}
			}catch(Exception e){							
				System.out.println(e.getMessage());
			}finally{
					try {
						dbobj.closeConnection();
						} catch (SQLException e) {
							e.printStackTrace();
						}
			}
		return lst;			
	}
	
	
	
	
	
/* function to list a particular proposal if the proposal id is passed from proposal table
  */	
 
	/*service 2*/
	
	public List<Features> getFeaturesforaproduct(int proposal_id) throws ClassNotFoundException, SQLException{
	
		List<Features> lst= new ArrayList<Features>();
		Features features = null;
		dbobj = new DatabaseConnectionPostgreSQL();
		
		con = dbobj.getConnection();
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select \"Features\".feature_id, \"Features\".product_id, \"Features\".specification, \"Features\".priority_order, \"Features\".attachment from \"Features\",\"Products\" where \"Features\".product_id=\"Products\".product_id AND \"Products\".proposal_id ="+proposal_id);
		
		
		while(rs.next()){
			features = new Features();
			features.setFeature_id(rs.getInt("feature_id"));
			features.setProduct_id(rs.getInt("product_id"));
			features.setSpecification(rs.getString("specification"));
			features.setPriority_order(rs.getString("priority_order"));
			lst.add(features);
		}
		return lst;
		
	}


 
 /*service to update the seller's status(accept/reject/later) in the proposal_sellers_bid table 
  */	
 	/* SERVICE 4*/
  	
  	public void updateBidSellerStatus(int seller_id,int proposal_id,String seller_status){
  	try{
  	 dbobj = new DatabaseConnectionPostgreSQL();
  	 con = dbobj.getConnection();
  	Statement st = con.createStatement();
  	con.commit();
  	st.executeUpdate("update proposal_sellers_bid set seller_status= " +seller_status+ " where seller_id=" +seller_id+ " and proposal_id="+proposal_id);
  	}
  	catch(Exception e){
  		System.out.println(e.getMessage());
  	}
  	}
  }
 
