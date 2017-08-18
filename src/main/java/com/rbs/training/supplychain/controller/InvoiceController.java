package com.rbs.training.supplychain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplychain")
public class InvoiceController {
	
    @RequestMapping("/greeting")
    public String greeting() {
<<<<<<< HEAD
        return "Hello check" ;
=======
        return "Hello" ;
>>>>>>> branch 'master' of https://github.com/johnson-jose/supplychain.git
    }

}
