package com.rbs.training.supplychain.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rbs.training.supplychain.DAO.DataBaseConnection;
import com.rbs.training.supplychain.model.Invoice;
import com.rbs.training.supplychain.util.CustomMessage;

@Service("invoiceServiceObj")
public class InvoiceService {
	public Invoice search(double InvoiceID){
		DataBaseConnection dbobj = new DataBaseConnection();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from invoice where invoice_id='"+InvoiceID+"'");
				invobj = new Invoice();
				System.out.println(rs.toString());
				while(rs.next()){
			
					invobj.setInvoiceID(rs.getDouble(1)); 
					invobj.setContractID(rs.getDouble(2));
					invobj.setSellerID(rs.getDouble(3));
					invobj.setBuyerID(rs.getDouble(4));
					invobj.setBillbookNo(rs.getDouble(5));
					invobj.setSenderID(rs.getDouble(6));
					invobj.setReceiverID(rs.getDouble(7));
					invobj.setFundingRequestStatus(rs.getInt(8));
					invobj.setApprovalStatus(rs.getInt(9));
					invobj.setDraftStatus(rs.getInt(10));
					invobj.setInvoiceCreatedDate(rs.getDate(11));
					invobj.setPaymentDate(rs.getDate(12));
					invobj.setInvoiceAmount(rs.getFloat(13));
					invobj.setInvoiceDueDate(rs.getDate(14));
					invobj.setComplianceStatus(rs.getInt(15));
					invobj.setDeleteStatus(rs.getInt(16));
					invobj.setDeleteTimestamp(rs.getDate(17));
					System.out.println(invobj.toString());
			}
			}
			catch(Exception e)
			{							
				System.out.println(e);
			}
		return invobj;			
	}
	public List<Invoice> listAllInvocies(double sellerID){
		DataBaseConnection dbobj = new DataBaseConnection();
		List<Invoice> lst = new ArrayList<Invoice>();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from invoice where sellerid=? and deletestatus=0");
				stmt.setDouble(1,sellerID);
				ResultSet rs = stmt.executeQuery();				
				
				while(rs.next()){
					invobj = new Invoice();
					invobj.setInvoiceID(rs.getDouble(1)); 
					invobj.setContractID(rs.getDouble(2));
					invobj.setSellerID(rs.getDouble(3));
					invobj.setBuyerID(rs.getDouble(4));
					invobj.setBillbookNo(rs.getDouble(5));
					invobj.setSenderID(rs.getDouble(6));
					invobj.setReceiverID(rs.getDouble(7));
					invobj.setFundingRequestStatus(rs.getInt(8));
					invobj.setApprovalStatus(rs.getInt(9));
					invobj.setDraftStatus(rs.getInt(10));
					invobj.setInvoiceCreatedDate(rs.getDate(11));
					invobj.setPaymentDate(rs.getDate(12));
					invobj.setInvoiceAmount(rs.getFloat(13));
					invobj.setInvoiceDueDate(rs.getDate(14));
					invobj.setComplianceStatus(rs.getInt(15));
					invobj.setDeleteStatus(rs.getInt(16));
					invobj.setDeleteTimestamp(rs.getDate(17));
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
	public List<Invoice> approvedInvocies(int approvalStatus){
		DataBaseConnection dbobj = new DataBaseConnection();
		List<Invoice> lst = new ArrayList<Invoice>();
		Invoice invobj = null;
		try{
			Connection con = dbobj.getConnection();
				PreparedStatement stmt = con.prepareStatement("select * from invoice where approvalStatus=? and deletestatus=0");
				stmt.setInt(1,approvalStatus);
				ResultSet rs = stmt.executeQuery();				
				invobj = new Invoice();
				while(rs.next()){
					invobj = new Invoice();
					invobj.setInvoiceID(rs.getDouble(1)); 
					invobj.setContractID(rs.getDouble(2));
					invobj.setSellerID(rs.getDouble(3));
					invobj.setBuyerID(rs.getDouble(4));
					invobj.setBillbookNo(rs.getDouble(5));
					invobj.setSenderID(rs.getDouble(6));
					invobj.setReceiverID(rs.getDouble(7));
					invobj.setFundingRequestStatus(rs.getInt(8));
					invobj.setApprovalStatus(rs.getInt(9));
					invobj.setDraftStatus(rs.getInt(10));
					invobj.setInvoiceCreatedDate(rs.getDate(11));
					invobj.setPaymentDate(rs.getDate(12));
					invobj.setInvoiceAmount(rs.getFloat(13));
					invobj.setInvoiceDueDate(rs.getDate(14));
					invobj.setComplianceStatus(rs.getInt(15));
					invobj.setDeleteStatus(rs.getInt(16));
					invobj.setDeleteTimestamp(rs.getDate(17));
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
	
		public Invoice addInvoice(double invoiceID,double contractID, double sellerID, double buyerID, double billbookNo, Date invoiceCreatedDate, Date paymentDate, float invoiceAmount,Date invoiceDueDate) {
			DataBaseConnection dbobj = new DataBaseConnection();
			Invoice invobj = null;
			CustomMessage msg = null; 
			try{
				Connection con = dbobj.getConnection();
				msg = new CustomMessage();
					invobj = new Invoice();
					//double =invobj.invoiceNo++;
					String updateTableSQL1 ="insert into invoice values("+invoiceID+","+contractID+","+sellerID+","+buyerID+","
							+billbookNo+",NULL,NULL,0,0,1,"+invoiceCreatedDate+","+paymentDate+","+invoiceAmount+","+invoiceDueDate+",NULL,0,NULL)";
					/*stmt.executeQuery("insert into invoice values("+invoiceNo+","+contractNo+","+buyerId+","+sellerId+","
					+productId+","+unitPrice+","+quantity+","+grossAmount+","+tax+","+netAmount+","+"0,1,0");*/
					System.out.println(updateTableSQL1);
					PreparedStatement preparedStatement  = con.prepareStatement(updateTableSQL1);
					
					preparedStatement.executeUpdate();

					System.out.println("Record is inserted to DBUSER table!");
					String updateTableSQL2 = "COMMIT";
					preparedStatement = con.prepareStatement(updateTableSQL2);
					preparedStatement.executeUpdate();
					msg.setMessage("Succesfully Updated");
						con.close();
						invobj.setInvoiceID(invoiceID); 
						invobj.setContractID(contractID);
						invobj.setSellerID(sellerID);
						invobj.setBuyerID(buyerID);
						invobj.setBillbookNo(billbookNo);
						//invobj.setSenderID(SenderID);
						//invobj.setReceiverID(ReceiverID);
						invobj.setFundingRequestStatus(0);
						invobj.setApprovalStatus(0);
						invobj.setDraftStatus(1);
						invobj.setInvoiceCreatedDate(invoiceCreatedDate);
						invobj.setPaymentDate(paymentDate);
						invobj.setInvoiceAmount(invoiceAmount);
						invobj.setInvoiceDueDate(invoiceDueDate);
						
						
						
						
						System.out.println(invobj.toString());
						System.out.println(msg.getMessage());
				}
				catch(Exception e)
				{							
					System.out.println(e.getMessage());
				}
			return invobj;			
	}
	
	
	public CustomMessage updateInvoice(double invoiceID) throws ClassNotFoundException {
		DataBaseConnection dbobj = new DataBaseConnection();
		CustomMessage msg = new CustomMessage();
		msg=null;
		try {
			
			msg = new CustomMessage();
			Connection dbConnection1 = dbobj.getConnection();
		String updateTableSQL1 = "UPDATE INVOICE SET USERNAME = ? "
		        + " WHERE USER_ID = ?";
		PreparedStatement preparedStatement  = dbConnection1.prepareStatement(updateTableSQL1);

		preparedStatement.setString(1,"new_value");
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
	
	public CustomMessage deleteInvoice(double invoiceID) {
		//insert your logic Here can change input and return parameters to your requirements
		
		DataBaseConnection dbobj = new DataBaseConnection();
		CustomMessage msg=null;
		
		try{
			Connection con = dbobj.getConnection();
			msg = new CustomMessage();
			PreparedStatement stmt=con.prepareStatement("update invoice set deletestatus=1 where invoice_id=? and deletestatus=0");
			stmt.setDouble(1,invoiceID);
			stmt.executeUpdate();

			System.out.println("Record is updated to DBUSER table!");
			String updateTableSQL2 = "COMMIT";
			stmt = con.prepareStatement(updateTableSQL2);
			stmt.executeUpdate();
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
	
	public CustomMessage approveInvoice(double invoiceID) {
		//insert your logic Here can change input and return parameters to your requirements
		
		return null;
	}
	private static String UPLOADED_FOLDER = "C://temp//";
    public String uploadInvoice(MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "nofile";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return UPLOADED_FOLDER +file.getOriginalFilename();
    }

    @GetMapping("/invoice/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
    
    
    
    
    //private static final String FILE_NAME = "C:/temp/.xlsx";

    
    public String processInvoice(String FILE_NAME) {
    	String s="<html><body><table style=\"border:1px solid black\">";
        try {
        	
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
            	s+="<tr>";
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                int count=0;
                while (cellIterator.hasNext()) {
                	count++;
                	
                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        s+="<td>";
                        //System.out.println("string type");
                    	s+=currentCell.getStringCellValue();
                    } else {
                        s+="<td>";
                        //System.out.println("integer type");
                    	s+=currentCell.getNumericCellValue();
                    }
                    s+="</td>";
                }
                s+="</tr>";
                if(count!=10)
                {
                	s="<html><body>not in right format...<br> <input type=\"button\" onClick=\"parent.location='http://localhost:8181/uploadInvoice.html'\" value=\"Go back and upload\"></body></html>" ;
                	return s;
                }
                
                //excelFile.close();
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        s+="</table></body></html>";
        s+="in right format...add to db<br> <input type=\"button\" onClick=\"parent.location='http://localhost:8181/invoice/insertAll'\" value=\"Enter data into db\"></body></html>";
        return s;
    }

    public String insertAll(String file)
    {
    	
    	int rowcount=0;
    	
    	String s="<html><body><table style=\"border:1px solid black\">";
        try {
        	
            FileInputStream excelFile = new FileInputStream(new File(file));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            double invoiceNo=0;double contractNo=0; String buyerId=""; String sellerId=""; double quantity=0; String productId=""; double unitPrice=0; double  grossAmount=0;double netAmount=0; double tax=0;
            int count=0;
            while (iterator.hasNext()) {
            	s+="<tr>";
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                count=0;
                
                while (cellIterator.hasNext()) {
                	count++;
                	
                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        s+="<td>";
                    	s+=currentCell.getStringCellValue();
                    	switch(count)
                    	{
                    	case 3:
                    		buyerId=currentCell.getStringCellValue();
                    		break;
                    	case 4:
                    		sellerId=currentCell.getStringCellValue();
                    		break;
                    	default:
                    		productId=currentCell.getStringCellValue();
                    		break;
                    	}
                    	
                    } else {
                        s+="<td>";
                    	s+=currentCell.getNumericCellValue();
                    	//System.out.println("op"+currentCell.getNumericCellValue());
                    	switch(count)
                    	{
                    	case 1:
                    		invoiceNo=currentCell.getNumericCellValue();
                    		break;
                    	case 2:
                    		contractNo=currentCell.getNumericCellValue();
                    		break;
                    	case 5:
                    		quantity=currentCell.getNumericCellValue();
                    		break;
                    	case 7:
                    		unitPrice=currentCell.getNumericCellValue();
                    		break;
                    	case 8:
                    		grossAmount=currentCell.getNumericCellValue();
                    		break;
                    	case 9:
                    		netAmount=currentCell.getNumericCellValue();
                    		break;
                    	default:
                    		tax=currentCell.getNumericCellValue();
                    		break;
                    	}
                    }
                    s+="</td>";
                }
                rowcount++;
                System.out.println(invoiceNo);
                //addInvoice(invoiceNo,contractNo,buyerId,sellerId,quantity,productId,unitPrice,grossAmount,netAmount,tax);
                s+="</tr>";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s+="</table>"+rowcount+"rows inserted"+"</body></html>";
        s+="in right format...add to db<br> <input type=\"button\" onClick=\"parent.location='http://localhost:8181/invoice/insertAll'\" value=\"Enter data into db\"></body></html>";
        
        return s;
    }

}
