package com.rbs.training.supplychain.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		 	List<String> coaList=accountingManagementServiceObj.getCOAswiftList();
			String resultString="<html><body>";
			for(String s:coaList)
			{
				resultString +="<a href='http://localhost:8181/index.html'>"+s+"</a>\n";
			}
			resultString+="</body></html>";
			return resultString;
	 }
}
