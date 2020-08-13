$(document).ready(function () {
    showScoreOfCalculation3();
    //clearCalculator();
    //hideForm();
})

function showScoreOfCalculation3() {
    var score = $('#results\\:possibleLoanAmount');
    score = parseInt(score.text());

    if(score>0){
        $('.scorePanel').show();
        $('.scorePanelNoPossibility').hide();
    }
    else if(score===0){
        $('.scorePanelNoPossibility').hide();
        $('.scorePanel').hide();
    }
    else if(score<0){
        $('.scorePanelNoPossibility').show();
        $('.scorePanel').hide();
    }
}

/*function hideForm() {
    $('.scorePanelNoPossibility').hide();
    $('.scorePanel').hide();
}*/

/*function showScoreOfCalculation2() {
    var score = $('.score');
    score = parseInt(score.text());

    if(score===1){
        $('.scorePanel').show();
        $('.scorePanelNoPossibility').hide();
    }

    else if(score===0){
        $('.scorePanelNoPossibility').show();
        $('.scorePanel').hide();
    }
    else{
        $('.scorePanelNoPossibility').hide();
        $('.scorePanel').hide();
    }
}*/

/*function showScoreOfCalculation() {
    var score = $('.score');
    score = parseInt(score.text());
    var scorePanel = $('.scorePanel');
    var scorePanelNoPossibility = $('.scorePanelNoPossibility');

    if(score===1){
        scorePanel.css("display", "block");
    }

    else if(score===0){
        scorePanelNoPossibility.css("display", "block");
    }
}*/

function clearCalculator() {
    //var form = $('.calculator');
    $('#creditCalculator\\:numberOfLoanInstallment').val(0);
    $('#creditCalculator\\:amountOfCredit').val(0.0);
    $('#creditCalculator\\:monthlyIncome').val(0.0);
    $('#creditCalculator\\:monthlyExpenses').val(0.0);

}


