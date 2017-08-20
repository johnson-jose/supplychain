package com.rbs.training.supplychain.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.training.supplychain.model.Invoice;
import com.rbs.training.supplychain.model.Proposal;
import com.rbs.training.supplychain.model.Proposal_Sellers_Bid;
import com.rbs.training.supplychain.service.ContractManagementSellerService;

@RestController
@RequestMapping("/contractmanagementseller")
public class ContractManagementSellerController {
	 ContractManagementSellerService service = new ContractManagementSellerService();
    
	
	 @RequestMapping(value = "/viewrfp/{sellerid}",method = RequestMethod.POST)
    public ResponseEntity<List<Proposal_Sellers_Bid>> getProposals(@PathVariable("sellerid") String id) {
		
		int seller_id = Integer.parseInt(id);
		
			List<Proposal_Sellers_Bid> proposals = new ArrayList<Proposal_Sellers_Bid>();
			try {
				proposals = service.listAllProposals(seller_id);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			if (proposals.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Proposal_Sellers_Bid>>(proposals, HttpStatus.OK);
			
		}
    
   
	@RequestMapping("/listfeatures")
	public String service2() {
        return "Hello, World!" ;
    }
    
    @RequestMapping("/updatesellerresponse")
    public String service3() {
        return "Hello, World!" ;
    }
   

}
