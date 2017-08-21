package com.rbs.training.supplychain.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.training.supplychain.model.ChartOfAccount;
import com.rbs.training.supplychain.model.GeneralLedger;
import com.rbs.training.supplychain.service.AccountingManagementService;

@Component
@RestController
@RequestMapping("/ACM")
public class AccountingManagementController {
	AccountingManagementService accountingManagementServiceObj = new AccountingManagementService();
	//@Autowired

	 @RequestMapping("/test")
	    public String service1() {
	        return "Hello, World!" ;
	    }
	 @RequestMapping(value = "/viewGL",method = RequestMethod.GET)
	 public String ViewLedger(){
	
			String resultString="";
			List<GeneralLedger> generalledgerlists=accountingManagementServiceObj.getEachGLEntry();
			
			for(GeneralLedger generalledgerlist:generalledgerlists){
								resultString += "Account Entry Number = " + generalledgerlist.getAccountEntryNo() + "\n"+
								"Payment Date = " + generalledgerlist.getPaymentDate()  + "\n"+
								"Transaction Number = " + generalledgerlist.getTransactionNo() +"\n"+
								"Customer Account Number = " + generalledgerlist.getCustomerAccountNo()+"\n"+
								"Invoice Number = " + generalledgerlist.getInvoiceNo()+"\n"+
								"Debit or Credit = " + generalledgerlist.getDrOrCr()+"\n"+
								"Amount = " + generalledgerlist.getAmount() +"\n"+
								"Due Date = " + generalledgerlist.getDueDate() + "\n";
	       }
			return resultString;
		}
	 /*@RequestMapping(value = "/viewCOA/{productSwiftID}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ChartOfAccount getCOAinJSON(@PathParam("productSwiftID") String ProductSwiftID){
			
			AccountingManagementService acmDAO=new AccountingManagementService();
			ChartOfAccount coa=acmDAO.getChartOfAccountValues(ProductSwiftID);
			
			return coa;
		}*/
	
	 @RequestMapping(value = "/viewCOAlist",method = RequestMethod.GET)
	 public String dispCOAlist()
	 {
		 	List<ChartOfAccount> coaList=accountingManagementServiceObj.getCOAList();
			String resultString="<html><head><meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'><title>Chart of Accounts List</title><script type='text/javascript' src='chartOfAccountsList.js'></script></head><body><h1>Chart of Accounts(Swift ID)</h1><br>";
			
			resultString +="<div id='chartList'><form action='http://localhost:8181/ACM/delCOA' method='post'>";
			int flag=0;
			resultString +="<table>";
			for(ChartOfAccount coa:coaList)
			{
				if(flag==0)
					resultString+="<tr><th></th><th>Head</th><th>Legal Entity</th><th>Country</th><th>Branch</th><th>Product</th><th>Currency</th><th>Book</th><th>SWIFT ID</th></tr>";
				flag=1;
				//resultString +="<a href='http://localhost:8181/index.html'>"+s+"</a><br>\n";
				//String url="ChartOfAccountsEdit.jsp?chSID="+s;
				String url="http://localhost:8181/index.html";
				resultString+="<tr><td><input type='checkbox' name='chartGroup' value='"+coa.getProductSwiftID()+"'/></td><td>"+coa.getHead()+"</td><td>"+coa.getLegalEntity()+"</td><td>"+coa.getCountry()+"</td><td>"+coa.getBranch()+"</td><td>"+coa.getProduct()+"</td><td>"+coa.getCurrency()+"</td><td>"+coa.getBook()+"</td><td>"+coa.getProductSwiftID()+"</td></tr>";
			}
			resultString +="</table>";
			if(flag==1)
				resultString +="<input type='submit' value='Delete Charts'><br>";
			resultString+="</form></div><div id='newEntrySpace'></div><a href='http://localhost:8181/addCOA.html'>Add a chart of Accounts</a><br><a href='http://localhost:8181/index.html'>Home</a></body></html>";
			return resultString;
	 }
	 
	 @RequestMapping(value = "/delCOA",method = RequestMethod.POST)
	 @ResponseBody
	 public void delCOA(HttpServletRequest request,HttpServletResponse response)
	 {
		 String[] chartNamesToDelete=request.getParameterValues("chartGroup");
		 List<String> res=new LinkedList<String>();
		 for(String chName:chartNamesToDelete)
			 res.add(chName);
		 try
		    {
			 	accountingManagementServiceObj.deleteCOA(res);
		    }

		    catch(Exception e)
		    {
		    	System.out.println("Exception " + e.getMessage());
		    }
		    finally
		    {
		    	try{
		    	response.sendRedirect("http://localhost:8181/ACM/viewCOAlist");
		    	}catch(Exception e)
			    {
			    	System.out.println("Exception " + e.getMessage());
			    }
		    }
	 }
	 @RequestMapping(value = "/addCOAContoller",method = RequestMethod.POST)
	 @ResponseBody
	 public void addCOAController(@PathParam("head") String head,@PathParam("legalEntity") String legalEntity,@PathParam("country") String country,@PathParam("branch") String branch,@PathParam("product") String product,@PathParam("currency") String currency,@PathParam("book") int book,@PathParam("productSwiftID") String productSwiftID,HttpServletResponse response)
	 {
		 System.out.println("inside addCOAController");
		 ChartOfAccount coa=new ChartOfAccount();
		 coa.setHead(head);
		 coa.setLegalEntity(legalEntity);
		 coa.setCountry(country);
		 coa.setBranch(branch);
		 coa.setProduct(product);
		 coa.setCurrency(currency);
		 coa.setBook(book);
		 coa.setProductSwiftID(productSwiftID);
		 System.out.println(coa.toString());
		 System.out.println("Before calling add");
		 accountingManagementServiceObj.addCOAService(coa);
		 System.out.println("After calling add");
	    try
	    {
	    	System.out.println("Inside redirect");
	    	response.sendRedirect("http://localhost:8181/ACM/viewCOAlist");
	    	
	    }
	    
	    catch(Exception e)
	    {
	    	System.out.println("Exception " + e.getMessage());
	    }
	 }
}
