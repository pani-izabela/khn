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
        console.log("dataFlat przekazane na backend ", flatData);
        console.log("adres przekazane na backend ", flatAddress);
        addFlat(flatAddress, flatData);
    });
}

function addFlat(address, flat) {
    console.log('Przekazany do addFlat adres: ', address);
    console.log('Przekazany do addFlat flat: ', flat);
    var obj = {address, flat};
    $.ajax({
        url: "http://localhost:8080/seller/addFlat",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(obj),
        success: function (res) {
            console.log('Id mieszkania dodanego do bazy: ', res.id);
            window.location.href = "addProperty";
            console.log('Mieszkanie zostało dodane');
        },
        error: function (res) {
            alert('Nie udało się dodać mieszkania');
        }
    })
}
