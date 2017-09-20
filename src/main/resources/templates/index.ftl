<!DOCTYPE html>
<html lang="en" ng-app="javaApp">
<head>
    <meta charset="UTF-8">
    <title>Text Analyzer</title>
    <link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container" ng-controller="TextCtrl as ctrl">
    <input type="file" accept="text/*" file-model="file"/>
    <div>
        <a class="btn btn-success" ng-click="ctrl.analyzeText()">Analyze</a>
    </div>
    <div class="container" ng-show="ctrl.words != undefined">
        <h2>{{ctrl.count}} top words</h2>
        <p ng-repeat="word in ctrl.words">
        <p>{{word}}</p>
    </div>
</div>
<!-- SCRIPTS-->
<script src="js/lib/angular.min.js"></script>
<script src="js/lib/ngStorage.min.js"></script>
<script src="js/lib/angular-ui-router.min.js"></script>

<script src="js/app.js"></script>
<script src="js/textService.js"></script>
<script src="js/TextCtrl.js"></script>
<script src="js/appDirective.js"></script>
</body>
</html>