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

@Component
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	
	//@Autowired
	//InvoiceService invoiceServiceObj;
	
	@RequestMapping(value = "/searchInvoice",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> searchInvoice(@PathParam("invoiceNo") String invoiceNo) throws ClassNotFoundException, SQLException {
		System.out.println("Getting Invoice with Invoice no"+ invoiceNo);
		//Invoice invoiceObj = invoiceServiceObj.search(invoiceNo);
		InvoiceService obj = new InvoiceService();
		Invoice invoiceObj = obj.search(invoiceNo);
		if(invoiceObj == null){
			System.out.println("Invoice with Invoice No"+invoiceNo+"is not found");
			return new ResponseEntity<Invoice>(invoiceObj,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoice>(invoiceObj,HttpStatus.OK);
		
    }

}
