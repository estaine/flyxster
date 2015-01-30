
<div class="row">



  <div class="col-md-3 col-md-offset-3 search-params">
    <h2>From</h2>
    <div class="input-group">
      <span class="input-group-addon airport-addon" id="airport-from-addon">Airport</span>
      <input type="text" class="form-control airport" id="airport-from" placeholder="Start typing..." aria-describedby="airport-from-addon" autocomplete="off">
    </div>
    <br>
    <h2>Outward</h2>
    <div class="input-group date" id="date-from-group">
      <span class="input-group-addon date-addon" id="date-from-addon"> <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
      <input type="text" class="form-control date" id="date-from" aria-describedby="date-from-addon" readonly="readonly">

    </div>
  </div>


  <div class="col-md-3  search-params">
    <h2>To</h2>
    <div class="input-group">
      <span class="input-group-addon airport-addon" id="airport-to-addon">Airport</span>
      <input type="text" class="form-control airport" id="airport-to" placeholder="Start typing..." aria-describedby="airport-to-addon" autocomplete="off">
    </div>
    <br>
    <h2>Return</h2>
    <div class="input-group date" id="date-to-group">
      <span class="input-group-addon date-addon" id="date-to-addon"> <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
      <input type="text" class="form-control date" id="date-to" aria-describedby="date-to-addon" readonly="readonly">
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
    <button type = button class="btn btn-default pull-right" id="btn-search">Search</button>
  </div>
  <div class="col-md-4"></div>
</div>


</div>