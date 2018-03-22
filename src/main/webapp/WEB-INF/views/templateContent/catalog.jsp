<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>	
		<div class="container" ng-controller="CatalogCtrl">
			<div class="row">
								
				<div class="col-lg-3 col-md-3">
					
					<div class="panel panel-primary">
						<div class="panel-heading">Цена</div>
						<div  class="panel-body">
							<input type="text" style="width: 40%; float: left" ng-model="models.minPrice" ng-pattern="/^\d+$/" class="form-control"/>
							<input type="text" style="width: 40%; float: right" ng-model="models.maxPrice" ng-pattern="/^\d+$/" class="form-control"/>
						</div>
						<button type="submit" style="width: 45%; margin-bottom: 10px" class="btn btn-success center-block" ng-click="findProductsByParameters('${category}')">OK</button>
					</div>
					
					<div ng-init="getMaterialsByCategory('${category}')">
					<div class="panel panel-primary">
						<div class="panel-heading">Материал изделия</div>
						<div class="panel-body" uib-popover-template="'myPopoverTemplate.html'"
      						 				    popover-is-open="isOpenMaterials"
      						                    popover-placement="right"
      						                    popover-trigger="'none'">
							<div class="checkbox" ng-repeat="material in models.materials" >
								<label>
									<input type="checkbox" 
										   checklist-value = "material.materialId" 
										   checklist-model = "models.selectedMaterials" />{{material.materialName}}
								</label>
								<span style="margin-right: 20px" class="badge pull-right">{{material.materialQuantity}}</span>
							</div>
						</div>
					</div>
					</div>
				
					<div ng-init="getTypesByCategory('${category}')">
					<div class="panel panel-primary">
						<div class="panel-heading">Тип изделия</div>
						<div class="panel-body" uib-popover-template="'myPopoverTemplate.html'"
      						 				    popover-is-open="isOpenTypes"
      						                    popover-placement="right"
      						                    popover-trigger="'none'">
							<div class="checkbox" ng-repeat="type in models.types" >
								<label>
									<input type="checkbox" 
										   checklist-value="type.typeId" 
										   checklist-model="models.selectedTypes" />{{type.typeName}}
								</label>
								<span style="margin-right: 20px" class="badge pull-right">{{type.typeQuantity}}</span>
							</div>
						</div>
					</div>
					</div>
				
					<div ng-init="getGendersByCategory('${category}')">
					<div class="panel panel-primary">
						<div class="panel-heading">Пол</div>
						<div class="panel-body" uib-popover-template="'myPopoverTemplate.html'"
      						 				    popover-is-open="isOpenGenders"
      						                    popover-placement="right"
      						                    popover-trigger="'none'">
							<div class="checkbox" ng-repeat="gender in models.genders" >
								<label>
									<input type="checkbox" 
										   checklist-value="gender.genderId" 
										   checklist-model="models.selectedGenders" />{{gender.genderName}}
								</label>
								<span style="margin-right: 20px" class="badge pull-right">{{gender.genderQuantity}}</span>
							</div>
						</div>
					</div>
					</div>
					
					<div ng-init="getGemstonesByCategory('${category}')">
					<div class="panel panel-primary">
						<div class="panel-heading">Драгоценные камни</div>
						<div class="panel-body" uib-popover-template="'myPopoverTemplate.html'"
      						 				    popover-is-open="isOpenGemstones"
      						                    popover-placement="right"
      						                    popover-trigger="'none'">
							<div class="checkbox" ng-repeat="gemstone in models.gemstones" >
								<label>
									<input type="checkbox" 
										   checklist-value="gemstone.gemstoneId" 
										   checklist-model="models.selectedGemstones" />{{gemstone.gemstoneName}}
								</label>
								<span style="margin-right: 20px" class="badge pull-right">{{gemstone.gemstoneQuantity}}</span>
							</div>
						</div>
					</div>
					</div>
					
					<div ng-init="getSizesByCategory('${category}')">
					<div class="panel panel-primary" ng-if="models.sizes != null">
						<div class="panel-heading">Размер изделия</div>
						<div class="panel-body" uib-popover-template="'myPopoverTemplate.html'"
      						 				    popover-is-open="isOpenSizes"
      						                    popover-placement="right"
      						                    popover-trigger="'none'">
							<div class="checkbox" ng-repeat="size in models.sizes" >
								<label>
									<input type="checkbox" 
										   checklist-value="size.sizeId" 
										   checklist-model="models.selectedSizes" />{{size.sizeName}}
								</label>
								<span style="margin-right: 20px" class="badge pull-right">{{size.sizeQuantity}}</span>
							</div>
						</div>
					</div>
					</div>
					<script type="text/ng-template" id="myPopoverTemplate.html">
        				<div class="form-group" ng-init="getAvailableQuantityProducts('${category}')">
							<label ng-repeat="guantity in models.quantities">Выбрано: {{guantity.productQuantity}}</label>
							<br>
          					<button type="button" class="btn btn-success center-block" ng-click="findProductsByParameters('${category}')">Показать</button>
        				</div>
    				</script>
					
				</div>
				
				
				
				<div class="col-lg-9 col-md-9" ng-init="findProductsByParameters('${category}')">
				<div style="border-bottom: solid 1px blue; margin-top: 3px" class="col-lg-12 col-md-12" >
					<div class="form-group pull-right">
  						<label for="sel1">Сортировка</label>
 	 						<select class="form-control" ng-model="models.selectedSort">
 	 							<option value="">Будет в будущем</option>
    							<option value="-productPrice">от дешевых к дорогим</option>
    							<option value="productPrice">от дорогих к дешевым</option>
    							<option>по рейтингу</option>
  							</select>
					</div>
					{{models.selectedSort}}
				</div>
				
				<div class="align-middle" ng-if="models.products.length==0">
				<h1 class="text-center align-middle">{{messageNotFound}}</h1>
				</div>
				<div ng-if="models.products.length > 0">
					<div style="margin-top: 10px" 
						 ng-repeat="product in models.products"
						 class="col-xs-6 col-sm-6 col-md-4 col-lg-4" >
						 
						<div class="thumbnail">
						
							<a href="/sj/details/{{product.productId}}">
								<img alt="{{product.productName}}" src="{{product.image.link}}" >
							</a>
							<a href="/sj/details/{{product.productId}}">{{product.productName}}</a>
								<div class="caption">
									<h5 ng-if="product.productQuantity == 0"><span style="font-weight:bold">Нет вналичии</span></h5>
									<h4 ng-if="product.productQuantity == 0"><span class="label label-default">{{product.productPrice}} грн</span></h4>
									<h4 ng-if="product.productQuantity != 0"><span class="label label-success">{{product.productPrice}} грн</span></h4>
									<p>
										<a href="/sj/details/{{product.productId}}" class="btn btn-default hidden-xs" role="button">Подробнее</a>
									</p>
								</div>
						</div>
						
					</div>
				</div>
					
				</div>
				
				
			</div>
		</div>