var isCustomer, isCustomerSeller;
var selTable;
$(document).ready(function () {
    getUserRole();
    selectTable();
});

function getUserRole() {
    isCustomer = (localStorage.getItem('userRole') === 'customer');
    isCustomerSeller = (localStorage.getItem('userRole') === 'customer+seller');
}

function selectTable() {
    $(".dropdown-menu a").click(function () {
        selTable = $(this).attr('data-value');
        console.log("wartość selTable po showTables() to: ", selTable);
        $(".tables").show();
        showTables();
    })
}

function showTables() {
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
                {data: "asset_type"},
                {data: "price"},
                {data: "size"},
                {data: "rooms"},
                {data: "city"},
                {
                    data: "akcja",
                    "render": function (data, type, full) {
                        let houseId = full.asset_id;
                        let auctionViewId = full.id;
                        if(isCustomer === true || isCustomerSeller === true){
                            return '<button onclick="buyHouse('+ localStorage.getItem('loggedUserId') +','+ houseId +','+ "\'house\'" +','+ auctionViewId +')" id="houseBuyBtn" type="button" class="btn btn-success deleteBtn" style="display: block">Kup</button>';
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
                {data: "asset_type"},
                {data: "price"},
                {data: "size"},
                {data: "rooms"},
                {data: "floor"},
                {data: "city"},
                {
                    data: "akcja",
                    "render": function (data, type, full) {
                        let flatId = full.asset_id;
                        let auctionViewId = full.id;
                        if(isCustomer === true || isCustomerSeller === true){
                            return '<button onclick="buyFlat('+ localStorage.getItem('loggedUserId') +','+ flatId +','+ "\'flat\'" +','+ auctionViewId +')" id="flatBuyBtn" type="button" class="btn btn-success deleteBtn" style="display: block">Kup</button>'
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
                {data: "asset_type"},
                {data: "price"},
                {data: "size"},
                {data: "plot_type"},
                {data: "city"},
                {
                    data: "akcja",
                    "render": function (data, type, full) {
                        let plotId = full.asset_id;
                        let auctionViewId = full.id;
                        if(isCustomer === true || isCustomerSeller === true){
                            return '<button onclick="buyPlot('+ localStorage.getItem('loggedUserId') +','+ plotId +','+ "\'plot\'" +','+ auctionViewId +')" id="plotBuyBtn" type="button" class="btn btn-success buyBtn" style="display: block">Kup</button>';
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

function buyFlat(user_id, property_id, property_type) {
    if(confirm("Jesteś pewny/a, że chcesz kupić to mieszkanie?")) {
        $.ajax({
            url: "http://localhost:8080/buyFlat?" + $.param({appuserid: user_id}) + "&" + $.param({assetsId: property_id}) + "&" + $.param({assetsType: property_type}),
            method: "POST",
            success: function (res) {
                console.log("Udało się kupić mieszkanie");
                alert(res);
                window.location.href = "myProperties"
            },
            error: function () {
                console.log("Nie udało się kupić");
            }
        });
    }
}

function buyHouse(user_id, property_id, property_type) {
    if(confirm("Jesteś pewny/a, że chcesz kupić ten dom?")) {
        $.ajax({
            url: "http://localhost:8080/buyHouse?" + $.param({appuserid: user_id}) + "&" + $.param({assetsId: property_id}) + "&" + $.param({assetsType: property_type}),
            method: "POST",
            success: function (res) {
                console.log("Udało się kupić dom");
                alert(res);
                window.location.href = "myProperties"
            },
            error: function () {
                console.log("Nie udało się kupić");
            }
        });
    }
}

function buyPlot(user_id, property_id, property_type) {
    if(confirm("Jesteś pewny/a, że chcesz kupić tę działkę?")) {
        $.ajax({
            url: "http://localhost:8080/buyPlot?" + $.param({appuserid: user_id}) + "&" + $.param({assetsId: property_id}) + "&" + $.param({assetsType: property_type}),
            method: "POST",
            success: function (res) {
                console.log("Udało się kupić działkę");
                alert(res);
                window.location.href = "myProperties"
            },
            error: function () {
                console.log("Nie udało się kupić");
            }
        });
    }
}



