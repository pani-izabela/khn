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
    let user = setRole();
        $.ajax({
            url: "http://localhost:8080" + '/' + user + '/addAppUser',
            method: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "login";
            },
            error: function (res) {
                alert('Nie udało się zarejestrować');
            }
        })
}

function setRole() {
    if ((window.location.href).includes('customer')){
        return 'customer'
    }
    else if((window.location.href).includes('seller')){
        return 'seller'
    }
}