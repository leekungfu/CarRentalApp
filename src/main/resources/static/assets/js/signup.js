$(document).ready(function () {

    $.validator.addMethod(
        "emailValidate",
        function (value, element, params) {
            const regex = new RegExp("^\\w*@\\w{5}\\.\\w{3}");

            return regex.test(value) === params;
        },
        "Email is invalid. Please enter your correct email"
    );

    $.validator.addMethod(
        "phoneValidate",
        function (value, element, params) {
            const regex = new RegExp("^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4}$");

            return regex.test(value) === params;
        },
        "Phone number is invalid. Please enter your correct phone number"
    );

    $("#signUpForm").validate({
        rules: {
            email: {
                required: true,
                emailValidate: true,
            },
            fullName: {
                required: true,
                minlength: 3,
                maxlength: 50,
            },

            phone: {
                required: true,
                phoneValidate: true,
            },

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
            username: {
                required: "Please input your user name",
                minlength: "User name is at least 3 characters",
                maxlength: "User name can not be over 50 characters",
            },
            email: {},
            phone: {},
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

    $(".signUpp").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });

    function fire_ajax_submit() {
        let member = {};
        member["fullName"] = $('[name = fullName]').val();
        member["email"] = $('.signup-email[name = email]').val();
        member["phone"] = $('[name = phone]').val();
        member["password"] = $('.signup-password[name = password]').val();
        member["role"] = $('[name = role]:checked').val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/signupAjax",
            data: JSON.stringify(member),
            dataType: 'json',
            cache: false,
            success: function (data) {
                if (data.message === "YES") {
                    Swal.fire(
                        'Good job!',
                        'Sign up successfully!',
                        'success'
                    )
                    window.location.href = "/home"
                } else if (data.message === "NO") {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Email is taken! Try again please.',
                    })
                }
            }
        })
    }
});
