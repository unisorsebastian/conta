<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Timesheet </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <input ng-model="ctrl.timesheet.date"/>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="salary">Rate</label>
                            <div class="col-md-7">
                            <#--<input type="text" ng-model="ctrl.billableDay.rate" id="rate" class="form-control input-sm" placeholder="Enter your Salary." required ng-pattern="ctrl.onlyNumbers"/>-->
                                <input type="text" ng-model="ctrl.billableDay.rate" id="rate"
                                       class="form-control input-sm" placeholder="Enter rate"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="salary">Desc</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.billableDay.description" id="description"
                                       class="form-control input-sm" placeholder="Description"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit" value="{{!ctrl.user.id ? 'Add' : 'Update'}}"
                                   class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm"
                                    ng-disabled="myForm.$pristine">Reset Form
                            </button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Billable Days </span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Hours</th>
                        <th>Rate</th>
                        <th>Desc</th>
                        <th>Version</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#--<tr ng-repeat="u in ctrl.getTimesheet('2018-08-01').getBillableDays()">-->
                        <#--<td>{{u.id}}</td>-->
                        <#--<td>{{u.date}}</td>-->
                        <#--<td>{{u.hours}}</td>-->
                        <#--<td>{{u.rate}}</td>-->
                        <#--<td>{{u.description}}</td>-->
                        <#--<td>{{u.version}}</td>-->
                        <#--<td>-->
                            <#--<button type="button" ng-click="ctrl.editUser(u.id)" class="btn btn-success custom-width">-->
                                <#--Edit-->
                            <#--</button>-->
                        <#--</td>-->
                        <#--<td>-->
                            <#--<button type="button" ng-click="ctrl.removeUser(u.id)" class="btn btn-danger custom-width">-->
                                <#--Remove-->
                            <#--</button>-->
                        <#--</td>-->
                    <#--</tr>-->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>