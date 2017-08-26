package com.rbs.training.supplychain.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.training.supplychain.model.ChartOfAccount;
import com.rbs.training.supplychain.model.GeneralLedger;
import com.rbs.training.supplychain.model.Proposal_Sellers_Bid;
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
	 /*@RequestMapping(value = "/viewGL",method = RequestMethod.GET)
	 public String ViewLedger(){
		 String resultString ="<html><head>";
			resultString +="<style>table, th, td { border: 1px solid black;}</style>";
			resultString +="</head><body>";
			resultString+="<form action='http://localhost:8181/ACM/viewGLBySearch'>"+"<b>ENTER THE ACCOUNT ENTRY NUMBER</b>"+"<br>"+
	    	 "<input type='text' name='search'></input>"+"<input type='submit' value='search'>"+"</form>";
			List<GeneralLedger> generalledgerlists=accountingManagementServiceObj.getEachGLEntry();
			
			resultString +="<table>";
			resultString +="<tr><th>Account Entry No.</th><th>Current Date</th><th>Payment Date</th><th>Transaction No</th><th>Customer Account No.</th><th>Invoice No.</th><th>Dr/Cr</th><th>Amount</th><th>Due Date</th></tr>";
			for(GeneralLedger generalledgerlist:generalledgerlists){
								resultString += "<tr><td>" + generalledgerlist.getAccountEntryNo() + "</td><td>"+
								generalledgerlist.getCurrentDate()  + "</td><td>"+
								generalledgerlist.getPaymentDate()  + "</td><td>"+
								generalledgerlist.getTransactionNo() +"</td><td>"+
								generalledgerlist.getCustomerAccountNo()+"</td><td>"+
								generalledgerlist.getInvoiceNo()+"</td><td>"+
								generalledgerlist.getDrOrCr()+"</td><td>"+
								generalledgerlist.getAmount() +"</td><td>"+
								generalledgerlist.getDueDate() + "</td></tr>";
	       }
			
			resultString +="</table><br><a href='http://localhost:8181/index.html'>Home</a></BODY></html>";
			
			return resultString;
		}*/
	 
	 @RequestMapping(value = "/viewGL",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<GeneralLedger>> ViewLedger(){
			List<GeneralLedger> generalledgerlists=accountingManagementServiceObj.getEachGLEntry();
			return new ResponseEntity<List<GeneralLedger>>(generalledgerlists, HttpStatus.OK);
		}
	 
	 @RequestMapping(value = "/viewGLBySearch",method = RequestMethod.GET)
	 @ResponseBody
	 public String ViewLedgerBySearch(HttpServletRequest request,HttpServletResponse response){
		 String resultString ="<html><head>";
			resultString +="<style>table, th, td { border: 1px solid black;}</style>";
			resultString +="</head><body>";
			String searchBy=request.getParameter("search");
			List<GeneralLedger> generalledgerlists=accountingManagementServiceObj.getEachGLEntryBySearch(searchBy);
			resultString +="<table>";
			int flag=0;
			for(GeneralLedger generalledgerlist:generalledgerlists){
				if(flag==0)
				{
					resultString +="<tr><th>Account Entry No.</th><th>Current Date</th><th>Payment Date</th><th>Transaction No</th><th>Customer Account No.</th><th>Invoice No.</th><th>Dr/Cr</th><th>Amount</th><th>Due Date</th></tr>";
				}
				flag=1;
				resultString += "<tr><td>" + generalledgerlist.getAccountEntryNo() + "</td><td>"+
						generalledgerlist.getCurrentDate()  + "</td><td>"+
						generalledgerlist.getPaymentDate()  + "</td><td>"+
						generalledgerlist.getTransactionNo() +"</td><td>"+
						generalledgerlist.getCustomerAccountNo()+"</td><td>"+
						generalledgerlist.getInvoiceNo()+"</td><td>"+
						generalledgerlist.getDrOrCr()+"</td><td>"+
						generalledgerlist.getAmount() +"</td><td>"+
						generalledgerlist.getDueDate() + "</td></tr>";
	       }
			resultString +="</table>";
			if(flag==0)
			{
				try{
					resultString +="<script type='text/javascript'>alert('Record not found');location='http://localhost:8181/ACM/viewGL';</script>";
					//response.sendRedirect("http://localhost:8181/ACM/viewGL");
				
				}
				catch(Exception e)
				{
			    	System.out.println("Exception " + e.getMessage());
			    }
			}
			
			resultString+="<br><form action='http://localhost:8181/ACM/viewGL'>"+
	    	"<input type='submit' value='back'>"+"</form>";
			resultString +="<br><a href='http://localhost:8181/index.html'>Home</a></BODY></html>";
			return resultString;
		}
	 /*@RequestMapping(value = "/viewCOA/{productSwiftID}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ChartOfAccount getCOAinJSON(@PathParam("productSwiftID") String ProductSwiftID){
			
			AccountingManagementService acmDAO=new AccountingManagementService();
			ChartOfAccount coa=acmDAO.getChartOfAccountValues(ProductSwiftID);
			
			return coa;
		}*/
	
	@RequestMapping(value = "/viewCOAlist",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<ChartOfAccount>> dispCOAlist()
	 {
		 	List<ChartOfAccount> coaList=accountingManagementServiceObj.getCOAList();
		 	return new ResponseEntity<List<ChartOfAccount>>(coaList, HttpStatus.OK);
	 }
	 @RequestMapping(value = "/viewCOASINGLE",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public ResponseEntity<ChartOfAccount> dispCOA(HttpServletRequest request)
	 {
		 	ChartOfAccount coa=accountingManagementServiceObj.getCOA(request.getParameter("swiftID"));
		
			System.out.println(coa.getBranch());
			/*String resultString="<html><head><meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'><title>Chart of Accounts List</title><style>table, th, td { border: 1px solid black;}</style><script type='text/javascript' src='chartOfAccountsList.js'></script></head><body><h1>Chart of Accounts</h1><br>";
			
			resultString +="<div id='chartList'><form action='http://localhost:8181/ACM/delCOA' method='post'>";
			int flag=0;
			resultString +="<head>";
			resultString +="<style>table, th, td { border: 1px solid black;}</style>";
			resultString +="</head>";
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
			*/return new ResponseEntity<ChartOfAccount>(coa, HttpStatus.OK);
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
	 public void addCOAController(HttpServletRequest request,HttpServletResponse response)
	 {
		 System.out.println("inside addCOAController");
		 ChartOfAccount coa=new ChartOfAccount();
		 coa.setHead(request.getParameter("head"));
		 coa.setLegalEntity(request.getParameter("legalEntity"));
		 coa.setCountry(request.getParameter("country"));
		 coa.setBranch(request.getParameter("branch"));
		 coa.setProduct(request.getParameter("product"));
		 coa.setCurrency(request.getParameter("currency"));
		 coa.setBook(Integer.parseInt(request.getParameter("book")));
		 coa.setProductSwiftID(request.getParameter("productSwiftID"));
		 System.out.println(coa.toString());
		 System.out.println("Before calling add");
		 accountingManagementServiceObj.addCOAService(coa);
		 System.out.println("After calling add");
	    try
	    {
	    	System.out.println("Inside redirect");
	    	response.sendRedirect("http://localhost:8181/ACMindex.html");
	    	
	    }
	    
	    catch(Exception e)
	    {
	    	System.out.println("Exception  " + e.getMessage());
	    }
	 }
	 
	 @RequestMapping(value = "/CheckCompliance",method = RequestMethod.GET)
	 @ResponseBody
	 public String complianceCheck(HttpServletRequest request,HttpServletResponse response){
	
		 
			String resultString="";
			String individualCountry=request.getParameter("Countryname");
			String individualname=request.getParameter("individualname");
			
			List<String> lstCountries = new ArrayList<String>();
			List<String> lstNames = new ArrayList<String>();
			
			lstCountries = accountingManagementServiceObj.sanctionedCountries();
			lstNames =accountingManagementServiceObj.sanctionedIndividuals();
			
		int c1=	checkCountry(lstCountries, individualCountry);
		int c2=	checkName(lstNames, individualname);
			
		if(c1==0||c2==0)
		{
			resultString="<b>Approval Denied. <br>Under Sanctioned List</b>";
		}
		else
			resultString="<b>Approval Sanctioned.</b>";
			
			return resultString;
		}
	 public int checkCountry(List<String> lstCountries,String country){
				for(int i=0;i<lstCountries.size();i++)
				    if(country.equalsIgnoreCase((String) lstCountries.get(i)))
				    	return 0;
				return 1;
			}
			
	public int checkName(List<String> lstNames,String name){
				for(int i=0;i<lstNames.size();i++)
					if(name.equalsIgnoreCase((String) lstNames.get(i)))
					     return 0;
				return 1;
		}
}