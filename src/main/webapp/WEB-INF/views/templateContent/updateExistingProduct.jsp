<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section class="container" ng-controller="updateExistingProductCtrl" ng-init="getDetailsOfProductForUpdate('${productId}')">
	<form name="updateProductForm" 
		  method="POST" 
		  novalidate 
		  ng-class="{'form-error': submitted}" 
		  ng-submit="updateProduct()"
		  class="form-horizontal text-center" 
		  accept-charset="UTF-8"
		  enctype="multipart/form-data" >
		  
	   <div class="form-group has-success has-error has-feedback">
			<label class="control-label col-lg-3">Артикул*:</label>
			<div class="col-lg-5">
				<input type="text" 
					   name="productArticleId" 
					   required	
					   ng-model="models.product.productArticleId" 
					   class="form-control" 
					   placeholder="Артикул" />
				<span ng-show="updateProductForm.productArticleId.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
				<span ng-show="submitted && updateProductForm.productArticleId.$invalid" class="glyphicon glyphicon-remove form-control-feedback"></span>
				
				<div ng-messages="updateProductForm.productArticleId.$error" 
					 ng-show="submitted && updateProductForm.productArticleId.$invalid" 
					 class="text-danger">
					<p ng-message="required">Заполните артикул!</p>
				</div>
			</div>
		</div>
		
		<div class="form-group has-success has-error has-feedback">
			<label class="control-label col-lg-3">Название изделия*:</label>
			<div class="col-lg-5">
				<input type="text" 
					   name="productName" 
					   ng-model="models.product.productName" 
					   class="form-control" 
					   placeholder="Имя продукта" 
					   required
					   ng-minlength = "2"
					   ng-maxlength = "32"/>
				<span ng-show="updateProductForm.productName.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
				<span ng-show="submitted && updateProductForm.productName.$invalid" class="glyphicon glyphicon-remove form-control-feedback"></span>
				
				<div ng-messages="updateProductForm.productName.$error" 
					 ng-show="submitted && updateProductForm.productName.$invalid" 
					 class="text-danger">
					<p ng-message="required">Заполните название изделия!</p>
					<p ng-message="minlength">Название изделия должно быть больше 2 символов!</p>
					<p ng-message="maxlength">Название изделия не может быть больше 32 символов!</p>
				</div>
			</div>
		</div>
		
		<div class="form-group has-success has-error has-feedback">
			<label class="control-label col-lg-3">Описание изделия*:</label>
			<div class="col-lg-5">
				<textarea
					   name="productDescription" 
					   required	
					   ng-model="models.product.productDescription" 
					   class="form-control" 
					   placeholder="Описание продукта"
					   rows="5"
					   wrap="soft"
					   cols="100"
					   ng-minlength = "2"
					   ng-maxlength = "300">
			    </textarea>
			    
			    <span ng-show="updateProductForm.productName.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
				<span ng-show="submitted && updateProductForm.productName.$invalid" class="glyphicon glyphicon-remove form-control-feedback"></span>
				
				<div ng-messages="updateProductForm.productDescription.$error" 
					 ng-show="submitted && updateProductForm.productDescription.$invalid" 
					 class="text-danger">
					<p ng-message="required">Заполните описание изделия!</p>
					<p ng-message="minlength">Название изделия должно быть больше 2 символов!</p>
					<p ng-message="maxlength">Название изделия не может быть больше 300 символов!</p>
				</div>
			</div>
		</div>
		
		<div class="form-group has-success has-error has-feedback">
			<label class="control-label col-lg-3">Цена изделия*:</label>
			<div class="col-lg-5">
				<input type="number" 
					   name="productPrice" 
					   required	
					   ng-model="models.product.productPrice" 
					   class="form-control" 
					   placeholder="Цена продукта" />
				<span ng-show="updateProductForm.productPrice.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
				<span ng-show="submitted && updateProductForm.productPrice.$invalid" class="glyphicon glyphicon-remove form-control-feedback"></span>
				
				<div ng-messages="updateProductForm.productPrice.$error" 
					 ng-show="submitted && updateProductForm.productPrice.$invalid" 
					 class="text-danger">
					<p ng-message="required">Заполните цену изделия!</p>
				</div>
			</div>
		</div>
		
		<div class="form-group has-success has-error has-feedback">
			<label class="control-label col-lg-3">Вес изделия*:</label>
			<div class="col-lg-5">
				<input type="number" 
					   name="productWeight" 
					   required
					   min="0.01" 
					   max="99.99"
					   step="0.01"
					   ng-model="models.product.productWeight" 
					   class="form-control" 
					   placeholder="Вес продукта" />
					   
				<span ng-show="updateProductForm.productWeight.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
				<span ng-show="submitted && updateProductForm.productWeight.$invalid" class="glyphicon glyphicon-remove form-control-feedback"></span>
				
				<div ng-messages="updateProductForm.productWeight.$error" 
					 ng-show="submitted && updateProductForm.productWeight.$invalid" 
					 class="text-danger">
					<p ng-message="required">Заполните вес изделия!</p>
					<p ng-message="min">Минимальное значение 0,01!</p>
					<p ng-message="max">Максимальное значение 99,99!</p>
				</div>
			</div>
		</div>
		
		<div class="form-group has-success has-error has-feedback" ng-init="getMaterials()">
			<label class="control-label col-lg-3">Материал изделия*:</label>
			<div class="col-lg-5">
				<select ng-model="models.product.material"
						name="productMaterial"
						ng-options="material as material.materialName for material in materials track by material.materialId"
						class="form-control text-center"
						required>
					<option value="" class="text-center">- Выберите материал -</option>
				</select>
				<div ng-messages="updateProductForm.productMaterial.$error" 
					 ng-show="submitted && updateProductForm.productMaterial.$invalid" 
					 class="text-danger">
					<p ng-message="required">Выберите материал изделия!</p>
				</div>
			</div>
			<div ng-show="updateProductForm.productMaterial.$valid">&#x2713;</div>
		</div>
		
		<div class="form-group has-success has-error has-feedback" ng-init="getGenders()">
			<label class="control-label col-lg-3">Для кого изделие*:</label>
			<div class="col-lg-5">
				<select ng-model="models.product.gender"
						name="productGender"
						ng-options="gender as gender.genderName for gender in genders track by gender.genderId"
						class="form-control text-center"
						required>
					<option value="" class="text-center">- Выберите для кого изделие -</option>
				</select>
				<div ng-messages="updateProductForm.productGender.$error" 
					 ng-show="submitted && updateProductForm.productGender.$invalid" 
					 class="text-danger">
					<p ng-message="required">Выберите для кого изделие!</p>
				</div>
			</div>
			<div ng-show="updateProductForm.productGender.$valid">&#x2713;</div>
		</div>
		
		<div class="form-group has-success has-error has-feedback" ng-init="getTypes()">
			<label class="control-label col-lg-3">Тип изделия*:</label>
			<div class="col-lg-5">
				<select ng-model="models.product.type"
						name="productType"
						ng-options="type as type.typeName for type in types track by type.typeId"
						class="form-control text-center"
						required>
					<option value="" class="text-center">- Выберите тип -</option>
				</select>
				<div ng-messages="updateProductForm.productType.$error" 
					 ng-show="submitted && updateProductForm.productType.$invalid" 
					 class="text-danger">
					<p ng-message="required">Выберите тип изделия!</p>
				</div>
			</div>
			<div ng-show="updateProductForm.productType.$valid">&#x2713;</div>
		</div>
		
		<div class="form-group has-success has-error has-feedback" ng-init="getGemstones()">
			<label class="control-label col-lg-3">Камень изделия*:</label>
			<div class="col-lg-5">
				<select ng-model="models.product.gemstone"
						name="productGemstone"
						ng-options="gemstone as gemstone.gemstoneName for gemstone in gemstones track by gemstone.gemstoneId"
						class="form-control text-center"
						required>
					<option value="" class="text-center">- Выберите камень изделия -</option>
				</select>
				<div ng-messages="updateProductForm.productGemstone.$error" 
					 ng-show="submitted && updateProductForm.productGemstone.$invalid" 
					 class="text-danger">
					<p ng-message="required">Выберите камень изделия изделия!</p>
				</div>
			</div>
			<div ng-show="updateProductForm.productGemstone.$valid">&#x2713;</div>
		</div>
		
		<div class="form-group has-success has-error has-feedback">
			<label class="control-label col-lg-3">Кактегория изделия*:</label>
			<div class="col-lg-5">
				<input type="text" 
					   name="categoryName" 
					   required
					   ng-model="categories[0].categoryName" 
					   class="form-control" 
					   placeholder="Категория продукта"
					   ng-readonly="true" />
			</div>
		</div>
		
		
		<div ng-if="sizes != null">
			<div class="form-group">
				<label class="control-label col-lg-3">Размеры изделия*:</label>
				<div class="col-lg-5">
					<div class="col-lg-2" ng-repeat="size in sizes">
						<label class="checkbox-inline">
							<input type="checkbox"
				 				checklist-value = "size"
				 				checklist-model = "models.product.productSizes"
								name="productSizes"
								/>{{size.sizeName}}
						</label>
					</div>
				</div>
			</div>
		</div>
		

		<button type="submit" class="btn btn-success">Обновить</button>
		<a href=" /sj/details/{{models.product.productId}} "
								class="btn btn-default" role="button">Назад</a>
		
	</form>
	
</section>