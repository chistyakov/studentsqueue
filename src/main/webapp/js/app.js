
    var BASE_URL = "http://188.226.132.225:8080/studentsqueue-1.0-SNAPSHOT/webresources/studentsqueue."
    //if (window.location.hash == "#student") {
        var myApp = angular.module('myApp',[]);

        myApp.service('dataService', function($http) {
            //delete $http.defaults.headers.common['X-Requested-With'];
            this.getData = function() {
                // $http() returns a $promise that we can add handlers with .then()
                return $http({
                    method: 'GET',
                    url: 'http://188.226.132.225:8080/mavenproject6-1.0-SNAPSHOT/webresources/studentsqueue.studentinqueue',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
            }
        });

        myApp.controller('TodoCtrl', function($scope, $q, $http, dataService) {
            var queues = $http.get(BASE_URL + "queue"),
                students_in_queues = $http.get(BASE_URL + "studentinqueue"),
                users = $http.get(BASE_URL + "quser");
            $q.all([queues, students_in_queues, users]).then(function(arrayOfResults) { 
                $scope.current_student = 3; // Nikolay Klimov 
                $scope.queues = $scope.prepareData(arrayOfResults);
            });            

            $scope.addStudentToQueues = function() {
                $scope.newQueue.students.push($scope.current_student);
                $scope.newQueue = null;
            }; 

            $scope.isCurrentQueue = function() {
                return function( item ) {
                    var show = true;
                    for (var i = 0; i < item.students.length; i++) {
                        if (item.students[i].studentId.userId == $scope.current_student.studentId.userId) {
                            show = false; 
                            break;
                        }
                    } 
                    return !show;
                };
            };

            $scope.isNewQueue = function() {
                return function( item ) {
                    var show = true;
                    for (var i = 0; i < item.students.length; i++) {
                        if (item.students[i].studentId.userId == $scope.current_student.studentId.userId) {
                            show = false; 
                            break;
                        }
                    } 
                    return show;
                };
            };

            $scope.deleteStudentToQueues = function(id){
                var arrayNew = [];

                angular.forEach($scope.queues, function(queue){
                    if(!(queue.id==id)){
                        arrayNew.push(queue);
                    }
                });
                $scope.queues = arrayNew;
            };

            $scope.expand = function (id){
                angular.forEach($scope.queues, function(queue){
                    if((queue.id==id)){
                        queue.expanded=!queue.expanded;
                    }
                });
            };

            // normalize data from RESTful service 
            $scope.prepareData = function(arrayOfObject) {
                var queues = arrayOfObject[0].data,
                    students_in_queues = arrayOfObject[1].data,
                    users = arrayOfObject[2].data,
                    current_queue;
                
                for (var i = 0; i < users.length; i++) {
                    // set students's names
                    for (var j = 0; j < students_in_queues.length; j++) {
                        if (students_in_queues[j].studentId.userId === users[i].id) {
                            students_in_queues[j].studentName = users[i].realName;
                            break;
                        }
                        // set current student param
                        if (students_in_queues[j].studentId.userId === $scope.current_student) {
                            $scope.current_student = students_in_queues[j];
                        }
                    }
                    // set teacher's name
                    for (var j = 0; j < queues.length; j++) {
                        if (queues[j].teacherId.userId === users[i].id) {
                            queues[j].teacherName = users[i].realName;
                            break;
                        }
                    }
                    
                }
                // set queue's students
                for (var i = 0; i < queues.length; i++) {
                    queues[i].expanded = false;
                    queues[i].students = [];
                    for (var j = 0; j < students_in_queues.length; j++) {
                        if (students_in_queues[j].queueId.id === queues[i].id) {
                            queues[i].students.push(students_in_queues[j]);
                        }
                    }
                }
                console.log(queues);
                return queues;
            };
            
        })
    //}
    //else if (window.location.hash == "#teacher") {
        // init controller for teacher
    //}

    $(document).ready(function() {  
        $(".add_queue .icon-plus").on('click', function() {
            $('.add_queue_form').slideToggle();
        });
    })










