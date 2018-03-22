<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container" >
	<div class="panel panel-default">
		<div class="panel-heading">Контактные данные <a href="/sj/cart/checkout">Редактировать</a></div>
		
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">Выбор способов доставки и оплаты</div>
		<div class="panel-body" ng-controller="cartCtrl">
		
		
			<form name="orderForm"
				  method="POST"
				  novalidate
				  ng-class="{'form-error': submitted}"
				  ng-submit="orderConfirmed()"
				  accept-charset="UTF-8"
				  class="form-horizontal text-center col-md-7">
				  
				<table class="table" ng-init="initCartId('${cartId}')">
				
					<tr ng-repeat="item in models.cart.cartItems">
						<td>
							<div style="width: 51.2px; height: 38.6px; border: 1px solid red;" class="thumbnail">
								<a href="/sj/details/{{item.product.productId}}">
									<img 
										alt="{{models.product.productName}}" 
										src="{{item.product.image.link}}" >
								</a>
							</div>
						</td>
						<td>{{item.product.productName}}</td>
						<td>{{item.quantity}} шт.</td>
						<td>{{item.product.productPrice}} грн.</td>
					</tr>
					
					<tr ng-if="models.orderDeliveryType.id == 2">
						<td>Город</td>
						<td>
							<label>Вознесенськ</label>
						</td>
						<td></td>
						<td></td>
					</tr>
					
					<tr ng-if="models.orderDeliveryType.id != 2">
						<td>Город тест АПИ</td>
						<td>
							<input type="text"
               					   ng-model="models.order.orderSettlement"
              					   uib-typeahead="settlement.MainDescription for settlement in getSettlement($viewValue)"
               					   typeahead-select-on-blur="true"
               					   typeahead-no-results="noSettlements"
               					   typeahead-loading="loadingLocations"
               					   typeahead-focus-first = "true"
               					   typeahead-min-length = "2"
               					   typeahead-select-on-exact = "true"
               					   typeahead-on-select="getWarehouses($item, $model, $label)"
               					   placeholder="Выберите город..."
               					   class="form-control" />
               				<i ng-show="loadingLocations" 
               				   class="glyphicon glyphicon-refresh"></i>
               				<div ng-show="noSettlements">
      							<i class="glyphicon glyphicon-remove"></i> 
      							Город не найден. <br>Проверьте написание или введите ближайший к вам!
   							</div>
						</td>
						<td></td>
						<td></td>
					</tr>
					
					<tr ng-if="selectedSettlement != undefined && models.orderDeliveryType.id == 1">
						<td>Отделение</td>
						<td>
							<select
								ng-model="models.orderWarehouse"
								ng-options="warehouse.Description for warehouse in warehouses track by warehouse.Description"
								placeholder="Выберите отделение ..."
								class="form-control col-lg-3">
							</select>
						</td>
						<td></td>
						<td></td>
					</tr>
								
					<tr>
						<td>Доставка</td>
						<td>
							<div class="btn-group" ng-init="getDeliveryType()">
								<label class="btn btn-info" 
					   				   ng-repeat="deliveryType in models.deliveryTypes"
					    		  	   uib-btn-radio="deliveryType"
					   				   ng-model="models.orderDeliveryType"
					   				   name="order.payment.paymentId">
								  {{deliveryType.name}}
								</label>
							</div>
						</td>
						<td></td>
						<td></td>
					
					</tr>
					
					<tr>
						<td>Оплата</td>
						<td>
														
							<div class="btn-group" ng-init="getPaymentsType()">
								<label class="btn btn-info" 
					   				   ng-repeat="payment in models.payments"
					    		  	   uib-btn-radio="payment"
					   				   ng-model="models.order.payment"
					   				   name="order.payment.paymentId">
								  {{payment.paymentName}}
								</label>
							</div>
						</td>
						<td></td>
						<td></td>
					
					</tr>
							

				</table>
				
				<div class="checkbox">
  					<label>
  						<input type="checkbox"
  							   ng-model="otherRecipientFlag">
  						На подарок!
  					</label>
				</div>
				
				<div ng-if="otherRecipientFlag == true">
				
				<div class="form-group">
					<div>
						<p style="font-size: 10px; color: red;">
									Внимание! Получение заказа по паспорту. <br>
									Введите фамилию, имя и отчество получателя заказа</p>
					</div>						
				</div>
				
				
				<div class="form-group">
					<lable class="control-lable col-lg-3">Имя*:</lable>
						<div class="col-lg-5">
							<input type="text" 
					   			   name="models.order.recipientFirstName" 
					   			   ng-model="models.order.recipientFirstName" 
					               class="form-control" 
					               placeholder="Имя" 
					   			   ng-minlength = "2"
					   			   ng-maxlength = "32"/>
						</div>
				</div>
				
				<div class="form-group">
					<lable class="control-lable col-lg-3">Фамилия*:</lable>
						<div class="col-lg-5">
							<input type="text" 
					   			   name="models.order.recipientLastName" 
					   			   ng-model="models.order.recipientLastName" 
					               class="form-control" 
					               placeholder="Фамилия" 
					   			   ng-minlength = "2"
					   			   ng-maxlength = "32"/>
						</div>
				</div>
				
				<div class="form-group">
					<lable class="control-lable col-lg-3">Отчество*:</lable>
						<div class="col-lg-5">
							<input type="text" 
					   			   name="models.order.recipientPatronymic" 
					   			   ng-model="models.order.recipientPatronymic" 
					               class="form-control" 
					               placeholder="Отчество" 
					   			   ng-minlength = "2"
					   			   ng-maxlength = "32"/>
						</div>
				</div>
				</div>
				
				
				
				<div class="form-group">
						<textarea
					   			name="order.orderCpmment"	
					   			ng-model="models.order.orderComment" 
					   			class="form-control" 
					   			placeholder="Оставьте комментарий ...."
					   			rows="4"
					   			wrap="soft"
					   			cols="100"
					   			ng-minlength = "2"
					   			ng-maxlength = "500">
			   			</textarea>
				</div>
				
				<button type="submit" class="btn btn-success">Заказ подтверждаю</button>
				
			</form>
			
			
			
		</div>
	</div>
</div>