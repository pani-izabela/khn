$(document).ready(function () {
    prepareData();
});

function prepareData() {
    $('#passwordChange').click(function () {
        let emailField = $('#email').val();
        let oldPassField = $('#oldPass').val();
        let newPassField = $('#newPass').val();
        if(emailField.trim().length===0 || oldPassField.trim().length===0 || newPassField.trim().length===0)
            alert("Pola nie zostały prawidłowo wypełnione");

        changePass(emailField, oldPassField, newPassField );
    })
}

function changePass(emailField, oldPassField, newPassField) {
    $.ajax({
        url: "http://localhost:8080/userChangePassword",
        method: "POST",
        data: {
            "emailField": emailField,
            "oldPassField": oldPassField,
            "newPassField" : newPassField
        },
        success: function (res){
            if(res.id!==null) {
                alert('Hasło zostało zmienione');
            }
        },
        error: function (res) {
            if(res.id===null){
                alert('Nie udało się zmienić hasła');
            }
        }
    })
}
