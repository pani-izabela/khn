$(document).ready(function () {
    //clearCalculator();
})

function clearCalculator() {
    $('#creditCalculator\\:numberOfLoanInstallment').val(0);
    $('#creditCalculator\\:amountOfCredit').val(0.0);
    $('#creditCalculator\\:monthlyIncome').val(0.0);
    $('#creditCalculator\\:monthlyExpenses').val(0.0);
    $('.scorePanel').hide();
    $('.scorePanelNoPossibility').hide();
}


