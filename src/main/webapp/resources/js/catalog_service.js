/**
 * 
 */

engineApp.service('CatalogService', function($http, $q) {
	
	return {
		findProductsByParameter: findProductsByParameter,
		getAvailableQuantityProducts: getAvailableQuantityProducts,
		getSizesByCategory: getSizesByCategory,
		getGemstonesByCategory: getGemstonesByCategory,
		getGendersByCategory: getGendersByCategory,
		getTypesByCategory: getTypesByCategory,
		getMaterialsByCategory: getMaterialsByCategory
	};
	
	function getAvailableQuantityProducts(category, selectedSizes, selectedGemstones, selectedGenders, selectedTypes, selectedMaterials) {
		return $http({
			method: 'GET',
			url: '/sj/rest/catalog/products/quantity/'+category,
			params: {
				selectedsizes: selectedSizes,
				selectedgemstones: selectedGemstones,
				selectedgenders: selectedGenders,
				selectedtypes: selectedTypes,
				selectedmaterials: selectedMaterials
			}
		})
			.then(handleSuccess, handleError);
	}
	
	function findProductsByParameter(category, selectedSizes, selectedGemstones, selectedGenders, selectedTypes, selectedMaterials, minPrice, maxPrice) {
		return $http({
			method: 'GET',
			url: '/sj/rest/catalog/products/'+category,
			params: {
				selectedsizes: selectedSizes,
				selectedgemstones: selectedGemstones,
				selectedgenders: selectedGenders,
				selectedtypes: selectedTypes,
				selectedmaterials: selectedMaterials,
				minprice: minPrice,
				maxprice: maxPrice
			}
		})
			.then(handleSuccess, handleError);
	}
	
	function getSizesByCategory(category) {
		return $http.get('/sj/rest/catalog/sizes/' + category)
			.then(handleSuccess, handleError);
	}
	
	function getGemstonesByCategory(category) {
		return $http.get('/sj/rest/catalog/gemstones/' + category)
			.then(handleSuccess, handleError);
	}
	
	function getGendersByCategory(category) {
		return $http.get('/sj/rest/catalog/genders/' + category)
			.then(handleSuccess, handleError);
	}
	
	function getTypesByCategory(category) {
		return $http.get('/sj/rest/catalog/types/' + category)
			.then(handleSuccess, handleError);
	}
	
	function getMaterialsByCategory(category) {
		return $http.get('/sj/rest/catalog/materials/' + category)
			.then(handleSuccess, handleError);
	}
	
	function handleError( response ) {
		if(!angular.isObject(response.data) || !response.data.message) {
			return $q.reject( "An unknown error occurred." ) ;
		}
		return $q.reject( response.data.message ) ;
	};
	
	function handleSuccess( response ) {
        return response.data;
    }
	
});