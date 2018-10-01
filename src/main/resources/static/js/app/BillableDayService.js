'use strict';

angular.module('crudApp').factory('BillableDayService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllUsers: loadAllUsers,
                getAllUsers: getAllUsers,
                getUser: getUser,
                createUser: createUser,
                updateUser: updateUser,
                removeUser: removeUser
            };

            return factory;

            function loadAllUsers() {
                console.log('Fetching all billableDays');
                var deferred = $q.defer();
                $http.get(urls.BILLABLE_DAY_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all billableDays');
                            $localStorage.billableDay = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading billableDays');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllUsers() {
                return $localStorage.billableDay;
            }

            function getUser(id) {
                console.log('Fetching User with id :' + id);
                var deferred = $q.defer();
                $http.get(urls.BILLABLE_DAY_API + "/id/" + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully User with id :' + id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading billableDay with id :' + id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createUser(billableDay) {
                console.log('Creating User');
                var deferred = $q.defer();
                $http.post(urls.BILLABLE_DAY_API, billableDay)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while creating User : ' + errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateUser(billableDay) {
                console.log('Updating User with id ' + billableDay.id);
                var deferred = $q.defer();
                $http.put(urls.BILLABLE_DAY_API + billableDay.id, billableDay)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating User with id :' + id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeUser(id) {
                console.log('Removing User with id ' + id);
                var deferred = $q.defer();
                $http.delete(urls.BILLABLE_DAY_API + id)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing User with id :' + id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);