$(document).ready(function () {

    prepareRegisterData();
});

function prepareRegisterData() {
    $('#registerBtn').click(function () {
        let registerData = {
            firstname: $('#firstname').val(),
            lastname: $('#lastname').val(),
            email: $('#email').val(),
            pass: $('#pwd').val()
        };
        register(registerData);
    })
}

function register(data) {
    $.ajax({
        url: "http://localhost:8080" + '/customer/addAppUser',
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (res) {
            window.location.href = "login"
            alert('Rejestracja udała się!');
        },
        error: function (res) {
            alert('Nie udało się zarejestrować');
        }
    })
}