<!DOCTYPE html>

<html lang="en" ng-app="crudApp">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>
        <div ui-view></div>
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/app/appTimesheet.js"></script>
        <script src="js/app/billableDayService.js"></script>
        <script src="js/app/billableDayController.js"></script>
        <script src="js/app/timesheetService.js"></script>
        <script src="js/app/timesheetController.js"></script>
    </body>
</html>