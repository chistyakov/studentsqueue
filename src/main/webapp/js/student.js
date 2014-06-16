
    var BASE_OF_BASE_URL = "http://188.226.132.225:8080/studentsqueue-1.0-SNAPSHOT/";
    var BASE_URL = BASE_OF_BASE_URL + "webresources/studentsqueue.";

    //if (window.location.hash == "#student") {
        var myApp = angular.module('myApp',[]);

        myApp.controller('TodoCtrl', function($scope, $q, $http) {
            var queues = $http.get(BASE_URL + "queue"),
                students_in_queues = $http.get(BASE_URL + "studentinqueue"),
                users = $http.get(BASE_URL + "quser"),
                current_user = $http.get(BASE_URL + "auth/currentuser");
            console.time("Retrieve data from server");
            $q.all([queues, students_in_queues, users, current_user]).then(function(arrayOfResults) { 
                current_user = arrayOfResults[3].data;
                if (current_user.student === null)
                {
                    window.location= BASE_OF_BASE_URL + "teacher.jsp";
                }
                $scope.current_student = current_user.id;
                console.timeEnd("Retrieve data from server");
                console.time("Conver JSON processing");
                $scope.prepareDataForStudent(arrayOfResults);
                console.timeEnd("Conver JSON processing");
                $scope.newQueue = null;
                $scope.newTeacher = null;
            });            

            $scope.addStudentToQueue = function() {
                //$scope.current_student.rank = $scope.getNextRank($scope.newQueue);
                //$scope.recalculate_students_ranks($scope.newQueue);
                $http.put(
                    BASE_URL + 'studentinqueue',
                    JSON.stringify({
                        studentId: {
                            userId: $scope.current_student.id
                        },
                        queueId: {
                            id: $scope.newQueue.id
                        }
                    }),
                    {'Content-Type': 'application/json'}
                ).success(function() {
                    $scope.newQueue.students.push({
                        rank: $scope.getNextRank($scope.newQueue),
                        student_id: $scope.current_student.student.userId,
                        studentName: $scope.current_student.realName,
                        groupName: $scope.current_student.student.groupName,
                    });
                    $.pnotify({
                        type: 'success',
                        text: "Successfully joined the " + $scope.newQueue.name,
                        delay: 5000
                    });
                    $scope.newQueue = null;
                    $scope.newTeacher = null;
                }).error(function() {
                    console.error('Something goes wrong');
                    $.pnotify({
                        type: 'error',
                        text: "Failed to join the queue",
                        delay: 5000
                    });
                });
                /*
                $.ajax({
                    method: 'PUT',
                    url: BASE_URL + 'studentinqueue',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        studentId: {
                            userId: $scope.current_student.id
                        },
                        queueId: {
                            id: $scope.newQueue.id
                        }
                    }),
                    success: function(data) {
                        $scope.newQueue.students.push({
                            rank: $scope.getNextRank($scope.newQueue),
                            student_id: $scope.current_student.student.userId,
                            studentName: $scope.current_student.realName,
                            groupName: $scope.current_student.student.groupName,
                        });
                        $scope.newQueue = null;
                        $scope.newTeacher = null;
                    },
                    error: function() {
                        alert('Failed to set in queue');
                    }
                });
*/
            }; 

            $scope.deleteStudentFromQueue = function(queue){
                // get studentsinqueue ID to delete
                var id;
                debugger
                for ( var i =0; i < queue.students.length; i++) {
                    if((queue.students[i].student_id == $scope.current_student.id)){
                        id = queue.students[i].rank;
                        break;
                    }
                } 

                $http.delete(
                    BASE_URL + 'studentinqueue/' + id,
                    {'Content-Type': 'application/json'}
                ).success(function() {
                    $.pnotify({
                        type: 'success',
                        text: "Successfully exit from the " + queue.name,
                        delay: 5000
                    });
                    for ( var i =0; i < queue.students.length; i++) {
                        if((queue.students[i].student_id == $scope.current_student.id)){
                            queue.students.splice(i, 1);
                            break;
                        }
                    } 
                }).error(function() {
                    console.error('Something goes wrong');
                    $.pnotify({
                        type: 'error',
                        text: "Error while exit from the " + queue.name,
                        delay: 5000
                    });
                });
            };

            $scope.isCurrentQueue = function() {
                return function( item ) {
                    var show = false;
                    for (var i = 0; i < item.students.length; i++) {
                        if (item.students[i].student_id == $scope.current_student.id) {
                            show = true; 
                        }
                    } 
                    return show;
                };
            };

            $scope.isAnyActiveQueue = function() {
                if ($scope.queues) {
                    for (var i = 0; i < $scope.queues.length; i++) {
                        for (var j = 0; j<$scope.queues[i].students.length; j++) {
                            if ($scope.queues[i].students[j].student_id  == $scope.current_student.id) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
                return false;
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
                        if (item.students[i].student_id == $scope.current_student.id) {
                            show = false; 
                        }
                    } 
                    return show;
                };
            };

            $scope.hasQueues = function() {
                return function( item ) {  
                    var show = false;                 
                    for (var i = 0; i < $scope.queues.length; i++) {
                        if ($scope.queues[i].teacher_id == item.id) {
                            show = true; 
                        }
                        
                    } 
                    return show;
                };
            };

            $scope.getNextRank = function(queue) {
                return ++queue.max_rank;
            };  

            $scope.recalculate_students_ranks = function(queue) {
                for ( var i = 0; i < queue.students.length; i++) {
                    if((queue.students[i].student_id == $scope.current_student)){
                        queue.students.splice(i, 1);
                        break;
                    }
                } 
            }   

            $scope.prepareDataForStudent = function(arrayOfObject) {

                // normalize data from RESTful service (common both for teachers and students)
                var queues = prepareData(arrayOfObject);

                var students_in_queues = arrayOfObject[1].data,
                    users = arrayOfObject[2].data,
                    teachers = [];
                
                for (var i = 0; i < users.length; i++) {   
                    // set current student param                    
                    if (users[i].id === $scope.current_student) {
                        $scope.current_student = users[i];
                    }    
                    // set teachers array
                    if (users[i].teacher !== null) {
                        teachers.push(users[i]);
                        $scope.newTeacher = users[i];
                    }
                }
                
                $scope.queues =  queues;
                $scope.teachers = teachers;
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
        $("body").on('click', ".well", function() {

            $(this).toggleClass('expanded');
        });
        $('.logout').attr('href', BASE_URL + 'auth/logout');
    })










