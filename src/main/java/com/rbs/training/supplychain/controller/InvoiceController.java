package com.rbs.training.supplychain.controller;

import java.sql.SQLException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.training.supplychain.model.*;
import com.rbs.training.supplychain.service.*;
import com.rbs.training.supplychain.util.CustomMessage;

@Component
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	InvoiceService invoiceServiceObj = new InvoiceService();
	//@Autowired
	//InvoiceService invoiceServiceObj;
	
// List all the invoices by id
		
	@RequestMapping(value = "/viewInvoices/{id}", method = RequestMethod.GET)
		public ResponseEntity<List<Invoice>> listAllUsers(@PathParam("id") String id) {
		System.out.println("id is"+id);
			List<Invoice> invoices = invoiceServiceObj.listAllInvocies(id);
			if (invoices.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
		}
	
	@RequestMapping(value = "/searchInvoice",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> searchInvoice(@PathParam("invoiceNo") String invoiceNo) throws ClassNotFoundException, SQLException {
		System.out.println("Getting Invoice with Invoice no"+ invoiceNo);
		//Invoice invoiceObj = invoiceServiceObj.search(invoiceNo);
		
		Invoice invoiceObj = invoiceServiceObj.search(invoiceNo);
		if(invoiceObj == null){
			System.out.println("Invoice with Invoice No"+invoiceNo+"is not found");
			return new ResponseEntity<Invoice>(invoiceObj,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoice>(invoiceObj,HttpStatus.OK);
    }
	@RequestMapping(value = "/addInvoice",method = RequestMethod.POST)
	 public ResponseEntity<CustomMessage> addInvoice(@PathParam("invoiceNo") String invoiceNo) throws ClassNotFoundException, SQLException {
		return null;
    }

}
