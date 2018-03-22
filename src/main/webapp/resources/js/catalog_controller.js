/**
 * 
 */
engineApp.controller('CatalogCtrl', function($scope, CatalogService) {
	
	$scope.models = {};
	$scope.isOpenSizes = false;
	$scope.isOpenGemstones = false;
	$scope.isOpenGenders = false;
	$scope.isOpenTypes = false;
	$scope.isOpenMaterials = false;
	$scope.models.selectedSizes = [];
	$scope.models.selectedGemstones = [];
	$scope.models.selectedGenders = [];
	$scope.models.selectedTypes = [];
	$scope.models.selectedMaterials = [];
	
	
	
	
	$scope.getAvailableQuantityProducts = function(category) {
		CatalogService.getAvailableQuantityProducts($scope.models.category, $scope.models.selectedSizes, $scope.models.selectedGemstones,
												$scope.models.selectedGenders, $scope.models.selectedTypes, $scope.models.selectedMaterials)
			.then(function(data) {
				$scope.models.quantities = data;
			});
	};
	
	
	$scope.findProductsByParameters = function(category) {
		$scope.models.category = category;
		CatalogService.findProductsByParameter($scope.models.category, $scope.models.selectedSizes, $scope.models.selectedGemstones,
												$scope.models.selectedGenders, $scope.models.selectedTypes, $scope.models.selectedMaterials,
												$scope.models.minPrice, $scope.models.maxPrice)
			.then(function(data) {
				$scope.isOpenSizes = false;
				$scope.isOpenGemstones = false;
				$scope.isOpenGenders = false;
				$scope.isOpenTypes = false;
				$scope.isOpenMaterials = false;
				if (data.length) {
					$scope.models.products = data;
				} else {
					$scope.messageNotFound = "По данному запросу продуктов не найдено";
					$scope.models.products = data;
				}
				
				
			})
	};
	
	$scope.getSizesByCategory = function(category) {
		CatalogService.getSizesByCategory(category)
			.then(function(data){
				if (data == "") {
					$scope.models.sizes = null;
				} else {
					$scope.models.sizes = data;
				}
			});
	};
	
	$scope.getGemstonesByCategory = function(category) {
		CatalogService.getGemstonesByCategory(category)
			.then(function(data){
				$scope.models.gemstones = data;
			});
	};
	
	$scope.getGendersByCategory = function(category) {
		CatalogService.getGendersByCategory(category)
			.then(function(data){
				$scope.models.genders = data;
			});
	};
	
	$scope.getTypesByCategory = function(category) {
		CatalogService.getTypesByCategory(category)
			.then(function(data){
				$scope.models.types = data;
			});
	};
	
	$scope.getMaterialsByCategory = function(category) {
		CatalogService.getMaterialsByCategory(category)
			.then(function(data){
				$scope.models.materials = data;
			});
	};
	
	$scope.$watchCollection(
			function() {
				return $scope.models.selectedSizes;
			},
			function() {
				if ($scope.models.selectedSizes.length) {
					$scope.isOpenSizes = true;
					$scope.isOpenGemstones = false;
					$scope.isOpenGenders = false;
					$scope.isOpenTypes = false;
					$scope.isOpenMaterials = false;
				}
	});
	
	$scope.$watchCollection(
			function() {
				return $scope.models.selectedGemstones;
			},
			function() {
				if($scope.models.selectedGemstones.length) {
					$scope.isOpenGemstones = true;
					$scope.isOpenSizes = false;
					$scope.isOpenGenders = false;
					$scope.isOpenTypes = false;
					$scope.isOpenMaterials = false;
					$scope.getAvailableQuantityProducts($scope.models.category);
				}
	});
	
	$scope.$watchCollection(
			function() {
				return $scope.models.selectedGenders;
			},
			function() {
				if($scope.models.selectedGenders.length) {
					$scope.isOpenGemstones = false;
					$scope.isOpenSizes = false;
					$scope.isOpenGenders = true;
					$scope.isOpenTypes = false;
					$scope.isOpenMaterials = false;
					$scope.getAvailableQuantityProducts($scope.models.category);
				}
	});
	
	$scope.$watchCollection(
			function() {
				return $scope.models.selectedTypes;
			},
			function() {
				if($scope.models.selectedTypes.length) {
					$scope.isOpenGemstones = false;
					$scope.isOpenSizes = false;
					$scope.isOpenGenders = false;
					$scope.isOpenTypes = true;
					$scope.isOpenMaterials = false;
					$scope.getAvailableQuantityProducts($scope.models.category);
				}
	});
	
	$scope.$watchCollection(
			function() {
				return $scope.models.selectedMaterials;
			},
			function() {
				if($scope.models.selectedMaterials.length) {
					$scope.isOpenGemstones = false;
					$scope.isOpenSizes = false;
					$scope.isOpenGenders = false;
					$scope.isOpenTypes = false;
					$scope.isOpenMaterials = true;
					$scope.getAvailableQuantityProducts($scope.models.category);
				}
	});
	
});