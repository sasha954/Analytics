/**
 * Created by fomenko on 13.03.2017.
 */

$(document).ready(function () {

    $("#addLink").on("click", function () {
       $(".form-wrapper").toggleClass("visible");
    });

    $("#closeAddForm").on("click", function () {
        $(".form-wrapper").removeClass("visible");
    })

    $('tr[data-href]').dblclick(function() {
        document.location = $(this).data('href');
    });
});