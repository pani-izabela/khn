$(document).ready(function () {
    showScoreOfCalculation();
})

function showScoreOfCalculation() {
    var score = $('.score');
    score = parseInt(score.text());
    var scorePanel = $('.scorePanel');
    var scorePanelNoPossibility = $('.scorePanelNoPossibility');

    if(score>=5){
        scorePanel.css("display", "block");
    }

    else if(score<5 && score>0){
        scorePanelNoPossibility.css("display", "block");
    }
}

