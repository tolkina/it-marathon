'use strict';
angular.module('javaApp').service('textService', function ($http, $q, urls) {
    this.analyzeText = function(file){
        var deferred = $q.defer();
        var fd = new FormData();
        fd.append('file', file);
        $http.post(urls.ANALYZE_TEXT_API, fd, {
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    };
    this.checkBrackets = function(file){
        var deferred = $q.defer();
        var fd = new FormData();
        fd.append('file', file);
        $http.post(urls.CHECK_BRACKETS_API, fd, {
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    };
});