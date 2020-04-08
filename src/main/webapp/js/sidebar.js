$(document).ready(function(){
    schowCorrectMenu();
});

/*function schowCorrectMenu() {
    $("#firstSubmenu li").hide();
    if(localStorage.getItem('userIsAdmin', true)){
        $('#usersList').show();
    }
    else if(localStorage.getItem('userIsSeller', true)){
        $('#mySalesSeller').show();
        $('#addForSale').show();
    }
    else if(localStorage.getItem('userIsCustomer', true)){
        $('#myShoppingCustomer').show();
        $('#myCreditCustomer').show();
    }
    else if(localStorage.getItem('userIsCustomerAndSeller', true)){
        $('#mySalesSeller').show();
        $('#addForSale').show();
        $('#myShoppingCustomer').show();
        $('#myCreditCustomer').show();
    }
    $('#auctions').show();
    $('#profile').show();
    $('#logout').show();
}*/

function schowCorrectMenu() {
    if(localStorage.getItem('userIsAdmin')){
        console.log("admin = ",admin);
        console.log("isAdmin : ", localStorage.getItem('userIsAdmin'));
        $("#menuForRoleLiC").hide();
        $("#menuForRoleLiS").hide();
    }
    else if(localStorage.getItem('userIsSeller')){
        console.log("isSeller : ", localStorage.getItem('userIsSeller'));
        $("#menuForRoleLiA").hide();
        $("#menuForRoleLiC").hide();
    }
    else if(localStorage.getItem('userIsCustomer')){
        console.log("klient = ", customer);
        console.log("isCustomer : ", localStorage.getItem('userIsCustomer'));
        $("#menuForRoleLiA").hide();
        $("#menuForRoleLiS").hide();
    }
    else if(localStorage.getItem('userIsCustomerAndSeller')){
        console.log("isCustomer+Seller : ", localStorage.getItem('userIsCustomerAndSeller'));
        $("#menuForRoleLiA").hide();
    }

    $('#profile').show();
    $('#logout').show();
}
