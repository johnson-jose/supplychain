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
import com.rbs.training.supplychain.model.Sfeatures;


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
					Proposal_Sellers_Bid_obj.setScore(rs.getInt("score"));
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
	
	 public List<Sfeatures> getFeaturesforaproduct(int proposal_id) throws ClassNotFoundException, SQLException{
			
			List<Sfeatures> lst= new ArrayList<Sfeatures>();
			Sfeatures features = null;
			dbobj = new DatabaseConnectionPostgreSQL();
			
			con = dbobj.getConnection();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from \"Features\" where proposal_id ="+proposal_id);
			
			
			while(rs.next()){
				Statement stmt2=con.createStatement();
				int fid=rs.getInt("f_id");
				int pid=rs.getInt("p_id");
				
				features = new Sfeatures();
				features = new Sfeatures();
				features.setF_id(rs.getInt("f_id"));
				features.setP_id(rs.getInt("p_id"));
				features.setProposal_id(rs.getInt("proposal_id"));
				features.setPriority_order(rs.getString("priority_order"));
				features.setAttachment(rs.getString("attachment"));
				ResultSet rs2 = stmt2.executeQuery("select * from \"feature\" where feature_id ="+fid+"and product_id="+pid);
				while(rs2.next()){
					
					features.setFeature_name(rs2.getString("feature_name"));
					System.out.println(features.getFeature_name());
					
				}
				lst.add(features);
				
			}
			return lst;
			
		}

	//service 3
	 
	 public void updatesellerresponse(int productid, int proposalid, int featureid, int sellerid, String response) throws ClassNotFoundException, SQLException {

			
		 dbobj = new DatabaseConnectionPostgreSQL();
		 con = dbobj.getConnection();
		 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from \"Response\" where p_id="+productid+" AND f_id=" + featureid + " AND" + " seller_id="+sellerid+" AND proposal_id="+proposalid);
		 if(!rs.next()){
			 stmt.executeUpdate("insert into \"Response\" (response_status,seller_id,f_id,p_id,proposal_id) VALUES('"+response+"',"+sellerid+","+featureid+","+productid+","+proposalid+");");
		 }
		 else
		 {
			 stmt.execute("delete from \"Response\" where p_id="+productid+" AND f_id=" + featureid + " AND" + " seller_id="+sellerid+" AND proposal_id="+proposalid);
			 stmt.executeUpdate("insert into \"Response\" VALUES('"+response+"',"+sellerid+","+featureid+","+productid+","+proposalid+")");

		 }
		
	 }

	 
	 
	 
	 
	/*service to fetch the buyer's status for the proposals 
	 * which the seller has already accepted
	 * service 4
*/
	public List<Proposal_Sellers_Bid> fetchBuyerStatus(int seller_id) throws SQLException{
 		 //lst = null;
		DatabaseConnectionPostgreSQL dbobj = new DatabaseConnectionPostgreSQL();
		List<Proposal_Sellers_Bid> lst= new ArrayList<Proposal_Sellers_Bid>();
		Proposal_Sellers_Bid Proposal_Sellers_Bid_obj = null;
		 
		try{
		
 	
 		
 			Connection con = dbobj.getConnection();
 			
 			Statement st = con.createStatement();

 			ResultSet rs1=st.executeQuery("select \"Proposals\".proposal_id,\"Proposals\".bid_seller_id , buyer_status "
 					+ "from \"Proposals\" where \"Proposals\".proposal_id in "
 					+ "(select \"Proposal_sellers_bid\".proposal_id from \"Proposal_sellers_bid\" where "
 					+ "\"Proposal_sellers_bid\".seller_id=" +seller_id+"AND \"Proposal_sellers_bid\".seller_status='A')");
 			
 			//System.out.println("1");
 			while(rs1.next()){
 					int selected_seller=rs1.getInt("bid_seller_id");
 					
		//System.out.println(x);
					char status=rs1.getString("buyer_status").charAt(0);
		//System.out.println(y);
					int p_id=rs1.getInt("proposal_id");
		//System.out.println(z);
		if(selected_seller==seller_id && status=='y'){
			System.out.println("11");
			Statement st1 = con.createStatement();
			st1.executeUpdate("update \"Proposal_sellers_bid\" set buyer_bid_status='a' where seller_id=" +seller_id+ "AND proposal_id="+p_id );
		}
		else if(selected_seller!=seller_id && selected_seller!=0 && status=='y' ){
			Statement st2 = con.createStatement();
			System.out.println("111");
			st2.executeUpdate("update \"Proposal_sellers_bid\" set buyer_bid_status='r' where seller_id=" +seller_id+ "AND proposal_id="+p_id);
		}
		else if(selected_seller==0){
			Statement st3 = con.createStatement();
			System.out.println("1111");
			st3.executeUpdate("update \"Proposal_sellers_bid\" set buyer_bid_status='p' where seller_id=" +seller_id+ "AND proposal_id="+p_id);
		}
		
 		
 	}
 	Statement st4 = con.createStatement();
	ResultSet rs2=st4.executeQuery("select * from \"Proposal_sellers_bid\" where seller_status='A' AND seller_id='" + seller_id+"'");
	
	//System.out.println("1");
	while(rs2.next()){
		Proposal_Sellers_Bid_obj = new Proposal_Sellers_Bid();
		Proposal_Sellers_Bid_obj.setProposal_id(rs2.getInt("proposal_id"));
		Proposal_Sellers_Bid_obj.setSeller_id(rs2.getInt("seller_id"));
		Proposal_Sellers_Bid_obj.setSeller_status(rs2.getString("seller_status").charAt(0));
		//System.out.println(rs2.getString("seller_status").charAt(0));
		Proposal_Sellers_Bid_obj.setCost_avail(rs2.getInt("cost_avail"));
		Proposal_Sellers_Bid_obj.setCost_avail_cust(rs2.getInt("cost_avail_cust"));
		Proposal_Sellers_Bid_obj.setScore(rs2.getInt("score"));
		Proposal_Sellers_Bid_obj.setBuyer_bid_status(rs2.getString("buyer_bid_status").charAt(0));
		System.out.println(Proposal_Sellers_Bid_obj.toString());
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
	
 
	/*service to send additional response-service 5*/
	
	
	public void addnresp(int seller_id,int product_id,String specification){
	  	try{
	  	 dbobj = new DatabaseConnectionPostgreSQL();
	  	 con = dbobj.getConnection();
	  	 System.out.println("came here");
	  	 System.out.println(seller_id);
	  	 System.out.println(product_id);
	  	 System.out.println(specification);
	  	 
	  	 
	  	Statement st = con.createStatement();
	  	System.out.println("insert into \"Addn_response\"(seller_id,specification,product_id,id) values("+seller_id+",'"+specification+"',"+product_id+",2)");
	  	st.executeUpdate("insert into \"Addn_response\"(seller_id,specification,product_id,id) values("+seller_id+",'"+specification+"',"+product_id+",2)");
	  	//con.commit();
	  	
	  	}
	  	catch(Exception e){
	  		System.out.println(e.getMessage());
	  	}
	  	}
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
 
 
 
