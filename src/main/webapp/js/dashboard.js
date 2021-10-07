allCustomer();
function allCustomer() {

    $.ajax({
        url: './customer-get',
        type: 'GET',
        dataType: 'Json',
        success: function (data) {
            console.log(data);
            $("#customer_count").text(data.length);
        },
        error: function (err) {
            console.log(err)
        }
    })

}
allBills();
function allBills(){
    $.ajax({
        url:"./allbills-post",
        type:"POST",
        dataType:"Json",
        success:function ( data ){
            console.log("burada" + data);
            $("#order_count").text(data.length)
            createOrders(data);
        },
        error : function ( err ){
            console.log(err);
        }
    })
}
allProducts();
function allProducts(){
    $.ajax({
        url: './stock-get',
        type: 'GET',
        dataType: 'JSON',

        success : function (data){
           $("#product_count").text(data.length);
           createProducts( data );
           productsDetail(data);
        },
        error: function (err){
            console.log(err);
        }
    })
}



function createOrders( data ){
    let j = 0;
    let html = ``;
        for (i =0; i <data.length && j<5;i++){
            const item = data[i];
            console.log(item);
            const st = item.customer.cu_name + " " + item.customer.cu_surname;
            console.log(item)
            html += `<tr role="row" class="odd">
                <td>`+item.re_rid+`</td>
                <td>`+st+`</td>
                <td>8</td>
                <td>`+item.amount+`</td>
              </tr>`;
            j++;
        }
        $("#tableOrdersRow").html(html);
}

function createProducts( data ){
    let j = 0;
    let html = ``;
    for (i =data.length; i > 0 && j<5;i--){
        const item = data[i-1];
        console.log(item)
        html += `<tr role="row" class="odd">
                <td>`+item.st_id+`</td>
                <td>`+item.st_code+`</td>
                <td>`+item.st_title+`</td>
                <td>`+item.st_purchase_price+`</td>
              </tr>`;
        j++;
    }
    $("#tableProductsRow").html(html);

}
function productsDetail(data){
    let sellPrice=0,purchasePrice=0,quantity=0;
    for (i = 0 ; i<data.length;i++){
        const item = data[i];
        purchasePrice += parseInt(item.st_purchase_price );
        sellPrice += parseInt(item.st_sell_price );
        quantity += parseInt(item.st_quantity)
    }
    $("#kind").text(data.length);
    $("#quantity").text(quantity);
    $("#purchasePrice").text(purchasePrice);
    $("#sellPrice").text(sellPrice);
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
    $("#total").text(itm.checkout_amount);
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
    $("#today_total").text(totalPayment);
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