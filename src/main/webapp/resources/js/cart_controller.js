engineApp.controller('cartCtrl',  function($scope, cartService) {
	  $scope.models={
			  order: {}
	  };
	  
	  $scope.submitted = false;
	  $scope.selected = undefined;
	  
	  $scope.deliveryTypes = [
		  {
			  id: 1,
			  name: "Нова пошта"
		  },
		  {
			  id: 2,
			  name: "Самовывоз"
		  },
		  {
			  id: 3,
			  name: "Укрпочта"
		  }
	  ];
	  
	  $scope.refreshCart = function(cartId) {
		  cartService.refreshCart($scope.cartId)
		  	.then(function(cartData) {
		  		$scope.models.cart = cartData;
		  		});
	  };
	  
	  $scope.initCartId = function(cartId) {
			$scope.cartId = cartId;
			$scope.refreshCart($scope.cartId);
	  };
	  						
	  $scope.addToCart = function(productId, productSizeName) {
		    cartService.addToCart(productId, productSizeName)
		    	.then(function(cartData) {
		    		$scope.refreshCart($scope.cartId)
		    	})
	  };
	  
	  $scope.minusFromCart = function(productId) {
		  	cartService.minusFromCart(productId)
		  		.then(function(cartData) {
		  			$scope.refreshCart($scope.cartId)
		  		})
	  }
	  
	  $scope.clearCart = function() {
		  	cartService.clearCart($scope.cartId)
		  		.then(function(cartData) {
		  			$scope.refreshCart($scope.cartId)
		  		});
	  };
	  
	  $scope.removeFromCart = function(productId) {
		  	cartService.removeFromCart(productId)
		  		.then(function(cartData){$scope.refreshCart($scope.cartId)});
	  };
	  
	  $scope.getPaymentsType = function() {
		  cartService.getPaymentsType()
		  	.then(function(data) {
		  		$scope.models.payments = data;
		  		$scope.models.order.payment = $scope.models.payments[0];
		  	});
	  };
	  
	  $scope.getDeliveryType = function() {
		  $scope.models.deliveryTypes = $scope.deliveryTypes;
	  }
	  
	  
	  $scope.getAllSettlements = function() {
		  cartService.getAllSettlements()
		  	.then(function(data) {
		  		$scope.settlements = data;
		  	});
	  };
	  
	  $scope.getSettlement = function(settlement) {
		  return cartService.getSettlement(settlement)
		  	.then(function(response){
		  		return response.data.data[0].Addresses.filter(function(item){
		  			return item.Warehouses > 0;
		  		});
		  	});
	  }
	  
	  $scope.getWarehouses = function($item, $model, $label) {
		  $scope.selectedSettlement = $model;
		  cartService.getWarehouses($model)
		  	.then(function(data) {
		  		$scope.warehouses = data.data;
		  		if($scope.warehouses.length > 0) {
		  			$scope.models.orderWarehouse = $scope.warehouses[0].Description;
		  		}
		  	})
	  } 
	  	  
	  $scope.orderConfirmed = function() {
		  $scope.submitted = true;
		  $scope.models.order.cartId = $scope.cartId;
		  if ($scope.otherRecipientFlag == 'undefined') {
			  $scope.models.order.otherRecipient = 'false';
		  } else {
			  $scope.models.order.otherRecipient = $scope.otherRecipientFlag;
		  }
		  
		  if ($scope.models.orderDeliveryType.id == 2) {
			  $scope.models.order.orderDeliveryType = $scope.models.orderDeliveryType.name;
			  $scope.models.order.orderSettlement = 'Вознесенськ';
		  } else {
			  $scope.models.order.orderDeliveryType = $scope.models.orderDeliveryType.name;
		  }
		  
		  $scope.models.order.orderWarehouse = $scope.models.orderWarehouse.Description;
		  if($scope.orderForm.$valid)
			  cartService.orderConfirmed($scope.models.order)
			  	.then(function success(response) {
			  		response = null;
			  		$scope.errorMessage = '';
					$scope.models.order = null;
					$scope.models.cart = null;
					$scope.submitted = false;
					location.href = '/sj/cart/checkout/orderconfirmed';
				},
				function error(response) {
					if (response.status == 409) {
						$scope.errorMessage = "Письмо не было отправлено!";
					}
					console.log('Мы попали в ошибку');
					console.log(response);
				})
	  }
	  	  
});
