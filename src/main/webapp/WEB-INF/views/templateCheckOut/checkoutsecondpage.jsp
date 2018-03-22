<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container" >
	<div class="panel panel-default">
		<div class="panel-heading">Контактные данные</div>
		<div class="panel-body">

			<div class="container">
			
				<div class="text-center col-md-5" ng-controller="customerCtrl" ng-init="getCurrentCustomer()">
						
					<form class="form-horizontal text-center"
						  name="customerForm"
				  		  method="POST"
				  		  novalidate
				  		  ng-class="{'form-error': submitted}"
				  		  ng-submit=""
				  		  accept-charset="UTF-8">
						<div class="form-group">
							<label class="control-label col-sm-4">Имя:*</label>
								<div class="col-sm-8">
									<input style="padding: 3px 2px 3px 2px" 
								   	   		class="form-control"
								   	   		type="text" 
								       		ng-model="models.customer.userFirstName">
								</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-4">Фамилия:*</label>
								<div class="col-sm-8">
									<input style="padding: 3px 2px 3px 2px" 
									   		class="form-control"
									   		type="text" 
									   		ng-model="models.customer.userLastName">
								</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-4">Номер телефона:*</label>
								<div class="col-sm-8">
									<input style="padding: 3px 2px 3px 2px" 
											class="form-control"
											type="number" 
											min="0" 
											ng-model="models.customer.userPhoneNumber"
											value="models.customer.userPhoneNumber" />
								</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-4">Город:*</label>
								<div class="col-sm-8">
									<input type="text"
               					   			ng-model="selectedSettlement"
              					   			uib-typeahead="settlement.MainDescription for settlement in getSettlement($viewValue)"
               					  			typeahead-select-on-blur="true"
               					   			typeahead-no-results="noSettlements"
               					   			typeahead-loading="loadingLocations"
               					   			typeahead-focus-first = "true"
               					   			typeahead-min-length = "2"
               					   			typeahead-select-on-exact = "true"
               					   			class="form-control" />
               							<i ng-show="loadingLocations" class="glyphicon glyphicon-refresh"></i>
               							<div ng-show="noSettlements">
      										<i class="glyphicon glyphicon-remove"></i> Ни одного города найдено!
   										</div>
   								</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-4">Почта:*</label>
								<div class="col-sm-8">
									<input style="padding: 3px 2px 3px 2px" 
											class="form-control"
											type="email" 
											ng-model="models.customer.userEmail" />
								</div>
						</div>
						
						<a href="/sj/cart/checkout/stepsecond" class="btn btn-success">Далее</a>
						
					</form>
					
					
					
				</div>
				
				<div class="col-md-5" ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
					<div class="form-inline" ng-repeat="item in models.cart.cartItems">
						<div class="form-group">
							<div style="width: 102.4px; height: 76.8px; border: 1px solid red;" class="thumbnail">
								<a href="/sj/details/{{product.productId}}">
									<img 
										alt="{{item.product.productName}}" 
										src="{{item.product.image.link}}" >
								</a>
							</div>
						</div>
						<div style="vertical-align: middle;" class="form-group">
							<a href="/sj/details/{{product.productId}}">
								<p>{{item.product.productName}}</p>
							</a>
							<p><span>{{item.quantity}} шт. </span> <span>{{item.product.productPrice}} грн.</span></p>
						</div>
					</div>
					<div style="border-top: 1px solid black;">
						Итого: {{models.cart.grandTotal}} грн.
					</div>
					
				</div>
				
				
			</div>

		</div>


	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">Выбор способов доставки и оплаты</div>
	</div>
</div>