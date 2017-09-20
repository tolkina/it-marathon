<!DOCTYPE html>
<html lang="en" ng-app="javaApp">
<head>
    <meta charset="UTF-8">
    <title>Java app</title>
    <link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container" ng-controller="TextCtrl as ctrl">
    <input type="file" accept=".txt" file-model="file"/>
    <div>
        <a class="btn btn-success" ng-click="ctrl.analyzeText()">Analyze</a>
        <a class="btn btn-success" ng-click="ctrl.checkBrackets()">Verify Brackets</a>
    </div>
    <div class="container" ng-show="ctrl.words != undefined">
        <h2>{{ctrl.words.length}} top words</h2>
        <ul class="list-group">
            <li class="list-group-item"
                ng-repeat="word in ctrl.words">
                <p>{{word}}</p>
            </li>
        </ul>
    </div>
    <div class="container" ng-show="ctrl.ans != undefined">
        <h2>{{ctrl.ans}}</h2>
    </div>
</div>
<!-- SCRIPTS-->
<script src="js/lib/angular.min.js"></script>
<script src="js/lib/ngStorage.min.js"></script>
<script src="js/lib/angular-ui-router.min.js"></script>

<script src="js/app.js"></script>
<script src="js/textService.js"></script>
<script src="js/TextCtrl.js"></script>
<script src="js/fileDirective.js"></script>
</body>
</html>