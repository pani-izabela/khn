$(document).ready(function(){
    showCorrectMenu();
    showCorrectProfile();
});

function showCorrectMenu() {
    if((localStorage.getItem('userRole') === 'admin') === true){
        $("#menuForRoleLiC").hide();
        $("#menuForRoleLiS").hide();
    }
    else if((localStorage.getItem('userRole') === 'seller') === true){
        $("#menuForRoleLiA").hide();
        $("#menuForRoleLiC").hide();
    }
    if((localStorage.getItem('userRole') === 'customer') === true){
        $("#menuForRoleLiA").hide();
        $("#menuForRoleLiS").hide();
    }
    else if((localStorage.getItem('userRole') === 'customer+seller') === true){
        $("#menuForRoleLiA").hide();
        $("#auctionsSeller").hide();
    }
}

function showCorrectProfile() {
    if((localStorage.getItem('userRole') === 'admin') === true){
        $("#profileCustomer").hide();
        $("#profileSeller").hide();
    }
    else if((localStorage.getItem('userRole') === 'seller') === true){
        $("#profileAdmin").hide();
        $("#profileCustomer").hide();
    }
    if((localStorage.getItem('userRole') === 'customer') === true){
        $("#profileAdmin").hide();
        $("#profileSeller").hide();
    }
    else if((localStorage.getItem('userRole') === 'customer+seller') === true){
        $("#profileAdmin").hide();
        $("#profileCustomer").hide();
    }
}
