let userData;
var role;
$(document).ready(function () {
    getLoggedUserData();
    editUserData();
    saveUserData();
    back();
    becomeCustomer();
});

function getLoggedUserData() {
    $.ajax({
        url: "http://localhost:8080/getAppUser?" + $.param({id: localStorage.getItem('loggedUserId')}),
        type: "GET",
        contentType: "application/json",
        success: function (res) {
            role = checkRole(res);
            localStorage.setItem('userRole', role);
            showCorrectBtn();
            userData = {
                id: res.id,
                firstname: res.firstname,
                lastname: res.lastname,
                email: res.email,
                pass: res.pass
            };
            $('#firstname').val(userData.firstname);
            $('#lastname').val(userData.lastname);
            $('#email').val(userData.email);
            $('#pass').val(userData.pass);

        },
        error: function (res) {
            console.log("Nie udało się pobrać danych zalogowanego usera")
        }
    });
}

function getUserDataAfterChange() {
    $('#firstname').val(userData.firstname);
    $('#lastname').val(userData.lastname);
    $('#email').val(userData.email);
    $('#pass').val(userData.pass);
}

function showCorrectBtn() {
    var formClass = $('#profileForm').attr("class");
    if(formClass === 'is-readonly'){
        $('#saveBtn').hide();
        $('#backBtn').hide();
        $('#editBtn').show();
        if((role === 'seller') === true){
            $('#becomeCustomerBtn').show();
        }
        else{
            $('#becomeCustomerBtn').hide();
        }
    }
    else{
        $('#editBtn').hide();
        $('#backBtn').show();
        $('#saveBtn').show();
        $('#becomeCustomerBtn').hide();
    }

}

function editUserData() {
    $('.js-edit').on('click', function() {
        var $form = $(this).closest('form');
        $form.toggleClass('is-readonly is-editing');
        var isReadonly = $form.hasClass('is-readonly');
        $form.find('input,textarea').prop('disabled', isReadonly);
        showCorrectBtn();
    });
}

function back() {
    $('.js-back').on('click', function() {
        var $form = $(this).closest('form');
        $form.toggleClass('is-readonly is-editing');
        var isReadonly = $form.hasClass('is-readonly');
        $form.find('input,textarea').prop('disabled', isReadonly);
        showCorrectBtn();
        getUserDataAfterChange();
    });
}

function saveUserData(){
    $('.js-save').on('click', function() {
        if(compareOldDataWithNew()){
            console.log('dane są identyczne');
        }
        else{
            console.log('dane różnią się aktualizuję dane');
            prepareDataToChange();
            changeDataAppUser();
        }
        var $form = $(this).closest('form');
        $form.toggleClass('is-editing is-readonly');
        $form.find('input,textarea').prop('disabled', true);
        showCorrectBtn();
    });
}

function becomeCustomer(){
    $('.js-becomeCustomer').on('click', function() {
        $.ajax({
            url: "http://localhost:8080/seller/addCustomerRole",
            method: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(userData),
            success: function (res) {
                alert("Zostałes kupujacym");
                window.location.reload();
            },
            error: function (res) {
                alert("Operacja zostania kupujacym nie powiodla sie");
            }
        })
    });
}

function changeDataAppUser(){
    var user = setRole();
    $.ajax({
        url: "http://localhost:8080" + '/' + user + '/changeDataAppUser',
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(userData),
        success: function (res) {
            alert(res.responseText);
        },
        error: function (res) {
            alert(res.responseText);
        }
    })
}

function compareOldDataWithNew(){
    if(userData.firstname === $('#firstname').val() &&
        userData.lastname === $('#lastname').val() &&
        userData.email === $('#email').val() &&
        userData.pass === $('#pass').val()){
        return true;
    }
}

function prepareDataToChange() {
    userData = {
        id: localStorage.getItem('loggedUserId'),
        firstname: $('#firstname').val(),
        lastname: $('#lastname').val(),
        email: $('#email').val(),
        pass: $('#pass').val()
    };
}