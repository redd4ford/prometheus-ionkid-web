var logout = function() {
    $.post("/logout", function() {
        $("#user").html('');
        $(".unauthenticated").show();
        $(".authenticated").hide();
        window.location = '/';
    });
    return true;
};