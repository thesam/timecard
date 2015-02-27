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
    $scope.newEntry = function(day) {
        if (!day.entries) {
            day.entries = [];
        }
        day.entries.push({start: "9:00", stop: "17:00"});
    }
});