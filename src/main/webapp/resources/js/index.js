$(document).ready(function() {
    $("form").submit(function(){
        if($("#date-range-from-fixed").hasClass("active"))
            $("#outwardDateRange").val(0);
        if($("#date-range-from-3").hasClass("active"))
            $("#outwardDateRange").val(3);
        if($("#date-range-from-7").hasClass("active"))
            $("#outwardDateRange").val(7);

        if ($("input[name=flight-type]:checked").val() == "One-way") {
            $("#returnDate").val("");
            $("#returnDateRange").val("");
        }
        else {
            if($("#date-range-to-fixed").hasClass("active"))
                $("#returnDateRange").val(0);
            if($("#date-range-to-3").hasClass("active"))
                $("#returnDateRange").val(3);
            if($("#date-range-to-7").hasClass("active"))
                $("#returnDateRange").val(7);
        }

    });
});