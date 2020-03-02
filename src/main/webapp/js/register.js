$(document).ready(function () {

    prepareRegisterData();
});

function prepareRegisterData() {
    $('#registerBtn').click(function () {
        let firstnameField = $('#firstname').val();
        let lastnameField = $('#lastname').val();
        let emailField = $('#email').val();
        let passField = $('#pwd').val();
        let registerData;

        // mozna by pomyslec o jakiejs oddzielnej metodzie, ktora przygotuje haslo - preparePassword?
        if(emailField.trim().length===0 || passField.trim().length===0 || firstnameField.trim().length===0 || lastnameField.trim().length===0)
            alert('Uzupełnij brakujące pola');
        else {
            registerData = {
                firstname: firstnameField,
                lastname: lastnameField,
                email: emailField,
                pass: passField
            }
        };
        register(registerData);
    })
}

function register(data) {
    var user;
    if ((window.location.href).includes('customer')){
        user = 'customer'
    }
    else if((window.location.href).includes('seller')){
        user = 'seller'
    }
        $.ajax({
            url: "http://localhost:8080" + '/' + user + '/addAppUser',
            method: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "login";
                alert('Rejestracja udała się!');
            },
            error: function (res) {
                alert('Nie udało się zarejestrować');
            }
        })
}