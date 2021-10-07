function allIncomes(){
    $.ajax({
        url:"./payin-get",
        type:"GET",
        dataType:"Json",
        success:function ( data ){
           allincome( data );
        },
        error : function ( err ){
            console.log(err);
        }
    })
}
allIncomes();
function allincome( data){
    let totalPayment = 0;
    for (let i = 0; i < data.length; i++) {
       const itm = data[i]
        totalPayment += parseInt(itm.payment_amount);
    }
    $("#totalIncome").text(totalPayment);
}
dailyIncomes();
function dailyIncomes(){
    $.ajax({
        url:"./checkout-payin-get",
        type:"GET",
        dataType:"Json",
        success:function ( data ){
            dailyincome( data );
        },
        error : function ( err ){
            console.log(err);
        }
    })
}
function dailyincome( data){
    let totalPayment = 0;
    for (let i = 0; i < data.length; i++) {
        const itm = data[i]
        totalPayment += parseInt(itm);
    }
    $("#dailyIncome").text(totalPayment);
}
allPayments()
function allPayments(){
    $.ajax({
        url:"./payout-get",
        type:"GET",
        dataType:"Json",
        success:function ( data ){
            allPayment( data );
        },
        error : function ( err ){
            console.log(err);
        }
    })
}
function allPayment( data ){
    let totalPayment = 0;
    for (let i = 0; i < data.length; i++) {
        const itm = data[i]
        totalPayment += parseInt(itm.payOutTotal);
    }
    $("#allPayment").text(totalPayment);

}

dailyPayments();
function dailyPayments(){
    $.ajax({
        url:"./checkout-payment-get",
        type:"GET",
        dataType:"Json",
        success:function ( data ){
            dailyPayment( data );
        },
        error : function ( err ){
            console.log(err);
        }
    })
}
function dailyPayment(data){
    let totalPayment = 0;
    for (let i = 0; i < data.length; i++) {
        const itm = data[i]
        totalPayment += parseInt(itm);
    }
    $("#dailyPayment").text(totalPayment);
}
checkOut();
function checkOut(){
    $.ajax({
        url:"./checkout-amount-get",
        type:"GET",
        dataType:"Json",
        success:function ( data ){
            checkOut_amount( data );
        },
        error : function ( err ){
            console.log(err);
        }
    })
}
function checkOut_amount(data){
    console.log(data)
    const itm = data[0];
    $("#checkOut").text(itm.checkout_amount);
}
allCustomer();
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
    $("#cname").append(`<option data-subtext="" value="-1">Tümü</option>`);
    globalCusArr = data;
    for (let i = 0; i<data.length;i++){
        const itm = data[i];
        const st = itm.cu_name + " "+ itm.cu_surname;
        console.log(st);
        $("#cname").append(`<option data-subtext="" value="`+i+`">`+st+`</option>`) ;
    }
    $("#cname").selectpicker("refresh");
}
let globalCusArr = [];
$('#searchForm').submit( ( event ) => {
    event.preventDefault();
    let cid ;
    const cname = $("#cname").val()
    const ctype = $("#ctype").val()
    const startDate = $("#startDate").val()
    const endDate = $("#endDate").val()
    if (cname != -1){
        cid =  globalCusArr[cname].cu_id
    }else {
        cid = -1;
    }
    const obj = {
        cu_id : cid,
        ctype: ctype,
        startDate: startDate,
        endDate: endDate,
    }
    console.log(obj + "Burada");
    $.ajax({
        url: './checkout-post',
        type: 'POST',
        data: { obj: JSON.stringify(obj) },
        dataType: 'JSON',
        success: function (data) {
            if ( ctype == 2 ) {
                    createPaymentRows( data);
            }
            else {
                createAllPayinRows( data );
            }
        },
        error: function (err) {
            console.log(err)
            alert("İşlem işlemi sırısında bir hata oluştu!");
        }
    })


})
function  createPaymentRows(data){
    let st ;
    let html = `<thead>
          <tr>
            <th>Id</th>
            <th>Başlık</th>
            <th>Ödeme Türü</th>
            <th>Ödeme Detayı</th>
            <th>Ödeme Tutarı</th>
            <th>Tarih</th>

          </tr>
          </thead>
            <tbody>`
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
         html += ` <tr role="row" class="odd">
            <td>`+itm.id+`</td>
            <td>`+itm.payOutTitle+`</td>
            <td>`+st+`</td>
            <td>`+itm.payOutTotal+`</td>
            <td>`+itm.payOutDetail+`</td>
            <td>`+itm.date+`</td>
          </tr>`;

    }
    html+=`</tbody>`
    $('#tableRow').html(html);
}
function  createAllPayinRows( data ){
    let html = `<thead>
          <tr>
            <th>Id</th>
            <th>Adı</th>
            <th>Soyadı</th>
            <th>Fiş No</th>
            <th>Ödeme Tutarı</th>
            <th>Ödeme Detay</th>
            <th>Toplam Ödenen Tutar</th>
            <th>Toplam Fatura Tutarı</th>
            <th>Tarih</th>
          </tr>
          </thead>
            <tbody>`
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];
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
    html+=`</tbody>`
    $('#tableRow').html(html)
}
