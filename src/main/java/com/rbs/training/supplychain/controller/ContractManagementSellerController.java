package com.rbs.training.supplychain.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.training.supplychain.model.Features;
import com.rbs.training.supplychain.model.Invoice;
import com.rbs.training.supplychain.model.Proposal;
import com.rbs.training.supplychain.model.Proposal_Sellers_Bid;
import com.rbs.training.supplychain.service.ContractManagementSellerService;

@RestController
@RequestMapping("/contractmanagementseller")
public class ContractManagementSellerController {
	 ContractManagementSellerService service = new ContractManagementSellerService();
    
	/*service 1*/
	 @RequestMapping(value = "/viewrfp/{sellerid}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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
    
	 /*service 2*/
	@RequestMapping(value="/listfeatures/{proposalid}",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Features>> getFeatures(@PathVariable("proposalid") String propid) {
        int proposal_id = Integer.parseInt(propid);
		List<Features> featureslist= new ArrayList<Features>();
		try {
			featureslist = service.getFeaturesforaproduct(proposal_id);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(featureslist.isEmpty()){
			//return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Features>>(featureslist, HttpStatus.OK);
    }
    
    @RequestMapping("/updatesellerresponse")
    public String service3() {
        return "Hello, World!" ;
    }
    
 /*  SERVICE 4*/
    
    @RequestMapping(value = "/updatebidsellerstatus/{seller_id}/{proposal_id}/{seller_status}",method = RequestMethod.POST)
   
    public void updatebidSellerStatus(@PathVariable("seller_id") String id,@PathVariable("proposal_id") String pid,@PathVariable("seller_status") String sts) {
    	
    	int seller_id = Integer.parseInt(id);
    	int proposal_id=Integer.parseInt(pid);
    	try {
    		
			 service.updateBidSellerStatus(seller_id,proposal_id,sts);
			 
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
    	
      
    }
   
   

}
