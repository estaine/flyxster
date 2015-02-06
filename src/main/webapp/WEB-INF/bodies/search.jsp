<%@ page import="java.util.Set" %>
<%@ page import="com.estaine.flyxster.common.FlightGroup" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.estaine.flyxster.common.TimestampConverter" %>
<%@ page import="java.util.List" %>
<%@ page import="com.estaine.flyxster.model.Flight" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <c:set var="flightGroupIndex" value="0"/>
        <c:forEach items="${flightGroups}" var="flightGroup">
          <%
            FlightGroup flightGroup = (FlightGroup) pageContext.getAttribute("flightGroup");
            pageContext.setAttribute("groupAirlines", flightGroup.getAirlines());
            pageContext.setAttribute("groupFrom", flightGroup.getDepartureAirport());
            pageContext.setAttribute("groupTo", flightGroup.getDestinationAirport());
            pageContext.setAttribute("groupOutwardStops", flightGroup.getOutwardStops());
            pageContext.setAttribute("groupReturnStops", flightGroup.getReturnStops());
            pageContext.setAttribute("groupPrice", flightGroup.getTotalPrice());

            Timestamp groupStart = flightGroup.getTripStartTime();
            Timestamp groupEnd = flightGroup.getTripEndTime();


            String groupStartDate = TimestampConverter.toResult(groupStart, TimestampConverter.RESULT_DATE_FORMAT);
            String groupStartWeekDay = TimestampConverter.toResult(groupStart, TimestampConverter.RESULT_WEEKDAY_FORMAT);
            String groupStartTime = TimestampConverter.toResult(groupStart, TimestampConverter.RESULT_TIME_FORMAT);

            String groupEndDate = TimestampConverter.toResult(groupEnd, TimestampConverter.RESULT_DATE_FORMAT);
            String groupEndWeekDay = TimestampConverter.toResult(groupEnd, TimestampConverter.RESULT_WEEKDAY_FORMAT);
            String groupEndTime = TimestampConverter.toResult(groupEnd, TimestampConverter.RESULT_TIME_FORMAT);

            pageContext.setAttribute("groupStartDate", groupStartDate);
            pageContext.setAttribute("groupStartWeekDay", groupStartWeekDay);
            pageContext.setAttribute("groupStartTime", groupStartTime);
            pageContext.setAttribute("groupEndDate", groupEndDate);
            pageContext.setAttribute("groupEndWeekDay", groupEndWeekDay);
            pageContext.setAttribute("groupEndTime", groupEndTime);


          %>
        <c:set var="flightGroupIndex" value="${flightGroupIndex + 1}"/>
        <tr class="short-data-row" id="row-${flightGroupIndex}">
          <td class="airline-logo-container">
            <c:forEach items="${groupAirlines}" var="groupAirline">
              <img src="<c:url value="/resources/images/airline-logos/${groupAirline.iconLocation}"/>" title="<c:url value="${groupAirline.name}"/>"/>
            </c:forEach>
          </td>
          <td>
            ${groupFrom.name} (<b>${groupFrom.code}</b>)
          </td>
          <td>
              ${groupTo.name} (<b>${groupTo.code}</b>)
          </td>
          <td class="stops-short-cell">
            ${groupOutwardStops} / ${groupReturnStops}
          </td>
          <td>${groupStartWeekDay} ${groupStartDate}</td>
          <td>${groupStartTime}</td>
          <td>${groupEndWeekDay} ${groupEndDate}</td>
          <td>${groupEndTime}</td>
          <td align="right"><b>
            <fmt:formatNumber type="number" maxFractionDigits="0" value="${groupPrice}" /> EUR
          </b></td>
          <td data-toggle="collapse" data-target="#detailed-table-${flightGroupIndex}" class="accordion-toggle line-expander"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></td>
        </tr>

        <tr class="hidden-row">
          <td colspan="10" class="hidden-cell">
            <div class="accordion-body collapse detailed-data-block"  id="detailed-table-${flightGroupIndex}">
              <table class="detailed-flight-info">
          <c:forEach items="${flightGroup}" var="flight">
            <%
              Flight flight = (Flight) pageContext.getAttribute("flight");

              Timestamp flightStart = flight.getDepartureDatetime();
              Timestamp flightEnd = flight.getArrivalDatetime();

              String flightStartDate = TimestampConverter.toResult(flightStart, TimestampConverter.RESULT_DATE_FORMAT);
              String flightStartWeekDay = TimestampConverter.toResult(flightStart, TimestampConverter.RESULT_WEEKDAY_FORMAT);
              String flightStartTime = TimestampConverter.toResult(flightStart, TimestampConverter.RESULT_TIME_FORMAT);

              String flightEndDate = TimestampConverter.toResult(flightEnd, TimestampConverter.RESULT_DATE_FORMAT);
              String flightEndWeekDay = TimestampConverter.toResult(flightEnd, TimestampConverter.RESULT_WEEKDAY_FORMAT);
              String flightEndTime = TimestampConverter.toResult(flightEnd, TimestampConverter.RESULT_TIME_FORMAT);

              String flightDuration = TimestampConverter.getFlightDuration(flightStart, flightEnd);

              pageContext.setAttribute("flightStartDate", flightStartDate);
              pageContext.setAttribute("flightStartWeekDay", flightStartWeekDay);
              pageContext.setAttribute("flightStartTime", flightStartTime);
              pageContext.setAttribute("flightEndDate", flightEndDate);
              pageContext.setAttribute("flightEndWeekDay", flightEndWeekDay);
              pageContext.setAttribute("flightEndTime", flightEndTime);
              pageContext.setAttribute("flightDuration", flightDuration);

            %>
                <tr>
                  <td class="detailed-cell airline-cell">
                    ${flight.airline.name}
                  </td>
                  <td class="detailed-cell flight-number-cell">
                    ${flight.airline.code} ${flight.number}
                  </td>
                  <td class="detailed-cell departure-weekday-cell">
                    ${flightStartWeekDay}
                  </td>
                  <td class="detailed-cell departure-date-cell">
                    ${flightStartDate}
                  </td>
                  <td class="detailed-cell departure-airport-cell">
                    ${flight.airportFrom.name}
                  </td>
                  <td class="detailed-cell departure-airport-code-cell">
                    (<b>${flight.airportFrom.code}</b>)
                  </td>
                  <td class="detailed-cell departure-time-cell">
                      ${flightStartTime}
                  </td>
                  <td class="detailed-cell">-</td>
                  <td class="detailed-cell arrival-time-cell">
                      ${flightEndTime}
                  </td>
                  <td class="detailed-cell arrival-airport-code-cell">
                    (<b>${flight.airportTo.code}</b>)
                  </td>
                  <td class="detailed-cell arrival-airport-cell">
                      ${flight.airportTo.name}
                  </td>
                  <td class="detailed-cell flight-duration-cell">
                    ${flightDuration}
                  </td>
                  <td class="detailed-cell price-cell"><b>
                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${flight.price}" /> EUR
                  </b></td>
                </tr>
            </c:forEach>
              </table>
            </div>
          </td>
        </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
