function slideshow($scope, $http) {

    $http.get('../ServerData/survey.feeba')
        .then(function(res){
            $scope.questions = res.data.questions;
            $scope.welcomeMessage = res.data.welcomeMessage;
        });

    $scope.questIndex = 0;

    $scope.sendAnswer = function($http,data) {

     var data = $scope.getAnswer();

     $http.post('/', data);

    };

    $scope.getAnswer = function() {

      var answer = {};
      answer.question = $scope.questIndex;
      var values = [];
      $.each($('input').serializeArray(), function(i, field) {
        values.push(field.value);

});

      answer.answers = values;

      return answer;

    }


    $scope.nextClick = function () {


        if ($scope.questIndex < $scope.questions.length) {
            $scope.sendAnswer($http);
            $scope.hideQuestion();
            $scope.questIndex = $scope.questIndex + 1;
            Bars.redraw();
            $scope.isEndOfArray();
            $scope.animateQuestion();

        }

        if($scope.questIndex == $scope.questions.length ) {

          $("#questionCount").text("");
          $("#questionContent").text("Die Beantwortung des Fragebogens ist nun zu Ende! Vielen Dank fuer Ihre Teilnahme!");
          Bars.redraw();

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

        if($scope.questIndex == $scope.questions.length) {

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