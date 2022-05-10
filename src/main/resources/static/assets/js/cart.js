function addProductToCart(id){
    $.ajax({
        url: "/cart/add_product/" + id,
        type:"get",
        success: function(response){
            alert(response)
        },
        error: function (xhr){

        },
    });
}