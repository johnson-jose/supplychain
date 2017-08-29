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
			/*  view Invoice  */
			$http.post('http://localhost:8181/invoice/searchInvoice?invoiceID='+invoiceNo).success(function (data) {
				console.log( "in view http");
				$scope.invoice = data;
                console.log($scope.invoice);
                window.location = "#/InvoiceDeleteConfirm";
            });
			/*  view product  */
            $http.post('http://localhost:8181/invoice/viewProduct/?id=' + invoiceNo)
			.success(function (data) {
                $scope.productslist = data;
                console.log($scope.productslist);
                });
                
		}
        $scope.searchfunc = function () {
            
        	console.log("in search function ");
    			var invoiceNo = $scope.invoiceNo;
    			console.log(invoiceNo);
    			/*  view Invoice  */
    			$http.post('http://localhost:8181/invoice/searchInvoice?invoiceID='+invoiceNo).success(function (data) {
    				console.log( "in search page after service");
    				$scope.invoice = data;
                    console.log($scope.invoice);
                    window.location = "#/InvoiceSearch";
                });
    			/*  view product  */
                $http.post('http://localhost:8181/invoice/viewProduct/?id=' + invoiceNo)
    			.success(function (data) {
                    $scope.productslist = data;
                    console.log($scope.productslist);
                });
    	}
      
        $scope.updateSearchfunc = function () {
            
        	console.log("in search function ");
    			var invoiceNo = $scope.invoiceNo;
    			console.log(invoiceNo);
    			/*  view Invoice  */
    			$http.post('http://localhost:8181/invoice/searchInvoice?invoiceID='+invoiceNo).success(function (data) {
    				console.log( "in updat after Search http");
    				$scope.invoice = data;
                    console.log($scope.invoice);
                    window.location = "#/InvoiceUpdate";
                });
    			/*  view product  */
                $http.post('http://localhost:8181/invoice/viewProduct/?id=' + invoiceNo)
    			.success(function (data) {
                    $scope.productslist = data;
                    console.log($scope.productslist);
                });
    	}
        $scope.updatefunc = function () {
            
        	console.log("in update function ");
    			var invoiceNo = $scope.invoiceNo;
    			console.log(invoiceNo);
    			/*  view Invoice  */
    			$http.post('http://localhost:8181/invoice/searchInvoice?invoiceID='+invoiceNo).success(function (data) {
    				console.log( "in updat after Search http");
    				$scope.invoice = data;
                    console.log($scope.invoice);
                    window.location = "#/InvoiceUpdate";
                });
    			/*  view product  */
                $http.post('http://localhost:8181/invoice/viewProduct/?id=' + invoiceNo)
    			.success(function (data) {
                    $scope.productslist = data;
                    console.log($scope.productslist);
                });
    	}
		$scope.landingfunc = function () {
		console.log('go to landing page');
		window.location = "#/InvoiceLanding";
        }
	
	 
		$scope.deleteresultfunc = function () {
		console.log('go to delete result page');
		 $http.post('http://localhost:8181/invoice/deleteInvoice?invoiceID=' + $scope.invoiceNo)
			.success(function (data) {
                $scope.message = data;
                console.log(data);
                });
		window.location = "#/InvoiceDeleteResult";
        }
	});
	