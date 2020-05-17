let userData;
$(document).ready(function () {
    getUserData();
    showCorrectBtn();
    editUserData();
    saveUserData();
    back();
});

function getUserData() {
    userData = {
        id: localStorage.getItem('loggedUserId'),
        firstname: localStorage.getItem('loggedUserFirstname'),
        lastname: localStorage.getItem('loggedUserLastname'),
        email: localStorage.getItem('loggedUserEmail'),
        pass: localStorage.getItem('loggedUserPass')
    };
    $('#firstname').val(userData.firstname);
    $('#lastname').val(userData.lastname);
    $('#email').val(userData.email);
    $('#pass').val(userData.pass);
}

function getUserDataAfterChange() {
    $('#firstname').val(userData.firstname);
    $('#lastname').val(userData.lastname);
    $('#email').val(userData.email);
    $('#pass').val(userData.pass);
}

function showCorrectBtn() {
    var formClass = $('#profileForm').attr("class");
    if(formClass === 'is-readonly'){
        $('#saveBtn').hide();
        $('#backBtn').hide();
        $('#editBtn').show();
        if((localStorage.getItem('userRole') === 'seller') === true){
            $('#becomeCustomerBtn').show();
        }
        else{
            $('#becomeCustomerBtn').hide();
        }
    }
    else{
        $('#editBtn').hide();
        $('#backBtn').show();
        $('#saveBtn').show();
        $('#becomeCustomerBtn').hide();
    }

}

function editUserData() {
    $('.js-edit').on('click', function() {
        var $form = $(this).closest('form');
        $form.toggleClass('is-readonly is-editing');
        var isReadonly = $form.hasClass('is-readonly');
        $form.find('input,textarea').prop('disabled', isReadonly);
        showCorrectBtn();
    });
}

function back() {
    $('.js-back').on('click', function() {
        var $form = $(this).closest('form');
        $form.toggleClass('is-readonly is-editing');
        var isReadonly = $form.hasClass('is-readonly');
        $form.find('input,textarea').prop('disabled', isReadonly);
        showCorrectBtn();
        getUserDataAfterChange();
    });
}

function saveUserData(){
    $('.js-save').on('click', function() {
        if(localStorage.getItem('loggedUserFirstname') === $('#firstname').val() &&
            localStorage.getItem('loggedUserLastname') === $('#lastname').val() &&
            localStorage.getItem('loggedUserEmail') === $('#email').val() &&
            localStorage.getItem('loggedUserPass') === $('#pass').val()){
            console.log('dane są identyczne');
        }
        else{
            console.log('dane różnią się aktualizuję dane');
            userData = {
                id: localStorage.getItem('loggedUserId'),
                firstname: $('#firstname').val(),
                lastname: $('#lastname').val(),
                email: $('#email').val(),
                pass: $('#pass').val()
            };
            var user = setRole();
            console.log("id zalogowanego usera to: ", localStorage.getItem('loggedUserId'));
            console.log("userData to: ", userData);
            $.ajax({
                url: "http://localhost:8080" + '/' + user + '/changeDataAppUser',
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(userData),
                success: function (res) {
                    alert(res.responseText);
                },
                error: function (res) {
                    alert(res.responseText);
                }
            })
        }
        var $form = $(this).closest('form');
        $form.toggleClass('is-editing is-readonly');
        $form.find('input,textarea').prop('disabled', true);
        showCorrectBtn();
    });
}
