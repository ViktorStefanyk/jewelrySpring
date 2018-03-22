/**
 * 
 */
engineApp.controller('headerCtrl', function($scope, $http) {
	
	var slides = $scope.slides = [];
	var currIndex = 0;
	
	$scope.addSlide = function() {
	    slides.push({
	    	image: '/sj/resources/carousel/' + i + '.jpg',
	    	id: currIndex++
	    	});
	};

	for (var i = 1; i < 8; i++) {
	    $scope.addSlide(i);
	}
	
	$scope.refreshCatalogList = function() {
		$http.get('/sj/rest/header/catalog')
			.success(function(catalogData) {
				$scope.categories = catalogData;
			})
			.error(function(catalogData, status) {
				console.error('Response error', catalogData, status);
		});
	}
	
	$scope.refreshUserName = function() {
		$http.get('/sj/rest/header/loggedname')
			.success(function(usernameData) {
				$scope.customer = usernameData;
			})
			.error(function(usernameData, status) {
				console.log('Response error', usernameData, status);
			});
	}
	
});