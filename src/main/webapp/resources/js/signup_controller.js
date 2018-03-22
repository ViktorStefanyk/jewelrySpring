/**
 * 
 */
engineApp.controller('SignupCtrl', function($scope, SignupService) {
	
	$scope.submitted = false;
	
	$scope.saveCustomer = function() {
		$scope.submitted = true;
		if ($scope.customerForm.$valid) {
			SignupService.saveCustomer($scope.customer)
				.then(function success(response) {
					$scope.message = 'User added!';
					$scope.errorMessage = '';
					$scope.customer = null;
					$scope.submitted = false;
					location.href = '/sj/login';
				},
				function error(response) {
					if (response.status == 409) {
						$scope.errorMessage = response.data[0];
					} else {
						$scope.errorMessage = 'Error adding user!';
					}
					$scope.message = '';
				});
		}
	}
	
	
	$scope.resetForm = function () {
		$scope.customerForm.$setPristine();
		$scope.customer = null;
		$scope.message = '';
		$scope.errorMessage = '';
		$scope.submitted = false;
	};
		
});