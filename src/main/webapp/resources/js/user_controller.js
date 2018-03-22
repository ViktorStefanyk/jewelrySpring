/**
 * 
 */
engineApp.controller('productCtrl',  function($scope, $http) {
		
	$scope.refreshProduct = function() {
		$http.get('/sj/rest/product/')
			.success(function(productData){
				$scope.products = productData;
		})
			.error(function(productData){
				console.error('Repos error', productData);
				console.log(productData);
		});
	}
	
});