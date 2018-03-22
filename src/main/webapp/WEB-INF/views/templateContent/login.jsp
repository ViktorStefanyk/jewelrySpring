<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>	
	<div class="row row-centered">
		<h2 class="text-center">Авторизируйтесь на сайте</h2>
		<br>
		<form class="form-horizontal vertical-center" name="loginForm" action="<c:url value='/login' />" method="POST">
		
		<c:if test="${not empty error}"><div>${error}</div></c:if>
		<c:if test="${not empty message}"><div>${message}</div></c:if>
		
			<div class="form-group vertical-center">
				<label class="control-label col-sm-2 vertical-center" for="email">Электронный адрес:</label>
				<div class="col-sm-5 vertical-center">
					<input type="text" class="form-control mx-sm-3" id="email" placeholder="Введите имейл" name="username" >
				</div>
			</div>
			<div class="form-group vertical-center">
				<label class="control-label col-sm-2 vertical-center" for="pwd">Пароль:</label>
				<div class="col-sm-5 vertical-center">
					<input type="password" class="form-control mx-sm-3" id="pwd" placeholder="Введите пароль" name="password">
				</div>
			</div>
			<div class="form-group vertical-center">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label><input type="checkbox" name="remember-me">
							Запомнить меня</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Войти</button>
				</div>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			
		</form>
		</div>