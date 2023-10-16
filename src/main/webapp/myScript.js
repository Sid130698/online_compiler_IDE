$(document).ready(function() {
    $.get("hello", function(data) {
        $("#output").html("check it");
    });
});


function addNumber() {
    var num1 = parseInt($("#num1").val());
    var num2 = parseInt($("#num2").val());

    // Check if both inputs have valid numbers
    if (isNaN(num1) || isNaN(num2)) {
        alert("Please enter valid numbers.");
        return;
    }

    // Inside this function, you can put your AJAX request code.
    return jQuery.ajax({
        type: "POST",
        url: contextPath+module,
        data: {
            action: "add",
            num1: num1,
            num2: num2
        },
        success: function(result) {
            // Handle the result
            $("#result").text(result);
        }
    });
}
