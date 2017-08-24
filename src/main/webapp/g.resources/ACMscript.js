var acmApp = angular.module('acmApp', ["ngRoute"]);
		acmApp.config(function ($routeProvider){
		console.log("page1");
		$routeProvider
		.when('/', {
		redirectTo : 'ACMindex.html'
		})
		.when('/coaList', {
		templateUrl : 'coaList.html'
		})
		.when('/viewGL', {
		templateUrl : 'viewGL.html'
		})
	});
	
	/*myApp.controller('myController', function($scope, $http){	
		
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
	});*/
	
	acmApp.controller('coaListCtrl', function ($scope, $http){    
		$scope.callCoaList = function () {
				console.log("coaListCtrl");
                $http.post('http://localhost:8181/ACM/viewCOAlist').success(function (data) {
                    $scope.coaList = data;
                    console.log(data);
                    window.location = "#/coaList";
							
        });
		}
		$scope.callGl = function () {
			console.log("glCtrl");
            $http.post('http://localhost:8181/ACM/viewGL').success(function (data) {
                $scope.glList = data;
                console.log(data);
                window.location = "#/viewGL";
						
    });
	}
	});
	acmApp.controller('glCtrl', function ($scope, $http){    
		
	});
	/*myApp.controller("sampleController", function ($scope, $window) {
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
	});*/