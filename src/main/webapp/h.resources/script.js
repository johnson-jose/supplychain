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
	});
	
	myApp.controller('myController', function($scope, $http){	
		
		$scope.myfunc = function () {
        	
			console.log("on click function ");
			var x = $scope.seller_id;
			console.log(x);
			$http.post('http://localhost:8181/contractmanagementseller/viewrfp/' + x)
			.success(function (data) {
                $scope.proposals = data;                    
                console.log(data);
				window.location = "#/view_table";
			});
		}        		
	});
	
	myApp.controller('proposalTableCtrl', function ($scope, $http){       
				console.log("proposalTableCtrl");
				
				var y = 1;
				console.log("y=" +y);
                $http.post('http://localhost:8181/contractmanagementseller/listfeatures/' + y)
                .success(function (data) {
                    $scope.features = data;
                    console.log(data);
							
        });
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