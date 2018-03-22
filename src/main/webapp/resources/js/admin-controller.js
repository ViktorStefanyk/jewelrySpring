engineApp.controller('AddNewProductCtrl', function($scope, adminService){
	
	$scope.submitted = false;
	$scope.sizes = null;
	$scope.newFile = {};
	
	
	
	$scope.addNewProduct = function() {
		$scope.submitted = true;
		var file = $scope.newFile;
		if ($scope.addNewForm.$valid) {
			var formData = new FormData();
			formData.append("file", file);
			formData.append("product",new Blob([JSON.stringify($scope.product)],{type: "application/json"}));
			adminService.addNewProduct(formData)
			.then(function success(response) {
				$scope.product = null;
				$scope.sizes = null;
				$scope.submitted = false;
			},
			function error(response){
				console.log('Мы попали в ошибку');
				console.log(response);
			});
		};	
	};
	
	$scope.getCategories = function() {
		adminService.getCategories()
			.then(function(data) {
				$scope.categories = data;
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
	
	$scope.getSizesByCategory = function(categoryId) {
		if(categoryId != '2') {
			adminService.getSizesByCategory(categoryId)
				.then(function(data) {
					$scope.sizes = data;
				},
				function error(data) {
					console.log(data);
					console.log(data.status);
				});
		} else {
			$scope.sizes = null;
		}
	};
		
})