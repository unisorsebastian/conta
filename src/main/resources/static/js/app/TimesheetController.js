'use strict';

angular.module('crudApp').controller('TimesheetController',
    ['TimesheetService', '$scope', function (TimesheetService, $scope) {

        var self = this;
        self.timesheet = {};
        self.timesheets = [];

        self.getTimesheet = getTimesheet;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        function getTimesheet() {
            return TimesheetService.getTimesheet();
        }

        // function getBillableDays() {
        //     return TimesheetService.getBillableDays();
        // }

        function reset() {
            self.successMessage = '';
            self.errorMessage = '';
            self.timesheet = {};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);