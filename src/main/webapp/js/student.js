
    var BASE_URL = "http://188.226.132.225:8080/studentsqueue-1.0-SNAPSHOT/webresources/studentsqueue."
    //if (window.location.hash == "#student") {
        var myApp = angular.module('myApp',[]);

        myApp.controller('TodoCtrl', function($scope, $q, $http) {
            var queues = $http.get(BASE_URL + "queue"),
                students_in_queues = $http.get(BASE_URL + "studentinqueue"),
                users = $http.get(BASE_URL + "quser");
            $q.all([queues, students_in_queues, users]).then(function(arrayOfResults) { 
                $scope.current_student = 3; // Nikolay Klimov 
                $scope.prepareDataForStudent(arrayOfResults);
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
                    $scope.newQueue = null;
                    $scope.newTeacher = null;
                }).error(function() {
                    console.error('Something goes wrong');
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
                    for ( var i =0; i < queue.students.length; i++) {
                        if((queue.students[i].student_id == $scope.current_student.id)){
                            queue.students.splice(i, 1);
                            break;
                        }
                    } 
                }).error(function() {
                    console.error('Something goes wrong');
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
        $("body").on('click', ".expand-queue", function() {

            $(this).parent().parent().find('table').toggle();
        });
    })









