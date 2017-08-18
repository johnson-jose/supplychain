package com.rbs.training.supplychain.ContractManagementOne_chennai;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicesh")
public class Services {
	
    @RequestMapping("/hw")
    public String helloworld() {
        return "Hello, World!" ;
    }

}
