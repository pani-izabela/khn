let emailField, passField, typeOfPage, role, userId;

function prepareLoginData() { //zbiera dane: email i pass oraz rodzaj strony customer/seller
    typeOfPage = setRole();
    emailField = $('#email').val();
    passField = $('#pwd').val();
    let loginData;
    if (emailField.trim().length === 0 || passField.trim().length === 0)
        alert("Brakuje loginu lub hasła");
    else {
        loginData = {
            email: emailField,
            pass: passField
        };
    }
    if (typeOfPage === "customer") {
        checkUserCustomer(loginData, typeOfPage);
    } else if (typeOfPage === "seller") {
        checkUserSeller(loginData, typeOfPage);
    }
}

function checkUserCustomer(data, page) {
    $.ajax({
        url: "http://localhost:8080" + '/' + page + '/loginUser',
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (res) {
            if (res.id !== null) {
                userId = res.id;
                role = checkRole(res);
                if (role === "admin") {
                    let loggedUser = $('form').serialize();
                    loginSecured(loggedUser)
                } else if (role === "customer") {
                    let loggedUser = $('form').serialize();
                    loginSecured(loggedUser)
                } else if (role === "customer+seller") {
                    let loggedUser = $('form').serialize();
                    loginSecured(loggedUser)
                } else {
                    alert("Nie masz uprawnień, aby zalodować się na tej stronie")
                }
            }
        },
        error: function (res) {
            if (res.id == null)
                alert('Nie udało się zalogować, brak uprawnień');
        }
    })
}

function checkUserSeller(data, page) {
    $.ajax({
        url: "http://localhost:8080" + '/' + page + '/loginUser',
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (res) {
            if (res.id !== null) {
                userId = res.id;
                role = checkRole(res);
                if (role === "admin") {
                    let loggedUser = $('form').serialize();
                    loginSecured(loggedUser)
                } else if (role === "seller") {
                    let loggedUser = $('form').serialize();
                    loginSecured(loggedUser)
                } else if (role === "customer+seller") {
                    let loggedUser = $('form').serialize();
                    loginSecured(loggedUser)
                } else {
                    alert("Nie masz uprawnień, aby zalodować się na tej stronie")
                }
            }
        },
        error: function (res) {
            if (res.id == null)
                alert('Nie udało się zalogować, brak uprawnień');
        }
    })
}

function loginSecured(data) {
    console.log('Jest loginSecured() z ' + data);
    $.ajax({
        url: "http://localhost:8080/performLogin",
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        data: data,
        success: function () {
            localStorage.setItem('loggedUserEmail', emailField);
            localStorage.setItem('loggedUserId', userId);
            if (role === 'admin') {
                window.location.href = "http://localhost:8080/admin/menu";
            } else {
                window.location.href = "menu";
            }
        },
        error: function () {
            alert("Login error - bad credentials");
        }
    });
}

