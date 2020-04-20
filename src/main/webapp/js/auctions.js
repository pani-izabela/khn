$(document).ready(function () {
    showTables();
    getHousesList();
});

function showTables(){
    $(".dropdown-menu a").click(function () {
        var selTable = $(this).attr('data-value');
        $(".tables").show();
        if(selTable == 1){
            $("#auctionsListHouse").show();
            $("#auctionsListFlat").hide();
            $("#auctionsListPlot").hide();
        }
        else if(selTable == 2){
            $("#auctionsListFlat").css("display", "block");
            $("#auctionsListHouse").css("display", "none");
            $("#auctionsListPlot").css("display", "none");
        }
        else if(selTable == 3){
            $("#auctionsListHouse").css("display", "none");
            $("#auctionsListFlat").css("display", "none");
            $("#auctionsListPlot").css("display", "block");
        }
    })
}

function getHousesList() {
    $.get("http://localhost:8080" + "/getAllHouses", function (data) {
        $('#auctionsListHouse').DataTable({
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
