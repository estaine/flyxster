<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:importAttribute name="commonJavascripts"/>
<tiles:importAttribute name="specialJavascript"/>

<tiles:importAttribute name="commonStylesheets"/>
<tiles:importAttribute name="specialStylesheet"/>


<html>
  <head>
    <tiles:insertAttribute name="head" />

    <c:forEach var="commonJavascript" items="${commonJavascripts}">
      <script src="<c:url value="${commonJavascript}"/>"></script>
    </c:forEach>

    <script src="<c:url value="${specialJavascript}"/>"></script>


    <c:forEach var="commonStylesheet" items="${commonStylesheets}">
      <link rel="stylesheet" type="text/css" href="<c:url value="${commonStylesheet}"/>">
    </c:forEach>

    <link rel="stylesheet" type="text/css" href="<c:url value="${specialStylesheet}"/>">

  </head>

  <body>
    <tiles:insertAttribute name="nav" />
    <tiles:insertAttribute name="logo" />
    <tiles:insertAttribute name="mainContent" />
  </body>
</html>