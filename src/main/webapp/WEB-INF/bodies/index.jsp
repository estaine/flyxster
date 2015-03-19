<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--
TODO: Define a style for language selector
-->
<a href="?language=ru">Русский</a> | <a href="?language=en">English</a>
<form:form method="post" commandName="simpleSearchForm" action="search">
  <input type="hidden" name="locale" id="locale" value="${locale}"/>
    <input type="hidden" name="home-airport" id="home-aiport" value="${homeAirport.id}"/>
<div class="row">

  <div class="col-md-3 col-md-offset-3 search-params">
    <h2><spring:message code="index.from" /></h2>
    <div class="input-group">
      <span class="input-group-addon airport-addon" id="airport-from-addon"><spring:message code="index.airport" /></span>
      <input type="text" class="form-control airport" id="airport-from" placeholder="Start typing..." aria-describedby="airport-from-addon" autocomplete="off" value="${homeAirportNameCode}">
      <form:hidden path="airportFromId" />
    </div>
    <br>
    <h2><spring:message code="index.outward" /></h2>
    <div class="input-group date" id="date-from-group">
      <span class="input-group-addon date-addon" id="date-from-addon"> <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
      <input type="text" class="form-control date" id="date-from" aria-describedby="date-from-addon" readonly="readonly">
      <form:hidden path="outwardDate"/>
    </div>

    <div class="date-range-container" id="date-range-from-container">
      <ul class="nav nav-pills nav-justified">
        <li class="active" id="date-range-from-fixed"><a data-toggle="pill"><spring:message code="index.fixedDate" /></a></li>
        <li id="date-range-from-3"><a data-toggle="pill"><spring:message code="index.range3Days" /></a></li>
        <li id="date-range-from-7"><a data-toggle="pill"><spring:message code="index.range7Days" /></a></li>
      </ul>
      <form:hidden path="outwardDateRange" />
    </div>
  </div>


  <div class="col-md-3  search-params">
    <h2><spring:message code="index.to" /></h2>
    <div class="input-group">
      <span class="input-group-addon airport-addon" id="airport-to-addon"><spring:message code="index.airport" /></span>
      <input type="text" class="form-control airport" id="airport-to" placeholder="Start typing..." aria-describedby="airport-to-addon" autocomplete="off">
      <form:hidden path="airportToId" />
    </div>
    <br>
    <h2><spring:message code="index.return" /></h2>
    <div class="input-group date" id="date-to-group">
      <span class="input-group-addon date-addon" id="date-to-addon"> <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
      <input type="text" class="form-control date" id="date-to" aria-describedby="date-to-addon" readonly="readonly">
      <form:hidden path="returnDate"/>
    </div>
    <div class="date-range-container" id="date-range-to-container">
      <ul class="nav nav-pills nav-justified">
        <li class="active" id="date-range-to-fixed"><a data-toggle="pill"><spring:message code="index.fixedDate" /></a></li>
        <li id="date-range-to-3"><a data-toggle="pill"><spring:message code="index.range3Days" /></a></li>
        <li id="date-range-to-7"><a data-toggle="pill"><spring:message code="index.range7Days" /></a></li>
      </ul>
      <form:hidden path="returnDateRange" />
    </div>
    <div id="flight-type-selector-container">
        <form:checkbox path="twoWayFlight" name="two-way-flight" id="two-way-flight" class="flight-type-selector"  value="Return"  /><label><spring:message code="index.radioOptionReturn" /></label>
    </div>
  </div>

</div>

<div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4 button-container ">
    <button type = button class="btn btn-default pull-left" id="btn-advanced-search"><spring:message code="index.buttonAdvancedOptions" /></button>
    <button type = submit class="btn btn-default pull-right" id="btn-search"><spring:message code="index.buttonSimpleSearch" /></button>
  </div>
  <div class="col-md-4"></div>
</div>


</div>
</form:form>