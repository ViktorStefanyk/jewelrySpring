<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<div ng-controller="productDetailsCtrl" ng-init="refreshProductDetails('${productId}')">
<div class="container" >
	<div class="row">
		<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">

			<div calss="container">
				<div class="row">
					<div class="scrollbar col-sm-2 col-md-2" >
						<label class="thumbnail" 
					   			ng-repeat="image in models.images"
					   			uib-btn-radio="image.link"
					   			ng-model="models.selectedImage">
								<img src="{{image.link}}">
						</label>
					</div>
					<div class="col-sm-7 col-md-7">
						<div class="thumbnail">
							<a href="#" ng-click="openGallery()">
								<img 
									alt="{{models.product.productName}}" 
									src="{{models.selectedImage}}" >
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/ng-template" id="imageTemplate.html">

    <!-- Modal content-->
    <div class="modal-content" ng-init="getProductDetailsByProductId('${productId}')">
      <div class="modal-header">
        <button type="button" class="close" ng-click="close()">&times;</button>
        <h3 class="modal-title">{{models.product.productName}}</h3>
		<h4 class="modal-title">{{models.product.productPrice}} грн.</h4>
      </div>
      <div class="modal-body">
        <div calss="container" ng-init="getProductDetailsByProductId('${productId}')">
				<div class="row">
					<div style="height: 350px;" class="scrollbar col-md-2" >
						<label class="thumbnail" 
					   			ng-repeat="image in models.images"
					   			uib-btn-radio="image.link"
					   			ng-model="models.selectedImage">
								<img src="{{image.link}}">
						</label>
					</div>
					<div class="col-sm-10 col-md-10">
						<div class="thumbnail">
								<img 
									alt="{{models.product.productName}}" 
									src="{{models.selectedImage}}" >
						</div>
					</div>
				</div>
		</div>
      </div>
    </div>

			

    	</script>
		
		
		<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">

		<sec:authorize access="hasRole('ROLE_ADMIN')">
					
			<div class="pull-right">
				<button type="button" class="btn btn-info" ng-click="openToUpdateProduct()">Обновить продукт</button>
				<button type="button" class="btn btn-danger" ng-click="openToDeleteProduct()">Удалить продукт</button>
			</div>
			
		</sec:authorize>
			
			<script type="text/ng-template" id="deleteTemplate.html">
			<div class="modal-content modal-sm">
        		<div class="modal-body">
					Хотите удалить продукт?
        		</div>
        		<div class="modal-footer">
            		<button class="btn btn-primary" type="button" ng-click="deleteProduct('${productId}')">Да</button>
            		<button class="btn btn-warning" type="button" ng-click="cancel()">Нет</button>
        		</div>
			</div>
    		</script>
    		
    		<script type="text/ng-template" id="updateTemplate.html">
			<div class="modal-content modal-sm">
        		<div class="modal-body">
					Будет в будущем.
        		</div>
        		<div class="modal-footer">
            		<a href=" /sj/update/${productId} " class="btn btn-primary">Да</a>
            		<button class="btn btn-warning" type="button" ng-click="cancel()">Нет</button>
        		</div>
			</div>
    		</script>
			
			<div class="btn-group" ng-if="models.product.productQuantity != 0">
				<label class="btn btn-primary" 
					   ng-repeat="size in models.sizes"
					   uib-btn-radio="size.sizeName"
					   ng-model="models.selectedSizeName">
					{{size.sizeName}}
					
				</label>
			</div>
			
			<div ng-if="models.product.productQuantity == 0">
				<h3><span class="label label-default">Нет вналичии</span></h3>
			</div>
			
		
			<h3>{{models.product.productName}}</h3>
			<br>
			<h4>Цена изделия: {{models.product.productPrice}} грн.</h4>
			
			<a href=" <spring:url value= "/cart" /> " class="btn btn-success btn-large" ng-click="addToCart()" ng-if="models.product.productQuantity != 0"> <span
					class="glyphicon-shopping-cart glyphicon"></span> Купить
			</a>
			
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<table class="table">
			<thead>
				<tr>
					<th><h4>Описание изделия:</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>{{models.product.productDescription}}</td>
				</tr>
			</tbody>
		</table>
		<table class="table">
			<thead>
				<tr>
					<th><h4>Характеристики:</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Материал изделия:</td>
					<td>{{models.product.material.materialName}}</td>
				</tr>
				<tr>
					<td>Вес изделия:</td>
					<td>{{models.product.productWeight}}</td>
				</tr>
				<tr>
					<td>Для кого изделие:</td>
					<td>{{models.product.gender.genderName}}</td>
				</tr>
				<tr>
					<td>Тип камня изделия:</td>
					<td>{{models.product.gemstone.gemstoneName}}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</div>