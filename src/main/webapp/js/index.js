$('#rememberForm').submit((event)=>{
    event.preventDefault();

   const email = $("#pass_email").val();
    $("#passwordMessage").text("")
    $("#errorMessage").text("")

    $.ajax({
        url:'./remember',
        type:'POST',
        data:{email:email},
        dataType:'JSON',
        success: function ( data ){
            alert("işlem Başarılı");
            if (data !=""){
                $("#passwordMessage").text(data)

            }
            else {
                $("#errorMessage").text("Böyle Bir Kullanıcı Sistemde Bulunmamaktadır.")
            }
        },
        error: function ( err ){
            alert("işlem sırasında bir hata oluştu");
        }
    })

})