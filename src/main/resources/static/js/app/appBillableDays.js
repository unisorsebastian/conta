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
                templateUrl: 'partials/list',
                controller: 'BillableDayController',
                controllerAs: 'ctrl',
                resolve: {
                    billableDay: function ($q, BillableDayService) {
                        console.log('Load all billableDays');
                        var deferred = $q.defer();
                        BillableDayService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

