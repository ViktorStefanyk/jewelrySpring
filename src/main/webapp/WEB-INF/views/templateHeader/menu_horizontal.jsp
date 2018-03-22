<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="container">
	<div class="row row-centered">
		<div class="col-lg-12">
			<div class="col-lg-3 col-md-3 col-sm-3 pull-left">
				<div ng-init="refreshCatalogList()">
				<div class="dropdown" >
					<button style="width: 90%;" class="btn btn-default dropdown-toggle"
						type="button" id="menu1" data-toggle="dropdown">
						<span style="font-size: 20px;"
							class="glyphicon glyphicon-align-justify"></span> <span
							style="font-size: 20px;">Каталог товаров</span> <span
							class="caret"></span>
					</button>
					<ul style="width: 90%;" class="dropdown-menu" role="menu"
						aria-labelledby="menu1">
						
						<li role="presentation" ng-repeat="category in categories"><a role="menuitem" tabindex="-1"
							href=" /sj/{{category.categoryLink}}">{{category.categoryName}}</a></li>
						
					</ul>
				</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6 pull-left">
				<form>
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Поиск товаров" name="search">
						<div class="input-group-btn">
							<button style="font-size: 20px;" class="btn btn-default"
								type="submit">
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>

			<!-- 					Cart -->
			<div class="col-lg-2 col-md-2 col-sm-2 vcenter pull-right">
				<span style="font-size: 20px;"
					class="glyphicon glyphicon-shopping-cart"></span> <a
					style="font-size: 20px;" href=" <spring:url value= "/cart" /> ">КОРЗИНА</a>
			</div>
		</div>
	</div>
</div>