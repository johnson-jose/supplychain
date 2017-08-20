package com.rbs.training.supplychain.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.training.supplychain.model.*;
import com.rbs.training.supplychain.service.*;

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
			
			AccountingManagementService viewDAO=new AccountingManagementService();
			String resultString="";
			List<GeneralLedger> generalledgerlists=viewDAO.getEachGLEntry();
			
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
	 @RequestMapping(value = "/viewCOA/{productSwiftID}",method = RequestMethod.GET)
	 public String getCOAinJSON(@PathParam("productSwiftID") String ProductSwiftID){
			
			AccountingManagementService acmDAO=new AccountingManagementService();
			ChartOfAccount coa=acmDAO.getChartOfAccountValues(ProductSwiftID);
			
			return coa.getBranch();
		}
}
