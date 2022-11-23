$(document).ready(function () {
    $.validator.addMethod(
        "emailValidate",
        function (value, element, params) {
            const regex = new RegExp("^\\w*@\\w{5}\\.\\w{3}");

            return regex.test(value) === params;
        },
        "Email is invalid. Please enter your correct email"
    );

    $("#loginForm").validate({
        rules: {
            email: {
                required: true,
                emailValidate: true,
                maxlength: 50,
            },

            password: {
                required: true,
                minlength: 3,
                maxlength: 50,
            },

        },
        messages: {
            email: {
                maxlength: "Email is not exceed 50 characters",
            },

            password: {
                required: "Please input your password",
                minlength: "At least 3 characters",
                maxlength: "Not exceeded 50 characters",
            },
        },
    });


    $("#signUpForm").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });

    function fire_ajax_submit() {
        let member = {};
        member["fullName"] = $("#fullName").val();
        member["email"] = $("#email").val();
        member["phone"] = $("#phone").val();
        member["password"] = $("#password").val();
        member["role"] = $('[name=role]').val();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/signupAjax",
            data: JSON.stringify(member),
            dataType: 'json',
            cache: false,
            success: function (data) {
                if (data.message == "YES")
                    window.location.href = "/home"
                else{
                    //TODO SOMETHING
                }
            },
        })
    }

    $("#loginButton").click(function () {
    });
});