$().ready(function () {
    $("#editCar").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            mileage: {
                required: true,
                minlength: 3,
                maxlength: 15,
            }, fuelConsumption: {
                required: true,
                minlength: 3,
                maxlength: 15,
            }, price: {
                required: true,
                minlength: 5,
                maxlength: 15,
            }, deposit: {
                required: true,
                minlength: 5,
                maxlength: 15,
            },
            cityID: {
                required: true,
            },
            districtID: {
                required: true,
            },
            wardID: {
                required: true,
            },
            street: {
                required: true,
            },
            description: {
                required: true,
            }
        },
        messages: {
            mileage: {
                minlength: "Please enter 3 number",
                maxlength: "Please do not enter more than 15 numbers",
            },
        },
    });

    // $(document).on("click", "#btnEditCar", function () {
    //     if ($("#profileForm").valid()) {
    //         $.ajax({
    //             method: "POST",
    //             url: "/car/edit/",
    //             success: function () {
    //                 $(".main-content").text("Profile update successful");
    //             },
    //             error: function (err) {
    //                 console.log(err);
    //             },
    //         });
    //     }
    // });
});