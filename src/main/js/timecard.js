angular.module("mainApp", ['ngResource']).controller("MainController", function ($resource) {
    var vm = this;

    vm.now = new Date();
    vm.days = [
        {name: "Monday", entries: []},
        {name: "Tuesday", entries: []},
        {name: "Wednesday", entries: []},
        {name: "Thursday", entries: []},
        {name: "Friday", entries: []},
        {name: "Saturday", entries: []},
        {name: "Sunday", entries: []}
    ];
    vm.types = [
        "Billable",
        "Non-billable",
        "Break"
    ];
    var offset = 0;

    vm.dayIndex = function () {
        var day = (vm.now.getDay() + 6) % 7;
        return day;
    };

    vm.updateWeek = function () {
        for (var i = 0; i < 7; i++) {
            var currentDay = vm.dayIndex();
            var diff = i - currentDay - offset;
            vm.days[i].date = new Date(vm.now.getTime() + diff * (60 * 60 * 24 * 1000));
        }
    }

    vm.updateWeek(0);

    vm.prevWeek = function () {
        offset += 7;
        vm.updateWeek();
    }

    vm.nextWeek = function () {
        offset -= 7;
        vm.updateWeek();
    }


    vm.newEntry = function (day, type) {
        day.entries.push({start: "9:00", stop: "17:00", type: type});
    };
    vm.sum = function (entries) {
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
    };

    vm.sum2 = function (day, type) {
        if (!day) {
             return;
        }
        var entriesOfType = day.entries.filter(function (entry) {
            return entry.type === type;
        });
        return vm.sum(entriesOfType);
    }

    vm.deleteEntry = function (day, index) {
        day.entries.splice(index, 1);
    }
    vm.dateOnly = function (date) {
        var dateStr = date.toISOString();
        return dateStr.split("T")[0];
    }
    vm.save = function (day) {
        var Day = $resource("/day");
        var dayApi = angular.copy(day);
        dayApi.date = vm.dateOnly(dayApi.date);
        Day.save(dayApi);
    }
    vm.lock = function (day) {
        throw "Not implemented!";
    }
});
