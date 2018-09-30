'use strict';

angular.module('crudApp').factory('TimesheetService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                getTimesheet: getTimesheet
            };

            return factory;

            function getTimesheet(date) {
                console.log('Fetching timesheet with date :' + date);
                var deferred = $q.defer();
                $http.get(urls.TIMESHEET_API + "/date/" + date)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Timesheet for date :' + date);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Timesheet for date :' + date);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);