<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div ng-controller="productCtrl" ng-init="refreshProduct()">
	<div class="row">
		<div class="col-xs-8 col-sm-6 col-md-4 col-lg-3" ng-repeat="product in products">
			<div class="thumbnail">
					<a href="/sj/details/{{product.productId}} ">
						<img alt="{{product.productName}}" src="{{product.image.link}}">
					</a>
					<div class="caption">
						<h3>{{product.productName}}</h3>
						<p>
							<a href=" /sj/details/{{product.productId}} "
								class="btn btn-default" role="button">Подробнее</a>
						</p>
					</div>
			</div>
		</div>
	</div>
	</div>
</div>