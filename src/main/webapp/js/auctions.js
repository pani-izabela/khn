$(document).ready(function () {
    getHousesList();
});

function getHousesList() {
    $.get("http://localhost:8080" + "/getAllHouses", function (data) {
        $('#tableUsersList').DataTable({
            data: data,
            paging: true,
            searching: true,
            destroy: true,
            autoWidth: true,
            columns: [
                {data: "id"},
                {data: "size"},
                {data: "price"},
                {data: "rooms"},
                {
                    data: "akcja",
                    "render": function (data, type, full) {
                        let houseId = full.id;
                        //return '<button onclick="deleteUser(' + houseId + ')" type="button" class="btn btn-success deleteBtn">Kup</button>'
                        return '<button type="button" class="btn btn-success deleteBtn">Kup</button>'
                    }
                },
            ]
        });
    })
}