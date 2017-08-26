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
		.when('/addCOA', {
		templateUrl : 'addCOA.html'
		})
	});
	
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
            $http.post('http://localhost:8181/ACM/viewGL').success(function (data) {
                $scope.glList = data;
                console.log(data);
                window.location = "#/viewGL";
						
    });
	}
		$scope.callGlSearch = function () {
			console.log("glSearch");
			var acEntryNo=this.acEntryNo;
			var transNo=this.transNo;
			var custAcNo=this.custAcNo;
			var swiftID=this.swiftID;
			var paymentDate=this.paymentDate;
			var invoiceNo=this.invoiceNo;
			var drCr=this.drCr;
			var dueDate=this.dueDate;
			console.log(acEntryNo);
			console.log(transNo);
			if(acEntryNo==undefined||acEntryNo=='null')
				acEntryNo='';
			if(transNo==undefined||transNo=='null')
				transNo='';
			if(custAcNo==undefined||custAcNo=='null')
				custAcNo='';
			if(swiftID==undefined||swiftID=='null')
				swiftID='';
			if(paymentDate==undefined||paymentDate=='null')
				paymentDate='';
			if(invoiceNo==undefined||invoiceNo=='null')
				invoiceNo='';
			if(drCr==undefined||drCr=='null')
				drCr='';
			if(dueDate==undefined||dueDate=='null')
				dueDate='';
			//var url='http://localhost:8181/ACM/viewGLBySearch/'+acEntryNo+'/'+transNo+'/2343434/GOUP762/564XXX/Cr/13-JUL-17/14-JUL-17';
			var url='http://localhost:8181/ACM/viewGLBySearch';
			console.log(url);
			$http({url:url,method:"POST",params:{'acEntryNo':acEntryNo,'transNo':transNo,'custAcNo':custAcNo,'swiftID':swiftID,'paymentDate':paymentDate,'invoiceNo':invoiceNo,'drCr':drCr,'dueDate':dueDate}}).success(function (data) {
                $scope.glList = data;
                console.log(data);
                window.location = "#/viewGL";
						
    });
	}
	});
	acmApp.controller('glCtrl', function ($scope, $http){    
		
	});