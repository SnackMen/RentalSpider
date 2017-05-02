/**
 * Created by laowang on 17-5-1.
 */
$(document).ready(function () {
    var mapHeight = $(document.body).height();
    $("#l-map").css("height",mapHeight);
    $("#submit-data").on("click", function () {
        var dataSource = $("#data-source").val();
        var lowPrice = $('#low-price').val().trim();
        var highPrice = $('#high-price').val().trim();
        var workPlace = $('#workplace').val().trim();
        //ajax
    })
});
