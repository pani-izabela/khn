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
        // ten if else tez mozna do oddzielnej metody :)
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
    // tak jak dla login, tylko skoro robi to samo co login to wystarczy jedna metode i sie odwolac do niej tutaj ponownie
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
                //alert('Rejestracja udała się!');
            },
            error: function (res) {
                alert('Nie udało się zarejestrować');
            }
        })
}