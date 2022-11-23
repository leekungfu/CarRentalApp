$(document).ready(function () {
    let amount = $("#js_wallet_amount").val();
    $("#js_wallet_top").click(function () {
        $("#js_wallet_modal_function").text("TOP UP");
        $("#js_wallet_modal_description").text("Please enter the amount to top-up to your wallet");
        $("#modal_top").modal('show');
        amount = 1;
    })
    $("#js_wallet_withdraw").click(function () {
        $("#js_wallet_modal_function").text("WITHDRAW");
        $("#js_wallet_modal_description").text("Please enter the amount to withdraw from your wallet");
        $("#modal_top").modal('show');
        amount = -1;
    })
    $("#js_wallet_modal_btn_submit").click(function () {
        amount *= $("#js_wallet_amount").val();
        updateWallet();
    })

    function updateWallet() {
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '/top_up',
            data: JSON.stringify(amount),
            dataType: 'json',
            cache: false,
            success: function (data) {
                console.log(data);
                $("#js_wallet_balance").text(data.balance);
                let body = $("#js_wallet_table_transaction tbody")
                var html = '';
                for (let i = 0; i < data.tran.length; i++) {
                    html = html +
                        `<tr">
                                <th scope="row">${data.tran[i].id}</th>
                                <td style="text-align: right">${data.tran[i].amount}</td>
                                <td>${data.tran[i].type}</td>
                                <td>${data.tran[i].date}</td>
                                <td>${data.tran[i].note}</td>
                             </tr>`
                }
                body.html(html);
            }
        })
    }
})