$(document).ready(function () {
    showScoreOfCalculation();
})

function showScoreOfCalculation() {
    var score = $('.score');
    score = parseInt(score.text());
    var scorePanel = $('.scorePanel');

    if(score>=5){
        scorePanel.css("display", "block");
    }
}

