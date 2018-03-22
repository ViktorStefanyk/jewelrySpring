<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
		<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">

			<div class="container" ng-if="models.cart.quantityListCart > 0">
				<a class="btn btn-danger pull-left"
					ng-click="clearCart()"> <span
					class="glyphicon glyphicon-remove-sign"></span> Clear Cart
				</a>
			</div>
			
			<div ng-if="models.cart.quantityListCart == 0" >
				<h1 class="text-center">
					<span style="font-size: 30px;"	class="glyphicon glyphicon-shopping-cart"></span> Корзина пустая
				</h1>
			</div>
			
			<table class="table table-hover" ng-if="models.cart.quantityListCart > 0">
				<tr class="text-center">
					<th>Картинка</th>
					<th>Название</th>
					<th>Цена за единицу</th>
					<th>Размер</th>
					<th>Количество</th>
					<th>Сумма</th>
					<th></th>
				</tr>
				<tr class="text-center" ng-repeat="item in models.cart.cartItems">
					<td>
						
							<div style="width: 102.4px; height: 76.8px;" class="thumbnail">
								<img 
									alt="{{models.product.productName}}" 
									src="{{item.product.image.link}}" >
							</div>
					</td>
					<td class="align-middle">{{item.product.productName}}</td>
					<td class="align-middle">{{item.product.productPrice}}</td>
					<td>{{item.product.productSizeName}}</td>
					<td>
					<button type="button" 
							style="font-size: 5px;" 
							class="btn btn-primary btn-sm" 
							ng-click="minusFromCart(item.product.productId)" 
							ng-disabled="item.quantity==1">
                  		<span style="font-size: 10px;" class="glyphicon glyphicon-minus"></span>
             		</button>
						 {{item.quantity}} шт. 
					<button type="button" 
							style="font-size: 5px;" 
							class="btn btn-primary btn-sm" 
							ng-click="addToCart(item.product.productId, item.product.productSizeName)"
							>
                  		<span style="font-size: 10px;" class="glyphicon glyphicon-plus"></span>
             		</button>
					</td>
					<td>{{item.totalPrice}} грн.</td>
					<td><a href="#" class="label label-danger" ng-click="removeFromCart(item.product.productId)"> <span
							class="glyphicon glyphicon-remove" /></span> Remove
					</a></td>
				</tr>
				<tr class="text-center">
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th><h4>Итого:</h4></th>
					<th><h4>{{models.cart.grandTotal}} грн.</h4></th>
					<th></th>
					<th></th>
				</tr>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th class="align-middle">Купить</th>
					<th><a href="/sj/cart/checkout" class="btn btn-success pull-right"> 
							<span class="glyphicon-shopping-cart glyphicon"></span> Оформить заказ
						</a>
				</th>
					<th></th>
					<th></th>
				</tr>
			</table>
			
		</div>
</div>