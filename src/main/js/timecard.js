"use strict";

class MainController {

    constructor($resource, $scope) {
        this.$resource = $resource;
        this.$scope = $scope;

        this.now = moment();

        this.currentDay = {};

        this.types = [
            "Billable",
            "Non-billable",
            "Break"
        ];

        this.offset = 0;

        this.updateDay();
    }

    updateDay() {
        let currentDate = angular.copy(this.now).add(this.offset, 'days').format("YYYY-MM-DD");

        this.currentDay = {
            date: currentDate,
            entries: []
        };

        this.$resource('day').get({date: this.currentDay.date}).$promise.then(dayFromServer => {
            this.currentDay.id = dayFromServer.id;
            this.currentDay.entries = dayFromServer.entries || [];
        });
    }


    prev() {
        this.offset -= 1;
        this.updateDay();
    }

    next() {
        this.offset += 1;
        this.updateDay();
    }


    start(day) {
        day.entries.push({start: moment()});
        day.running = true;
    }

    stop(day) {
        day.entries[day.entries.length - 1].stop = moment();
        day.running = false;
    }


    sum(entries) {
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

;

    sum2(day, type) {
        if (!day || !day.entries) {
            return;
        }
        var entriesOfType = day.entries.filter(function (entry) {
            return entry.type === type;
        });
        return this.sum(entriesOfType);
    }

    deleteEntry(day, index) {
        if (!day.running) {
            day.entries.splice(index, 1);
        }
    }

    save(day) {
        var Day = this.$resource("day");
        var dayApi = angular.copy(day);
        Day.save(dayApi).$promise.then((savedDay) => {
            savedDay.date = moment(savedDay.date[0] + "-" + savedDay.date[1] + "-" + savedDay.date[2], "YYYY-M-D").format("YYYY-MM-DD");
            angular.copy(savedDay, day);
            this.$scope.form.$setPristine();
        });
    }

    lock(day) {
        throw "Not implemented!";
    }
}

angular.module("mainApp", ['ngResource']).controller("MainController", MainController);
