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
		.when('/proposals', {
		templateUrl : 'proposals.htm'
		})
		.when('/view_stats', {
		templateUrl : 'view_stats.htm'
		})
		.when('/features', {
			templateUrl : 'features.html'
		})
	});
	
	myApp.controller('myController', function($scope,$rootScope, $http){	
		
		/*function to fetch request for proposals*/
		 
		var newScope = $rootScope;
		newScope.proposal_id = $scope.proposal_id;
		
		$scope.myfunc = function () {
        	
			console.log("on click function 1");
			var x = $scope.seller_id;
			console.log(x);
			$http.post('http://localhost:8181/contractmanagementseller/viewrfp/' + x)
			.success(function (data) {
                $scope.proposals = data;                    
                console.log($scope.proposals);
				window.location = "#/view_table";
			});
		}
<<<<<<< HEAD
		$scope.getFeatures = function() { 
			$rootScope.proposal_id = $scope.proposal_id;
			console.log("Proposal Table Ctroller: on click function 2");	
			var y = $rootScope.proposal_id;
	            window.location = "#/proposals";
	       
		}
=======
		
		/* function to get Features for a given proposal id */	
		 
>>>>>>> branch 'master' of https://github.com/johnson-jose/supplychain.git
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
			//var p=$scope.product_id;
			//var spec=$scope.specification;
			var p=3;
			//var spec= new String('i can provide higher');
			console.log(p);
			$http.post('http://localhost:8181/contractmanagementseller/addresponse/'+s+'/'+p+'/a')
			.success(function (data) {
                $scope.stats = data;                    
                console.log(data);
			});
		}   

	});
	
<<<<<<< HEAD
	myApp.controller("proposalTableCtrl", function($scope,$rootScope, $http) {
		
		 /*function to get Features for a given proposal id 
		
			console.log("Proposal Table Ctroller: on click function 2");	
			var y = $scope.proposal_id;
			
			console.log("proposal id=" +y);		
=======
	myApp.controller("proposalTableCtrl", function($scope, $http) {
		/* function to get Features for a given proposal id */
			console.log("on click function 2");			
			//var y = $scope.proposal_id;
			//console.log("proposal id=" +y);		
>>>>>>> branch 'master' of https://github.com/johnson-jose/supplychain.git
			console.log("seller id=" + $scope.seller_id);
            $http.post('http://localhost:8181/contractmanagementseller/listfeatures/1')
            .success(function (data) {
                $scope.features = data;
<<<<<<< HEAD
                console.log(data);
               // window.location = "#/proposals";
            });*/	
            
            console.log("Proposal Table Ctroller: on click function 2");	
			var y = $rootScope.proposal_id;
			
			console.log("proposal id=" +y);		
			console.log("seller id=" + $scope.seller_id);
	        $http.post('http://localhost:8181/contractmanagementseller/listfeatures/' +y)
	        .success(function (data) {
	            $scope.features = data;
	            console.log(data);
	            window.location = "#/proposals";
	        });
            
	});
=======
                console.log($scope.features);
               //window.location = "#/features";
            });
	});	
		
		    		
>>>>>>> branch 'master' of https://github.com/johnson-jose/supplychain.git
	
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