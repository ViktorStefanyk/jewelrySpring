engineApp.controller('updateExistingProductCtrl', function($scope, adminService){
	
	$scope.submitted = false;
	$scope.models = {};
	
	$scope.updateProduct = function() {
		$scope.submitted = true;
		if ($scope.updateProductForm.$valid) {
			adminService.updateProduct($scope.models.product)
				.then(function success(data) {
					location.href = '/sj/details/'+ $scope.models.product.productId;
					$scope.models.product = null;
				},
				function error(data) {
					console.log(data);
					console.log(data.status);
				});
		};
	};
	
	$scope.getDetailsOfProductForUpdate = function(productId) {
		adminService.getDetailsOfProductForUpdate(productId)
			.then(function(product) {
				$scope.models.product = product;
				$scope.getCategories();
				$scope.getAllSizesByCategory();
			});
	};
		
	$scope.getCategories = function() {
		adminService.getCategories()
			.then(function(data) {
				$scope.categories = data.filter(function(item){
					return item.categoryId == $scope.models.product.category.categoryId;
				});
			});
	};
	
	$scope.getGemstones = function() {
		adminService.getGemstones()
			.then(function(data) {
				$scope.gemstones = data;
			});
	};
	
	$scope.getGenders = function() {
		adminService.getGenders()
			.then(function(data) {
				$scope.genders = data;
			});
	};
	
	$scope.getMaterials = function() {
		adminService.getMaterials()
			.then(function(data) {
				$scope.materials = data;
			});
	};
	
	$scope.getTypes = function() {
		adminService.getTypes()
			.then(function(data) {
				$scope.types = data;
			});
	};
		
	$scope.getAllSizesByCategory = function() {
		if ($scope.models.product.category.categoryId != 2) {
			adminService.getSizesByCategory($scope.models.product.category.categoryId)
				.then(function(data) {
					$scope.sizes = data;
					$scope.getSelectedSizesByProductId();
				},
				function error(data) {
				console.log(data);
				console.log(data.status);
			});
		} else {
			$scope.sizes = null;
		}
	}
	
	$scope.getSelectedSizesByProductId = function() {
		adminService.getSelectedSizesByProductId($scope.models.product.productId)
			.then(function(data){
				$scope.models.product.productSizes = data
			},
			function error(data) {
				console.log(data);
			})
	}
	
	
})