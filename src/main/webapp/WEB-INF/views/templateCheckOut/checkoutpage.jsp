<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">
			Контактные данные
		</div>
		<div class="panel-body">
			<div class="container">
				<!-- 				New buyer -->
				<div class="text-center col-md-4">
					<h5 style="padding: 15px 0 15px 0;" class="bg-success">Я новый
						покупатель</h5>
					<form class="form-horizontal text-center">
						<div class="form-group">
							<lable class="control-lable col-lg-5">Имя:*</lable>
							<input style="padding: 3px 2px 3px 2px" class="col-lg-6"
								type="text">
						</div>

						<div class="form-group">
							<lable class="control-lable col-lg-5">Фамилия:*</lable>
							<input style="padding: 3px 2px 3px 2px" class="col-lg-6"
								type="text">
						</div>

						<div class="form-group">
							<lable class="control-lable col-lg-5">Номер телефона:*</lable>
							<input style="padding: 3px 2px 3px 2px" class="col-lg-6"
								type="number">
						</div>

						<div class="form-group">
							<lable class="control-lable col-lg-5">Почта:*</lable>
							<input style="padding: 3px 2px 3px 2px" class="col-lg-6"
								type="email">
						</div>

					</form>
				</div>
				<!-- 				Regular customer -->
				<div class="text-center col-md-4">
					<h5 style="padding: 15px 0 15px 0;" class="bg-success">Я
						постоянный клиент</h5>
					<form class="form-horizontal" name="loginForm"
						action="<c:url value='/login' />" method="POST">

						<c:if test="${not empty error}">
							<div>${error}</div>
						</c:if>
						<c:if test="${not empty message}">
							<div>${message}</div>
						</c:if>

						<div class="form-group text-center">
							<label class="control-lable col-lg-5" for="email">Почта:</label>
							<input style="padding: 3px 2px 3px 2px" type="text" class="col-lg-6" id="email"
									placeholder="Введите имейл" name="username">
						</div>
						<div class="form-group text-center">
							<label class="control-lable col-lg-5" for="pwd">Пароль:</label>
							<input style="padding: 3px 2px 3px 2px" type="password" class="col-lg-6" id="pwd"
									placeholder="Введите пароль" name="password">
						</div>
						
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<a href="#"><button type="submit" class="btn btn-default">Войти</button></a>
							</div>
						</div>

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

					</form>
				</div>
			</div>


		</div>
	</div>
</div>