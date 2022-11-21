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

    // $(document).on("click", "#loginButton", function submitForm() {
    //     if ($("form").valid()) {
    //         Swal.fire(
    //             'Well done!',
    //             'Log in successfully!',
    //             'success',
    //             'timer:500000'
    //         )
    //     }
    // });

    $("#loginButton").click(function submitForm() {
            // event.preventDefault();
            // const form_data = $(this).serialize();
            $.ajax({
                url:"./signup",
                method:'POST',
                data:{data},
                success: function(data){
                    if(data)
                    {
                        Swal.fire(
                            'Well done!',
                            'Log in successfully!',
                            'success',
                            'timer:50000'
                        )
                    }else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Something went wrong!',
                            time: '50000',
                            footer: '<a href="">Why do I have this issue?</a>'
                        })
                    }
                }
            });
    });
});