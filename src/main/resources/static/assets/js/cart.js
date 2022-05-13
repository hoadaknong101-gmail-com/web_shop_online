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

function addProductToCartAndQuantity(id) {
    const quantity = document.getElementById('quantity').value;
    $.ajax({
        url: "/cart/add_product/" + id + "/" + quantity,
        type: "get",
        success: function (response) {
            alert(response)
        },
        error: function (xhr) {

        },
    });
}