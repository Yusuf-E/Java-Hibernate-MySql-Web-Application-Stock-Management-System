$('#box_add_form').submit((event)=>{
    event.preventDefault();
    i=$("#cname").val();
    console.log("Customer i :" + globalCusArr[i].cu_id)
    const cname = globalCusArr[i].cu_id;
    const pname = $("#pname").val();
    const count = $("#count").val();
    const bno   = $("#bNo").val();

    const obj = {
        cu_id : cname,
        pro_id : pname,
        box_quantity : count,
        box_rid : bno,
    }
    if (box.length != 0 ){
        // update box
        obj.update_id = 0;
    }
    else {
        obj.update_id = 1;
    }
    $.ajax({
        url:'./boxaction-post',
        type:'POST',
        data:{obj:JSON.stringify(obj)},
        dataType:'JSON',
        success: function ( data ){
            if (data == 1){
                allBox();
                fncAddReset();
                alert("işlem Başarılı");
            }
        },
        error: function ( err ){
            alert("işlem sırasında bir hata oluştu");
        }
    })

})

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
let globalCusArr = [];
allCustomer();
allProducts();
function allProducts(){
    $.ajax({
        url:"./stock-get",
        type:"GET",
        dataType: "Json",
        success:function ( data ){
            createProduct( data );
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

let globalProArr = []
function createProduct( data ){
    $("#pname").find('option').remove().end();
    $("#pname").append(`<option data-subtext="" value="-1">Seçim Yapınız</option>`);

    globalProArr = data;
    console.log(data);
    for (let i = 0; i<data.length;i++){
        const item = data[i];
        if (item.st_quantity > 0){
            $("#pname").append(`<option data-subtext="Stok : `+item.st_quantity+`" value="`+i+`">`+item.st_title+`</option>`);
        }

    }
    $("#pname").selectpicker("refresh");

}

function changeQuantity(){
    const i =$('#pname').val();
    console.log(i);
   const data = globalProArr[i];
   if (i != -1){
       $('#count').attr({
           "max": data.st_quantity
       })
   }else {
       $('#count').attr({
           "max": ""
       })
   }

}
let box = [];
let globalBoxArr = [];

function allBox( ){
    const i = $('#cname').val();
    const data = globalCusArr[i];
    console.log(data);
    console.log(data.cu_id);
    $.ajax({
        url:'./boxaction-get',
        type:'GET',
        data:{id: encodeURIComponent(data.cu_id)},
        dataType:'JSON',

        success:function (data){
                console.log(data)
                createBox(data);
                fncAddReset();
        },
        error:function (err){

        }

    })
}
let globalCusBox = []
let rid = 0;
let obje={};
function createBox( data ){
    globalCusBox = [];
   const id = $("#cname").val();
   let receipt_id =0;
   let customer_id =0;
   let productlist =[];
   let box_ids = [];

        globalBoxArr = data;
        let total = 0;
        let box_count = 0;
        let html = ``;
        for (let i = 0 ; i<data.length;i++){
           item = data[i];
           console.log("id"+globalCusArr[id].cu_id);
           console.log("item id :"+item.customer.cu_id);

            if (item.customer.cu_id == globalCusArr[id].cu_id){
                globalCusBox.push(item);
                    productlist.push(item.quantity_id.stocks[0].st_id)
                    box_ids.push(item.box_bid)
                   receipt_id = item.box_rid;
                    customer_id=item.customer.cu_id;

                rid = item.box_rid;
                total += ((item.quantity_id.stocks[0].st_sell_price)*(item.quantity_id.quantity));
                console.log(item);
                html +=`<tr role="row" class="odd">
                        <td>`+item.box_bid+`</td>
                        <td>`+item.quantity_id.stocks[0].st_title+`</td>
                        <td>`+item.quantity_id.stocks[0].st_sell_price+`</td>
                        <td>`+item.quantity_id.quantity+`</td>
                        <td>`+item.customer.cu_name+`</td>
                        <td>`+item.box_rid+`</td>
                        <td class="text-right" >
                            <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                                <button onclick="fncDelete(`+item.box_bid+`)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                            </div>
                        </td>
                    </tr>`
            }

        }
        obje.rid = receipt_id;
        obje.cu_id=customer_id;
        obje.products = productlist;
        obje.box_ids = box_ids;
        obje.total = total;
    box_count = 0;
    $("#total").text(total);
    $("#tableRow").html(html);

    }

fncAddReset();
function fncAddReset() {
    select_id = 0;
    $("#count").val("");

    if(rid != 0){
        $("#bNo").val(rid);
        rid = 0;
    }else {
        codeGenerator();
    }
}
function codeGenerator() {
    const date = new Date();
    const time = date.getTime();
    const key = time.toString().substring(4);
    $("#bNo").val(key);
}

function fncDelete(box_bid){
    let answer = confirm("Silmek istediğinizden emin misniz?");
    if (answer){
        $.ajax({
            url: './boxaction-delete?box_bid='+box_bid,
            type: 'DELETE',
            dataType: 'text',
            success: function (data) {
                if ( data != "0" ) {
                    alert("İşlem Başarılı")
                    allBox();
                    fncAddReset();
                }else {
                    alert("Silme sırasında bir hata oluştu!");
                }
            },
            error: function (err) {
                console.log(err)
            }
        })
    }
}
function fncSaveBox(){
    console.log(obje)
    if (globalCusBox.length != 0){
        $.ajax({
            url:'./savebox-post',
            type:'POST',
            data: {obj:JSON.stringify(obje)},
            dataType:'JSON',
            success: function ( data ){
                if (data == 1){
                    allBox();
                    allProducts();
                    fncAddReset();
                    alert("işlem Başarılı");
                }
            },
            error: function ( err ){
                alert("işlem sırasında bir hata oluştu");
            }
        })
    }
}
