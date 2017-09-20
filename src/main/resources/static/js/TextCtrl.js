'use strict';
angular.module('javaApp').controller('TextCtrl',
    function ($scope, textService) {
        $scope.file = undefined;
        var self = this;
        self.checkBrackets = checkBrackets;
        self.analyzeText = analyzeText;
        function checkBrackets() {
            textService.checkBrackets($scope.file).then(
                function (response) {
                    self.ans = response.message;
                    self.words = undefined;
                },
                function (errResponse) {
                    self.error = errResponse.data.error;
                    self.message = '';
                }
            );
        }
        function analyzeText() {
            textService.analyzeText($scope.file).then(
                function (response) {
                    self.words = response.data;
                    self.ans = undefined;
                },
                function (errResponse) {
                    self.error = errResponse.data.error;
                    self.message = '';
                }
            );
        }
    }
);