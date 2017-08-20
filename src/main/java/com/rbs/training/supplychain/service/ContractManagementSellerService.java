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
import com.rbs.training.supplychain.model.Proposal;
import com.rbs.training.supplychain.model.Proposal_Sellers_Bid;


 @Service("contractmanagementsellerservice")
 
public class ContractManagementSellerService {
	 static DatabaseConnectionPostgreSQL dbobj;
	 static Connection con;
	 
	/*service to fetch all the request for proposals(rfps)*/
	 
 
	 public List<Proposal_Sellers_Bid> listAllProposals(int seller_id) throws SQLException{
	 	//public static void main(String [] args){
	 		//int seller_id=1;
			dbobj = new DatabaseConnectionPostgreSQL();
			List<Proposal_Sellers_Bid> lst = new ArrayList<Proposal_Sellers_Bid>();
			Proposal_Sellers_Bid Proposal_Sellers_Bid_obj = null;
			try{
				con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from \"Proposal_sellers_bid\" where seller_id='"+seller_id+"'");
				Proposal_Sellers_Bid_obj = new Proposal_Sellers_Bid();
				while(rs.next()){
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
	
	
/*service to update the seller's status(accept/reject/later) in the proposal_sellers_bid table 
*/	
	
	public void updateSellerResponseStatus(int seller_id){
	try{
	 dbobj = new DatabaseConnectionPostgreSQL();
	 con = dbobj.getConnection();
	Statement st = con.createStatement();
	int res = st.executeUpdate("update proposal_sellers_bid set status=? where seller_id=?;");

	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	}
	
	
/* function to list a particular proposal if the proposal id is passed from proposal table
  */	
 
	//this function is yet to be edited.
	public Proposal_Sellers_Bid getProposalsellersbid(int proposal_id) throws ClassNotFoundException, SQLException{
		dbobj = new DatabaseConnectionPostgreSQL();
		
		con = dbobj.getConnection();
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from \"Proposals\" where proposal_id ="+proposal_id);
		Proposal_Sellers_Bid result = new Proposal_Sellers_Bid();
		while(rs.next()){
			result.setProposal_id(rs.getInt("proposal_id"));
			result.setSeller_id(rs.getInt("seller_id"));
			result.setCost_avail(rs.getInt("cost_avail"));
			result.setCost_avail_cust(rs.getInt("cost_avail_cust"));
			result.setSeller_status(rs.getString("seller_status"));
			result.setScore(rs.getInt("score"));
		}
		dbobj.closeConnection();
		return result;
	}
}

