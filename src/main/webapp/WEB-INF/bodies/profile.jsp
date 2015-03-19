<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-4 col-md-offset-4">
    <form:form method="post" action="change-password" commandName="passwordChange">
        <c:if test="${passwordChanged == true}">
            <div class="alert alert-success" role="alert"><spring:message code="profile.passwordChanged" /></div>
        </c:if>
        <h4><spring:message code="profile.changePassword" /></h4>
        <form:input path="oldPassword" type="password" class="form-control password" name="j_password" id="j_password" size="30" maxlength="32" placeholder="Old password" value="" /><br/>
        <form:input path="newPassword" type="password" class="form-control password" name="new_password" id="new_password" size="30" maxlength="32" placeholder="New password" value=""/><br/>
        <!--
            TODO: Add js password checker
        -->
        <input type="password" class="form-control password" name="new_password_check" id="new_password_check" size="30" maxlength="32" placeholder="Repeat new password" /><br/>
        <input type="submit" class="form-control password" value="Change" />
    </form:form>
    <form:form method="post" action="change-personal-data" commandName="personalDataChange">
        <c:if test="${personalDataChanged == true}">
            <div class="alert alert-success" role="alert"><spring:message code="profile.personalDataChanged" /></div>
        </c:if>
        <h4><spring:message code="profile.changePersonalData" /></h4>
        <form:input path="city" class="form-control password" name="city" id="city" size="30" maxlength="32" placeholder="City" value="" /><br/>
        <form:select path="locale" class="form-control password">
            <form:option value="en" label="English" />
            <form:option value="ru" label="Русский" />
        </form:select><br/>
        <input type="submit" class="form-control password" value="Change" />
    </form:form>

</div>