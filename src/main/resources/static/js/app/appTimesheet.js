var app = angular.module('crudApp', ['ui.router', 'ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080',
    BILLABLE_DAY_API: 'http://localhost:8080/BillableDay/',
    TIMESHEET_API: 'http://localhost:8080/Timesheet/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/timesheet',
                controller: 'TimesheetController',
                controllerAs: 'ctrl',
                resolve: {
                    billableDay: function ($q, TimesheetService) {
                        console.log('Load all timesheet data');
                        var deferred = $q.defer();
                        TimesheetService.getTimesheet("2018-08-01").then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

