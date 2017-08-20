var myApp = angular.module('myApp', ["ngRoute"]);
		myApp.config(function ($routeProvider){
		console.log("page1");
		$routeProvider
		.when('/', {
		redirectTo : 'index.html'
		})
		.when('/view_table', {
		templateUrl : 'view_table.html'
		})
	});
	myApp.controller('myController', function($scope, $http){				
				$http.get('proposals.txt').success(function (data) {
                    $scope.proposals = data;

                })
            });
var myApp2 = angular.module('myApp2', []);
	myApp2.controller('proposalTableCtrl', function ($scope, $http) {       
				console.log("proposalTableCtrl");
                $http.get('features.txt').success(function (data) {
                    $scope.features = data;
                });           
        });
	myApp2.controller("sampleController", function ($scope, $window) {
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