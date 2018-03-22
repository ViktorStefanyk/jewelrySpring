engineApp.service('adminService', function($http, $q) {
	
	
	return {
		addNewProduct: addNewProduct,
		updateProduct: updateProduct,
		getDetailsOfProductForUpdate: getDetailsOfProductForUpdate,
		getCategories: getCategories,
		getGemstones: getGemstones,
		getGenders: getGenders,
		getMaterials: getMaterials,
		getTypes: getTypes,
		getSizesByCategory: getSizesByCategory,
		getSelectedSizesByProductId: getSelectedSizesByProductId
	};
	
	function addNewProduct(formData) {
		return $http({
			method: 'POST',
			url: '/sj/rest/admin/add', 
			headers: { 'Content-Type': undefined},
			data: formData
		})
			.then(handleSuccess, handleError);
	};
	
	function updateProduct(product) {
		return $http.post('/sj/rest/admin/update', product)
			.then(handleSuccess, handleError);
	}
	
	function getDetailsOfProductForUpdate(productId) {
		return $http.get('/sj/rest/admin/getinformation/' + productId)
			.then(handleSuccess, handleError);	
	}
	
	function getCategories() {
		return $http.get('/sj/rest/admin/categories')
			.then(handleSuccess, handleError);
	}
	
	function getGemstones() {
		return $http.get('/sj/rest/admin/gemstones')
			.then(handleSuccess, handleError);
	}
	
	function getGenders() {
		return $http.get('/sj/rest/admin/genders')
			.then(handleSuccess, handleError);
	}
	
	function getMaterials() {
		return $http.get('/sj/rest/admin/materials')
			.then(handleSuccess, handleError);
	}
	
	function getTypes() {
		return $http.get('/sj/rest/admin/types')
			.then(handleSuccess, handleError);
	}
	
	function getSizesByCategory(productCategory) {
		return $http({
			method: 'GET',
			url: '/sj/rest/admin/sizes',
			params: {
				productCategory: productCategory
			}
		}).then(handleSuccess, handleError);
	}
	
	function getSelectedSizesByProductId(productId) {
		return $http({
			method: 'GET',
			url: '/sj/rest/admin/selected/sizes', 
			params: {
				productId: productId
			}
		}).then(handleSuccess, handleSuccess);
	}
	
	function handleError(response) {
		if(!angular.isObject(response.data) || !response.data.message) {
			if (response.status == 409) {
				return $q.reject(response);
			} else {
				return $q.reject( "An unknown error occurred." ) ;
			}
		}
		return $q.reject(response.data.message) ;
	};
	
	function handleSuccess(response) {
        return response.data;
    };
	
});


//engineApp.directive('fileModel', ['$parse', function ($parse) {
//    return {
//       restrict: 'A',
//       link: function(scope, element, attrs) {
//          var model = $parse(attrs.fileModel);
//          var modelSetter = model.assign;
//          
//          element.bind('change', function(){
//             scope.$apply(function(){
//                modelSetter(scope, element[0].files[0]);
//             });
//          });
//       }
//    };
// }]);

engineApp.directive('fileField', function() {
	  return {
		    require:'ngModel',
		    restrict: 'E',
		    link: function (scope, element, attrs, ngModel) {
		        // set default bootstrap class
		        if(!attrs.class && !attrs.ngClass){
		            element.addClass('btn');
		        }

		        var fileField = element.find('input');

		        // If an ACCEPT attribute was provided, add it to the input.
		        if (attrs.accept) {
		          fileField.attr('accept', attrs.accept);
		        }

		        fileField.bind('change', function(event){
		            scope.$evalAsync(function () {
		              ngModel.$setViewValue(event.target.files[0]);
		              if(attrs.preview){
		                var reader = new FileReader();
		                reader.onload = function (e) {
		                    scope.$evalAsync(function(){
		                        scope[attrs.preview]=e.target.result;
		                    });
		                };
		                reader.readAsDataURL(event.target.files[0]);
		              }
		            });
		        });
		        fileField.bind('click',function(e){
		            e.stopPropagation();
		        });
		        element.bind('click',function(e){
		            e.preventDefault();
		            fileField[0].click();
		        });
		    },
		    template:'<button type="button"><ng-transclude></ng-transclude><input type="file" style="display:none"></button>',
		    replace:true,
		    transclude:true
		  };
});