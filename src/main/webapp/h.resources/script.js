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
				window.location = "#/view_table";
			});
		}
		$scope.getFeatures = function() { 
			$rootScope.proposal_id = $scope.proposal_id;
			console.log("Proposal Table Ctroller: on click function 2");	
	            window.location = "#/features";
	       
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
		$scope.myfunc4 = function () {        	
			console.log("on click function 4");
			var s = $scope.seller_id;
			console.log(s);
			var p=$scope.product_id;
			console.log(p);
			var spec=this.specification;
			console.log(spec);
			//var spec= new String('i can provide higher');
			$http.post('http://localhost:8181/contractmanagementseller/addresponse/'+s+'/3/'+spec)
			.success(function (data) {
                $scope.stats = data;                    
                console.log('hi');
			});
		}   

	});
	
	myApp.controller("proposalTableCtrl", function($scope,$rootScope, $http) {
		
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
				/*$http.post('http://localhost:8181/contractmanagementseller/updatesellerresponse/'
						+ $rootScope.proposal_id +'/' + p + '/' +f + '/' + s + '/' +r)
				.success(function (data) {
	                $scope.stats = data;                    
	                console.log('hi');
				});*/
			}   
            
	});
	
	myApp.controller("sampleController", function ($scope, $window) {
				console.log("sampleCtrl"); 
				$scope.showconfirmbox = function () {
					if($window.confirm("Do you want to accept the proposal?"))
						$scope.result = "You accepted this proposal";
					}
				$scope.showconfirmbox1 = function () {
					if ($window.confirm("Do you want to reject the proposal?"))
					$scope.result1 = "You Rejected this proposal";
					}
				$scope.showconfirmbox2 = function () {
					if ($window.confirm("Do you want to see the proposal later?"))
					$scope.result2 = "You have neither accepted nor rejected the proposal it is saved for later use";
					}
	});