var selForm;
var loggedAppUser;
var cityF, streetF, house_numberF, flat_numberF, postcodeF, priceF, areaF, rooms_noF, floorF;
var cityH, streetH, house_numberH, postcodeH, priceH, areaH, rooms_noH;
var cityP, streetP, house_numberP, postcodeP, priceP, areaP, typeP;
var flatAddressId, houseAddressId, plotAddressId;
var flatId, houseId, plotId;
let flatData, houseData, plotData;
let flatAddress, houseAddress, plotAddress;
$(document).ready(function () {
    selectForm();
    getLoggedAppUser();
});

function selectForm() {
    $(".dropdown-menu a").click(function () {
        selForm = $(this).attr('data-value');
        $(".forms").show();
        showForms();
    })
}

function getLoggedAppUser() {
   $.ajax({
      url: "http://localhost:8080/getAppUser?" + $.param({id: localStorage.getItem('loggedUserId')}),
      type: "GET",
      contentType: "application/json",
      success: function (res) {
          loggedAppUser = res;
      }
   });
}

function showForms() {
    if(selForm == 1){
        $("#formFlat").hide();
        $("#formPlot").hide();
        $("#formHouse").show();
        prepareHouse();
    }
    else if(selForm == 2){
        $("#formHouse").hide();
        $("#formPlot").hide();
        $("#formFlat").show();
        prepareFlat();
    }
    else if(selForm == 3){
        $("#formHouse").hide();
        $("#formFlat").hide();
        $("#formPlot").show();
        preparePlot();
    }
}

//---------------mieszkanie

function prepareFlat() {
    $('#addFlatBtn').click(function () {
        cityF = $('#cityF').val();
        streetF = $('#streetF').val();
        house_numberF = $('#houseNoF').val();
        flat_numberF = $('#flatNoF').val();
        postcodeF = $('#postcodeF').val();
        priceF = $('#priceF').val();
        areaF = $('#areaF').val();
        rooms_noF = $('#roomsNoF').val();
        floorF = $('#floorF').val();
        if (cityF.trim().length === 0 || streetF.trim().length === 0 || house_numberF.trim().length === 0 ||
            flat_numberF.trim().length === 0 || postcodeF.trim().length === 0 || priceF.trim().length === 0
            || areaF.trim().length === 0 || rooms_noF.trim().length === 0 || floorF.trim().length === 0)
            alert('Uzupełnij brakujące pola');
        else {
            flatAddress = {
                city: cityF,
                street: streetF,
                homeNumber: house_numberF,
                localNumber: flat_numberF,
                postcode: postcodeF,
                realAssetsId: 1
            };
            flatData = {
                price: priceF,
                size: areaF,
                rooms: rooms_noF,
                floor: floorF,
                appUser: loggedAppUser,
                address: null
            }
        }
        addFlat(flatAddress, flatData);
    });
}

function addFlat(address, flat) {
    var obj = {address, flat};
    $.ajax({
        url: "http://localhost:8080/seller/addFlat",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(obj),
        success: function (res) {
            alert('Mieszkanie zostało dodane');
            window.location.href = "addProperty";

        },
        error: function (res) {
            alert('Nie udało się dodać mieszkania');
            window.location.href = "addProperty";
        }
    })
}

//------------------------dom
function prepareHouse() {
    $('#addHouseBtn').click(function () {
        cityH = $('#cityH').val();
        streetH = $('#streetH').val();
        house_numberH = $('#houseNoH').val();
        postcodeH = $('#postcodeH').val();
        priceH = $('#priceH').val();
        areaH = $('#areaH').val();
        rooms_noH = $('#roomsNoH').val();
        if (cityH.trim().length === 0 || streetH.trim().length === 0 || house_numberH.trim().length === 0 ||
            postcodeH.trim().length === 0 || priceH.trim().length === 0
            || areaH.trim().length === 0 || rooms_noH.trim().length === 0)
            alert('Uzupełnij brakujące pola');
        else {
            houseAddress = {
                city: cityH,
                street: streetH,
                homeNumber: house_numberH,
                postcode: postcodeH,
                realAssetsId: 2
            };
            houseData = {
                price: priceH,
                size: areaH,
                rooms: rooms_noH,
                appUser: loggedAppUser,
                address: null
            }
        }
        addHouse(houseAddress, houseData);
    });
}


function addHouse(address, house) {
    var objHouse = {address, house};
    $.ajax({
        url: "http://localhost:8080/seller/addHouse",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(objHouse),
        success: function (res) {
            alert('Dom został dodany');
            window.location.href = "addProperty";

        },
        error: function (res) {
            alert('Nie udało się dodać domu');
            window.location.href = "addProperty";
        }
    })
}

//----------------------działka
function preparePlot() {
    $('#addPlotBtn').click(function () {
        cityP = $('#cityP').val();
        streetP = $('#streetP').val();
        house_numberP = $('#houseNoP').val();
        postcodeP = $('#postcodeP').val();
        priceP = $('#priceP').val();
        areaP = $('#areaP').val();
        typeP = $('#typeP').val();
        if (cityP.trim().length === 0 || streetP.trim().length === 0 || house_numberP.trim().length === 0 ||
            postcodeP.trim().length === 0 || priceP.trim().length === 0
            || areaP.trim().length === 0 || typeP.trim().length === 0)
            alert('Uzupełnij brakujące pola');
        else {
            plotAddress = {
                city: cityP,
                street: streetP,
                homeNumber: house_numberP,
                postcode: postcodeP,
                realAssetsId: 3
            };
            plotData = {
                price: priceP,
                size: areaP,
                type: typeP,
                appUser: loggedAppUser,
                address: null
            }
        }
        addPlot(plotAddress, plotData);
    });
}

function addPlot(address, plot) {
    var objPlot = {address, plot};
    $.ajax({
        url: "http://localhost:8080/seller/addPlot",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(objPlot),
        success: function (res) {
            alert('Działka została dodana');
            window.location.href = "addProperty";

        },
        error: function (res) {
            alert('Nie udało się dodać działki');
            window.location.href = "addProperty";
        }
    })
}