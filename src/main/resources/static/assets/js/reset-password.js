$(document).ready(function () {

    $.validator.addMethod(
        "passwordValidate",
        function (value, element, params) {
            const regex = new RegExp("^(?=.*\\d)(?=.*[a-zA-Z]).{7,50}$");
            return regex.test(value) === params;
        },
        "Password invalid! Please enter password with the following direction"
    );

    $("#forgotPass").validate({
        rules: {
            password: {
                required: true,
                passwordValidate: true,
                minlength: 9,
                maxlength: 50,
            },
            repassword: {
                required: true,
                minlength: 9,
                maxlength: 50,
                equalTo: "#password",
            },

        },
        messages: {
            password: {
                required: "Please input your password",
                minlength: "Use at least one letter, one number and seven characters",
                maxlength: "Not exceeded 50 characters",
            },
            repassword: {
                required: "Please enter your password again",
                minlength: "Use at least one letter, one number and seven characters",
                maxlength: "Not exceed 50 characters",
                equalTo: "Password doesn't match!",
            },
        },
    });

    $(document).on("click", "#btnSubmit", function submitForm() {
        if ($("form").valid()) {
            $('#changePassword').modal('show');
        }
    });
});