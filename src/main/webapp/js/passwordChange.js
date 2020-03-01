$(document).ready(function () {
    prepareData();
});

function prepareData() {
    $('#passwordChange').click(function () {
        let emailField = $('#email').val();
        let oldPassField = $('#oldPass').val();
        let newPassField = $('#newPass').val();
        let data;
        if(emailField.trim().length===0 || oldPassField.trim().length===0 || newPassField.trim().length===0)
            alert("Pola nie zostały prawidłowo wypełnione")
        else{
            data = {
                email: emailField,
                oldPass: oldPassField,
                newPass: newPassField
            };
        }
        changePass(data);
    })
}

function changePass(data) {
    $.ajax({
        url: "http://localhost:8080" + '/userChangePassword',
        method: "PUT",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (res){
            if(res.id!==null) {
                console.log('Hasło użytkownika o emailu: ' + res.email + ' zostało zmienione na: ' + res.pass);
                window.location.href = "/index";
                alert('Hasło zostało zmienione');
            }
        },
        error: function (res) {
            if(res.id===null){
                console.log('Hasło użytkownika o emailu: ' + res.email + ' nie zostało zmienione');
                alert('Nie udało się zmienić hasła');
            }
        }
    })
}
