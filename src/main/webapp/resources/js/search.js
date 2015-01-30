$(document).ready(function() {
    $(".line-expander").on("click", function(){
        if($("span", this).hasClass("glyphicon-chevron-down")) {
            $("span", this).removeClass("glyphicon-chevron-down");
            $("span", this).addClass("glyphicon-chevron-up");
        }
        else {
            $("span", this).removeClass("glyphicon-chevron-up");
            $("span", this).addClass("glyphicon-chevron-down");
        }
    });
});