$(document).ready(function () {
    addColorToFrameCustomer();
})

function addColorToFrameCustomer() {
    var amount = $('.amount');
    amount = parseFloat(amount.text());
    var frameCustomer = $('.frame');

    if(amount>=0){
        frameCustomer.removeClass();
        frameCustomer.addClass("GREEN");

    }
    else if(amount<0){
        frameCustomer.removeClass();
        frameCustomer.addClass("CORAL");
    }
}

