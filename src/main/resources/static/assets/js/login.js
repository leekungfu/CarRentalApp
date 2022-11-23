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

    $('#loginForm').submit(function (event) {
        event.preventDefault();
        login_submit();
    });

    function login_submit() {
        let member = {};
        member["email"] = $('.login-email[name = email]').val();
        member["password"] = $('.login-password[name = password]').val();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/loginAjax",
            data: JSON.stringify(member),
            dataType: 'json',
            cache: false,
            success: function (data) {
                if (data.message === "OK") {
                    Swal.fire(
                        'Good job!',
                        'Login successfully!',
                        'success'
                    )
                    window.location.href = "/home"
                } else if (data.message === "FAILED") {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Email or password incorrect! Try again please.',
                    })
                }
            }
        })
    }
});