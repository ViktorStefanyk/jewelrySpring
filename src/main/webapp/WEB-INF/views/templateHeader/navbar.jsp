<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href=" <spring:url value= "/" /> "> 
				<span style="pendding: 0; margin: 0" class="icon-bar"></span> 
				<span style="pendding: 0; margin: 0" class="icon-bar"></span> 
				<span style="pendding: 0; margin: 0" class="icon-bar"></span> 
				<span style="pendding: 0; margin: 0" class="icon-bar">
					<img src=" <c:url value="/resources/images/brand/brand.png"></c:url> " width="29.41176" height="26.470588" class="d-inline-block align-top" alt="Жемчужинка">
				</span> Жемчужинка
			</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a></a></li>
			<li><a href=" <spring:url value= "/delivery" /> ">Доставка и
					оплата</a></li>
			<li><a href=" <spring:url value= "/contactus" /> ">Наши
					контакты</a></li>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href=" <spring:url value= "/add" /> ">Добавить новый
						товар</a></li>
			</sec:authorize>

		</ul>
		<ul class="nav navbar-nav navbar-right" ng-init="refreshUserName()">

			<sec:authorize access="!isAuthenticated()">
				<li><a href=" <spring:url value= "/signup" /> "><span
						class="glyphicon glyphicon-user"></span> Регистрация</a></li>
				<li><a href=" <spring:url value= "/login" /> "><span
						class="glyphicon glyphicon-log-in"></span> Авторизация</a></li>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#" ng-repeat="name in customer"> 
					Здрасте, <span
								class="glyphicon glyphicon-user"></span> {{name.userFirstName}} {{name.userLastName}}
				</a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/logout" />"><span
								class="glyphicon glyphicon-log-out"></span> Выход</a></li>
					</ul></li>
			</sec:authorize>
		</ul>
	</div>
</nav>