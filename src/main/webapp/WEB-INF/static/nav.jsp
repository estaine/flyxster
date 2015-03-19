<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>


        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right" method="post" action="<c:url value='j_spring_security_check'/>" >
                <sec:authorize access="!hasRole('ROLE_VIEW_USER')">


                <div class="form-group">
                    <input type="text"  name="j_username" id="j_username" placeholder="<spring:message code="nav.username" />" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" name="j_password" id="j_password" placeholder="<spring:message code="nav.password" />" class="form-control">
                </div>

                <button type="submit" class="btn btn-success "><spring:message code="nav.loginButton" /></button>
                <button type="submit" class="btn btn-primary "><spring:message code="nav.signupButton" /></button>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_VIEW_USER')">
                    <spring:message code="nav.hello" />, <sec:authentication property="principal.username"/>
                    <a href="<c:url value='j_spring_security_logout'/>" >Logout</a> | <a href="<c:url value='/profile'/>" >Profile</a>
                </sec:authorize>

            </form>
        </div>
    </div>
</nav>