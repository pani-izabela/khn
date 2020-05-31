var selForm;
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


