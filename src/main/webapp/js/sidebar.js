$(document).ready(function(){
    showCorrectMenu();
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
        $("#auctionsCustomer").hide();
    }
}
