
    var BASE_URL = "http://188.226.132.225:8080/studentsqueue-1.0-SNAPSHOT/webresources/studentsqueue."
    //if (window.location.hash == "#student") {
        var myApp = angular.module('myApp',[]);

        myApp.controller('TodoCtrl', function($scope, $q, $http) {

            var queues = $http.get(BASE_URL + "queue"),
                students_in_queues = $http.get(BASE_URL + "studentinqueue"),
                users = $http.get(BASE_URL + "quser");
            $q.all([queues, students_in_queues, users]).then(function(arrayOfResults) { 
                $scope.current_teacher = 6; // Sergei Klimenkov 
                $scope.prepareDataForTeacher(arrayOfResults);
                $scope.newQueue = {
                    name: null,
                    desc: null
                };
            });  

            $scope.createNewQueue = function(){

                $scope.queues.push({
                    
                })
            };

            $scope.deleteStudentFromQueue = function(queue){

                for ( var i =0; i < queue.students.length; i++) {
                    if((queue.students[i].student_id == $scope.current_student.id)){
                        queue.students.splice(i, 1);
                        break;
                    }
                } 
            };

            $scope.isMyQueue = function() {
                return function( item ) {                     
                    return item.teacher_id == $scope.current_teacher.id;
                };
            };

            $scope.getNextRank = function(queue) {
                return ++queue.max_rank;
            };  

            $scope.prepareDataForTeacher = function(arrayOfObject) {

                // normalize data from RESTful service (common both for teachers and students)
                var queues = prepareData(arrayOfObject);

                var students_in_queues = arrayOfObject[1].data,
                    users = arrayOfObject[2].data;
                
                // set current teacher param
                for (var i = 0; i < users.length; i++) {
                    if (users[i].id === $scope.current_teacher) {
                        $scope.current_teacher = users[i];
                    } 
                }
                
                $scope.queues =  queues;
            };
            
        })
    //}
    //else if (window.location.hash == "#teacher") {
        // init controller for teacher
    //}

    $(document).ready(function() {  
        $(".add_queue .icon-plus").on('click', function() {
            $('.create_queue_form').slideToggle();
        });
        $("body").on('click', ".expand-queue", function() {

            $(this).parent().parent().find('table').toggle();
        });
    })










