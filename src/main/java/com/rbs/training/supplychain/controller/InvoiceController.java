package com.rbs.training.supplychain.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rbs.training.supplychain.model.Invoice;
import com.rbs.training.supplychain.service.InvoiceService;
import com.rbs.training.supplychain.util.CustomMessage;

@Component
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	InvoiceService invoiceServiceObj = new InvoiceService();
	//@Autowired
	//InvoiceService invoiceServiceObj;
	
// List all the invoices by id
		
	@RequestMapping(value = "/viewInvoices", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Invoice>> listAllUsers(@PathParam("sellerID") double sellerID) {
		System.out.println("id is"+sellerID);
			List<Invoice> invoices = invoiceServiceObj.listAllInvocies(sellerID);
			if (invoices.isEmpty()) {
				return new ResponseEntity<List<Invoice>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
		}
	
	@RequestMapping(value = "/approvedInvocies", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Invoice>> listAllUsers(@PathParam("approvalStatus") int approvalStatus) {
		System.out.println("id is"+approvalStatus);
			List<Invoice> invoices = invoiceServiceObj.approvedInvocies(approvalStatus);
			if (invoices.isEmpty()) {
				return new ResponseEntity<List<Invoice>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
		}
	
	@RequestMapping(value = "/searchInvoice/{InvoiceID}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> searchInvoice(@PathVariable("invoiceID") double invoiceID) throws ClassNotFoundException, SQLException {
		System.out.println("Getting Invoice with Invoice no"+ invoiceID);
		//Invoice invoiceObj = invoiceServiceObj.search(invoiceNo);
		
		Invoice invoiceObj = invoiceServiceObj.search(invoiceID);
		if(invoiceObj == null){
			System.out.println("Invoice with Invoice No"+invoiceID+"is not found");
			return new ResponseEntity<Invoice>(invoiceObj,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoice>(invoiceObj,HttpStatus.OK);
    }
	@RequestMapping(value = "/addInvoice",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	
	 public ResponseEntity<Invoice> addInvoice(@PathParam("invoiceID") double invoiceID,@PathParam("contractID") double contractID,@PathParam("sellerID") double sellerID,@PathParam("buyerID") double buyerID,@PathParam("billbookNo") double billbookNo,@PathParam("invoiceCreatedDate") Date invoiceCreatedDate,@PathParam("paymentDate") Date paymentDate,@PathParam("invoiceAmount") float  invoiceAmount,@PathParam("invoiceDueDate") Date invoiceDueDate) throws ClassNotFoundException, SQLException {
		System.out.println("Adding Invoice with Invoice no"+ invoiceID);
		
		Invoice invoiceObj=invoiceServiceObj.addInvoice(invoiceID,contractID,sellerID,buyerID,billbookNo,invoiceCreatedDate,paymentDate,invoiceAmount,invoiceDueDate);
		if(invoiceObj == null){
			System.out.println("Invoice with Invoice No"+invoiceID+"is not found");
			return new ResponseEntity<Invoice>(invoiceObj,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoice>(invoiceObj,HttpStatus.OK);
   }
	@RequestMapping(value = "/deleteInvoice",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomMessage> deleteInvoice(@PathParam("invoiceID") double invoiceID) throws ClassNotFoundException, SQLException {
		System.out.println("delete Invoice with Invoice no"+ invoiceID);
		//Invoice invoiceObj = invoiceServiceObj.search(invoiceNo);
		
		CustomMessage msg = invoiceServiceObj.deleteInvoice(invoiceID);
		if(msg == null){
			System.out.println("Invoice with Invoice No"+invoiceID+"is not found");
			return new ResponseEntity<CustomMessage>(msg,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomMessage>(msg,HttpStatus.OK);
    }
	
	
	
	
	private static String s;
	@PostMapping("/upload")
	public String uploadInvoice(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
		s=invoiceServiceObj.uploadInvoice(file, redirectAttributes);
		String res=invoiceServiceObj.processInvoice(s);
		return res;
	}
	@RequestMapping(value="/insertAll",method = RequestMethod.GET,produces=MediaType.ALL_VALUE)
	public String insertAll()
	{
		String t=invoiceServiceObj.insertAll(s);
		return t;
	
	}
	
	
	 
}
