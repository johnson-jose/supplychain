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
import com.rbs.training.supplychain.model.Sfeatures;
import com.rbs.training.supplychain.service.ContractManagementSellerService;

@RestController
@RequestMapping("/contractmanagementseller")
public class ContractManagementSellerController {
	 ContractManagementSellerService service = new ContractManagementSellerService();
    
	/*service 1*/
	 @RequestMapping(value = "/viewrfp/{seller_id}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Proposal_Sellers_Bid>> getProposals(@PathVariable("seller_id") String id) {
		
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
	 
	 @RequestMapping(value="/listfeatures/{proposal_id}",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Sfeatures>> getFeatures(@PathVariable("proposal_id") String propid) {
	        int proposal_id = Integer.parseInt(propid);
			List<Sfeatures> featureslist= new ArrayList<Sfeatures>();
			try {
				featureslist = service.getFeaturesforaproduct(proposal_id);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			if(featureslist.isEmpty()){
				//return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Sfeatures>>(featureslist, HttpStatus.OK);
	    }
						
				
	
    @RequestMapping("/updatesellerresponse/{proposal_id}/{product_id}/{feature_id}/{seller_id}/{response}")
    public void updateresponse(@PathVariable("proposal_id") String propid,@PathVariable("product_id") String prodid, @PathVariable("feature_id") String fid,@PathVariable("seller_id") String sid, @PathVariable("response") String response) throws ClassNotFoundException, SQLException {
        int proposalid = Integer.parseInt(propid);
        int productid = Integer.parseInt(prodid);
        int featureid = Integer.parseInt(fid);
        int sellerid = Integer.parseInt(sid);
        service.updatesellerresponse(productid,proposalid,featureid,sellerid, response);
      
    }
    
   
  /*  service to fetch the buyer's status for the proposals 
	 * which the seller has already accepted
	 * service 4*/
    @RequestMapping(value="/fetchbuyerStatus/{seller_id}",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Proposal_Sellers_Bid>> getBuyerStatus(@PathVariable("seller_id") String sid) {
        int seller_id = Integer.parseInt(sid);
		List<Proposal_Sellers_Bid> buyerstatuslist= new ArrayList<Proposal_Sellers_Bid>();
		try {
			buyerstatuslist = service.fetchBuyerStatus(seller_id);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(buyerstatuslist.isEmpty()){
			//return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Proposal_Sellers_Bid>>(buyerstatuslist, HttpStatus.OK);
    }
    
    /*service to send additional response-service 5*/
    @RequestMapping(value = "/addresponse/{seller_id}/{product_id}/{specification}",method = RequestMethod.POST)
    
    public void additionalResponse(@PathVariable("seller_id") String id,
    		@PathVariable("product_id") String pid,@PathVariable("specification") String sps){
    	
    	int seller_id = Integer.parseInt(id);
    	int product_id=Integer.parseInt(pid);
    	try {
    		
			 service.addnresp(seller_id, product_id, sps);
			 
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
    }
}
   /* @RequestMapping("/updatesellerresponse")
    public String service3() {
        return "Hello, World!" ;
    }*/
