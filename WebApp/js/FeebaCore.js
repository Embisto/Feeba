function slideshow($scope) {
    $scope.questions = [{
        content: "Wie viel Beine haben Ameisen?",
        answers: ["8","10","12","6"],
        type:"radio"
    }, {
        content: "Haben Sie bereits programmiert?",
        answers: ["Ja" , "Nein"],
        type:"radio"

    }, {
        content: "Welche Farbe hat die Sonne?",
        answers: ["Rot" , "Orange","Blau","Gelb"],
        type:"radio"
    }, {
        content: "In welchem Semester studieren Sie?",
        answers: ["1","2","3","4","5","6","7"],
        type:"radio"

    }, {
            content: "Welche Sprachen beherrschen Sie?",
            answers: ["Java","C","C++","C#","Objective C","Javascript","Andere"],
            type:"checkbox"
    }, {
        content: "Wie geht es Ihnen?",
        answers: [""],
        type:"text"

    }, {
        content: "Ene, mene, ... ?",
        answers: ["Kiste" , "Miste"],
        type:"radio"
    }];

    $scope.questIndex = 0;
    $scope.nextClick = function () {


        if ($scope.questIndex < $scope.questions.length - 1) {

            $scope.hideQuestion();
            $scope.questIndex = $scope.questIndex + 1;
            Bars.redraw();
            //setTimeout(function() {Bars.reset();},2000);
            $scope.isEndOfArray();
            $scope.animateQuestion();

        }


    };

    $scope.getLetterIndex = function(index,type) {

        if(type =="text") {return;}

        switch(index) {
            case 0:
                return "A: ";
            case 1:
                return "B: ";
            case 2:
                return "C: ";
            case 3:
                return "D: ";
            case 4:
                return "E: ";
            case 5:
                return "F: ";
            case 6:
                return "G: ";
            case 7:
                return "H: ";

        }
    };

    $scope.isEndOfArray = function() {
        if($scope.questIndex == 0) {

            $('#arrowLeft').css({cursor:'default',
                                 opacity: 0})

    }
        else {
            $('#arrowLeft').css({cursor:'pointer',
                                 opacity: 1})

    }

        if($scope.questIndex == $scope.questions.length - 1) {

            $('#arrowRight').css({cursor:'default',
                                 opacity: 0}); }
        else {

            $('#arrowRight').css({cursor:'pointer',
                                  opacity: 1}); }


    };

    $scope.previousClick = function () {


        if ($scope.questIndex > 0) {
            $scope.hideQuestion();
            $scope.questIndex = $scope.questIndex - 1;
            Bars.redraw();
            //setTimeout(function() {Bars.reset();},2000);
            $scope.isEndOfArray();
            $scope.animateQuestion();


        }


    };

    $scope.hideQuestion = function() {

        $('#question').css({ opacity: 0})

    };

    $scope.animateQuestion = function() {

        $('#question').animate({
            opacity: 1
        }, 1000, function() {
            // Animation complete.
        });

    };
}