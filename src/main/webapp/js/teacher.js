
    var BASE_OF_BASE_URL = "http://188.226.132.225:8080/newapi/";
    var BASE_URL = BASE_OF_BASE_URL + "webresources/studentsqueue.";
    //if (window.location.hash == "#student") {
        var myApp = angular.module('myApp',[]);

        myApp.controller('TodoCtrl', function($scope, $q, $http) {

            $http.get(BASE_URL + "auth/currentuser").success(function(data) {
                current_user = data; 
                var queues = $http.get(BASE_URL + "teacher/" + current_user.id + "/queues");
                    users = $http.get(BASE_URL + "user");
                    //current_user = $http.get(BASE_URL + "auth/currentuser");
                $q.all([queues, users]).then(function(arrayOfResults) { 
                    //current_user = arrayOfResults[3].data;
                    $scope.current_teacher = current_user;
                    $scope.prepareDataForTeacher(arrayOfResults);
                    $scope.newQueue = {
                        name: null,
                        desc: null
                    };
                });  
            }).error(function() {
                $.pnotify({
                    type: 'error',
                    text: "Failed to load current user id ",
                    delay: 5000
                });
            });
            

            $scope.createNewQueue = function(){
                var queue = {
                    name: $scope.newQueue.name,
                    teacher: {
                        id: $scope.current_teacher.id
                    }                    
                };
                $http.post(
                    BASE_URL + 'queue',
                    JSON.stringify(queue),
                    {'Content-Type': 'application/json'}
                ).success(function(data) {
                    $.pnotify({
                        type: 'success',
                        text: "Successfully created " + data.name,
                        delay: 5000
                    });
                    $scope.queues.push(data);
                }).error(function() {
                    $.pnotify({
                        type: 'error',
                        text: "Error while creating " + $scope.newQueue.name,
                        delay: 5000
                    });
                    console.error('Something goes wrong');
                });
            };

            $scope.removeQueue = function(queue){
                $http.delete(
                    BASE_URL + 'queue/' + queue.id,
                    {'Content-Type': 'application/json'}
                ).success(function() {                 
                    $.pnotify({
                        type: 'success',
                        text: "Successfully deleted " + queue.name,
                        delay: 5000
                    });
                    for ( var i =0; i < $scope.queues.length; i++) {
                        if(($scope.queues[i].id == queue.id)){
                            $scope.queues.splice(i, 1);
                            break;
                        }
                    } 
                }).error(function() {
                    $.pnotify({
                        type: 'error',
                        text: "Error while deleting " + queue.name,
                        delay: 5000
                    });
                    console.error('Something goes wrong');
                }); 
            };

            $scope.deleteStudentFromQueue = function(queue, student){
                debugger
                $http.delete(
                    BASE_URL + 'studentinqueue/queue/' + queue.id + '/student/' + student.id,
                    {'Content-Type': 'application/json'}
                ).success(function() {                    
                    $.pnotify({
                        type: 'success',
                        text: "Successfully rejected student " + student.realName + " from the " + queue.name,
                        delay: 5000
                    });
                    for ( var i =0; i < $scope.queues.length; i++) {
                        if(($scope.queues[i].id == queue.id)){
                            for (var j = 0; j < $scope.queues[i].studentInQueueList.length; j++) {
                                if ($scope.queues[i].studentInQueueList[j].student.id == student.id) {
                                    $scope.queues[i].studentInQueueList.splice(j, 1);
                                    break;
                                }
                            }
                        }
                    } 
                }).error(function() {
                    $.pnotify({
                        type: 'error',
                        text: "Error while rejecting student " + student.studentName + " from the " + student.queueId.name,
                        delay: 5000
                    });
                    console.error('Something goes wrong');
                }); 
            };

            $scope.toggleReception = function(queue) {
                var isInProcess = queue.inProcess == 'Y';
                if (isInProcess) {
                    var prefix = 'pauseprocessqueue/';
                    var text = ' stopping ';
                }
                else {
                    var prefix = 'startprocessqueue/';
                    var text = ' starting ';
                }
                 $http.put(
                    BASE_URL + 'teacher/' + prefix + queue.id
                ).success(function(data) {                  
                    $.pnotify({
                        type: 'success',
                        text: "Successfully" + text + "queue " + queue.name,
                        delay: 5000
                    });
                    queue.inProcess = data.inProcess;
                }).error(function() {
                    $.pnotify({
                        type: 'error',
                        text: "Error while" + text + "queue " + queue.name,
                        delay: 5000
                    });
                    console.error('Something goes wrong');
                }); 
                
            };

            $scope.isMyQueue = function() {
                return function( item ) {                   
                    return item.teacher.id == $scope.current_teacher.id;
                };
            };

            $scope.getNextRank = function(queue) {
                return ++queue.max_rank;
            };  

            $scope.prepareDataForTeacher = function(arrayOfResults) {
                var users = arrayOfResults[1].data;
                /*
                // set current teacher param
                for (var i = 0; i < users.length; i++) {
                    if (users[i].id === $scope.current_teacher) {
                        $scope.current_teacher = users[i];
                    } 
                }
                */ 
                var queues = arrayOfResults[0].data;
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
        $('.logout').attr('href', BASE_URL + 'auth/logout');
    })










