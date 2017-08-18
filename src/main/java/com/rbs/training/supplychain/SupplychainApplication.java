package com.rbs.training.supplychain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rbs.training.supplychain.controller")
@ComponentScan("com.rbs.training.supplychain.ContractManagementOne_chennai")
public class SupplychainApplication {


	public static void main(String[] args) {
		SpringApplication.run(SupplychainApplication.class, args);
	}
}
