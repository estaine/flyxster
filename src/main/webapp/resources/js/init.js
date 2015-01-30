function initOutwardDatepicker() {
    var outwardDate = new Date();

    $("#date-from-group").datepicker({
        format: "d M yyyy",
        autoclose: true
    });

    $("#date-from-group").datepicker("setStartDate", outwardDate);
    $("#date-from-group").datepicker("setDate", outwardDate);
    $("#date-from-group").datepicker().on("changeDate", function() {
        if(!($("#date-to-group > *").hasClass("date-disabled"))) {
            var outwardDate = $("#date-from-group").datepicker("getDate");
            var returnDate = $("#date-to-group").datepicker("getDate");
            $("#date-to-group").datepicker("setStartDate", outwardDate);

            if (returnDate < outwardDate) {
                var proposedArrivalDate = new Date(outwardDate);
                proposedArrivalDate.setDate(proposedArrivalDate.getDate() + 7);
                $("#date-to-group").datepicker("setDate", proposedArrivalDate);
            }

            $("#date-to-group").datepicker("show");
        }
    });
}

function initReturnDatepicker() {
    var outwardDate = $("#date-from-group").datepicker("getDate");
    var returnDate =  new Date(outwardDate);
    returnDate.setDate(returnDate.getDate() + 7);

    $("#date-to-group").datepicker({
        format: "d M yyyy",
        autoclose: true
    });

    $("#date-to-group").datepicker("setStartDate", outwardDate);
    $("#date-to-group").datepicker("setDate", returnDate);

    if($("#date-to-group > *").hasClass("date-disabled"))
        $("#date-to-group > *").removeClass("date-disabled");
}

function destroyReturnDatepicker() {
    $("#date-to-group").datepicker("remove");
    $("#date-to-group > *").addClass("date-disabled");
    $("#date-to").val("");
}

$(document).ready(function() {
    var $input = $(".airport");
    $input.typeahead({
        source: ["Amsterdam (AMS)", "Barcelona (BCN)", "Brussels Charleroi (CRL)", "Madrid (MAD)", "Milan Bergamo (BGY)", "Paris Beauvais (BVA)", "Riga (RIX)", "Rome Ciampino (CIA)", "Rome Fiumicino (FCO)", "Vilnius (VNO)"],
        autoSelect: true
    });


    $(".flight-type-selector").click(function() {
        if ($("input[name=flight-type]:checked").val() == "One-way")
            destroyReturnDatepicker();
        else
            initReturnDatepicker();
    });

    initOutwardDatepicker();
    initReturnDatepicker();

});