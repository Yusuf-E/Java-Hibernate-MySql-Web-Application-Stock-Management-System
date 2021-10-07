$('#add_payIn').submit( ( event ) => {
    event.preventDefault();

    const cname = $("#cname").val()
    const bname = $("#bname").val()
    const payInTotal = $("#payInTotal").val()
    const payInDetail = $("#payInDetail").val()

    const obj = {
        cu_id: globalCusArr[cname].cu_id,
        bname: bname,
        payment_amount: payInTotal,
        payment_detail: payInDetail,
    }

    $.ajax({
        url: './payin-post',
        type: 'POST',
        data: { obj: JSON.stringify(obj) },
        dataType: 'JSON',
        success: function (data) {
            if ( data > 0 ) {
                alert("İşlem Başarılı")
                allCustomer();
                allPayments();
                fncPayinReset();
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
// add




function allCustomer(){
    $.ajax({
        url:"./customer-get",
        type:"GET",
        dataType:"Json",
        success:function ( data ){
            createCustomer( data );
        },
        error : function ( err ){
            console.log(err);
        }
    })
}
function createCustomer( data ){
    $("#cname").find('option').remove().end();
    $("#cname").append(`<option data-subtext="">Seçim Yapınız</option>`);
    globalCusArr = data;
    for (let i = 0; i<data.length;i++){
        const itm = data[i];
        const st = itm.cu_name + " "+ itm.cu_surname;
        console.log(st);
        $("#cname").append(`<option data-subtext="" value="`+i+`">`+st+`</option>`) ;
    }
    $("#cname").selectpicker("refresh");
}

function allBills(){
    let cu_id = $("#cname").val();
    $.ajax({
        url:"./bills-get",
        type:"GET",
        dataType:"Json",
        data:{id:globalCusArr[cu_id].cu_id},
        success:function ( data ){
            createBill( data);
        },
        error : function ( err ){
            console.log(err);
        }
    })
}
globalBillArr = [];
globalCusArr = [];
function createBill( data){
    $("#bname").find('option').remove().end();
    $("#bname").append(`<option data-subtext="">Seçim Yapınız</option>`);
    cu_id=$("#cname").val()
    globalBillArr = data;
    console.log(globalCusArr[cu_id].cu_id);
    for (let i = 0; i<data.length;i++){
        const itm = data[i];
        console.log(itm.customer.cu_id);
            if (itm.active == true){
                let remain = parseInt(itm.amount) - parseInt(itm.repayment);
                $("#bname").append(`<option data-subtext="`+"Kalan Borç"+remain+"TL"+`" value="`+itm.re_rid+`">`+itm.re_rid+`</option>`) ;
            }



    }
    $("#bname").selectpicker("refresh");
}
allCustomer();
allPayments();
function allPayments(){
    $.ajax({
        url:"./payin-get",
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
let globalPaymentArr= []
function createPayment( data ){
    globalPaymentArr = data;
    let html = ``
    console.log(data);
    for (let i = 0; i < data.length; i++) {

        const itm = data[i];
        console.log(itm.pay_id);
        html += `<tr role="row" class="odd">
            <td>`+itm.pay_id+`</td>
            <td>`+itm.customer.cu_name+`</td>
            <td>`+itm.customer.cu_surname+`</td>
            <td>`+itm.receipt.re_rid+`</td>
            <td>`+itm.payment_amount+`</td>
            <td>`+itm.payment_detail+`</td>
            <td>`+itm.receipt.repayment+`</td>
            <td>`+itm.receipt.amount+`</td>
            <td>`+itm.date+`</td>

          </tr>`;
    }
    console.log(html);
    $('#tableRow').html(html);
}
function fncPayinReset() {
    select_id = 0;
    $("#payInDetail").val("");
    $("#payInTotal").val("");
    $("#bname").find('option').remove().end();
    $("#bname").append(`<option data-subtext="">Seçim Yapınız</option>`);
    $("#bname").selectpicker("refresh");
}