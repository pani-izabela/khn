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
        console.log("dataFlat", flatData);
        console.log("adres", flatAddress);
        addFlatAddress(flatAddress);
        //addFlat(flatData);
    });
}

function addFlatAddress(address) {
    $.ajax({
        url: "http://localhost:8080/seller/addFlatAddress",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(address),
        success: function (res) {
            flatAddressId = res.id;
            console.log("FlatAddressId: ", flatAddressId);
            flatData = {
                id: flatAddressId,
                price: priceF,
                size: areaF,
                rooms: rooms_noF,
                floor: floorF,
                appUser: loggedAppUser,
                address: flatAddress
            };
            console.log("Adres " + flatAddressId + "został dodany do bazy");
            addFlat(flatData);
        },
        error: function (res) {
            alert('Nie udało się dodać adresu do bazy');
        }
    })
}

function addFlat(flat) {
    console.log('Flat: ', flat);
    $.ajax({
        url: "http://localhost:8080/seller/addFlat",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(flat),
        success: function (res) {
            flatId = res.id;
            addUserRealAssets(flatId);
            window.location.href = "addProperty";
            console.log('FlatId: ', flatId);
            alert('Mieszkanie zostało dodane');
        },
        error: function (res) {
            alert('Nie udało się dodać mieszkania');
        }
    })
}

//---------------------------------dom

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
        console.log("dataHouse", houseData);
        console.log("adresHouse", houseAddress);
        addHouseAddress(houseAddress);
    });
}

function addHouseAddress(address) {
    $.ajax({
        url: "http://localhost:8080/seller/addHouseAddress",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(address),
        success: function (res) {
            if(res.length===1){
                var addressHouse = res[0];
                houseAddressId = res[0].id;
                console.log("HouseAddressId: ", houseAddressId);
                houseData = {
                    price: priceH,
                    size: areaH,
                    rooms: rooms_noH,
                    appUser: loggedAppUser,
                    address: addressHouse
                };
                console.log("Zaczynam dodawać dom");
                addHouse(houseData);
                console.log("Skończyłem dodwać dom");
            }
            else if(res.length===2){
                var addressHouse = res[0];
                houseAddressId = res[0].id;
                console.log("HouseAddressId: ", houseAddressId);
                houseData = {
                    price: priceH,
                    size: areaH,
                    rooms: rooms_noH,
                    appUser: loggedAppUser,
                    address: addressHouse
                };
                console.log("Zaczynam dodawać dom");
                addHouse(houseData);
                console.log("Skończyłem dodwać dom");
                //-------updatePlot
                plotAddressId = res[1].id;
                console.log("PlotAddressId: ", plotAddressId);
                updatePlot(plotAddressId);
            }
        },
        error: function (res) {
            alert('Dom o takim adresie istnieje już w bazie');
        }
    })
}

function addHouse(house) {
    $.ajax({
        url: "http://localhost:8080/seller/addHouse",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(house),
        success: function (res) {
            console.log("Funkcja addHouse zwraca: ", res);
            houseData = {
                id: res.id,
                price: res.price,
                size: res.size,
                rooms: res.homeNumber,
                appUser: loggedAppUser,
                address: res.address
            };
            localStorage.setItem('newHouseId', res.id);
            console.log('Dom o id: ' + res.id + ' został dodany');
        },
        error: function (res) {
            alert('Nie udało się dodać domu');
        }
    })
}

