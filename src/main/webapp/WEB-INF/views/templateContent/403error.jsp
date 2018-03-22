<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container vertical-center text-center">
	<h1>HTTP Status 403 - Access is denied</h1>
	<c:choose>
		<c:when test="${empty username}">
			<h2>Вы не имеете доступа к этой странице!</h2>
		</c:when>
		<c:otherwise>
			<h2>
				<c:forEach items="${fullname}" var="name">
			Извините,  ${name.userFirstName} ${name.userLastName} <br /> Вы не имеете доступа к этой странице!
		</c:forEach>
			</h2>
		</c:otherwise>
	</c:choose>
</div>