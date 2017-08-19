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
	
    @RequestMapping("/service1")
    public String service1() {
        return "Hello, World!" ;
    }
    @RequestMapping("/service2")
    public String service2() {
        return "Hello, World!" ;
    }
    @RequestMapping("/service3")
    public String service3() {
        return "Hello, World!" ;
    }

}
