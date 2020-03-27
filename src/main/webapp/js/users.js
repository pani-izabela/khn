$(document).ready(function () {
    getUsersList();
});

function getUsersList() {
    $('#button').on("click", function (e) {
        e.preventDefault();

        $.get("http://localhost:8080" + "/getAppUsers", function (data) {
            $('#tableUsersList > tbody').empty();
            var users_data = '';
            $.each(data, function (key, value) {
                users_data += '<tr>';
                users_data += '<td id="userId' + key + '">' + value.id + '</td>';
                users_data += '<td>' + value.firstname + '</td>';
                users_data += '<td>' + value.lastname + '</td>';
                users_data += '<td>' + value.email + '</td>';
                users_data += '<td><button id="' + value.id + '" type="button" class="btn btn-success deleteBtn">Usuń</button></td>';
                users_data += '</tr>';
            });
            $('#tableUsersList > tbody').append(users_data);
            deleteUser(users_data);
        })
    });
}

function deleteUser() {

    $('.deleteBtn').click(function () {
        var buttonId = $(this).attr('id');
        var user_Id = buttonId;
        var row = $(this);
        if(user_Id != localStorage.getItem('loggedUserId')) {
            $.ajax({
                url: "http://localhost:8080" + "/deleteAppUser?" + $.param({id: user_Id}),
                method: 'DELETE',
                success: function () {
                    row.closest("tr").remove();
                },
                error: function () {
                    alert('Nie udało się usunąć użytkownika o id ' + user_Id + ' , spróbuj ponownie.')
                }
            });
        }
        else{
            alert('Nie możesz usunąć siebie!')
        }
    })
}