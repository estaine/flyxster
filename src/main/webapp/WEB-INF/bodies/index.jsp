<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form:form method="post" commandName="simpleSearchForm" action="search">
<div class="row">

  <div class="col-md-3 col-md-offset-3 search-params">
    <h2>From</h2>
    <div class="input-group">
      <span class="input-group-addon airport-addon" id="airport-from-addon">Airport</span>
      <input type="text" class="form-control airport" id="airport-from" placeholder="Start typing..." aria-describedby="airport-from-addon" autocomplete="off">
      <form:hidden path="airportFromId" />
    </div>
    <br>
    <h2>Outward</h2>
    <div class="input-group date" id="date-from-group">
      <span class="input-group-addon date-addon" id="date-from-addon"> <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
      <input type="text" class="form-control date" id="date-from" aria-describedby="date-from-addon" readonly="readonly">
      <form:hidden path="outwardDate"/>
    </div>

    <div class="date-range-container" id="date-range-from-container">
      <ul class="nav nav-pills nav-justified">
        <li class="active" id="date-range-from-fixed"><a data-toggle="pill">Fixed date</a></li>
        <li id="date-range-from-3"><a data-toggle="pill">+/- 3 days</a></li>
        <li id="date-range-from-7"><a data-toggle="pill">+/- 7 days</a></li>
      </ul>
      <form:hidden path="outwardDateRange" />
    </div>
  </div>


  <div class="col-md-3  search-params">
    <h2>To</h2>
    <div class="input-group">
      <span class="input-group-addon airport-addon" id="airport-to-addon">Airport</span>
      <input type="text" class="form-control airport" id="airport-to" placeholder="Start typing..." aria-describedby="airport-to-addon" autocomplete="off">
      <form:hidden path="airportToId" />
    </div>
    <br>
    <h2>Return</h2>
    <div class="input-group date" id="date-to-group">
      <span class="input-group-addon date-addon" id="date-to-addon"> <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
      <input type="text" class="form-control date" id="date-to" aria-describedby="date-to-addon" readonly="readonly">
      <form:hidden path="returnDate"/>
    </div>
    <div class="date-range-container" id="date-range-to-container">
      <ul class="nav nav-pills nav-justified">
        <li class="active" id="date-range-to-fixed"><a data-toggle="pill">Fixed date</a></li>
        <li id="date-range-to-3"><a data-toggle="pill">+/- 3 days</a></li>
        <li id="date-range-to-7"><a data-toggle="pill">+/- 7 days</a></li>
      </ul>
      <form:hidden path="returnDateRange" />
    </div>
    <div id="flight-type-selector-container">
      <div class="pull-left">
        <input type="radio" name="flight-type" class="flight-type-selector"  value="Return" checked><label>Return</label>
      </div>
      <div class="pull-right">
        <input type="radio" name="flight-type" class="flight-type-selector"  value="One-way"><label>One-way</label>
      </div>
    </div>
  </div>

</div>

<div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4 button-container ">
    <button type = button class="btn btn-default pull-left" id="btn-advanced-search">Show advanced options</button>
    <button type = submit class="btn btn-default pull-right" id="btn-search">Search</button>
  </div>
  <div class="col-md-4"></div>
</div>


</div>
</form:form>