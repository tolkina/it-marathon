'use strict';
var app = angular.module('javaApp', ['ui.router', 'ngStorage']);
app.constant('urls', {
    CHECK_BRACKETS_API: '/api/brackets/',
    ANALYZE_TEXT_API: '/api/repeated-words/'
});
app.config(function ($urlRouterProvider) {
        $urlRouterProvider.otherwise('/');
    }
);
