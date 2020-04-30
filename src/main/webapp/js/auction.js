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
    var property_type = "house";
    $.get("http://localhost:8080" + "/getAssets?" + $.param({assetType: property_type}), function (data) {
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
                        if((localStorage.getItem('userRole') === 'customer') === true || (localStorage.getItem('userRole') === 'customer+seller') === true) {
                            //return '<button id="houseBuyBtn" type="button" class="btn btn-success deleteBtn" style="display: block">Kup</button>';
                            return '<button onclick="buyProperty('+ localStorage.getItem('loggedUserId') +','+ houseId +','+ "\'house\'" +')" id="houseBuyBtn" type="button" class="btn btn-success deleteBtn" style="display: block">Kup</button>';
                        }
                        else {
                            return '<button id="houseBuyBtn" type="button" class="btn btn-success deleteBtn" style="display: none">Kup</button>';
                        }
                    }
                },
            ]
        });
    })
}

function getListOfFlats() {
    var property_type = "flat";
    $.get("http://localhost:8080" + "/getAssets?" + $.param({assetType: property_type}), function (data) {
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
                        let flatId = full.id;
                        if((localStorage.getItem('userRole') === 'customer') === true || (localStorage.getItem('userRole') === 'customer+seller') === true) {
                            //return '<button id="flatBuyBtn" type="button" class="btn btn-success deleteBtn" style="display: block">Kup</button>'
                            return '<button onclick="buyProperty('+ localStorage.getItem('loggedUserId') +','+ flatId +','+ "\'flat\'" +')" id="flatBuyBtn" type="button" class="btn btn-success deleteBtn" style="display: block">Kup</button>'
                        }
                        else{
                            return '<button id="flatBuyBtn" type="button" class="btn btn-success deleteBtn" style="display: none">Kup</button>'
                        }
                    }
                },
            ]
        });
    })
}

function getListOfPlots() {
    var property_type = "plot";
    $.get("http://localhost:8080" + "/getAssets?" + $.param({assetType: property_type}), function (data) {
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
                        let plotId = full.id;
                        if((localStorage.getItem('userRole') === 'customer') === true || (localStorage.getItem('userRole') === 'customer+seller') === true) {
                            //return '<button id="plotBuyBtn" type="button" class="btn btn-success buyBtn" style="display: block">Kup</button>';
                            return '<button onclick="buyProperty('+ localStorage.getItem('loggedUserId') +','+ plotId +','+ "\'plot\'" +')" id="plotBuyBtn" type="button" class="btn btn-success buyBtn" style="display: block">Kup</button>';
                        }
                        else {
                            return '<button id="plotBuyBtn" type="button" class="btn btn-success buyBtn" style="display: none">Kup</button>';
                        }
                    }
                },
            ]
        });
    })
}

function buyProperty(user_id, property_id, property_type) {
    if(confirm("Jesteś pewny/a, że chcesz kupić tę nieruchomość?")) {
        console.log('dupa');
    }
}


