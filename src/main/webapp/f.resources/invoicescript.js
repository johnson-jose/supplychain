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
        $scope.deletefunc = function () {
        console.log("on delete function ");

			var invoiceNo = $scope.invoiceNo;
			console.log(invoiceNo);
			$http.post('http://localhost:8180/invoice/searchInvoice?invoiceID='+invoiceNo).success(function (data) {
				console.log( "in view http");
				$scope.invoice = data;
                console.log($scope.invoice);
                window.location = "#/InvoiceDeleteConfirm";
            });
			/*$http.post('http://localhost:8180/invoice/viewInvoices/' + invoiceNo)
			.success(function (data) {
				console.log("hai");
                $scope.invoice = data;
                console.log(data);
                });
            $http.post('http://localhost:8180/invoice/viewProduct/' + invoiceNo)
			.success(function (data) {
                $scope.productlist = data;
                console.log(data);
                });*/
                
		}
    
		$scope.landingfunc = function () {
		console.log('go to landing page');
		window.location = "#/InvoiceLanding";
        }
	
	 
		$scope.deleteresultfunc = function () {
		console.log('go to delete result page');
		 $http.post('http://localhost:8180/invoice/deleteInvoice?invoiceID=' + $scope.invoiceNo)
			.success(function (data) {
                $scope.message = data;
                console.log(data);
                });
		window.location = "#/InvoiceDeleteResult";
        }
	});
	