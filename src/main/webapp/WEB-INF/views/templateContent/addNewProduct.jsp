<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section class="container" ng-controller="AddNewProductCtrl">
	<form name="addNewForm" 
		  method="POST" 
		  novalidate 
		  ng-class="{'form-error': submitted}" 
		  ng-submit="addNewProduct()"	
		  class="form-horizontal text-center" 
		  accept-charset="UTF-8"
		  enctype = "multipart/form-data" >
		  
	   <div class="form-group has-success has-error has-feedback">
			<label class="control-label col-lg-3">Артикул*:</label>
			<div class="col-lg-5">
				<input type="text" 
					   name="productArticleId" 
					   required	
					   ng-model="product.productArticleId" 
					   class="form-control" 
					   placeholder="Артикул" />
				<span ng-show="addNewForm.productArticleId.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
				<span ng-show="submitted && addNewForm.productArticleId.$invalid" class="glyphicon glyphicon-remove form-control-feedback"></span>
				
				<div ng-messages="addNewForm.productArticleId.$error" 
					 ng-show="submitted && addNewForm.productArticleId.$invalid" 
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
					   ng-model="product.productName" 
					   class="form-control" 
					   placeholder="Имя продукта" 
					   required
					   ng-minlength = "2"
					   ng-maxlength = "32"/>
				<span ng-show="addNewForm.productName.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
				<span ng-show="submitted && addNewForm.productName.$invalid" class="glyphicon glyphicon-remove form-control-feedback"></span>
				
				<div ng-messages="addNewForm.productName.$error" 
					 ng-show="submitted && addNewForm.productName.$invalid" 
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
					   ng-model="product.productDescription" 
					   class="form-control" 
					   placeholder="Описание продукта"
					   rows="5"
					   wrap="soft"
					   cols="100"
					   ng-minlength = "2"
					   ng-maxlength = "300">
			    </textarea>
			    
			    <span ng-show="addNewForm.productName.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
				<span ng-show="submitted && addNewForm.productName.$invalid" class="glyphicon glyphicon-remove form-control-feedback"></span>
				
				<div ng-messages="addNewForm.productDescription.$error" 
					 ng-show="submitted && addNewForm.productDescription.$invalid" 
					 class="text-danger">
					<p ng-message="required">Заполните описание изделия!</p>
					<p ng-message="minlength">Название изделия должно быть больше 2 символов!</p>
					<p ng-message="maxlength">Название изделия не может быть больше 300 символов!</p>
				</div>
			</div>
			<div ng-show="addNewForm.productDescription.$valid">&#x2713;</div>
		</div>
		
		<div class="form-group has-success has-error has-feedback">
			<label class="control-label col-lg-3">Цена изделия*:</label>
			<div class="col-lg-5">
				<input type="number" 
					   name="productPrice" 
					   required	
					   ng-model="product.productPrice" 
					   class="form-control" 
					   placeholder="Цена продукта" />
				<span ng-show="addNewForm.productPrice.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
				<span ng-show="submitted && addNewForm.productPrice.$invalid" class="glyphicon glyphicon-remove form-control-feedback"></span>
				
				<div ng-messages="addNewForm.productPrice.$error" 
					 ng-show="submitted && addNewForm.productPrice.$invalid" 
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
					   ng-model="product.productWeight" 
					   class="form-control" 
					   placeholder="Вес продукта" />
					   
				<span ng-show="addNewForm.productWeight.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
				<span ng-show="submitted && addNewForm.productWeight.$invalid" class="glyphicon glyphicon-remove form-control-feedback"></span>
				
				<div ng-messages="addNewForm.productWeight.$error" 
					 ng-show="submitted && addNewForm.productWeight.$invalid" 
					 class="text-danger">
					<p ng-message="required">Заполните вес изделия!</p>
					<p ng-message="min">Минимальное значение 0,01!</p>
					<p ng-message="max">Максимальное значение 99,99!</p>
				</div>
			</div>
		</div>
		
		<div class="form-group" ng-init="getMaterials()">
			<label class="control-label col-lg-3">Материал изделия*:</label>
			<div class="col-lg-5">
				<select ng-model="product.material"
						name="productMaterial"
						ng-options="material as material.materialName for material in materials"
						class="form-control text-center"
						required>
					<option value="" class="text-center">- Выберите материал -</option>
				</select>
				<div ng-messages="addNewForm.productMaterial.$error" 
					 ng-show="submitted && addNewForm.productMaterial.$invalid" 
					 class="text-danger">
					<p ng-message="required">Выберите материал изделия!</p>
				</div>
			</div>
			<div ng-show="addNewForm.productMaterial.$valid">&#x2713;</div>
		</div>
		
		<div class="form-group" ng-init="getGenders()">
			<label class="control-label col-lg-3">Для кого изделие*:</label>
			<div class="col-lg-5">
				<select ng-model="product.gender"
						name="productGender"
						ng-options="gender as gender.genderName for gender in genders"
						class="form-control text-center"
						required>
					<option value="" class="text-center">- Выберите для кого изделие -</option>
				</select>
				<div ng-messages="addNewForm.productGender.$error" 
					 ng-show="submitted && addNewForm.productGender.$invalid" 
					 class="text-danger">
					<p ng-message="required">Выберите для кого изделие!</p>
				</div>
			</div>
			<div ng-show="addNewForm.productGender.$valid">&#x2713;</div>
		</div>
		
		<div class="form-group" ng-init="getTypes()">
			<label class="control-label col-lg-3">Тип изделия*:</label>
			<div class="col-lg-5">
				<select ng-model="product.type"
						name="productType"
						ng-options="type as type.typeName for type in types"
						class="form-control text-center"
						required>
					<option value="" class="text-center">- Выберите тип -</option>
				</select>
				<div ng-messages="addNewForm.productType.$error" 
					 ng-show="submitted && addNewForm.productType.$invalid" 
					 class="text-danger">
					<p ng-message="required">Выберите тип изделия!</p>
				</div>
			</div>
			<div ng-show="addNewForm.productType.$valid">&#x2713;</div>
		</div>
		
		<div class="form-group" ng-init="getGemstones()">
			<label class="control-label col-lg-3">Камень изделия*:</label>
			<div class="col-lg-5">
				<select ng-model="product.gemstone"
						name="productGemstone"
						ng-options="gemstone as gemstone.gemstoneName for gemstone in gemstones"
						class="form-control text-center"
						required>
					<option value="" class="text-center">- Выберите камень изделия -</option>
				</select>
				<div ng-messages="addNewForm.productGemstone.$error" 
					 ng-show="submitted && addNewForm.productGemstone.$invalid" 
					 class="text-danger">
					<p ng-message="required">Выберите камень изделия изделия!</p>
				</div>
			</div>
			<div ng-show="addNewForm.productGemstone.$valid">&#x2713;</div>
		</div>
		
		<div class="form-group" ng-init="getCategories()">
			<label class="control-label col-lg-3">Кактегория изделия*:</label>
			<div class="col-lg-5">
				<select ng-model="product.category"
						name="productCategory"
						ng-options="category as category.categoryName for category in categories"
						class="form-control text-center"
						ng-change="getSizesByCategory(product.category.categoryId)"
						required>
					<option value="" class="text-center">- Выберите категорию -</option>
				</select>
				<div ng-messages="addNewForm.productCategory.$error" 
					 ng-show="submitted && addNewForm.productCategory.$invalid" 
					 class="text-danger">
					<p ng-message="required">Выберите категорию изделия!</p>
				</div>
			</div>
			<div ng-show="addNewForm.productCategory.$valid">&#x2713;</div>
		</div>
		
		
		<div ng-if="sizes != null">
			<div class="form-group">
				<label class="control-label col-lg-3">Размеры изделия*:</label>
				<div class="col-lg-5">
					<div class="col-lg-2" ng-repeat="size in sizes">
						<label class="checkbox-inline">
							<input type="checkbox"
				 				checklist-value = "size"
				 				checklist-model = "product.productSizes"
								name="productSizes"
								/>{{size.sizeName}}
						</label>
					</div>
				</div>
			</div>
		</div>
		
<!-- 		<div class="form-group"> -->
<!-- 			<label class="control-label col-lg-2">Загрузить фотку*:</label> -->
<!-- 			<input class="btn btn-success" type="file" name="productImage" file-model="newFile"/> -->
<!-- 		</div> -->
		
		<file-field class="btn" ng-model="newFile" ng-class="{'btn-success' :newFile" preview="previewImage">Выберите файл</file-field>
		<div class="col-sm-4" ng-show="previewImage">
                <h3>Preview</h3>
                <img ng-src="{{previewImage}}" width="200">
                <br>
                {{uploadFile.name}}
            </div>

		<button type="submit" class="btn btn-success">Добавить</button>
		
	</form>
	
</section>