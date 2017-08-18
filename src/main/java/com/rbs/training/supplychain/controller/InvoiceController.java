package com.rbs.training.supplychain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplychain")
public class InvoiceController {
	
    @RequestMapping("/greeting")
    public String greeting() {

        return "Hello merge" ;
    }

}
