'use strict';

angular.module('crudApp').controller('BillableDayController',
    ['BillableDayService', '$scope', function (BillableDayService, $scope) {

        var self = this;
        self.billableDay = {};
        self.billableDays = [];

        self.submit = submit;
        self.getAllUsers = getAllUsers;
        self.createUser = createUser;
        self.updateUser = updateUser;
        self.removeUser = removeUser;
        self.editUser = editUser;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.billableDay.id === undefined || self.billableDay.id === null) {
                console.log('Saving New User', self.billableDay);
                createUser(self.billableDay);
            } else {
                updateUser(self.billableDay, self.billableDay.id);
                console.log('User updated with id ', self.billableDay.id);
            }
        }

        function createUser(user) {
            console.log('About to create billableDay');
            BillableDayService.createUser(user)
                .then(
                    function (response) {
                        console.log('User created successfully');
                        self.successMessage = 'User created successfully';
                        self.errorMessage = '';
                        self.done = true;
                        self.billableDay = {};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        self.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        self.successMessage = '';
                    }
                );
        }


        function updateUser(user) {
            console.log('About to update billableDay');
            BillableDayService.updateUser(user)
                .then(
                    function (response) {
                        console.log('User updated successfully');
                        self.successMessage = 'User updated successfully';
                        self.errorMessage = '';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while updating User');
                        self.errorMessage = 'Error while updating User ' + errResponse.data;
                        self.successMessage = '';
                    }
                );
        }


        function removeUser(id) {
            console.log('About to remove User with id ' + id);
            BillableDayService.removeUser(id)
                .then(
                    function () {
                        console.log('User ' + id + ' removed successfully');
                    },
                    function (errResponse) {
                        console.error('Error while removing billableDay ' + id + ', Error :' + errResponse.data);
                    }
                );
        }


        function getAllUsers() {
            return BillableDayService.getAllUsers();
        }

        function editUser(id) {
            self.successMessage = '';
            self.errorMessage = '';
            BillableDayService.getUser(id).then(
                function (billableDay) {
                    self.billableDay = billableDay;
                },
                function (errResponse) {
                    console.error('Error while removing billableDay ' + id + ', Error :' + errResponse.data);
                }
            );
        }

        function reset() {
            self.successMessage = '';
            self.errorMessage = '';
            self.billableDay = {};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);