/**
 * 
 */

engineApp.service('cartService', function($http, $q){
	
	return {
		refreshCart: refreshCart,
		addToCart: addToCart,
		minusFromCart: minusFromCart,
		clearCart: clearCart,
		removeFromCart: removeFromCart,
		refreshProductDetails: refreshProductDetails,
		sizesOfProduct: sizesOfProduct,
		deleteProductByProductId: deleteProductByProductId,
		getImagesByProductId: getImagesByProductId,
		getCurrentUser: getCurrentUser,
		orderConfirmed: orderConfirmed,
		getPaymentsType: getPaymentsType,
		getSettlement: getSettlement,
		getAllSettlements: getAllSettlements,
		getWarehouses: getWarehouses
	};
	
	function refreshCart(cartId) {
		return $http.get('/sj/rest/cart/' + cartId)
			.then(handleSuccess, handleError);
	};
	
	/*-----------------Add and Minus the product--------------*/
	
	function addToCart(productId, selectedSizeName) {
		return $http.put('/sj/rest/cart/add/' + productId, selectedSizeName)
		.then(handleSuccess, handleError);
	};
	
	function minusFromCart(productId) {
		return $http.put('/sj/rest/cart/minus/' + productId)
			.then(handleSuccess, handleError);
	}
	
	/*--------------------------------------------------------*/
	
	function clearCart(cartId) {
		return $http.delete('/sj/rest/cart/' + cartId)
			.then(handleSuccess, handleError);
	}
	
	function removeFromCart(productId) {
		return $http.put('/sj/rest/cart/remove/' + productId)
			.then(handleSuccess, handleError);
	}
	
	/*Product details*/
	
	function refreshProductDetails(productId) {
		return $http.get('/sj/rest/productdetails/' + productId)
			.then(handleSuccess, handleError);
	}
	
	function sizesOfProduct(productId) {
		return $http.get('/sj/rest/productdetails/sizes/' + productId)
			.then(handleSuccess, handleError);
	}
	
	/*Get all images*/
	
	function getImagesByProductId(productId) {
		return $http.get('/sj/rest/productdetails/images/' + productId)
			.then(handleSuccess, handleError)
	}
	
	/* Delete product by productId */
	
	function deleteProductByProductId(productId) {
		return $http.delete('/sj/rest/admin/delete/' + productId)
			.then(handleSuccess, handleError);
	}
	
	/*-----------------------*/
	
	/*Get current user */
	function getCurrentUser() {
		return $http.get('/sj/rest/cart/getcurrentuser')
			.then(handleSuccess, handleError);
	}
	
	/*------------------------*/
	
	/*------------------------*/
	
	function getPaymentsType() {
		return $http.get('/sj/rest/order/payments')
			.then(handleSuccess, handleError);
	}
	
	function orderConfirmed(order) {
		return $http.post('/sj/rest/order/order', order)
			.then(handleSuccess, handleError);
	}
		
	function getAllSettlements() {
		return $http.get('/sj/rest/order/all/settlements')
			.then(handleSuccess, handleError);
	}
	
	function getSettlement(settlement) {
		return $http({
			method: 'POST',
			url: 'http://api.novaposhta.ua/v2.0/json/Address/searchSettlements/',
			headers: {
				"Content-Type": "application/json"
			},
			data: {
				"apiKey": "3b9476217ed77601a68698292d19566a",
				"modelName": "Address",
				"calledMethod": "searchSettlements",
				"methodProperties": {
					"CityName": settlement,
					"Limit": "500"
				}
			}
		})
	}
		
	function getWarehouses(warehouse) {
		return $http({
			method: 'POST',
			url: 'http://api.novaposhta.ua/v2.0/json/AddressGeneral/getWarehouses',
			headers: {
				"Content-Type": "application/json"
			},
			data: {
				"modelName": "AddressGeneral",
				"calledMethod": "getWarehouses",
				"methodProperties": {
					"Language": "ru",
					"CityName":warehouse
				},
				"apiKey": "3b9476217ed77601a68698292d19566a"
			}
		})
		.then(handleSuccess, handleError);
	}	
	
	function handleError(response) {
		if(!angular.isObject(response.data) || !response.data.message) {
			return $q.reject( "An unknown error occurred." ) ;
		}
		return $q.reject(response.data.message) ;
	};
	
	function handleSuccess( response ) {
        return response.data;
    }
	
	
});