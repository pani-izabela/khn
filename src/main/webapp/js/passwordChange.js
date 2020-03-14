$(document).ready(function () {
    prepareData();
});


// Elegancko tutaj "posprzatalas" :)
function prepareData() {
    $('#passwordChange').click(function () {
        let emailField = $('#email').val();
        let oldPassField = $('#oldPass').val();
        let newPassField = $('#newPass').val();
        checkFields(emailField, oldPassField, newPassField);
        changePass(emailField, oldPassField, newPassField);
    })
}

/*function prepareData2() {
    $('#passwordChange').click(function () {
        let emailField = $('#email').val();
        let oldPassField = $('#oldPass').val();
        let newPassField = $('#newPass').val();
        let data;
        checkFields(emailField, oldPassField, newPassField);
        data = {
            email: emailField,
            oldPass: oldPassField,
            newPass: newPassField
        };
        changePass(data);
    })
}*/

function checkFields(emailField, oldPassField, newPassField) {
    if (emailField.trim().length === 0 || oldPassField.trim().length === 0 || newPassField.trim().length === 0)
        alert("Pola nie zostały prawidłowo wypełnione");
}

function changePass(emailField, oldPassField, newPassField) {
    $.ajax({
        url: "http://localhost:8080/userChangePassword",
        method: "POST",
        data: {
            "emailField": emailField,
            "oldPassField": oldPassField,
            "newPassField": newPassField
        },
        success: function (res) {
            alert(res);
        },
        error: function (res) {
            alert(res.responseText)
        }
    })
}

/*function changePass2(data) {
    $.ajax({
        url: "http://localhost:8080" + '/userChangePassword',
        method: "PUT",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (res) {
            alert(res);
        },
        error: function (res) {
            alert(res);
        }
    })
}*/
