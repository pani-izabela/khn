var selForm;
$(document).ready(function () {
    selectForm();
    prepareFlat();
});

function selectForm() {
    $(".dropdown-menu a").click(function () {
        selForm = $(this).attr('data-value');
        $(".forms").show();
        showForms();
    })
}

function showForms() {
    if(selForm == 1){
        $("#formFlat").hide();
        $("#formPlot").hide();
        $("#formHouse").show();
    }
    else if(selForm == 2){
        $("#formHouse").hide();
        $("#formPlot").hide();
        $("#formFlat").show();
    }
    else if(selForm == 3){
        $("#formHouse").hide();
        $("#formFlat").hide();
        $("#formPlot").show();
    }
}

function prepareFlat() {
    $('#addFlatBtn').click(function () {
        let city = $('#cityF').val();
        let street = $('#streetF').val();
        let house_number = $('#houseNoF').val();
        let flat_number = $('#flatNoF').val();
        let postcode = $('#postcodeF').val();
        let price = $('#priceF').val();
        let area = $('#areaF').val();
        let rooms_no = $('#roomsNoF').val();
        let floor = $('#floorF').val();
        let propertyData;
        if (city.trim().length === 0 || street.trim().length === 0 || house_number.trim().length === 0 ||
            flat_number.trim().length === 0 || postcode.trim().length === 0 || price.trim().length === 0
            || area.trim().length === 0 || rooms_no.trim().length === 0 || floor.trim().length === 0)
            alert('Uzupełnij brakujące pola');
        else {
            propertyData = {
                city: city,
                street: street,
                homeNumber: house_number,
                localNumber: flat_number,
                postcode: postcode,
                price: price,
                size: area,
                rooms: rooms_no,
                floor: floor
            }
        }
        console.log("oooo", propertyData);
    });
}

function addFlat(data) {
    var user = setRole();
    $.ajax({
        url: "http://localhost:8080/seller/addFlat",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (res) {
            window.location.href = "login";
        },
        error: function (res) {
            alert('Nie udało się zarejestrować');
        }
    })
}




