var invoiceApp = angular.module('invoiceApp', ["ngRoute"]);
	invoiceApp.config(function ($routeProvider){
		$routeProvider
		.when('/InvoiceLanding', {
		templateUrl : 'InvoiceLanding.html'
		})
		.when('/InvoiceCreate', {
		templateUrl : 'InvoiceCreate.html'
		})
		
	});
	invoiceApp.controller('mainController', function($scope){	
		
		/*function to fetch request for proposals*/
		
		$scope.landingfunc = function () {
		console.log('2');
		window.location = "#/InvoiceLanding";
		
		
	}
	 
	/*function to get status of accepted proposals*/
	
	$scope.createfunc = function () {
		console.log('3');
		window.location = "#/InvoiceCreate";
	}
	});