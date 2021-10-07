$('#add_payOut').submit( ( event ) => {
    event.preventDefault();

    const payOutTitle = $("#payOutTitle").val()
    const payOutType = $("#payOutType").val()
    const payOutTotal = $("#payOutTotal").val()
    const payOutDetail = $("#payOutDetail").val()

    const obj = {
        payOutTitle: payOutTitle,
        payOutType: payOutType,
        payOutTotal: payOutTotal,
        payOutDetail: payOutDetail,
    }

    $.ajax({
        url: './payout-post',
        type: 'POST',
        data: { obj: JSON.stringify(obj) },
        dataType: 'JSON',
        success: function (data) {
            if ( data > 0 ) {
                allPayments();
                fncPayoutReset();
                alert("İşlem Başarılı")
            }else {
                alert("İşlem sırasında hata oluştu!");
            }
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırısında bir hata oluştu!");
        }
    })


})
allPayments();
function allPayments(){
    $.ajax({
        url:"./payout-get",
        type:"GET",
        dataType:"Json",
        success:function ( data ){
            createPayment( data );
        },
        error : function ( err ){
            console.log(err);
        }
    })
}
function createPayment( data ){
    globalPaymentArr = data;
    let st;
    let html = ``
    console.log(data);
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];
        if (itm.payOutType == 0){
            st = "Nakit"
        }else if (itm.payOutType == 1){
            st = "Kredi Kartı"
        }else if (itm.payOutType == 2){
            st = "Havale"
        }else if (itm.payOutType == 3){
            st = "EFT"
        }else if (itm.payOutType == 4){
            st = "Banka Çeki"
        }

        console.log(itm.pay_id);
        html += `<tr role="row" class="odd">
            <td>`+itm.id+`</td>
            <td>`+itm.payOutTitle+`</td>
            <td>`+st+`</td>
            <td>`+itm.payOutTotal+`</td>
            <td>`+itm.payOutDetail+`</td>
            <td>`+itm.date+`</td>

          </tr>`;
    }
    console.log(html);
    $('#tableRow').html(html);
}
function fncPayoutReset() {
    select_id = 0;
    $("#payOutTitle").val("");
    $("#payOutType").val("");
    $("#payOutTotal").val("");
    $("#payOutDetail").val("");

}