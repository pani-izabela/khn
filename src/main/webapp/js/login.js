$(document).ready(function () {
    prepareLoginData();
});

function prepareLoginData() {
    $('#loginbtn').click(function () {
        let emailField = $('#email').val();
        let passField = $('#pwd').val();
        let loginData;
        if(emailField.trim().length===0 || passField.trim().length===0)
            alert("Brakuje loginu lub hasła")
        else{
            loginData = {
                email: emailField,
                pass: passField
            };
        }
        login(loginData);
    })
}

function login(data) {
    let user = setRole();
    $.ajax({
        url: "http://localhost:8080" + '/' + user + '/loginUser',
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (res){
            if(res.id!==null) {
                console.log('Użytkownik o id: ' + res.id + ' zalogował się');
                localStorage.setItem('loggedUserEmail', res.email);
                window.location.href = "menu";
                //alert('Udało się zalogować');
            }
        },
        error: function (res) {
            if(res.id==null)
                console.log('Użytkownik o id: ' + res.id + ' nie zalogował się');
                alert('Nie udało się zalogować');
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
