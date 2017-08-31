var myApp = angular.module('myApp', ["ngRoute"]);
		myApp.config(function ($routeProvider){
		console.log("page1");
		$routeProvider
		.when('/', {
		redirectTo : 'index.html'
		})
		.when('/view_table', {
		templateUrl : 'view_table.htm'
		})
		.when('/features', {
		templateUrl : 'features.htm'
		})
		.when('/view_stats', {
		templateUrl : 'view_stats.htm'
		})
		.when('/addsubmission', {
		templateUrl : 'addsubmission.htm'
		})
		.when('/viewaddresp', {
		templateUrl : 'viewaddresp.htm'
		})
	});
	
	myApp.controller('myController', function($scope,$rootScope, $http){	
		
		/*function to fetch request for proposals*/
		
		$scope.myfunc = function () {
			console.log("on click function 1");
			var x = $scope.seller_id;
			console.log(x);
			$http.post('http://localhost:8181/contractmanagementseller/viewrfp/' + x)
			.success(function (data) {
                $scope.proposals = data;
                console.log(data);
                $rootScope.proposals = data;
                console.log("RS: " + $rootScope.proposals);
                window.location = "#/view_table";
			});			
		}
		
		$scope.getFeatures = function() { 
			//console.log("get Feature of="  + this.proposal_id);
			var a=document.getElementById("prop_id").innerHTML;
			console.log("new: " + a);
			
			if(a==undefined || a=='')
			{
				console.log("inside if");
				alert("Please Enter a Proposal ID!")
			}
			else{
				console.log("inside else and printing");
				$rootScope.proposal_id = a;
				console.log("RS2 :" + $rootScope.proposal_id);
				console.log("Proposal Table Ctroller: on click function 2");	
		        window.location = "#/features";
			}
		}
		/*function to get status of accepted proposals*/
		
		$scope.myfuncstats = function () {
        	
			console.log("on click function 3");
			var x2 = $scope.seller_id;
			console.log(x2);
			$http.post('http://localhost:8181/contractmanagementseller/fetchbuyerStatus/' + x2)
			.success(function (data) {
                $scope.stats = data;                    
                console.log(data);
				window.location = "#/view_stats";
			});
		}   
		
		/*function to send additional response
		*/
		$scope.addresponse = function () {        	
			console.log("on click function 4");
			var s = $scope.seller_id;
			console.log(s);
			var p= $scope.prod_id;
			console.log(p);
			var spec = $scope.specification;
			console.log(spec);
			//var spec= new String('i can provide higher');
			$http.post('http://localhost:8181/contractmanagementseller/addresponse/' +s+ '/' +p+ '/' +spec)
			.success(function (data) {
                $scope.stats = data;                    
                console.log('hi');
                window.location="#/addsubmission"
			});
		}   

	});
	
	myApp.controller("AddRespCtrl", function($scope,$rootScope, $http, $window) {
		
		 /*function to get Features for a given proposal id */
           
           console.log("Add response Ctroller: on click function 6");	
			var y = $scope.seller_id;
			
			console.log("seller id=" +y);		
			//console.log("seller id=" + $scope.seller_id);
	        $http.post('http://localhost:8181/contractmanagementseller/viewaddresp/' +y)
	        .success(function (data) {
	            $scope.addres = data;
	            console.log(data);
	            window.location="#/viewaddresp"
	        });
	        
	
	});
	
	myApp.controller("proposalTableCtrl", function($scope,$rootScope, $http, $window) {
		
		 /*function to get Features for a given proposal id */
            
            console.log("Proposal Table Ctroller: on click function 2");	
			var y = $rootScope.proposal_id;
			
			console.log("proposal id=" +y);		
			console.log("seller id=" + $scope.seller_id);
	        $http.post('http://localhost:8181/contractmanagementseller/listfeatures/' +y)
	        .success(function (data) {
	            $scope.features = data;
	            console.log(data);
	        });
	        
	        $scope.update = function (prodid, fid, resp) {        	
				console.log("radio button service");
				var s = $scope.seller_id;
				console.log("seller id:" +s);
				var p = prodid;
				console.log("product id:" +p);
				var f = fid;
				console.log("feature id:" + f);
				var r = resp;
				console.log("response:" + r);
				console.log('http://localhost:8181/contractmanagementseller/updatesellerresponse/'
						+ $rootScope.proposal_id +'/' + p + '/' +f + '/' + s + '/' +r);
				$http.post('http://localhost:8181/contractmanagementseller/updatesellerresponse/'
						+ $rootScope.proposal_id +'/' + p + '/' +f + '/' + s + '/' +r)
				.success(function (data) {                  
	                console.log('updated Radio Button');
				});
			}   			
				$scope.showconfirmbox = function (status) {
				console.log("sampleCtrl"); 
				var s = $scope.seller_id;
				if(status=='A'){
					if($window.confirm("Do you want to accept the proposal?")){
						//$scope.result = "You accepted this proposal";
						
						console.log("seller id:" +s);
						$http.post('http://localhost:8181/contractmanagementseller/updatebidsellerstatus/' +s+ '/' +$rootScope.proposal_id + '/'+ status)
						.success(function (data) {                 
			                console.log('updated Seller Status A');
						});
					}
				}
				if(status=='R'){
					if ($window.confirm("Do you want to reject the proposal?")){
						//$scope.result = "You accepted this proposal";
						$http.post('http://localhost:8181/contractmanagementseller/updatebidsellerstatus/' +s+ '/' +$rootScope.proposal_id + '/'+ status)
						.success(function (data) {                 
			                console.log('updated Seller Status R');
						});
					}
					}
				if(status=='P'){
					if ($window.confirm("Do you want to see the proposal later?")){
						$http.post('http://localhost:8181/contractmanagementseller/updatebidsellerstatus/' +s+ '/' +$rootScope.proposal_id + '/'+ status)
						.success(function (data) {                 
			                console.log('updated Seller Status P');
						});
					}
					
					}
				}
	});