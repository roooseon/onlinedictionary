/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


"use strict";

$(document).ready(function () {
    $("#btnsearch").click(dictionary.result);
    $("#search").keypress(function (e) {
        if (e.which == 13) {
            dictionary.result();
        }
    });

});

var dictionary = function () {

    var callAjax = function () {

        $("#content").empty();
        $("#content").removeClass();
        $("#alert").empty();
        $("#searchinput").empty();


        if ($("#search").val().length === 0) {
            $("#alert").html(" &nbsp; Please enter a text to search");
        } else {
            $("#loader").show();

            var timeDelay = 1000;           // MILLISECONDS (5 SECONDS).
            setTimeout(loadAjax, timeDelay);

            function loadAjax() {
                $.ajax("http://localhost:8080/Dictionary/dictServlet", {
                    "type": "post",
                    dataType: 'json',
                    "data": {
                        "searchkey": $("#search").val()
                    }

                })
                        .done(function (data) {
                            $("#searchinput").html($("#search").val().toUpperCase());
                            if (data.length) {

                                $.each(data, function (i, item) {

                                    var li = $("<li></li>");
                                    $("#content").append(li);


//                        $(li).text(item.definition + item.wordtype);
                                    if (item.wordtype === "") {
                                        $(li).text(item.definition);

                                    } else {
                                        $(li).text("(" + item.wordtype + ") :: " + item.definition);
                                    }
                                });
                            } else {
                                $("#content").html("<p>No result found</p>");
                                $("#content").addClass("warning");
                            }
                        })
                        .fail(function (errMsg) {
                            alert(errMsg);
                        })
                        .always(function () {
                            $("#loader").hide();
                        });
            }
            ;

        }
    };
    return{
        result: function () {
            callAjax();
        }
    };
}();