$(document).ready(function () {



    $(".login-form").validate({
        rules: {

            password: {
                required: true,
                minlength: 3,
                maxlength: 50,
            },
            repassword: {
                required: true,
                minlength: 3,
                maxlength: 50,
                equalTo: "#password",
            },

        },
        messages: {

            password: {
                required: "Please input your password",
                minlength: "At least 3 characters",
                maxlength: "Not exceed 50 characters",
            },
            repassword: {
                required: "Please enter your password again",
                minlength: "At least 3 characters",
                maxlength: "Not exceed 50 characters",
                equalTo: "Password doesn't match!",
            },

        },
    });


});
