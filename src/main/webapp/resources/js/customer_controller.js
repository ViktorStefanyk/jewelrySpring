engineApp.controller('customerCtrl',  function($scope, cartService) {
	
	$scope.models = {};
	
	$scope.getCurrentCustomer = function(){
		cartService.getCurrentUser()
			.then(function(data) {
				$scope.models.customer = data;
			})
	}
	
	$scope.getSettlement = function(settlement) {
		$rootScope.selectedSettlement = settlement;
		  return cartService.getSettlement(settlement)
		  	.then(function(data){
		  		return data.data.data[0].Addresses.filter(function(item){
		  			return item.Warehouses > 0;
		  		});
		  	});
	  }
	
	
})