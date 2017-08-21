package com.rbs.training.supplychain.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rbs.training.supplychain.DAO.*;
import com.rbs.training.supplychain.model.*;
import com.rbs.training.supplychain.util.CustomMessage;

@Service("invoiceServiceObj")
public class InvoiceService {
	public Invoice search(double invoiceNo){
		DataBaseConnection dbobj = new DataBaseConnection();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from invoice where invoiceNo='"+invoiceNo+"'");
				invobj = new Invoice();
				System.out.println(rs.toString());
				while(rs.next()){
			
					invobj.setInvoiceNo(rs.getDouble(1)); 
					invobj.setContractNo(rs.getDouble(2));
					invobj.setBuyerId(rs.getDouble(3));
					invobj.setSellerId(rs.getDouble(4));
					invobj.setProductId(rs.getDouble(5));
					invobj.setUnitPrice(rs.getDouble(6));
					invobj.setQuantity(rs.getDouble(7));
					invobj.setGrossAmount(rs.getDouble(8));
					invobj.setTax(rs.getDouble(9));
					invobj.setNetAmount(rs.getDouble(10));
					invobj.setApprovalStatus(rs.getDouble(11));
					invobj.setDraftStatus(rs.getDouble(12));
					invobj.setFinancingStatus(rs.getDouble(13));
					System.out.println(invobj.toString());
			}
			}
			catch(Exception e)
			{							
				System.out.println(e);
			}
		return invobj;			
	}
	public List<Invoice> listAllInvocies(double id){
		DataBaseConnection dbobj = new DataBaseConnection();
		List<Invoice> lst = new ArrayList<Invoice>();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from invoice where sellerid='"+id+"'");
				invobj = new Invoice();
				while(rs.next()){
					invobj.setInvoiceNo(rs.getDouble(1)); 
					invobj.setContractNo(rs.getDouble(2));
					invobj.setBuyerId(rs.getDouble(3));
					invobj.setSellerId(rs.getDouble(4));
					invobj.setProductId(rs.getDouble(5));
					invobj.setUnitPrice(rs.getDouble(6));
					invobj.setQuantity(rs.getDouble(7));
					invobj.setGrossAmount(rs.getDouble(8));
					invobj.setTax(rs.getDouble(9));
					invobj.setNetAmount(rs.getDouble(10));
					invobj.setApprovalStatus(rs.getDouble(11));
					invobj.setDraftStatus(rs.getDouble(12));
					invobj.setFinancingStatus(rs.getDouble(13));
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
	
		public Invoice addInvoice(double invoiceNo,double contractNo, double buyerId, double sellerId, double quantity, double productId, double unitPrice, double  grossAmount,double netAmount, double tax) {
			DataBaseConnection dbobj = new DataBaseConnection();
			Invoice invobj = null;
			CustomMessage msg = null; 
			try{
				Connection con = dbobj.getConnection();
				msg = new CustomMessage();
					invobj = new Invoice();
					//double =invobj.invoiceNo++;
					String updateTableSQL1 ="insert into invoice values("+invoiceNo+","+contractNo+","+buyerId+","+sellerId+","
							+productId+","+unitPrice+","+quantity+","+grossAmount+","+tax+","+netAmount+",0,1,0)";
					/*stmt.executeQuery("insert into invoice values("+invoiceNo+","+contractNo+","+buyerId+","+sellerId+","
					+productId+","+unitPrice+","+quantity+","+grossAmount+","+tax+","+netAmount+","+"0,1,0");*/
					PreparedStatement preparedStatement  = con.prepareStatement(updateTableSQL1);

					preparedStatement.executeUpdate();

					System.out.println("Record is inserted to DBUSER table!");
					String updateTableSQL2 = "COMMIT";
					preparedStatement = con.prepareStatement(updateTableSQL2);
					preparedStatement.executeUpdate();
					msg.setMessage("Succesfully Updated");
					
						invobj.setInvoiceNo(invoiceNo);
						invobj.setContractNo(contractNo);
						invobj.setBuyerId(buyerId);
						invobj.setSellerId(sellerId);
						invobj.setProductId(productId);
						invobj.setTax(tax);
						invobj.setUnitPrice(unitPrice);
						invobj.setQuantity(quantity);
						invobj.setGrossAmount(grossAmount);
						invobj.setNetAmount(netAmount);
						invobj.setDraftStatus(1);
						invobj.setApprovalStatus(0);
						invobj.setFinancingStatus(0);
						
						System.out.println(invobj.toString());
						System.out.println(msg.getMessage());
				}
				catch(Exception e)
				{							
					System.out.println(e.getMessage());
				}
			return invobj;			
	}
	
	
	public CustomMessage updateInvoice(double invoiceNo) throws ClassNotFoundException {
		DataBaseConnection dbobj = new DataBaseConnection();
		CustomMessage msg = new CustomMessage();
		msg=null;
		try {
			
			msg = new CustomMessage();
			Connection dbConnection1 = dbobj.getConnection();
		String updateTableSQL1 = "UPDATE INVOICE SET USERNAME = ? "
		        + " WHERE USER_ID = ?";
		PreparedStatement preparedStatement  = dbConnection1.prepareStatement(updateTableSQL1);

		preparedStatement.setString(1, "new_value");
		preparedStatement.setInt(2, 1001);


		preparedStatement.executeUpdate();

		System.out.println("Record is updated to DBUSER table!");
		String updateTableSQL2 = "COMMIT";
		preparedStatement = dbConnection1.prepareStatement(updateTableSQL2);
		preparedStatement.executeUpdate();
		msg.setMessage("Succesfully Updated");
		} catch (SQLException e) {

		System.out.println(e.getMessage());

		} 		

		return msg;
	}
	
	public CustomMessage deleteInvoice(double invoiceNo) {
		//insert your logic Here can change input and return parameters to your requirements
		
		DataBaseConnection dbobj = new DataBaseConnection();
		CustomMessage msg=null;
		
		try{
			Connection con = dbobj.getConnection();
			msg = new CustomMessage();
			PreparedStatement stmt=con.prepareStatement("delete from invoice where INVOICENO=?");
			stmt.setDouble(1,invoiceNo);
			msg.setMessage("successfully deleted");
			//ResultSet re=stmt.executeQuery();
			//while(re.next()){
				System.out.println("invoice deleted");
				//System.out.println("invoice number"+re.getInt(1)+"ContractNo"+re.getInt(2)+"BuyerId"+re.getInt(3)+"SellerId"+re.getInt(4)+"ProductId"+re.getInt(5)+"UnitPrice"+re.getInt(6)+"Quantity number"+re.getInt(7)+"GrossAmount"+re.getInt(8)+"tax"+re.getInt(9)+"NetAmount "+re.getInt(10)+"ApprovalStatus "+re.getInt(11)+"Draft number"+re.getInt(12)+"FinancialStatus "+re.getInt(13));
			//}
		}
		catch(Exception e){
			msg.setMessage(e.getMessage());
			System.out.println(e.getMessage());
			}
		return msg;
	}
	
	public CustomMessage approveInvoice(String invoiceNo) {
		//insert your logic Here can change input and return parameters to your requirements
		
		return null;
	}
	public CustomMessage uploadInvoice() {
		//insert your logic Here can change input and return parameters to your requirements
		
		return null;
	}

}
