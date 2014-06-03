var myApp = angular.module('myApp',[]);

myApp.service('dataService', function($http) {
    //delete $http.defaults.headers.common['X-Requested-With'];
    this.getData = function() {
        // $http() returns a $promise that we can add handlers with .then()
        return $http({
            method: 'GET',
            url: 'http://188.226.132.225:8080/mavenproject6-1.0-SNAPSHOT/webresources/com.mycompany.mavenproject6.teacher?',
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }
});

myApp.controller('TodoCtrl', function($scope, dataService) {
    $scope.data = null;
    $scope.queues = [
        {
            id:3,
            name:'физика',
            expanded: false, 
            items: [
                {id: 2,number: 12, fio: 'Miheev Dmitriy', description: 'qwe'},
                {id: 3,number: 1, fio: 'Chistakov', description: 'qwe'},
                {id: 4,number: 13, fio: 'Kola', description: 'qwe'}
            ]
        },
        {
            id:1,
            expanded: false, 
            name:'Математика', 
            items: [
                {id: 19,number: 12, fio: 'Miheev Dmitriy', description: 'qwe'}
            ]
        }
    ];

    $scope.isStudent = true;

    $scope.studentInfo = {id:1, fio: 'Dunicheva', number: 1, description:$scope.description};

    $scope.addStudentToQueues = function() {
        dataService.getData().then(function(dataResponse) {
            $scope.data = dataResponse;
            alert('ad');
        });
        $scope.queues.push({
                id:$scope.selectNewQueue.id,
                expanded: false,
                name:$scope.selectNewQueue.name, 
                items: [
                {
                    id:$scope.studentInfo.id, 
                    fio: $scope.studentInfo.fio, 
                    number: $scope.studentInfo.number, 
                    description:$scope.description}
                ]
        });
        $scope.selectNewQueue = null;
    }; 

    $scope.filterQueues = function() {
        return function( item ) {
            var show = true;
            angular.forEach($scope.queues, function(queue){
                if (queue.id==item.id) show = false; 
            });
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
    $scope.arrayQueues = [
        {id:1,name: 'Математика', teacher:'Борисыч'},
        {id:3,name: 'физика', teacher:'Борисыч'},
        {id:4,name: 'Схемота', teacher:'Борисыч'},
        {id:5,name: 'Английский', teacher:'Борисыч'},
        {id:6,name: 'Метрология', teacher:'Борисыч'}
    ];
    $scope.selectNewQueue = $scope.arrayQueues[2];

    $scope.description = null;
});

$(document).ready(function() {  
    $(".add_queue .icon-plus").on('click', function() {
        $('.add_queue_form').slideToggle();
    });
});









