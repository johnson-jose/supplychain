 var invoiceApp = angular.module('invoiceApp', ["ngRoute"]);
	invoiceApp.config(function ($routeProvider){
		$routeProvider
		.when('/InvoiceLanding', {
		templateUrl : 'InvoiceLanding.html'
		})
		.when('/InvoiceCreate', {
		templateUrl : 'InvoiceCreate.html'
		})
		.when('/InvoiceView', {
		templateUrl : 'InvoiceView.html'
		})
		.when('/InvoiceUpdate', {
		templateUrl : 'InvoiceUpdate.html'
		})
		.when('/InvoiceDelete', {
		templateUrl : 'InvoiceDelete.html'
		})
		.when('/InvoiceSearch', {
		templateUrl : 'InvoiceSearch.html',
	
		})
		.when('/InvoiceDeleteConfirm', {
		templateUrl : 'InvoiceDeleteConfirm.html',
	
		})
	});
	
	
	invoiceApp.controller('mainController', function($scope, $http){
		 $scope.createinvoicefunc = function () {
		        console.log("in create invoice function ");
		        window.location = "#/InvoiceCreate";
		 }
	});
	