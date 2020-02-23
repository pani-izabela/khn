$(document).ready(function () {

    prepareLoginData();
});

function prepareLoginData() {
    $('#loginbtn').click(function () {
        let loginData = {
            email: $('#email').val(),
            pass: $('#pwd').val()
        };
        login(loginData);
    })
}

//nie podoba mi się, że ten adres tu tak na sztywno jest zaszyty, jak to ominąć?
function login(data) {
    $.ajax({
        url: "http://localhost:8080" + '/customer/loginUser',
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (res){
            if(res.id!==null) {
                console.log('Użytkownik o id: ' + res.id + ' zalogował się');
                alert('Udało się zalogować');
            }
        },
        error: function (res) {
            if(res.id==null)
                alert('Nie udało się zalogować');
        }
    })
}