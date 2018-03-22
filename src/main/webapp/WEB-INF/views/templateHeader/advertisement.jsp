<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="container">

	<div style="height: 220px">
    	<div uib-carousel active="0" interval="6000" no-wrap="flase">
      		<div uib-slide ng-repeat="slide in slides track by slide.id" index="slide.id" style="height: 200px;">
        		<img ng-src="{{slide.image}}" style="margin:auto; height: auto; width: 1600px;">
      		</div>
    	</div>
  	</div>
  
</div>