<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>



<div class="col-md-2 col-md-offset-5">
<form method="post" action="<c:url value='j_spring_security_check'/>" >
    <p>
        <c:if test="${error == true}">
    <div class="alert alert-danger" role="alert"><b>Error.</b> Bad credentials. </div>
        </c:if>
    </p>
            <input type="text" class="form-control" name="j_username" id="j_username"size="30" maxlength="40" placeholder="Login"  /><br/>
            <input type="password" class="form-control" name="j_password" id="j_password" size="30" maxlength="32" placeholder="Password" /><br/>
            <input type="submit" class="form-control" value="Login" />
</form>
</div>
