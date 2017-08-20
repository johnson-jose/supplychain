package com.rbs.training.supplychain.service;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rbs.training.supplychain.DAO.DataBaseConnection;
import com.rbs.training.supplychain.model.Proposal_Sellers_Bid;


 @Service("ContractManagementSellerServiceObj")
 
public class ContractManagementSellerService {
	
	/*service to fetch all the request for proposals(rfps)*/
	
	public List<Proposal_Sellers_Bid> listAllProposals(int seller_id){
		
		
		DataBaseConnection dbobj = new DataBaseConnection();
		List<Proposal_Sellers_Bid> lst = new ArrayList<Proposal_Sellers_Bid>();
		Proposal_Sellers_Bid Proposal_Sellers_Bid_obj = null;
		try{
			Connection con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from proposal_sellers_bid where seller_id='"+seller_id+"'");
				Proposal_Sellers_Bid_obj = new Proposal_Sellers_Bid();
				while(rs.next()){
					Proposal_Sellers_Bid_obj.setProposal_id(rs.getInt("proposal_id"));
					Proposal_Sellers_Bid_obj.setSeller_id(rs.getInt("seller_id"));
					Proposal_Sellers_Bid_obj.setCost_avail(rs.getInt("cost_avail"));
	
					System.out.println(Proposal_Sellers_Bid_obj.toString());
					lst.add(Proposal_Sellers_Bid_obj);
			}
			}
			catch(Exception e)
			{							
				System.out.println(e.getMessage());
			}
		return lst;			
	}
	
	
/*sevice to update the seller's status(accept/reject/later) in the proposal_sellers_bid table 
*/	
	
	public void updateSellerResponseStatus(int seller_id){
	try{
	DataBaseConnection dao = new DataBaseConnection();
	Connection con = dao.getConnection();
	Statement st = con.createStatement();
	int res = st.executeUpdate("update proposal_sellers_bid set status=? where seller_id=?;");

	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	}
}

