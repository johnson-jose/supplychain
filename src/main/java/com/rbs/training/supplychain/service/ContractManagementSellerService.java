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
	
	/*service to fetch the buyer's status for the proposals 
	 * which the seller has already accepted
	 * service 4
*/
	public List<Proposal_Sellers_Bid> fetchBuyerStatus(int seller_id){
  		List<Proposal_Sellers_Bid> lst=null;
  		dbobj = new DatabaseConnectionPostgreSQL();
  	try{
  		
  			con = dbobj.getConnection();
  			lst= new ArrayList<Proposal_Sellers_Bid>();
  			Proposal_Sellers_Bid Proposal_Sellers_Bid_obj = null;
  			Statement st = con.createStatement();

  			ResultSet rs1=st.executeQuery("select proposal_id,bid_seller_id , buyer_status "
  					+ "from proposals where proposal_id in "
  					+ "(select proposal_id from proposal_sellers_bid where "
  					+ "seller_id=" +seller_id+"and seller_status='a')");
  			//System.out.println("1");
  			while(rs1.next()){
  					int selected_seller=rs1.getInt("bid_seller_id");
		//System.out.println(x);
					char status=rs1.getString("buyer_status").charAt(0);
		//System.out.println(y);
					int p_id=rs1.getInt("proposal_id");
		//System.out.println(z);
		if(selected_seller==seller_id && status=='a'){
			//System.out.println("11");
			Statement st1 = con.createStatement();
			st1.executeUpdate("update proposal_sellers_bid set buyer_bid_status='a' where seller_id=" +seller_id+ "and proposal_id="+p_id );
		}
		else if(selected_seller!=seller_id && selected_seller!=0 && status=='a' ){
			Statement st2 = con.createStatement();
			//System.out.println("111");
			st2.executeUpdate("update proposal_sellers_bid set buyer_bid_status='r' where seller_id=" +seller_id+ "and proposal_id="+p_id);
		}
		else if(selected_seller==0){
			Statement st3 = con.createStatement();
			//System.out.println("1111");
			st3.executeUpdate("update proposal_sellers_bid set buyer_bid_status='p' where seller_id=" +seller_id+ "and proposal_id="+p_id);
		}
		
  		
  	}
  	Statement st4 = con.createStatement();
	ResultSet rs2=st4.executeQuery("select * from proposal_sellers_bid where seller_status='a' and seller_id=" + seller_id);
	
	//System.out.println("1");
	while(rs2.next()){
		Proposal_Sellers_Bid_obj = new Proposal_Sellers_Bid();
		Proposal_Sellers_Bid_obj.setProposal_id(rs2.getInt("proposal_id"));
		Proposal_Sellers_Bid_obj.setSeller_id(rs2.getInt("seller_id"));
		Proposal_Sellers_Bid_obj.setSeller_status(rs2.getString("seller_status"));
		//System.out.println(rs2.getString("seller_status").charAt(0));
		Proposal_Sellers_Bid_obj.setBuyer_bid_status(rs2.getString("buyer_bid_status").charAt(0));
		System.out.println(Proposal_Sellers_Bid_obj.toString());
		lst.add(Proposal_Sellers_Bid_obj);
		}
  	

  	}
  	
  
  	catch(Exception e){
  		System.out.println(e.getMessage());
  	}
  	return lst;
  	
  	}
 
 /*service to update the seller's status(accept/reject/later) in the proposal_sellers_bid table 
  */	
 	/* SERVICE 4
  	
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
  	}*/
  }
 
 
