<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="generic-container text-center" ng-controller="SignupCtrl">

	<div class="panel panel-default center-block">
		<div class="panel-heading">
			<span class="lead">Форма регистрации пользователя </span>
		</div>
		<div class="formcontainer ">


			<form name="customerForm" method="POST" novalidate
				ng-class="{'form-error': submitted}" ng-submit="saveCustomer()"
				class="form-horizontal text-center" accept-charset="UTF-8">

				<div class="form-group">
					<lable class="control-lable col-lg-2">Имя пользователя*:</lable>
					<div class="col-lg-5">
						<input type="text" name="userFirstName"
							ng-model="customer.userFirstName" class="form-control" required
							placeholder="Имя" />
						<div ng-messages="customerForm.userFirstName.$error"
							ng-show="submitted && customerForm.userFirstName.$invalid"
							class="text-danger">
							<p ng-message="required">Заполните имя пользователя!</p>
						</div>
						<!-- 				<div class="text-danger" ng-show="customerForm.userFirstName.$touched && customerForm.userFirstName.$invalid">Заполните имя пользователя!</div> -->
					</div>
					<div ng-show="customerForm.userFirstName.$valid">&#x2713;</div>
				</div>

				<div class="form-group">
					<lable class="control-lable col-lg-2">Фамилия*:</lable>
					<div class="col-lg-5">
						<input type="text" name="userLastName" required
							ng-model="customer.userLastName" class="form-control"
							placeholder="Фамилия" />
						<div ng-messages="customerForm.userLastName.$error"
							ng-show="submitted && customerForm.userLastName.$invalid"
							class="text-danger">
							<p ng-message="required">Заполните фамилию пользователя!</p>
						</div>
					</div>
					<div ng-show="customerForm.userLastName.$valid">&#x2713;</div>
				</div>

				<div class="form-group">
					<lable class="control-lable col-lg-2" for="userPhoneNumber">Номер
					телефона*: </lable>
					<div class="col-lg-5">
						<input type="number" name="userPhoneNumber" required
							ng-model="customer.userPhoneNumber" class="form-control"
							placeholder="+380 ....." ng-minlength="2" ng-maxlength="10"
							 />
						<div ng-messages="customerForm.userPhoneNumber.$error"
							ng-show="submitted && customerForm.userPhoneNumber.$invalid"
							class="text-danger">
							<p ng-message="minlength">Номер телефона должен быть 2
								символов! И не больше и не меньше:)</p>
							<p ng-message="maxlength">Номер телефона должен быть 10
								символов! И не больше и не меньше:)</p>
							<p ng-message="required">Телефонный номер обязательный!</p>
						</div>
						<!-- 				<div class="text-danger" ng-show="customerForm.userPhoneNumber.$touched && customerForm.userPhoneNumber.$invalid">Телефонный номер обязательный!</div> -->
					</div>
					<div ng-show="customerForm.userPhoneNumber.$valid">&#x2713;</div>
				</div>

				<div class="form-group">
					<lable class="control-lable col-lg-2">Электронный адрес*:</lable>
					<div class="col-lg-5">
						<input type="email" name="userEmail" required
							ng-model="customer.userEmail" class="form-control"
							placeholder="Электронный адрес" />
						<div ng-messages="customerForm.userEmail.$error"
							ng-show="submitted && customerForm.userEmail.$invalid"
							class="text-danger">
							<p ng-message="userEmail">Не верный электронный адрес!</p>
							<p ng-message="required">Электронный адрес обязательный!</p>
						</div>
						<!-- 				<div class="text-danger" ng-show="customerForm.userEmail.$touched && customerForm.userEmail.$invalid">Электронный адрес обязательный!</div> -->
						<div style="color: red; font-weight: bold">{{errorMessage}}</div>
					</div>
					<div ng-show="customerForm.userEmail.$valid">&#x2713;</div>
				</div>


				<div class="form-group">
					<lable class="control-lable col-lg-2">Пароль*:</lable>
					<div class="col-lg-5">
						<input type="password" name="userPassword" required
							ng-model="customer.userPassword" class="form-control"
							ng-minlength="3" ng-maxlength="32" placeholder="Пароль" />
						<div ng-messages="customerForm.userPassword.$error"
							ng-show="submitted && customerForm.userPassword.$invalid"
							class="text-danger">
							<p ng-message="minlength">Пароль должен быть не менее 3
								символов!</p>
							<p ng-message="maxlength">Пароль должен быть не больше 32
								символа!</p>
							<p ng-message="required">Пароль обязательный!</p>
						</div>
						<!-- 				<div class="text-danger" ng-show="customerForm.userPassword.$touched && customerForm.userPassword.$invalid">Пароль обязательный!</div> -->
					</div>
					<div ng-show="customerForm.userPassword.$valid">&#x2713;</div>
				</div>

				<button type="submit" class="btn btn-primary btn-sm">Зарегистрироваться</button>
				<button type="reset" ng-click="resetForm()"
					class="btn btn-warning btn-sm">Очистить форму</button>

			</form>
		</div>
	</div>

	<br>

	<div class="form-group">
		<div>
			Если вы уже зарегистрированы, то можете: <a
				href=" <spring:url value= "/login" /> ">
				<button class="btn btn-primary">Авторизоваться</button>
			</a>
		</div>
	</div>

</div>