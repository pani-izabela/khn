$(document).ready(function () {
    showTables();
});

function showTables(){
    $(".dropdown-menu a").click(function () {
        var selTable = $(this).attr('data-value');
        console.log("wartość selTable po showTables() to: ", selTable);
        $(".tables").show();
        if(selTable == 1){
            getListOfHauses();
            $("#auctionsListFlat").hide();
            $("#auctionsListFlat_wrapper").hide();
            $("#auctionsListPlot").hide();
            $("#auctionsListPlot_wrapper").hide();
            $("#auctionsListHouse").show();
        }
        else if(selTable == 2){
            getListOfFlats();
            $("#auctionsListHouse").hide();
            $("#auctionsListHouse_wrapper").hide();
            $("#auctionsListPlot").hide();
            $("#auctionsListPlot_wrapper").hide();
            $("#auctionsListFlat").show();
        }
        else if(selTable == 3){
            getListOfPlots();
            $("#auctionsListHouse").hide();
            $("#auctionsListHouse_wrapper").hide();
            $("#auctionsListFlat").hide();
            $("#auctionsListFlat_wrapper").hide();
            $("#auctionsListPlot").show();
        }
    });
}

function getListOfHauses() {
    var type = "house";
    $.get("http://localhost:8080" + "/getAssets?" + $.param({assetType: type}), function (data) {
        $('#auctionsListHouse').DataTable({
            data: data,
            paging: true,
            searching: true,
            destroy: true,
            autoWidth: true,
            columns: [
                {data: "id"},
                {data: "price"},
                {data: "size"},
                {data: "rooms"},
                {data: "city"},
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

function getListOfFlats() {
    var type = "flat";
    $.get("http://localhost:8080" + "/getAssets?" + $.param({assetType: type}), function (data) {
        $('#auctionsListFlat').DataTable({
            data: data,
            paging: true,
            searching: true,
            destroy: true,
            autoWidth: true,
            columns: [
                {data: "id"},
                {data: "price"},
                {data: "size"},
                {data: "rooms"},
                {data: "floor"},
                {data: "city"},
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

function getListOfPlots() {
    var type = "plot";
    $.get("http://localhost:8080" + "/getAssets?" + $.param({assetType: type}), function (data) {
        $('#auctionsListPlot').DataTable({
            data: data,
            paging: true,
            searching: true,
            destroy: true,
            autoWidth: true,
            columns: [
                {data: "id"},
                {data: "price"},
                {data: "size"},
                {data: "plot_type"},
                {data: "city"},
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

function getHousesList() {
    $.get("http://localhost:8080" + "/getAllHousesView", function (data) {
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
                {data: "city"},
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

