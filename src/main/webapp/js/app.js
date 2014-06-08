
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
                $scope.prepareData(arrayOfResults);
                $scope.newQueue = null;
                $scope.newTeacher = null;
            });            

            $scope.addStudentToQueue = function() {
                //$scope.current_student.rank = $scope.getNextRank($scope.newQueue);
                //$scope.reclculate_students_ranks($scope.newQueue);
                $scope.newQueue.students.push({
                    rank: $scope.getNextRank($scope.newQueue),
                    student_id: $scope.current_student.student.userId,
                    studentName: $scope.current_student.realName,
                    groupName: $scope.current_student.student.groupName,
                });
                $scope.newQueue = null;
                $scope.newTeacher = null;
                /*
                $.ajax({
                    method: 'POST',
                    url: BASE_URL + '.studentinqueue',
                    data: JSON.stringify({
                        id: 'autoId',
                        rank: $scope.getNexRank($scope.newQueue),
                        studentId: {
                            userId: $scope.current_student.studentId.userId
                        },
                        queueId: {
                            id: $scope.newQueue.id
                        },
                        description: "fdsfds"
                    }),
                    success: function(data) {

                    }
                });
                */
            }; 

            $scope.deleteStudentFromQueue = function(queue){

                for ( var i =0; i < queue.students.length; i++) {
                    if((queue.students[i].student_id == $scope.current_student.id)){
                        queue.students.splice(i, 1);
                        break;
                    }
                } 
            };

            $scope.isCurrentQueue = function() {
                return function( item ) {
                    var show = true;
                    for (var i = 0; i < item.students.length; i++) {
                        if (item.students[i].student_id == $scope.current_student.id) {
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
                    if ($scope.newTeacher) {
                        if (item.teacherId.userId != $scope.newTeacher.id) {
                            show = false;
                        }
                    }                    
                    for (var i = 0; i < item.students.length; i++) {
                        if (item.students[i].student_id == $scope.current_student) {
                            show = false; 
                            break;
                        }
                    } 
                    return show;
                };
            };

            $scope.getNextRank = function(queue) {
                return ++queue.max_rank;
            };  

            $scope.reclculate_students_ranks = function(queue) {
                for ( var i = 0; i < queue.students.length; i++) {
                    if((queue.students[i].student_id == $scope.current_student)){
                        queue.students.splice(i, 1);
                        break;
                    }
                } 
            }           

            // normalize data from RESTful service 
            $scope.prepareData = function(arrayOfObject) {
                var queues = arrayOfObject[0].data,
                    students_in_queues = arrayOfObject[1].data,
                    users = arrayOfObject[2].data,
                    teachers = [],
                    current_teacher, current_queue;

                
                for (var i = 0; i < users.length; i++) {
                    // set students's names
                    for (var j = 0; j < students_in_queues.length; j++) {
                        if (students_in_queues[j].studentId.userId === users[i].id) {
                            students_in_queues[j].studentName = users[i].realName;
                            students_in_queues[j].groupName = users[i].student.groupName;
                            students_in_queues[j].student_id = users[i].id;
                            break;
                        }
                        // set current student param
                        if (users[i].id === $scope.current_student) {
                            $scope.current_student = users[i];
                        }
                        
                    }
                    // set teacher's name
                    for (var j = 0; j < queues.length; j++) {
                        if (queues[j].teacherId.userId === users[i].id) {
                            queues[j].teacherName = users[i].realName;
                            break;
                        }
                    }
                    // set teachers array
                    if (users[i].teacher !== null) {
                        teachers.push(users[i]);
                        $scope.newTeacher = users[i];
                    }
                }
                console.log(students_in_queues);
                // set queue's students
                for (var i = 0; i < queues.length; i++) {
                    queues[i].students = [];
                    var max_rank = 0;
                    for (var j = 0; j < students_in_queues.length; j++) {
                        if (students_in_queues[j].queueId.id === queues[i].id) {
                            if (students_in_queues[j].rank > max_rank) {
                                max_rank = students_in_queues[j].rank;
                            }
                            queues[i].students.push(students_in_queues[j]);
                        }
                    }
                    queues[i].max_rank = max_rank;
                    // sort students in queue
                    // convert DB rank to current applicetion rank (e.g 4, 5, 8, 9 -> 1, 2, 3, 4)
                    queues[i].students.sort(function(a, b) {
                        return a.rank - b.rank;
                    });
                }
                $scope.queues =  queues;
                $scope.teachers = teachers;
                console.log(queues);
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
        $("body").on('click', ".expand-queue", function() {

            $(this).parent().parent().find('table').toggle();
        });
    })










