<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/header.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/scrollbar.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/maincontent.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootstrap-theme.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" type="text/css" />

<link data-require="bootstrap-css@3.*" data-semver="3.3.7" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css" />

<script data-require="jquery@3.3.1" data-semver="3.3.1" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script data-require="angular.js@1.5.x" src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.11/angular.js" data-semver="1.5.11"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0/angular-messages.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.11/angular-animate.js"></script>


<script data-require="ui-bootstrap@*" data-semver="2.5.0" src="https://cdn.rawgit.com/angular-ui/bootstrap/gh-pages/ui-bootstrap-tpls-2.5.0.js"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
<script src="<c:url value="/resources/js/engineApp.js" />" ></script>
<script src="<c:url value="/resources/js/checklist-model.js" />"></script>
<script src="<c:url value="/resources/js/header_controller.js" />" ></script>
<script src="<c:url value="/resources/js/user_controller.js" />" ></script>
<script src="<c:url value="/resources/js/signup_service.js" />" ></script>
<script src="<c:url value="/resources/js/signup_controller.js" />" ></script>
<script src="<c:url value="/resources/js/productdetails_cart_service.js" />" ></script>
<script src="<c:url value="/resources/js/productdetails_controller.js" />" ></script>
<script src="<c:url value="/resources/js/cart_controller.js" />" ></script>
<script src="<c:url value="/resources/js/customer_controller.js" />" ></script>
<script src="<c:url value="/resources/js/catalog_service.js" />" ></script>
<script src="<c:url value="/resources/js/catalog_controller.js" />" ></script>
<script src="<c:url value="/resources/js/admin-service.js" />" ></script>
<script src="<c:url value="/resources/js/admin-controller.js" />" ></script>
<script src="<c:url value="/resources/js/update-existing-product-controller.js" />" ></script>



<title><tiles:getAsString name="title"/></title>
</head>
<body>
	<div class="wrapper" ng-app="engineApp">
		<div class="header" ng-controller="headerCtrl" ng-cloak>
			<tiles:insertAttribute name="navbar" />
			<tiles:insertAttribute name="advertisement" />
			<tiles:insertAttribute name="menu" />
		</div>
		<br>	
		<div class="content" ng-cloak>
			<tiles:insertAttribute name="body" />
		</div>
		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>