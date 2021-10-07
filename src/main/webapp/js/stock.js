// Stock

let select_id =0;

$('#stock_add_form').submit((event)=>{
   event.preventDefault();

   const ptitle = $("#ptitle").val();
   const aprice = $("#aprice").val();
   const oprice = $("#oprice").val();
   const pcode = $("#pcode").val();
   const ptax = $("#ptax").val();
   const psection = $("#psection").val();
   const size = $("#size").val();
   const pdetail = $("#pdetail").val();

   const obj = {
       st_title:ptitle,
       st_purchase_price:aprice,
       st_sell_price:oprice,
       st_code:pcode,
       st_tax_value:ptax,
       st_unit:psection,
       st_quantity:size,
       st_detail:pdetail

   }

   if (select_id !=0){
       // update
       obj["st_id"] = select_id;
   }
   $.ajax({
       url:'./stock-post',
       type:'POST',
       data: {obj:JSON.stringify(obj)},
       dataType: 'JSON',

       success: function (data){
           console.log(data);
           if (data > 0){
               alert("işlem Başarılı");
               fncReset();
           }
           else {
               alert("İşlem Sırasında Bir Hata Oluştu!");
           }
       },
       error:function (err){
           console.log(err);
           if (err.responseText.includes("Duplicate entry")){
               alert("Bu Ürün Daha Önce Eklenmiş!");
           }else {
               alert("İşlem Sırasında Bir Hata Oluştu!")
           }

       }
   })
});
allProducts();
function allProducts(){
    $.ajax({
        url: './stock-get',
        type: 'GET',
        dataType: 'JSON',

        success : function (data){
            createRow( data );
        },
        error: function (err){
            console.log(err);
        }
    })
}
let globalArr=[];
let new_st_tax_value,new_st_unit_value;
function createRow( data ){
    console.log(data);
    globalArr = data;
    let html = ``
    for (let i = 0; i<data.length;i++){
        const itm = data[i];
        convert(itm.st_tax_value,itm.st_unit);
        html+=`<tr role="row" class="odd">
            <td>`+itm.st_id+`</td>
            <td>`+itm.st_title+`</td>
            <td>`+itm.st_purchase_price+`</td>
            <td>`+itm.st_sell_price+`</td>
            <td>`+itm.st_code+`</td>
            <td>`+new_st_tax_value+`</td>
            <td>`+new_st_unit_value+`</td>
            <td>`+itm.st_quantity+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncProductDelete(`+itm.st_id+`)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                <button onclick="productDetail(`+i+`)"  data-bs-toggle="modal" data-bs-target="#customerDetailModel" type="button" class="btn btn-outline-primary "><i class="far fa-file-alt"></i></button>
                <button onclick="fncProductUpdate(`+i+`)" type="button" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
              </div>
            </td>
          </tr>`
    }
    $("#tableRow").html(html);
}

function convert(tax_value,unit){
    if (tax_value == 0){
        new_st_tax_value = "Dahil"
    }
    else if (tax_value == 1){
        new_st_tax_value = "%1"
    }
    else if (tax_value == 2){
        new_st_tax_value = "%8"
    }
    else if (tax_value == 3){
        new_st_tax_value = "%18"
    }
    if (unit == 0){
        new_st_unit_value = "Adet"
    }
    else if (unit == 1){
        new_st_unit_value = "KG"
    }
    else if (unit == 2){
        new_st_unit_value = "Metre"
    }
    else if (unit == 3){
        new_st_unit_value = "Paket"
    }
    else if (unit == 4){
        new_st_unit_value = "Litre"
    }
}

function productDetail( i ){
    const itm = globalArr[i]
    convert(itm.st_tax_value,itm.st_unit)
     $("#st_id").text(itm.st_title+" - "+itm.st_id);
    $("#st_title").text(itm.st_title);
    $("#st_purchase_price").text(itm.st_purchase_price);
    $("#st_sell_price").text(itm.st_sell_price);
    $("#st_code").text(itm.st_code);
    $("#st_tax_value").text(new_st_tax_value);
    $("#st_unit").text(new_st_unit_value);
    $("#st_quantity").text(itm.st_quantity);
    $("#st_detail").text(itm.st_detail);

}
function codeGenerator() {
    const date = new Date();
    const time = date.getTime();
    const key = time.toString().substring(4);
    $('#ccode').val( key )
    $('#pcode').val( key )
}
function fncReset(){
    select_id=0;
    $("#stock_add_form").trigger("reset");
    allProducts();
    codeGenerator();
}
function fncProductDelete(st_id){
    console.log(st_id);
    let answer = confirm("Silmek İstediğinize Eminmisiniz");
    if (answer){

        $.ajax({
            url:'./stock-delete?st_id='+st_id,
            type:'DELETE',
            dataType:'text',
            success:function (data){
                if (data!="0"){
                    fncReset();
                }else{
                    alert("Silme Sırasında Bir Hata Oluştu!");
                }
            },
            error:function (err){
                console.log(err);
            }
        })

    }
}
function fncProductUpdate( i ){
    const item = globalArr[i];
    select_id = item.st_id;
     $("#ptitle").val(item.st_title);
     $("#aprice").val(item.st_purchase_price);
     $("#oprice").val(item.st_sell_price);
     $("#pcode").val(item.st_code);
     $("#ptax").val(item.st_tax_value);
     $("#psection").val(item.st_unit);
     $("#size").val(item.st_quantity);
     $("#pdetail").val(item.st_detail);
}