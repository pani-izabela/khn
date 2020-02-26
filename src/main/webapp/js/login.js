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
    var user;
    if ((window.location.href).includes('customer')){
        user = 'customer'
    }
    else if((window.location.href).includes('seller')){
        user = 'seller'
    }
    $.ajax({
        url: "http://localhost:8080" + '/' + user + '/loginUser',
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (res){
            if(res.id!==null) {
                console.log('Użytkownik o id: ' + res.id + ' zalogował się');
                window.location.href = "menu";
                alert('Udało się zalogować');
            }
        },
        error: function (res) {
            if(res.id==null)
                console.log('Użytkownik o id: ' + res.id + ' nie zalogował się');
                alert('Nie udało się zalogować');
        }
    })
}