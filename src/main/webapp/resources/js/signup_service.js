/**
 * 
 */
engineApp.service('SignupService', function($http, $q) {
	
	return {
		saveCustomer: saveCustomer
	};
	
	function saveCustomer(customer) {
		return $http ({
			method: 'POST',
			url: '/sj/rest/customer/signup',
			params: {
				userFirstName: customer.userFirstName,
				userLastName: customer.userLastName,
				userPhoneNumber: customer.userPhoneNumber,
				userEmail: customer.userEmail,
				userPassword: customer.userPassword
			}
		}).then(handleSuccess, handleError); 
	};
	
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