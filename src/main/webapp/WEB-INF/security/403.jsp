<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="col-md-2 col-md-offset-5">
                <div class="alert alert-danger" role="alert"><b>HTTP 403.</b> Account <b><sec:authentication property="principal.username"/></b> is not allowed to access this page. </div>
</div>
