$(document).ready(function () {
    getUsersList();
});

function getUsersList() {
    $.get("http://localhost:8080" + "/getAppUsers", function (data) {
        $('#tableUsersList').DataTable({
            data: data,
            paging: true,
            searching: true,
            destroy: true,
            autoWidth: true,
            columns: [
                {data: "id"},
                {data: "firstname"},
                {data: "lastname"},
                {data: "email"},
                {
                    data: "akcja",
                    "render": function (data, type, full) {
                        let userId = full.id;
                        return '<button onclick="deleteUser(' + userId + ')" type="button" class="btn btn-success deleteBtn">Usuń</button>'
                    }
                },
            ]
        });
    })
}

function deleteUser(user_Id) {
    if(confirm("Jesteś pewny/a, że chcesz usunąć użytkownika?")) {
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/deleteAppUser?" + $.param({id: user_Id}) + "&" + $.param({loggedUserId: localStorage.getItem('loggedUserId')}),
            data: JSON.stringify(user_Id),
            contentType: "application/json",
            success: function () {
                location.reload();
                alert('Udało się usunąć użytkownika');
            },
            error: function () {
                console.log("Nie udało się usunąć użytkownika");
            }
        });
    }
}

/*function deleteUser(id) {
    var user_Id = id;
    var loggedUserId = localStorage.getItem('loggedUserId');

    $.ajax({
        url: "http://localhost:8080" + "/deleteAppUser?" + $.param({id: user_Id}) + "&" + $.param({loggedUserId: loggedUserId}),
        method: 'DELETE',
        success: function () {
            getUsersList();
        },
        error: function () {
            alert('Nie udało się usunąć użytkownika o id ' + user_Id + '.')
        }
    });
}*/


