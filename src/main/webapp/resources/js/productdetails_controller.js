engineApp.controller('productDetailsCtrl', function($scope, cartService, $uibModal) {
	
	
	$scope.animationsEnabled = true;
	$scope.models = {};
	

	$scope.refreshProductDetails = function(productId) {
		$scope.productId = productId;
		cartService.refreshProductDetails(productId)
			.then(function(detailsData) {
				$scope.models.product = detailsData;
				$scope.sizesOfProduct(productId);
				$scope.getImagesByProductId(productId);
			});
	};
	
	$scope.addToCart = function() {
		cartService.addToCart($scope.models.product.productId, $scope.models.selectedSizeName);	    
	};
	
	$scope.sizesOfProduct = function(productId) {
		cartService.sizesOfProduct(productId)
			.then(function(data) {
				if (data.length >= 1) {
					$scope.models.sizes = data;
					$scope.models.selectedSizeName = $scope.models.sizes[0].sizeName;
				} else {
					$scope.models.selectedSizeName = 0;
				}
			})
	};
	
	/*Images*/
	$scope.getImagesByProductId = function(productId) {
		cartService.getImagesByProductId(productId)
			.then(function(data) {
				$scope.models.images = data;
				$scope.models.selectedImage = $scope.models.images[0].link; 
			});
	}
	
	$scope.openGallery = function() {
		var modalInstance = $uibModal.open({
			animate: $scope.aminationsEnabled,
			templateUrl: 'imageTemplate.html',
			controller: 'ModalImageInstanceCtrl'
		});
	};
	
	/*------------*/
	
	$scope.deleteProductByProductId = function(productId) {
		cartService.deleteProductByProductId(productId)
			.then(location.href = '/sj/');
	}
	
	/*----------Popup----------- */
	

	$scope.openToDeleteProduct = function () {
	    var modalInstance = $uibModal.open({
	      animation: $scope.animationsEnabled,
	      templateUrl: 'deleteTemplate.html',
	      controller: 'ModalInstanceCtrl'
	    });

	 };
	 
	 $scope.openToUpdateProduct = function () {
		    var modalInstance = $uibModal.open({
		      animation: $scope.animationsEnabled,
		      templateUrl: 'updateTemplate.html',
		      controller: 'ModalInstanceCtrl'
		    });

	 };
	 
	/*--------------------------*/
 
});

engineApp.controller('ModalInstanceCtrl', function ($scope, cartService, $uibModalInstance) {	
	$scope.deleteProduct = function (productId) {
		$uibModalInstance.close($scope.deleteProductByProductId(productId));
	};
	$scope.cancel = function () {
		$uibModalInstance.dismiss('cancel');
	}; 
	$scope.deleteProductByProductId = function(productId) {
		cartService.deleteProductByProductId(productId)
			.then(location.href = '/sj/');
	}
	
	$scope.updateProduct = function(productId) {
		cartService.updateProductByProductId(productId)
			.then(function(product){
				$scope.models.product = product;
			
		})
	}
	
});

engineApp.controller('ModalImageInstanceCtrl', function($scope, cartService, $uibModalInstance) {
	
	$scope.models = {};
	
	$scope.getProductDetailsByProductId = function(productId) {
		cartService.refreshProductDetails(productId)
			.then(function(data){
				$scope.models.product = data;
				$scope.getImagesByProductId(productId);
			});
	};
	
	$scope.getImagesByProductId = function(productId) {
		cartService.getImagesByProductId(productId)
			.then(function(data) {
				$scope.models.images = data;
				$scope.models.selectedImage = $scope.models.images[0].link;
			});
	}
	
	$scope.close = function () {
		$uibModalInstance.dismiss('cancel');
	};
	
});
