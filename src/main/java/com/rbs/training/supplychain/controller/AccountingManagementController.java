package com.rbs.training.supplychain.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import com.RBS.training.supplychain.ACM.DAO.ACMdao;
import com.rbs.training.supplychain.model.GeneralLedger;
import com.rbs.training.supplychain.service.AccountingManagementService;

@Path("/ACM")
public class AccountingManagementController {
	@GET
	@Path("/viewGL")
	@Produces(MediaType.TEXT_PLAIN)
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
		//return Response.status(200).entity(resultString).build();
		return resultString;
	}
}
