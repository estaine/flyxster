function initOutwardDatepicker(outwardDate) {

    if(typeof outwardDate === "undefined")
        outwardDate = new Date();

    $("#date-from-group").datepicker({
        format: "d M yyyy",
        language: $("#locale").val(),
        autoclose: true
    });

    $("#date-from-group").datepicker("setStartDate", new Date());
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

function initReturnDatepicker(returnDate) {
    if(typeof returnDate === "undefined") {
        var outwardDate = $("#date-from-group").datepicker("getDate");
        returnDate =  new Date(outwardDate);
        returnDate.setDate(returnDate.getDate() + 7);
    }
    $("#date-to-group").datepicker({
        format: "d M yyyy",
        language: $("#locale").val(),
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

        source: function (query, process) {

            return $.ajax({
                url: "/typeahead",
                type: 'post',
                data: { query: query },
                dataType: 'json',
                success: function (jsonResult) {
                    return typeof jsonResult == 'undefined' ? false : process(jsonResult);
                }
            });
        },
        autoSelect: true
    });

    $("#airport-from").change(function() {
        var current = $("#airport-from").typeahead("getActive");
        if (current) {

            if (current.name == $("#airport-from").val()) {
                $("#airportFromId").val((current.id));
            } else {
                // This means it is only a partial match, you can either add a new item
                // or take the active if you don't want new items
            }
        } else {
            // Nothing is active so it is a new value (or maybe empty value)
        }
    });

    $("#airport-to").change(function() {
        var current = $("#airport-to").typeahead("getActive");
        if (current) {

            if (current.name == $("#airport-to").val()) {
                $("#airportToId").val((current.id));
            } else {
                // This means it is only a partial match, you can either add a new item
                // or take the active if you don't want new items
            }
        } else {
            // Nothing is active so it is a new value (or maybe empty value)
        }
    });

    $("#two-way-flight").click(function() {
        if ($(this).is(":checked"))
            initReturnDatepicker();
        else
            destroyReturnDatepicker();
    });

    if($("#airportFromId").val() != "") {
        initOutwardDatepicker($("#outwardDate").val());
        initReturnDatepicker($("#returnDate").val());
    }
    else {
        initOutwardDatepicker();
        initReturnDatepicker();
    }
});