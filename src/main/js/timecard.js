angular.module("mainApp", []).controller("MainController", function ($scope) {
    $scope.now = new Date();
    $scope.days = [
        {name: "Monday"},
        {name: "Tuesday"},
        {name: "Wednesday"},
        {name: "Thursday"},
        {name: "Friday"},
        {name: "Saturday"},
        {name: "Sunday"}
    ];

    $scope.dayIndex = function() {
        var day = ($scope.now.getDay() + 6) % 7;
        return day;
    };

    for (var i = 0; i < 7; i++) {
        var currentDay = $scope.dayIndex();
        var diff = i - currentDay;
        $scope.days[i].date = new Date($scope.now.getTime() + diff*(60*60*24*1000));
    }

    $scope.prevWeek = function() {}

    $scope.nextWeek = function() {}


    $scope.newEntry = function (day) {
        if (!day.entries) {
            day.entries = [];
        }
        day.entries.push({start: "9:00", stop: "17:00"});
    };
    $scope.sum = function (entries) {
        if (!entries) {
            return "0:00";
        }
        var sumMin = entries.map(function (entry) {
            var start = new Date("2015-04-03 " + entry.start);
            var stop = new Date("2015-04-03 " + entry.stop);
            var diffMs = stop - start;
            var diffMin = diffMs / (1000 * 60);
            return diffMin;
        }).reduce(function (sum, elem) {
            return sum + elem;
        }, 0);

        var hours = sumMin / 60;
        var minutes = sumMin % 60;
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        return hours + ":" + minutes;
    }
    $scope.deleteEntry = function (day, index) {
        day.entries.splice(index, 1);
    }
});
