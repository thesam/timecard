angular.module("mainApp",[]).controller("MainController", function($scope) {
	$scope.days = [
        {name: "Monday"},
        {name: "Tuesday"},
        {name: "Wednesday"},
        {name: "Thursday"},
        {name: "Friday"},
        {name: "Saturday"},
        {name: "Sunday"}
    ];
});