//--------------------------działka

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
        console.log("dataPlot", plotData);
        console.log("adresPlot", plotAddress);
        addPlotAddress(plotAddress);
    });
}
function addPlotAddress(address) {
    $.ajax({
        url: "http://localhost:8080/seller/addPlotAddress",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(address),
        success: function (res) {
            if(res.length===1){
                plotAddressId = res[0].id;
                console.log("PlotAddressId: ", plotAddressId);
                plotData = {
                    id: plotAddressId,
                    price: priceP,
                    size: areaP,
                    type: typeP,
                    appUser: loggedAppUser,
                    address: plotAddress
                };
                console.log("Adres " + plotAddressId + "został dodany do bazy");
                addPlot(plotData);
            }
            /*if(res.length===2){
                plotAddressId = res[0].id;
                plotAddressId = res[1].id;
                console.log("HouseAddressId: ", houseAddressId);
                houseData = {
                    price: priceH,
                    size: areaH,
                    rooms: rooms_noH,
                    appUser: loggedAppUser,
                    address: houseAddressId
                };
                console.log("Adres " + houseAddress + "został dodany do bazy");
                addHouse(houseData);
                plotData = {
                    price: priceP,
                    size: areaP,
                    type: typeP,
                    appUser: localStorage.getItem('loggedUserId'),
                    address: plotAddressId
                };
                console.log("Adres " + plotAddress + "został dodany do bazy");
                updatePlot(plotData, houseData);
            }*/
        },
        error: function (res) {
            alert('Nie udało się dodać adresu działki do bazy');
        }
    })
}

function addPlot(plot) {
    $.ajax({
        url: "http://localhost:8080/seller/addPlot",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(plot),
        success: function (res) {
            plotId = res.id;
            addUserRealAssetsPlot(plotId);
            window.location.href = "addProperty";
            alert('Działka została dodana');
        },
        error: function (res) {
            alert('Nie udało się dodać działki');
        }
    })
}

function updatePlot(plotAddressId) {
    var plotAddress = plotAddressId;
    var houseAddress = localStorage.getItem('newHouseId');
    $.ajax({
        url: "http://localhost:8080/seller/updatePlot?" + $.param({plotAddressId: plotAddress}) + "&" + $.param({houseId: houseAddress}),
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        //data: JSON.stringify(plot, house),
        success: function (res) {
            //alert('Dane działki zostały zaktualizowane');
            consol.log('Dane działki zostały zaktualizowane');
        },
        error: function (res) {
            alert('Nie udało się zaktualizować danych działki');
        }
    })
}

//----------------------------------------------


function addUserRealAssets(assetId) {
    console.log('AssetId: ', assetId);
    $.ajax({
        url: "http://localhost:8080/seller/addProperty?" + $.param({appUserId: localStorage.getItem('loggedUserId')}) + "&" + $.param({assetId: assetId}),
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(assetId),
        success: function (res) {
            console.log('Nieruchomość o id: ' + res.id + ' zapisano w userRealAssets');
        },
        error: function (res) {
            alert('Nie udało się zapisać nieruchomości w userRealAssets');
        }
    })
}

function addUserRealAssetsHouse(assetId) {
    console.log('AssetId: ', assetId);
    $.ajax({
        url: "http://localhost:8080/seller/addPropertyHouse?" + $.param({appUserId: localStorage.getItem('loggedUserId')}) + "&" + $.param({assetId: assetId}),
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(assetId),
        success: function (res) {
            console.log('Nieruchomość o id: ' + res.id + ' zapisano w userRealAssets');
        },
        error: function (res) {
            alert('Nie udało się zapisać nieruchomości w userRealAssets');
        }
    })
}

function addUserRealAssetsPlot(assetId) {
    console.log('AssetId: ', assetId);
    $.ajax({
        url: "http://localhost:8080/seller/addPropertyPlot?" + $.param({appUserId: localStorage.getItem('loggedUserId')}) + "&" + $.param({assetId: assetId}),
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(assetId),
        success: function (res) {
            console.log('Nieruchomość o id: ' + res.id + ' zapisano w userRealAssets');
        },
        error: function (res) {
            alert('Nie udało się zapisać nieruchomości w userRealAssets');
        }
    })
}

function editProperty(assetToUpdate) {
    $.ajax({
        url: "http://localhost:8080/seller/updateProperty",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(assetToUpdate),
        success: function (res) {
            console.log('Nieruchomość o id: ' + res.id + ' została edytowana w userRealAssets');
        },
        error: function (res) {
            alert('Nie udało się zapisać nieruchomości w userRealAssets');
        }
    })
}




