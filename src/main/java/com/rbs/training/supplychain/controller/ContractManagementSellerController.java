package com.rbs.training.supplychain.controller;
/* Team H : contract management seller's controller
 * Services :
 * 	1.
 * 	2.
 * 	3.
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contractmanagementseller")
public class ContractManagementSellerController {
	
    @RequestMapping("/viewrfp")
    public String service1() {
        return "Hello, World!" ;
    }
    
    @RequestMapping("/listfeatures")
    public String service2(){
    	return "Hello, world!";
    }
    
    @RequestMapping("/updatesellerresponse")
    public String service3() {
        return "Hello, World!" ;
    }
   

}
