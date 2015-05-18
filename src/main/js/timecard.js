angular.module("mainApp", ['ngResource']).controller("MainController", function ($scope, $resource) {
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
    var offset = 0;

    $scope.dayIndex = function () {
        var day = ($scope.now.getDay() + 6) % 7;
        return day;
    };

    $scope.updateWeek = function () {
        for (var i = 0; i < 7; i++) {
            var currentDay = $scope.dayIndex();
            var diff = i - currentDay - offset;
            $scope.days[i].date = new Date($scope.now.getTime() + diff * (60 * 60 * 24 * 1000));
        }
    }

    $scope.updateWeek(0);

    $scope.prevWeek = function () {
        offset += 7;
        $scope.updateWeek();
    }

    $scope.nextWeek = function () {
        offset -= 7;
        $scope.updateWeek();
    }


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
    $scope.dateOnly = function (date) {
        var dateStr = date.toISOString();
        return dateStr.split("T")[0];
    }
    $scope.submit = function (day) {
        var Day = $resource("/day");
        Day.save(day);
    }
});
