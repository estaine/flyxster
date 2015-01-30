<div class="row">

  <div class="col-md-2 col-md-offset-2">
    <h1>Search results</h1>
  </div>

  <div class="col-md-4">
    <div class="col-md-6 search-params">
      <div class="input-group">
        <span class="input-group-addon airport-addon" id="airport-from-addon">From</span>
        <input type="text" class="form-control airport" id="airport-from" placeholder="Start typing..." aria-describedby="airport-from-addon" autocomplete="off">
      </div>
      <br>

      <div class="input-group date" id="date-from-group">
        <span class="input-group-addon date-addon" id="date-from-addon"> <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
        <input type="text" class="form-control date" id="date-from" aria-describedby="date-from-addon" readonly="readonly">

      </div>
    </div>


    <div class="col-md-6 search-params">
      <div class="input-group">
        <span class="input-group-addon airport-addon" id="airport-to-addon">To</span>
        <input type="text" class="form-control airport" id="airport-to" placeholder="Start typing..." aria-describedby="airport-to-addon" autocomplete="off">
      </div>
      <br>

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

    <div class="col-md-4 col-md-offset-4 search-again-button-block">
      <button type = button class="btn btn-default">Search again</button>
    </div>
  </div>



</div>



<div class="container">
  <div class="row">
    <div class="col-md-10 col-md-offset-1">


      <table class="table" id="search-results">
        <thead>
        <tr>
          <th>Carrier(s)</th>
          <th>From</th>
          <th>To</th>
          <th>Stops</th>
          <th colspan="2">Outward</th>
          <th colspan="2">Return</th>
          <th>Price</th>
        </tr>
        </thead>

        <tbody>
        <tr class="short-data-row" id="row-1">
          <td class="airline-logo-container"><img src="/resources/images/airline-logos/norwegian.png" title="norwegian"></td>
          <td>Warsaw (<b>WAW</b>)</td>
          <td>Stockholm Skavsta (<b>NYO</b>)</td>
          <td class="stops-short-cell">0 / 1</td>
          <td>Sun 15.02.2015</td>
          <td>13:35</td>
          <td>Tue 24.02.2015</td>
          <td>19:10</td>
          <td align="right"><b>77 EUR</b></td>
          <td data-toggle="collapse" data-target="#detailed-table-1" class="accordion-toggle line-expander"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></td>
        </tr>

        <tr class="hidden-row">
          <td colspan="10" class="hidden-cell">
            <div class="accordion-body collapse detailed-data-block"  id="detailed-table-1">
              <table class="detailed-flight-info">
                <tr>
                  <td class="detailed-cell airline-cell">norwegian</td>
                  <td class="detailed-cell flight-number-cell">DY 1302</td>
                  <td class="detailed-cell departure-weekday-cell">Sun</td>
                  <td class="detailed-cell departure-date-cell">15.02.2015</td>
                  <td class="detailed-cell departure-airport-cell">Warsaw</td>
                  <td class="detailed-cell departure-airport-code-cell">(<b>WAW</b>)</td>
                  <td class="detailed-cell departure-time-cell">13:35</td>
                  <td class="detailed-cell">-</td>
                  <td class="detailed-cell arrival-time-cell">15:50</td>
                  <td class="detailed-cell arrival-airport-code-cell">(<b>NYO</b>)</td>
                  <td class="detailed-cell arrival-airport-cell">Stockholm Skavsta</td>
                  <td class="detailed-cell flight-duration-cell">2h 15min</td>
                  <td class="detailed-cell price-cell"><b>31.12 EUR</b></td>
                </tr>
                <tr>
                  <td class="detailed-cell airline-cell">norwegian</td>
                  <td class="detailed-cell flight-number-cell">DY 0102</td>
                  <td class="detailed-cell departure-weekday-cell">Tue</td>
                  <td class="detailed-cell departure-date-cell">24.02.2015</td>
                  <td class="detailed-cell departure-airport-cell">Stockholm Skavsta</td>
                  <td class="detailed-cell departure-airport-code-cell">(<b>NYO</b>)</td>
                  <td class="detailed-cell departure-time-cell">10:15</td>
                  <td class="detailed-cell">-</td>
                  <td class="detailed-cell arrival-time-cell">11:15</td>
                  <td class="detailed-cell arrival-airport-code-cell">(<b>TRF</b>)</td>
                  <td class="detailed-cell arrival-airport-cell">Oslo Torp</td>
                  <td class="detailed-cell flight-duration-cell">1h 00min</td>
                  <td class="detailed-cell price-cell"><b>18.23 EUR</b></td>
                </tr>
                <tr>
                  <td class="detailed-cell airline-cell">norwegian</td>
                  <td class="detailed-cell flight-number-cell">DY 0671</td>
                  <td class="detailed-cell departure-weekday-cell">Tue</td>
                  <td class="detailed-cell departure-date-cell">24.02.2015</td>
                  <td class="detailed-cell departure-airport-cell">Oslo Torp</td>
                  <td class="detailed-cell departure-airport-code-cell">(<b>TRF</b>)</td>
                  <td class="detailed-cell departure-time-cell">17:05</td>
                  <td class="detailed-cell">-</td>
                  <td class="detailed-cell arrival-time-cell">19:10</td>
                  <td class="detailed-cell arrival-airport-code-cell">(<b>WAW</b>)</td>
                  <td class="detailed-cell arrival-airport-cell">Warsaw</td>
                  <td class="detailed-cell flight-duration-cell">2h 05min</td>
                  <td class="detailed-cell price-cell"><b>27.73 EUR</b></td>
                </tr>
              </table>
            </div>
          </td>
        </tr>

        <tr class="short-data-row" id="row-2">
          <td class="airline-logo-container"><img src="/resources/images/airline-logos/norwegian.png" title="norwegian"><img src="/resources/images/airline-logos/wizzair.png" title="WizzAir"></td>
          <td>Warsaw (<b>WAW</b>)*</td>
          <td>Stockholm Skavsta (<b>NYO</b>)</td>
          <td class="stops-short-cell">0 / 0</td>
          <td>Sun 15.02.2015</td>
          <td>13:35</td>
          <td>Mon 23.02.2015</td>
          <td>10:00</td>
          <td align="right"><b>99 EUR</b></td>
          <td data-toggle="collapse" data-target="#detailed-table-2" class="accordion-toggle line-expander"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></td>
        </tr>

        <tr class="hidden-row">
          <td colspan="10" class="hidden-cell">
            <div class="accordion-body collapse detailed-data-block"  id="detailed-table-2">
              <table class="detailed-flight-info">
                <tr>
                  <td class="detailed-cell airline-cell">norwegian</td>
                  <td class="detailed-cell flight-number-cell">DY 1302</td>
                  <td class="detailed-cell departure-weekday-cell">Sun</td>
                  <td class="detailed-cell departure-date-cell">15.02.2015</td>
                  <td class="detailed-cell departure-airport-cell">Warsaw</td>
                  <td class="detailed-cell departure-airport-code-cell">(<b>WAW</b>)</td>
                  <td class="detailed-cell departure-time-cell">13:35</td>
                  <td class="detailed-cell">-</td>
                  <td class="detailed-cell arrival-time-cell">15:50</td>
                  <td class="detailed-cell arrival-airport-code-cell">(<b>NYO</b>)</td>
                  <td class="detailed-cell arrival-airport-cell">Stockholm Skavsta</td>
                  <td class="detailed-cell flight-duration-cell">2h 15min</td>
                  <td class="detailed-cell price-cell"><b>31.12 EUR</b></td>
                </tr>
                <tr>
                  <td class="detailed-cell airline-cell">WizzAir</td>
                  <td class="detailed-cell flight-number-cell">W6 1104</td>
                  <td class="detailed-cell departure-weekday-cell">Mon</td>
                  <td class="detailed-cell departure-date-cell">23.02.2015</td>
                  <td class="detailed-cell departure-airport-cell">Stockholm Skavsta</td>
                  <td class="detailed-cell departure-airport-code-cell">(<b>NYO</b>)</td>
                  <td class="detailed-cell departure-time-cell">08:00</td>
                  <td class="detailed-cell">-</td>
                  <td class="detailed-cell arrival-time-cell">10:00</td>
                  <td class="detailed-cell arrival-airport-code-cell">(<b>WMI</b>)</td>
                  <td class="detailed-cell arrival-airport-cell">Warsaw Modlin</td>
                  <td class="detailed-cell flight-duration-cell">2h 00min</td>
                  <td class="detailed-cell price-cell"><b>67.75 EUR</b></td>
                </tr>
              </table>
            </div>
          </td>
        </tr>

        <tr class="short-data-row" id="row-3">
          <td class="airline-logo-container"><img src="/resources/images/airline-logos/ryanair.png" title="Ryanair"><img src="/resources/images/airline-logos/wizzair.png" title="WizzAir"><img src="/resources/images/airline-logos/norwegian.png" title="norwegian"></td>
          <td>Warsaw Modlin (<b>WMI</b>)</td>
          <td>Stockholm Arlanda (<b>ARL</b>)*</td>
          <td class="stops-short-cell">1 / 1</td>
          <td>Fri 13.02.2015</td>
          <td>23:15</td>
          <td>Wed 25.02.2015</td>
          <td>14:40</td>
          <td align="right"><b>112 EUR</b></td>
          <td data-toggle="collapse" data-target="#detailed-table-3" class="accordion-toggle line-expander"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></td>
        </tr>

        <tr class="hidden-row">
          <td colspan="10" class="hidden-cell">
            <div class="accordion-body collapse detailed-data-block"  id="detailed-table-3">
              <table class="detailed-flight-info">
                <tr>
                  <td class="detailed-cell airline-cell">Ryanair</td>
                  <td class="detailed-cell flight-number-cell">FR 4423</td>
                  <td class="detailed-cell departure-weekday-cell">Fri</td>
                  <td class="detailed-cell departure-date-cell">13.02.2015</td>
                  <td class="detailed-cell departure-airport-cell">Warsaw Modlin</td>
                  <td class="detailed-cell departure-airport-code-cell">(<b>WMI</b>)</td>
                  <td class="detailed-cell departure-time-cell">23:15</td>
                  <td class="detailed-cell">-</td>
                  <td class="detailed-cell arrival-time-cell">01:40*</td>
                  <td class="detailed-cell arrival-airport-code-cell">(<b>CRL</b>)</td>
                  <td class="detailed-cell arrival-airport-cell">Brussels Charleroi</td>
                  <td class="detailed-cell flight-duration-cell">2h 25min</td>
                  <td class="detailed-cell price-cell"><b>16.55 EUR</b></td>
                </tr>
                <tr>
                  <td class="detailed-cell airline-cell">WizzAir</td>
                  <td class="detailed-cell flight-number-cell">W6 1293</td>
                  <td class="detailed-cell departure-weekday-cell">Sat</td>
                  <td class="detailed-cell departure-date-cell">14.02.2015</td>
                  <td class="detailed-cell departure-airport-cell">Brussels Charleroi</td>
                  <td class="detailed-cell departure-airport-code-cell">(<b>CRL</b>)</td>
                  <td class="detailed-cell departure-time-cell">06:10</td>
                  <td class="detailed-cell">-</td>
                  <td class="detailed-cell arrival-time-cell">07:45</td>
                  <td class="detailed-cell arrival-airport-code-cell">(<b>ARL</b>)</td>
                  <td class="detailed-cell arrival-airport-cell">Stockholm Arlanda</td>
                  <td class="detailed-cell flight-duration-cell">1h 35min</td>
                  <td class="detailed-cell price-cell"><b>37.23 EUR</b></td>
                </tr>
                <tr>
                  <td class="detailed-cell airline-cell">norwegian</td>
                  <td class="detailed-cell flight-number-cell">DY 0451</td>
                  <td class="detailed-cell departure-weekday-cell">Wed</td>
                  <td class="detailed-cell departure-date-cell">25.02.2015</td>
                  <td class="detailed-cell departure-airport-cell">Stockholm Bromma</td>
                  <td class="detailed-cell departure-airport-code-cell">(<b>BMA</b>)*</td>
                  <td class="detailed-cell departure-time-cell">09:25</td>
                  <td class="detailed-cell">-</td>
                  <td class="detailed-cell arrival-time-cell">10:30</td>
                  <td class="detailed-cell arrival-airport-code-cell">(<b>RYG</b>)</td>
                  <td class="detailed-cell arrival-airport-cell">Oslo Rygge</td>
                  <td class="detailed-cell flight-duration-cell">1h 05min</td>
                  <td class="detailed-cell price-cell"><b>22.99 EUR</b></td>
                </tr>
                <tr>
                  <td class="detailed-cell airline-cell">WizzAir</td>
                  <td class="detailed-cell flight-number-cell">W6 2601</td>
                  <td class="detailed-cell departure-weekday-cell">Wed</td>
                  <td class="detailed-cell departure-date-cell">25.02.2015</td>
                  <td class="detailed-cell departure-airport-cell">Oslo Torp</td>
                  <td class="detailed-cell departure-airport-code-cell">(<b>TRF</b>)*</td>
                  <td class="detailed-cell departure-time-cell">12:15</td>
                  <td class="detailed-cell">-</td>
                  <td class="detailed-cell arrival-time-cell">14:40</td>
                  <td class="detailed-cell arrival-airport-code-cell">(<b>WMI</b>)</td>
                  <td class="detailed-cell arrival-airport-cell">Warsaw Modlin</td>
                  <td class="detailed-cell flight-duration-cell">2h 25min</td>
                  <td class="detailed-cell price-cell"><b>35.34 EUR</b></td>
                </tr>
              </table>
            </div>
          </td>
        </tr>

        </tbody>
      </table>
    </div>
  </div>
</div>