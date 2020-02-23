$(document).ready(function () {

    prepareLoginData();
});

function prepareLoginData() {
    $('#loginbtn').click(function () {
        let loginData = {
            email: $('#email').val(),
            pass: $('#pwd').val()
        };
        //prepareUserData(loginData);
        login(loginData);
    })
}


function login(data) {
    $.ajax({
        url: "http://localhost:8080" + '/customer/loginUser',
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (res){
            console.log(res);
            if(res.id!==null) {
                console.log('Użytkownik o id: ' + res.id + ' zalogował się')
                alert('Udało się zalogować');
            }
            /*if(res==true)
                console.log('Użytkownik o id: ' + localStorage.getItem('userLoggedId') + ' zalogował się')
            //window.location.href = "usersList"
            alert('Udało się zalogować');*/
        },
        error: function (res) {
            if(res==false)
                alert('Nie udało się zalogować');
        }
    })
}